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
import vn.iotstar.impl.service.*;
import vn.iotstar.service.*;
import vn.iotstar.entity.*;

@WebServlet(urlPatterns = { "/user/mycourse" })
public class MyCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderService();
//	private IUserService userService = new UserService();
	private ICourseDetailService c = new CourseDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("account");
		List<OrderItem> courseList = new ArrayList<>();
		List<OrderItem> ItemList = orderService.getAllOrderItems();
		List<CourseProgress> progress = c.getAllCourseProgress();

		for (OrderItem o : ItemList) {
			for (CourseProgress g : progress) {
				if (o.getOrder().getUser().getId() == user.getId()) {
					courseList.add(o);
				}
				if (g.getCourse().getId() == o.getCourse().getId() && g.getUser().getId() == user.getId()) {
					req.setAttribute("percent", g.getProgressPercentage());
				}
			}
		}

		req.setAttribute("myCourseList", courseList);
		req.setAttribute("fullname", user.getFullname());
		req.getRequestDispatcher("/views/user/mycourse.jsp").forward(req, resp);
	}
}