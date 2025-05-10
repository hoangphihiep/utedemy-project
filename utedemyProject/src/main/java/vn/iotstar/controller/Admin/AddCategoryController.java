package vn.iotstar.controller.Admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.CourseType;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.CourseDetailService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.ICourseDetailService;

@WebServlet(urlPatterns = { "/admin/add" })

public class AddCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();
	private ICourseDetailService courseDetailService = new CourseDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CourseType> courseType = courseDetailService.getAllCourseTypes();
		req.setAttribute("courseTypes", courseType);
		req.getRequestDispatcher("/views/admin/addCa.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String courseName = req.getParameter("courseName");
		String courseTypeParam = req.getParameter("courseType");
		System.out.println("CourseName: " + courseName);
		System.out.println("courseType: " + courseTypeParam);

		if (courseTypeParam == null || courseTypeParam.trim().isEmpty()) {
			resp.sendRedirect("error.jsp");
			return;
		}

		try {
			int courseTypeId = Integer.parseInt(courseTypeParam);

			int n = courseTypeId;
			System.out.println("n: " + n);

			boolean success = courseService.addCourse(courseName, courseTypeId);

			if (success) {
				resp.sendRedirect(req.getContextPath() + "/admin/edit");
			} else {
				resp.sendRedirect("error.jsp");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.sendRedirect("error.jsp");
		}
	}

}
