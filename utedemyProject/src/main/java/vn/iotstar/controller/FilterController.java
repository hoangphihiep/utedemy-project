package vn.iotstar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.ReviewService;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.IReviewService;
import vn.iotstar.service.IUserService;
import vn.iotstar.entity.*;

@WebServlet(urlPatterns = { "/user/filter" })
public class FilterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService(); // Sử dụng service (DAO thông qua service)
	private IReviewService reviewService = new ReviewService(); // Tạo đối tượng service để gọi

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		String keyWord = (String) session.getAttribute("keyWord");
		String ratingParam = req.getParameter("rating");
		double ratingStr = 0.0;
		if (ratingParam != null && !ratingParam.isEmpty()) {
			try {
				ratingStr = Double.parseDouble(ratingParam);
			} catch (NumberFormatException e) {
				System.out.println("Rating không hợp lệ: " + ratingParam);
			}
		}
//		List<OrderItem> orderItems = courseService.getAllOrderItems();
		List<Course> courses = courseService.getAllCourses();
		List<Review> reviews = reviewService.getAllReviews();
//		List<OrderItem> courseList = new ArrayList<>();
		List<Course> courseList = new ArrayList<>();

		int i = 0;
		for (Course c : courses) {
			if (c.getCourseName().toLowerCase().contains(keyWord.toLowerCase())) {
				double sum = 0;
				int reviewCount = 0;
				for (Review r : reviews) {
					if (r.getCourse().getId() == c.getId()) {
						sum += r.getRate();
						reviewCount++;
					}
				}
				double roundedRating = 0;
				if (reviewCount > 0) {
					double average = sum / reviewCount;
					roundedRating = Math.round(average);
				} else {
					req.setAttribute("average" + c.getCourseName(), 0);
					req.setAttribute("reviewCount" + c.getCourseName(), 0);
				}
				if (roundedRating >= ratingStr) {
					courseList.add(c);
					i++;
					System.out.println("Ten khoa hoc: " + c.getCourseName());
					System.out.println("Ten giang vien khoa hoc: " + c.getTeacher().getFullname());
				}
			}
		}
		req.setAttribute("CourseList", courseList);
		req.setAttribute("Review", reviews);
		req.setAttribute("searchAmount", i);
		req.setAttribute("keyWord", keyWord);
		req.getRequestDispatcher("/views/user/filtercourse.jsp").forward(req, resp);
	}
}