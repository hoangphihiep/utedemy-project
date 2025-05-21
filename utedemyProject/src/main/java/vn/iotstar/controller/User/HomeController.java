package vn.iotstar.controller.User;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Notification;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.NotificationService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.INotificationService;

@WebServlet(urlPatterns = {"/user/homepage"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ICourseService courseService = new CourseService();
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String url = req.getRequestURI();
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    
	    HttpSession session = req.getSession();
	    
	    
		
	    List<Object[]> bestSellerCourses = courseService.getBestSellingCourses(5);
	    System.out.println("Best Seller Courses: " + bestSellerCourses); // In dữ liệu
	    for (Object[] course : bestSellerCourses) {
	        System.out.println("Course ID: " + course[5]); // In ID
	    }
	    req.setAttribute("bestSellerCourses", bestSellerCourses);

	    List<Object[]> todaySaleCourses = courseService.getTodaySaleCourses(10);
	    System.out.println("Today Sale Courses: " + todaySaleCourses); // In dữ liệu
	    for (Object[] course : todaySaleCourses) {
	        System.out.println("Course ID: " + course[6]); // In ID
	    }
	    req.setAttribute("todaySaleCourses", todaySaleCourses);

	    if (url.contains("/user/homepage")) {
	        req.getRequestDispatcher("/views/user/homepage.jsp").forward(req, resp);
	    }
	}
}