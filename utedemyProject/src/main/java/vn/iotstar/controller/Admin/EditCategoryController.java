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
import vn.iotstar.impl.service.CourseDetailService;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.service.*;

@WebServlet(urlPatterns = { "/admin/edit2" })
public class EditCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
	    int id = 0;

	    try {
	        id = Integer.parseInt(idStr);
	    } catch (NumberFormatException e) {
	        resp.sendRedirect(req.getContextPath() + "/admin/add2"); // hoặc hiển thị lỗi
	        return;
	    }

	    CourseType courseType = courseService.getCourseTypeById(id);
	    if (courseType != null) {
	        req.setAttribute("courseType", courseType);
	        req.getRequestDispatcher("/views/admin/editCa2.jsp").forward(req, resp);
	    } else {
	        resp.sendRedirect(req.getContextPath() + "/admin/edit2"); // Không tìm thấy -> về danh sách
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String idStr = req.getParameter("id");
		String newCourseTypeName = req.getParameter("courseTypeName");
		int id = 0;

		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			req.setAttribute("message", "ID không hợp lệ");
			req.getRequestDispatcher("/views/admin/editCa2.jsp").forward(req, resp);
			return;
		}

		if (newCourseTypeName == null || newCourseTypeName.trim().isEmpty()) {
			req.setAttribute("message", "Tên loại khóa học không được để trống");
			CourseType courseType = courseService.getCourseTypeById(id);
			req.setAttribute("courseType", courseType);
			req.getRequestDispatcher("/views/admin/editCa2.jsp").forward(req, resp);
			return;
		}

		CourseType courseType = courseService.getCourseTypeById(id);
		if (courseType == null) {
			req.setAttribute("message", "Không tìm thấy loại khóa học");
			req.getRequestDispatcher("/views/admin/editCa2.jsp").forward(req, resp);
			return;
		}

		courseType.setCourseTypeName(newCourseTypeName.trim());
		boolean isUpdated = courseService.updateCourseType(courseType);
		if (isUpdated) {
			resp.sendRedirect(req.getContextPath() + "/admin/edit2");
		} else {
			req.setAttribute("message", "Cập nhật thất bại");
			req.setAttribute("courseType", courseType);
			req.getRequestDispatcher("/views/admin/editCa2.jsp").forward(req, resp);
		}
	}
}