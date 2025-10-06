package vn.iotstar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.ReviewService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.utils.CSRFUtil;
import vn.iotstar.service.IReviewService;
import vn.iotstar.entity.*;

@WebServlet(urlPatterns = { "/user/search" })
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// Security constants
    private static final int MAX_KEYWORD_LENGTH = 200;
    private static final int MAX_RESULTS = 1000;
    private static final List<String> ALLOWED_SORT_OPTIONS = Arrays.asList(
        "highest-rated", "most-popular", "newest", "price-low-high", "price-high-low"
    );
    
    
	private ICourseService courseService = new CourseService();
	private IReviewService reviewService = new ReviewService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/homepage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (!CSRFUtil.validateToken(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF Token không hợp lệ hoặc bị thiếu");
            return;
        }

		// ===== VALIDATE & SANITIZE KEYWORD =====
        String keyWord = req.getParameter("keyword");
        ValidationResult keywordValidation = validateKeyword(keyWord);
        
        if (!keywordValidation.isValid()) {
            req.setAttribute("error", keywordValidation.getErrorMessage());
            req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
            return;
        }
        
        // Sanitize keyword
        keyWord = sanitizeKeyword(keyWord);
        
        // ===== VALIDATE & SANITIZE SORTBY =====
        String sortBy = req.getParameter("sortBy");
        if (sortBy != null && !sortBy.isEmpty()) {
            ValidationResult sortValidation = validateSortBy(sortBy);
            if (!sortValidation.isValid()) {
                // Default to most-popular if invalid
                sortBy = "most-popular";
            }
        } else {
            sortBy = "most-popular"; // Default sort option
        }
		
		
		HttpSession session = req.getSession();
		session.setAttribute("keyWord", keyWord);
		session.setAttribute("sortBy", sortBy);
		
		// ===== BUSINESS LOGIC =====
        try {
            List<Review> reviews = reviewService.getAllReviews();
            List<Course> courses = courseService.getAllCourses();
            List<Course> courseList = new ArrayList<>();
            int matchCount = 0;

            // Search and filter courses
            if (keyWord == null || keyWord.trim().isEmpty()) {
                // Return all active courses if no keyword
                for (Course c : courses) {
                    if (c.getStatus() == 1 && matchCount < MAX_RESULTS) {
                        courseList.add(c);
                        matchCount++;
                    }
                }
            } else {
                // Search by keyword
                String searchTerm = keyWord.toLowerCase().trim();
                
                for (Course c : courses) {
                    if (matchCount >= MAX_RESULTS) {
                        break; // Prevent excessive results
                    }
                    
                    if (c.getCourseName() != null && 
                        c.getStatus() == 1 &&
                        c.getCourseName().toLowerCase().contains(searchTerm)) {
                        courseList.add(c);
                        matchCount++;
                    }
                }
            }
            
            // Apply sorting
            courseList = applySorting(courseList, reviews, sortBy);

            // Generate new CSRF token
            String newCsrfToken = CSRFUtil.generateToken(req);
            req.setAttribute("csrfToken", newCsrfToken);

            // Set attributes with escaped values for XSS prevention
            req.setAttribute("CourseList", courseList);
            req.setAttribute("Review", reviews);
            req.setAttribute("searchAmount", matchCount);
            req.setAttribute("keyWord", escapeHtml(keyWord));
            req.setAttribute("sortBy", sortBy);

            req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
            
        } catch (Exception e) {
            System.err.println("Error in search: " + e.getMessage());
            req.setAttribute("error", "Có lỗi xảy ra khi tìm kiếm. Vui lòng thử lại.");
            req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
        }
	}
	
	/**
     * Validate keyword parameter to prevent Buffer Overflow
     */
    private ValidationResult validateKeyword(String keyword) {
        // Allow null or empty (will show all courses)
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ValidationResult(true, null);
        }
        
        // Check length to prevent Buffer Overflow
        if (keyword.length() > MAX_KEYWORD_LENGTH) {
            return new ValidationResult(false, 
                "Từ khóa tìm kiếm quá dài. Tối đa " + MAX_KEYWORD_LENGTH + " ký tự.");
        }
        
        // Check for null bytes (potential attack)
        if (keyword.contains("\0")) {
            return new ValidationResult(false, 
                "Từ khóa chứa ký tự không hợp lệ.");
        }
        
        // Check for control characters
        if (keyword.matches(".*[\\x00-\\x1F\\x7F].*")) {
            return new ValidationResult(false, 
                "Từ khóa chứa ký tự điều khiển không hợp lệ.");
        }
        
        return new ValidationResult(true, null);
    }
    
    /**
     * Sanitize keyword to prevent injection attacks
     */
    private String sanitizeKeyword(String keyword) {
        if (keyword == null) {
            return "";
        }
        
        // Trim whitespace
        keyword = keyword.trim();
        
        // Remove null bytes
        keyword = keyword.replace("\0", "");
        
        // Remove control characters
        keyword = keyword.replaceAll("[\\x00-\\x1F\\x7F]", "");
        
        // Remove multiple spaces
        keyword = keyword.replaceAll("\\s+", " ");
        
        // Remove leading/trailing special characters that might cause issues
        keyword = keyword.replaceAll("^[^a-zA-Z0-9\\u00C0-\\u1EF9]+", "");
        keyword = keyword.replaceAll("[^a-zA-Z0-9\\u00C0-\\u1EF9]+$", "");
        
        return keyword;
    }
    
    /**
     * Validate sortBy parameter (whitelist approach)
     */
    private ValidationResult validateSortBy(String sortBy) {
        if (sortBy == null || sortBy.trim().isEmpty()) {
            return new ValidationResult(true, null);
        }
        
        // Check length
        if (sortBy.length() > 50) {
            return new ValidationResult(false, 
                "Tham số sắp xếp không hợp lệ.");
        }
        
        // Whitelist validation - only allow predefined values
        if (!ALLOWED_SORT_OPTIONS.contains(sortBy.toLowerCase().trim())) {
            return new ValidationResult(false, 
                "Tùy chọn sắp xếp không hợp lệ.");
        }
        
        return new ValidationResult(true, null);
    }
    
    /**
     * Apply sorting to course list
     */
    private List<Course> applySorting(List<Course> courses, List<Review> reviews, String sortBy) {
        if (courses == null || courses.isEmpty()) {
            return courses;
        }
        
        switch (sortBy.toLowerCase()) {
            case "highest-rated":
                courses.sort((c1, c2) -> {
                    double avg1 = calculateAverageRating(c1, reviews);
                    double avg2 = calculateAverageRating(c2, reviews);
                    return Double.compare(avg2, avg1); // Descending
                });
                break;
                
            case "most-popular":
                courses.sort((c1, c2) -> {
                    int count1 = getEnrollmentCount(c1);
                    int count2 = getEnrollmentCount(c2);
                    return Integer.compare(count2, count1); // Descending
                });
                break;
                
                
            default:
                // Default: most-popular
                break;
        }
        
        return courses;
    }
    
    /**
     * Calculate average rating for a course
     */
    private double calculateAverageRating(Course course, List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0;
        int count = 0;
        
        for (Review r : reviews) {
            if (r.getCourse() != null && r.getCourse().getId() == course.getId()) {
                sum += r.getRate();
                count++;
            }
        }
        
        return count > 0 ? sum / count : 0.0;
    }
    
    /**
     * Get enrollment count (placeholder - implement based on your data model)
     */
    private int getEnrollmentCount(Course course) {
        // TODO: Implement based on your OrderItem or Enrollment entity
        return 0;
    }
    
    /**
     * Escape HTML to prevent XSS attacks
     */
    private String escapeHtml(String input) {
        if (input == null) {
            return "";
        }
        
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;")
                    .replace("/", "&#x2F;");
    }
    
    /**
     * Inner class for validation result
     */
    private static class ValidationResult {
        private final boolean valid;
        private final String errorMessage;
        
        public ValidationResult(boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }
        
        public boolean isValid() {
            return valid;
        }
        
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}