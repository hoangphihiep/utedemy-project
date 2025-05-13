package vn.iotstar.controller.Admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.CourseType;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.service.ICourseService;

@WebServlet(urlPatterns = { "/admin/add2" })

public class AddCategory2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/addCa2.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String newCourseTypeName = req.getParameter("courseTypeName");

		// Lấy danh sách các loại khóa học
		List<CourseType> list = courseService.getAllCourseTypes();

		// Kiểm tra tên rỗng
		if (newCourseTypeName == null || newCourseTypeName.trim().isEmpty()) {
			req.setAttribute("message", "Tên khóa học không được để trống");
			req.getRequestDispatcher("/views/admin/addCa2.jsp").forward(req, resp);
			return; // Dừng lại sau khi forward
		}

		// Kiểm tra tên trùng (so sánh bỏ qua khoảng trắng và không phân biệt hoa
		// thường)
		boolean isDuplicate = false;
		for (CourseType c : list) {
			if (c.getCourseTypeName().trim().equalsIgnoreCase(newCourseTypeName.trim())) {
				isDuplicate = true;
				break;
			}
		}

		if (isDuplicate) {
			req.setAttribute("message", "Tên khóa học đã tồn tại, vui lòng nhập tên khác!");
			req.getRequestDispatcher("/views/admin/addCa2.jsp").forward(req, resp);
			return;
		}

		// Không trùng, tiến hành thêm mới
		CourseType courseType = new CourseType();
		courseType.setCourseTypeName(newCourseTypeName.trim());

		try {
			courseService.addCourseType(courseType);
			resp.sendRedirect(req.getContextPath() + "/admin/add2");
		} catch (Exception e) {
			req.setAttribute("message", "Đã xảy ra lỗi khi thêm loại khóa học.");
			req.getRequestDispatcher("/views/admin/addCa2.jsp").forward(req, resp);
		}
	}

}