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
	private ICourseService courseService = new CourseService(); // Tạo đối tượng service để gọi
	private IReviewService reviewService = new ReviewService(); // Tạo đối tượng service để gọi
	private IUserService userService = new UserService(); // Tạo đối tượng service để gọi

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
//		req.getRequestDispatcher("/views/user/filtercourse.jsp").forward(req, resp);
		List<OrderItem> orderItems = courseService.getAllOrderItems();
		List<User> users = userService.getAllUsers();
		List<Review> reviews = reviewService.getAllReviews();
		List<Lesson> lessons = courseService.getAllLessons();
		List<OrderItem> courseList = new ArrayList<>();

		int i = 0;
//		for (Course course : courses) {
//			if (course.getCourseName().toLowerCase().contains(keyWord.toLowerCase())) {
//				matchedCourses.add(course);
//				i++;
//				a = course.getId();
		for (OrderItem o : orderItems) {
			for (User u : users) {
				if (o.getCourse().getCourseName().toLowerCase().contains(keyWord.toLowerCase())) {
					if (o.getOrder().getUser().getId() == u.getId()) {
						System.out.println("Vo day");
						courseList.add(o); // Add the course to the list
						req.setAttribute("CourseName", courseList);
						System.out.println("Ten khoa hoc: " + o.getCourse().getCourseName());
						System.out.println("Ten giang vien khoa hoc: " + o.getOrder().getUser().getFullname());
						i++;
					}
				}
			}
		}
		req.setAttribute("Lesson", lessons);
		req.setAttribute("Review", reviews);
		System.out.println("Toi day!");
		for (Review r : reviews) {
			System.out.println("So sao la: " + r.getRate());
		}
		req.setAttribute("User", users);
		req.setAttribute("searchAmount", i);
		req.setAttribute("keyWord", keyWord);

		req.getRequestDispatcher("/views/user/searchcourse.jsp").forward(req, resp);
	}

}