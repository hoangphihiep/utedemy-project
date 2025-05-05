package vn.iotstar.controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.FavoriteCourse;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.FavoriteCourseService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.IFavoriteCourseService;

@WebServlet(urlPatterns = {"/user/favoriteCourse","/user/addFavoriteCourse"})
public class FavoriteCourseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public IFavoriteCourseService favoriteCourseService = new FavoriteCourseService();
	public ICourseService courseService = new CourseService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	    HttpSession session = req.getSession();

	    User user = (User) session.getAttribute("account");

		if (url.contains("/user/favoriteCourse")) 
		{
			FavoriteCourse favoriteCourse = favoriteCourseService.findByIdUser(user.getId());

			if (favoriteCourse != null) {
                Set<Course> favoriteCourses = favoriteCourse.getCourses();
                req.setAttribute("favoriteCourses", favoriteCourses);
                req.setAttribute("fullname", user.getFullname());
            }
			
			req.getRequestDispatcher("/views/user/listFavoriteCourse.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
	    HttpSession session = req.getSession();
	    PrintWriter out = resp.getWriter();

		if (url.contains("/user/addFavoriteCourse")) 
		{
			User user = (User)session.getAttribute("account");

			if (user == null) {
	            // Return JSON response indicating user needs to login
	            out.print("{\"success\": false, \"message\": \"Please login to add favorite courses\"}");
	            return;
	        }

			try {
	            int courseId = Integer.parseInt(req.getParameter("courseId"));
	            Course course = courseService.findByIdCourse(courseId);
	            boolean success = favoriteCourseService.addCourseToFavorite(user, course);

	            if (success) {
	                out.print("{\"success\": true, \"message\": \"Course added to favorites\"}");
	            } else {
	                out.print("{\"success\": false, \"message\": \"Failed to add course to favorites\"}");
	            }

	        } catch (NumberFormatException e) {
	            out.print("{\"success\": false, \"message\": \"Invalid course ID\"}");
	        } catch (Exception e) {
	            e.printStackTrace();
	            out.print("{\"success\": false, \"message\": \"Server error\"}");
	        }
		}
	}

}