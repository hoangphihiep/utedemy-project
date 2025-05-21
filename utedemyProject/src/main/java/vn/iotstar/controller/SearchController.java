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
import vn.iotstar.service.IUserService;
import vn.iotstar.service.IReviewService;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.entity.*;
import vn.iotstar.strategy.*;

@WebServlet(urlPatterns = { "/user/search" })
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;
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

		String keyWord = req.getParameter("keyword");
		HttpSession session = req.getSession();
		session.setAttribute("keyWord", keyWord);
		System.out.println("Keyword: " + keyWord);
		List<Review> reviews = reviewService.getAllReviews();
		
		List<Course> allCourses=courseService.getAllCourses();
		
		SearchStrategy strategy=detectSearchStrategy(keyWord);
		SearchContext context=new SearchContext();
		
		context.setStrategy(strategy);
		List<Course> courseList=context.executeSearch(keyWord, allCourses);
		
		req.setAttribute("CourseList", courseList);
		req.setAttribute("Review", reviews);
		req.setAttribute("searchAmount", courseList.size());
		req.setAttribute("keyWord", keyWord);
		
		req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
	}
	private SearchStrategy detectSearchStrategy(String keyWord) {
		if (keyWord == null || keyWord.trim().isEmpty()) {
			return (k, courses) -> courses;
		}

		String normalizedKeyword = VietnameseNormalizer.normalize(keyWord);
		String[] keywordParts = normalizedKeyword.split("\\s+");

		List<Course> allCourses = courseService.getAllCourses();
		
		for(Course course:allCourses) {
			if(course.getCourseType()!=null && course.getCourseType().getCourseTypeName()!=null) {
				String typename=VietnameseNormalizer.normalize(course.getCourseType().getCourseTypeName());
				if(typename.contains(normalizedKeyword)) {
					return new SearchByType();
				}
			}
		}

		// Kiểm tra khớp tên giảng viên (so sánh không dấu)
		for (Course course : allCourses) {
			if (course.getTeacher() != null && course.getTeacher().getFullname() != null) {
				String fullname = VietnameseNormalizer.normalize(course.getTeacher().getFullname());
				for (String part : keywordParts) {
					if (fullname.contains(part)) {
						return new SearchByInstructorName();
					}
				}
			}
		}

		// Kiểm tra khớp tên khóa học (so sánh không dấu)
		for (Course course : allCourses) {
			if (course.getCourseName() != null) {
				String name = VietnameseNormalizer.normalize(course.getCourseName());
				for (String part : keywordParts) {
					if (name.contains(part)) {
						return new SearchByCourseName();
					}
				}
			}
		}

		return new SearchByCourseName();
	}
}