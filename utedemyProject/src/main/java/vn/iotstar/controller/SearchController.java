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
		List<Course> courseList = new ArrayList<>();
		List<Course> courses =  courseService.getAllCourses();
		int i = 0;
		System.out.println("Ten khoa hoc: ");
		for(Course c : courseList)
		{
			System.out.print(c.getCourseName());
		}
		if (keyWord == null || keyWord.trim().isEmpty()) {
			System.out.println("Vo day");
			courseList.addAll(courses);
		} else {
			for (Course c : courses) {
				System.out.println("Vo day");
				if (c.getCourseName().toLowerCase().contains(keyWord.toLowerCase()) && c.getStatus() == 1) {
				    courseList.add(c);
				    i++;
				}
			}
		}

		req.setAttribute("CourseList", courseList);
		req.setAttribute("Review", reviews);
		req.setAttribute("searchAmount", i);
		req.setAttribute("keyWord", keyWord);

		req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
	}
}