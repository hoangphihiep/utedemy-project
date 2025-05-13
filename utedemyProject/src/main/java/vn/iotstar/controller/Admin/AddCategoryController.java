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

		// Lấy dữ liệu từ form
		String newCourseName = req.getParameter("courseName");
		String courseTypeIdStr = req.getParameter("courseTypeId");
		String coursePriceStr = req.getParameter("coursePrice");

		// Kiểm tra dữ liệu đầu vào
		if (newCourseName == null || newCourseName.trim().isEmpty()) {
			req.setAttribute("message", "Tên khóa học không được để trống");
			req.getRequestDispatcher("/views/admin/addCourse.jsp").forward(req, resp);
			return;
		}

		int courseTypeId = 0;
		try {
			courseTypeId = Integer.parseInt(courseTypeIdStr);
		} catch (NumberFormatException e) {
			req.setAttribute("message", "Loại khóa học không hợp lệ");
			req.getRequestDispatcher("/views/admin/addCourse.jsp").forward(req, resp);
			return;
		}

		double coursePrice = 0;
		try {
			coursePrice = Double.parseDouble(coursePriceStr);
		} catch (NumberFormatException e) {
			req.setAttribute("message1", "Giá khóa học không hợp lệ");
			req.getRequestDispatcher("/views/admin/addCourse.jsp").forward(req, resp);
			return;
		}

		// Tạo đối tượng Course mới và gán dữ liệu
		Course course = new Course();
		course.setCourseName(newCourseName);

		// Gán CourseType từ ID đã có sẵn
		CourseType courseType = new CourseType();
		courseType.setId(courseTypeId); // Set ID cho CourseType
		course.setCourseType(courseType);

		course.setCoursePrice(coursePrice);

		try {
			// Gọi phương thức addCourse để thêm khóa học vào cơ sở dữ liệu
			courseService.addCourse(course);
			// Chuyển hướng về trang thành công
			resp.sendRedirect(req.getContextPath() + "/admin/add");		
			} catch (Exception e) {
			// Xử lý lỗi khi thêm khóa học thất bại
			req.setAttribute("message2", "Lỗi thêm khóa học");
			req.getRequestDispatcher("/views/admin/addCourse.jsp").forward(req, resp);
		}
	}
}