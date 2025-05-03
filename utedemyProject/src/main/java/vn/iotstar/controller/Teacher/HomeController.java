package vn.iotstar.controller.Teacher;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.Discount;
import vn.iotstar.entity.Notification;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.DiscountService;
import vn.iotstar.impl.service.NotificationService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.IDiscountService;
import vn.iotstar.service.INotificationService;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 15 // 15 MB
	)
@WebServlet(urlPatterns = {"/teacher/course", "/teacher/coupon"})
public class HomeController extends HttpServlet {

	public ICourseService courseService = new CourseService();
	public INotificationService notificationService = new NotificationService();
	public IDiscountService discountService = new DiscountService();
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		if (url.contains("/teacher/course")) 
		{
			User user = (User)session.getAttribute("account");
			req.setAttribute("fullname", user.getFullname());
			List<Course> listCourse = courseService.findByIdTeacher(user);
			List<Notification> listNotification = notificationService.findByIdUser(user.getId());
			int soLuongThongBao = listNotification.size();
			req.setAttribute("listCourse", listCourse);
			req.setAttribute("slthongbao", soLuongThongBao);
			req.setAttribute("listthongbao", listNotification);
			req.getRequestDispatcher("/views/teacher/course.jsp").forward(req, resp);
		}
		if (url.contains("/teacher/coupon")) 
		{
			User user = (User)session.getAttribute("account");
			req.setAttribute("fullname", user.getFullname());
			List<Discount> listDiscount = discountService.findByIdUser(user.getId());
			List<Notification> listNotification = notificationService.findByIdUser(user.getId());
			int soLuongThongBao = listNotification.size();
			req.setAttribute("listDiscount", listDiscount);
			req.setAttribute("slthongbao", soLuongThongBao);
			req.setAttribute("listthongbao", listNotification);
			req.getRequestDispatcher("/views/teacher/coupon.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}