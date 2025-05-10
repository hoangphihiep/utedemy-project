package vn.iotstar.controller.Admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Course;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.service.*;

@WebServlet(urlPatterns = { "/admin/edit" })
public class EditCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int courseId = Integer.parseInt(req.getParameter("id"));
		Course course = courseService.getCourseById(courseId);

		if (course != null) {
			req.setAttribute("course", course);
			req.getRequestDispatcher("/views/admin/editCa.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("categorypage");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		int courseId = Integer.parseInt(req.getParameter("id"));
		String newCourseName = req.getParameter("courseName"); // Tên mới từ form

		Course course = courseService.getCourseById(courseId);
		if (course != null) {
			course.setCourseName(newCourseName);

			boolean isUpdated = courseService.updateCourse(course); // Gọi hàm update đúng chuẩn
			if (isUpdated) {
				resp.sendRedirect(req.getContextPath() + "/admin/edit?id=" + courseId + "&success=true");
			} else {
				req.setAttribute("message", "Lỗi cập nhật khóa học");
				req.setAttribute("course", course);
				req.getRequestDispatcher("/views/admin/editCa.jsp").forward(req, resp);
			}
		} else {
			resp.sendRedirect("categorypage");
		}
	}

}