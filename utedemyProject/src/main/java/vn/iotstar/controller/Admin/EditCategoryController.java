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

@WebServlet(urlPatterns = { "/admin/edit" })
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
	        resp.sendRedirect(req.getContextPath() + "/admin/category2");
	        return;
	    }

	    CourseType courseType = courseService.getCourseTypeById(id);
	    if (courseType != null) {
	        req.setAttribute("courseType", courseType);
	        req.getRequestDispatcher("/views/admin/editCa2.jsp").forward(req, resp);
	    } else {
	        resp.sendRedirect(req.getContextPath() + "/admin/category2");
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		int courseId = Integer.parseInt(req.getParameter("id"));
		String newCourseName = req.getParameter("courseName"); 
		String courseTypeIdStr = req.getParameter("courseTypeId");
		String coursePriceStr = req.getParameter("coursePrice");
		int courseTypeId = 0;
		try {
			courseTypeId = Integer.parseInt(courseTypeIdStr);
		} catch (NumberFormatException e) {
			req.setAttribute("message1", "Loại khóa học không hợp lệ");
			req.getRequestDispatcher("/views/admin/addCourse.jsp").forward(req, resp);
			return;
		}

		double coursePrice = 0;
		try {
			coursePrice = Double.parseDouble(coursePriceStr);
		} catch (NumberFormatException e) {
			req.setAttribute("message2", "Giá khóa học không hợp lệ");
			req.getRequestDispatcher("/views/admin/addCourse.jsp").forward(req, resp);
			return;
		}
		Course course = courseService.getCourseById(courseId);
		if (course != null) {
			course.setCourseName(newCourseName);
			CourseType courseType = new CourseType();
			courseType.setId(courseTypeId); 
			course.setCourseType(courseType);
			course.setCoursePrice(coursePrice);

			boolean isUpdated = courseService.updateCourse(course); 
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