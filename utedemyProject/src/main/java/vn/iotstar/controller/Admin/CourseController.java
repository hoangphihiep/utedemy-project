package vn.iotstar.controller.Admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.Notification;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.NotificationService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.INotificationService;
import vn.iotstar.state.CourseContext;

@WebServlet(urlPatterns = {"/admin/courseManagement","/admin/cancelCourse","/admin/restoreCourse","/admin/reviewCourse"})
public class CourseController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICourseService courseService = new CourseService();
	INotificationService notificationService = new NotificationService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/courseManagement")) {
			System.out.println("Có vào đây2");
			List<Course> listCourse = courseService.findAllCourse();
			req.setAttribute("listCourse", listCourse);
			req.getRequestDispatcher("/views/admin/listCourse.jsp").forward(req, resp);
		}
		
		else if (url.contains("/admin/cancelCourse")) {
		    HttpSession session = req.getSession();
		    String idCourseStr = req.getParameter("id");
		    String content  = req.getParameter("noidung");

		    User user = (User)session.getAttribute("account");

		    if (idCourseStr != null) {
		        int idCourse = Integer.parseInt(idCourseStr);
		        Course course = courseService.findByIdCourse(idCourse);
		        CourseContext context = new CourseContext(course);
		        context.cancel(course); // Sử dụng state pattern

		        if (content != null) {
		            Notification notification = new Notification();
		            notification.setSender(user);
		            notification.setReceiver(course.getTeacher());
		            notification.setContent(content);
		            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		            notification.setSentDate(timestamp.toString());

		            if (notificationService.insert(notification) && courseService.updateCourse(course)) {
		                resp.sendRedirect(req.getContextPath() + "/admin/courseManagement");
		            }
		        }
		    }
		}

		else if (url.contains("/admin/restoreCourse")) {
		    String idCourseStr = req.getParameter("id");
		    if (idCourseStr != null) {
		        int idCourse = Integer.parseInt(idCourseStr);
		        Course course = courseService.findByIdCourse(idCourse);
		        CourseContext context = new CourseContext(course);
		        context.restore(course); // Sử dụng state pattern
		        if (courseService.updateCourse(course)) {
		            resp.sendRedirect(req.getContextPath() + "/admin/courseManagement");
		        }
		    }
		}

		
		else if (url.contains("/admin/reviewCourse")) {
		    String idCourseStr = req.getParameter("id");
		    if (idCourseStr != null) {
		        int idCourse = Integer.parseInt(idCourseStr);
		        Course course = courseService.findByIdCourse(idCourse); 
		        CourseContext context = new CourseContext(course);
		        context.review(course); // Sử dụng state pattern
		        if (courseService.updateCourse(course)) {
		            resp.sendRedirect(req.getContextPath() + "/admin/courseManagement");
		        }
		    }
		}

	}
}