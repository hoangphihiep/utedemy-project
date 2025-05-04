package vn.iotstar.controller.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.impl.service.CourseDetailService;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.ICourseDetailService;
import vn.iotstar.entity.*;

@WebServlet(urlPatterns = { "/user/mycourse" })
public class MyCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();
	private IUserService userService = new UserService();
	private ICourseDetailService c = new CourseDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/homepage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
//		HttpSession session = req.getSession();
//		Integer userId = (Integer) session.getAttribute("userId");
		int userId = 1; // Test
//		List<User> users = userService.getAllUsers();
//		List<Course> courses = courseService.getCoursesByUserId(userId);
		List<User> userList = userService.getUsersByRole(2);
//		List<Course> myCourse = courseService.getCoursesByUserId(userId);
		List<OrderItem> courseList = new ArrayList<>();
		List<OrderItem> ItemList = courseService.getAllOrderItems();
		List<CourseProgress> progress = c.getAllCourseProgress();

		for (OrderItem o : ItemList) {
			for (CourseProgress g : progress) {
				if (o.getOrder().getUser().getId() == userId) {
					courseList.add(o);
				}
				if (g.getCourse().getId() == o.getCourse().getId() && g.getUser().getId() == userId) {
					req.setAttribute("percent", g.getProgressPercentage());
				}
			}
		}

		req.setAttribute("myCourseList", courseList);
		req.getRequestDispatcher("/views/user/mycourse.jsp").forward(req, resp);
	}
}