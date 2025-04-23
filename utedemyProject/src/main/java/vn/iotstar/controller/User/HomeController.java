package vn.iotstar.controller.User;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.service.ICourseService;

@WebServlet(urlPatterns = {"/user/homepage"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
    	List<Object[]> bestSellerCourses = courseService.getBestSellingCourses(5);
    	req.setAttribute("bestSellerCourses", bestSellerCourses);

    	List<Object[]> todaySaleCourses = courseService.getTodaySaleCourses(10);
    	req.setAttribute("todaySaleCourses", todaySaleCourses);
	
	    if (url.contains("/user/homepage"))
		{
			req.getRequestDispatcher("/views/user/homepage.jsp").forward(req, resp);
		}
		
	}
}
