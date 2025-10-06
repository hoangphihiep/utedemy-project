package vn.iotstar.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import vn.iotstar.service.IReviewService;
import vn.iotstar.utils.CSRFUtil;
import vn.iotstar.entity.*;

@WebServlet(urlPatterns = { "/user/filter" })
public class FilterController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int MAX_KEYWORD_LENGTH = 100;
    private static final int MAX_RATING_LENGTH = 10;
    private static final int MAX_REQUEST_SIZE = 10000; // 10KB
    
    private ICourseService courseService = new CourseService();
    private IReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        // === STEP 1: Validate Request Size ===
        int contentLength = req.getContentLength();
        if (contentLength > MAX_REQUEST_SIZE) {
            System.err.println("Request size too large: " + contentLength);
            resp.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, 
                "Request body quá lớn");
            return;
        }
        
        // === STEP 2: Validate CSRF Token ===
        if (!CSRFUtil.validateToken(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, 
                "CSRF Token không hợp lệ hoặc bị thiếu");
            return;
        }

        // === STEP 3: Validate and Process Keyword ===
        String keyWord = validateAndProcessKeyword(req, resp);
        if (keyWord == null) {
            return; // Error already sent in validation method
        }
        
        // === STEP 4: Validate and Process Rating ===
        Double ratingStr = validateAndProcessRating(req, resp);
        if (ratingStr == null) {
            return; // Error already sent in validation method
        }
        
        // === STEP 5: Process Business Logic ===
        try {
            List<Course> courses = courseService.getAllCourses();
            List<Review> reviews = reviewService.getAllReviews();
            List<Course> courseList = filterCourses(courses, reviews, keyWord, ratingStr);
            
            // === STEP 6: Prepare Response ===
            HttpSession session = req.getSession();
            session.setAttribute("keyWord", keyWord);
            
            String newCsrfToken = CSRFUtil.generateToken(req);
            req.setAttribute("csrfToken", newCsrfToken);
            req.setAttribute("CourseList", courseList);
            req.setAttribute("Review", reviews);
            req.setAttribute("searchAmount", courseList.size());
            req.setAttribute("keyWord", escapeHtml(keyWord));
            
            req.getRequestDispatcher("/views/user/filtercourse.jsp").forward(req, resp);
            
        } catch (Exception e) {
            System.err.println("Error processing filter request: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "Có lỗi xảy ra khi xử lý yêu cầu");
        }
    }
    
    /**
     * Validate and process keyword parameter
     * Returns null if validation fails (error already sent to client)
     */
    private String validateAndProcessKeyword(HttpServletRequest req, 
            HttpServletResponse resp) throws IOException {
        
        String keyWord = req.getParameter("keyword");
        
        // Check if keyword is provided
        if (keyWord == null || keyWord.trim().isEmpty()) {
            // Try to get from session as fallback
            HttpSession session = req.getSession(false);
            if (session != null) {
                keyWord = (String) session.getAttribute("keyWord");
            }
            if (keyWord == null) {
                keyWord = "";
            }
        }
        
        // Validate length BEFORE any processing
        if (keyWord.length() > MAX_KEYWORD_LENGTH) {
            System.err.println("Keyword too long: " + keyWord.length() + " characters");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                "Từ khóa tìm kiếm quá dài (tối đa " + MAX_KEYWORD_LENGTH + " ký tự)");
            return null;
        }
        
        // Sanitize and trim
        keyWord = sanitizeInput(keyWord);
        keyWord = keyWord.trim();
        
        return keyWord;
    }
    
    /**
     * Validate and process rating parameter
     * Returns null if validation fails (error already sent to client)
     */
    private Double validateAndProcessRating(HttpServletRequest req, 
            HttpServletResponse resp) throws IOException {
        
        String ratingParam = req.getParameter("rating");
        
        // If no rating provided, return default
        if (ratingParam == null || ratingParam.isEmpty()) {
            return 0.0;
        }
        
        // Validate length BEFORE parsing
        if (ratingParam.length() > MAX_RATING_LENGTH) {
            System.err.println("Rating parameter too long: " + ratingParam.length());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                "Rating parameter không hợp lệ");
            return null;
        }
        
        // Parse and validate value
        try {
            double rating = Double.parseDouble(ratingParam);
            
            // Validate rating range
            if (rating < 0 || rating > 5) {
                System.err.println("Rating out of range: " + rating);
                return 0.0; // Return default instead of error
            }
            
            return rating;
            
        } catch (NumberFormatException e) {
            System.err.println("Invalid rating format: " + ratingParam);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                "Rating phải là số");
            return null;
        }
    }
    
    /**
     * Filter courses based on keyword and rating
     */
    private List<Course> filterCourses(List<Course> courses, List<Review> reviews, 
            String keyWord, double minRating) {
        
        List<Course> courseList = new ArrayList<>();
        
        for (Course course : courses) {
            // Check keyword match
            if (!course.getCourseName().toLowerCase().contains(keyWord.toLowerCase())) {
                continue;
            }
            
            // Calculate average rating
            double sum = 0;
            int reviewCount = 0;
            
            for (Review review : reviews) {
                if (review.getCourse().getId() == course.getId()) {
                    sum += review.getRate();
                    reviewCount++;
                }
            }
            
            // Check rating threshold
            if (reviewCount > 0) {
                double average = sum / reviewCount;
                double roundedRating = Math.round(average);
                
                if (roundedRating >= minRating) {
                    courseList.add(course);
                }
            } else if (minRating == 0) {
                // Include courses without reviews if no rating filter
                courseList.add(course);
            }
        }
        
        return courseList;
    }
    
    /**
     * Sanitize input to prevent injection attacks
     */
    private String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        
        // Remove potentially dangerous characters
        // Keep: letters, numbers, spaces, Vietnamese characters, basic punctuation
        String sanitized = input.replaceAll(
            "[^a-zA-Z0-9\\s\\u00C0-\\u1EF9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềế" +
            "ệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđĐ.,-]", "");
        
        // Remove multiple spaces
        sanitized = sanitized.replaceAll("\\s+", " ");
        
        // Additional length check after sanitization
        if (sanitized.length() > MAX_KEYWORD_LENGTH) {
            sanitized = sanitized.substring(0, MAX_KEYWORD_LENGTH);
        }
        
        return sanitized;
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
}