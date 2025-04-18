package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/user/addTarget","/user/add","/user/adBasicInformation", "/danhsachks/thichKS","/user/homepage"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/user/addTarget")) 
		{
			req.getRequestDispatcher("/views/teacher/addTarget.jsp").forward(req, resp);
		}
		else if (url.contains("/user/add")) 
		{
			req.getRequestDispatcher("/views/teacher/add.jsp").forward(req, resp);
		}
		else if (url.contains("/user/adBasicInformation")) 
		{
			req.getRequestDispatcher("/views/teacher/adBasicInformation.jsp").forward(req, resp);
		}
		else if (url.contains("/user/homepage"))
		{
			req.getRequestDispatcher("/views/user/homepage.jsp").forward(req, resp);
		}
		
	}
}
