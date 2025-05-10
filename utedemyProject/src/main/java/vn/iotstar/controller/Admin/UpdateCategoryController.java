package vn.iotstar.controller.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.service.ICourseService;

@WebServlet(urlPatterns = { "/admin/update" })

public class UpdateCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int courseId = Integer.parseInt(req.getParameter("id"));
		String courseName = req.getParameter("courseName");

		Course course = courseService.getCourseById(courseId);
		if (course != null) {
			course.setCourseName(courseName);
			courseService.updateCourse(course);
			resp.sendRedirect("categorypage"); 
		} else {
			resp.sendRedirect("categorypage");
		}
	}
}
