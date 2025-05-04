package vn.iotstar.controller.User;

import java.io.IOException;
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
		req.setAttribute("UserId", userId);
		List<User> users = userService.getAllUsers();
		List<Course> courses = courseService.getCoursesByUserId(userId);
		List<User> userList = userService.getUsersByRole(1);
		List<CourseProgress> courseProList = c.getAllCourseProgress();

		for(User g :  userList)
		{
			System.out.println("Danh sach giao vien la: " + g.getFullname());
		}
		for(CourseProgress v : courseProList)
		{
			System.out.println("Phan tram: "+ v.getProgressPercentage());
		}
		req.setAttribute("Users", users);
		for (User user : users) {
			for (User teacher : userList) {
				for (CourseProgress j : courseProList) {
					System.out.println("Test userName: " + user.getFullname());
					if (user.getId() == userId) {
						req.setAttribute("Users", user.getFullname());
					}
					if (teacher.getId() == userId && j.getUser().getId() == userId) {
						req.setAttribute("teacherList", teacher.getFullname());
						System.out.println("Danh sach giao vien la: " + teacher.getFullname());
						req.setAttribute("Percentage", j.getProgressPercentage());
						System.out.println("Phan tram: "+ j.getProgressPercentage());
//					}
				}

			}
		}

		System.out.println("Danh sach kh: ");
		for (Course c : courses) {
			System.out.println(c.getCourseName());
		}
		req.setAttribute("myCourseList", courses);
		req.getRequestDispatcher("/views/user/mycourse.jsp").forward(req, resp);

	}
}
