package vn.iotstar.controller.User;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;

@WebServlet("/user/Course")
public class CourseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	    HttpSession session = req.getSession();
	    
		User user = (User) session.getAttribute("account");
		req.setAttribute("fullname", user.getFullname());
		
		req.getRequestDispatcher("/views/user/registeredCourse.jsp").forward(req, resp);
	}

}