package vn.iotstar.controller.Admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.*;
import vn.iotstar.impl.service.*;
import vn.iotstar.service.*;

@WebServlet(urlPatterns = { "/admin/category2" })

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICourseService courseService = new CourseService();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CourseType> list = courseService.getAllCourseTypes();

		req.setAttribute("List", list);
		req.getRequestDispatcher("/views/admin/categorypage2.jsp").forward(req, resp);
	}
}