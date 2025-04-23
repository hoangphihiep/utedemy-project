package vn.iotstar.controller;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;
import vn.iotstar.utils.RoleUtil;
import vn.iotstar.entity.*;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
	
	IUserService user_service = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			
			//lấy user từ thuộc tính account của session
			User u = (User) session.getAttribute("account");
			System.out.println("hungdebug"+u.getId());
			Set<Role> roles = user_service.getRolesByUserId(u.getId());
			System.out.println("User roles: " + roles);
			 int roleId = 0;
			 // nếu user là teacher thì lấy role teacher

			 for (Role role : roles) {
			     if (role.getId() == 2) {
			    	 roleId = 2;
			         break;
			     } else if (role.getId() == 1 && roleId == 0) {
			    	 roleId = 1;
			     }
			 }
			
		 	if (roleId == RoleUtil.ADMIN) {
		 		System.out.println("session User là admin");
				resp.sendRedirect(req.getContextPath() + "/admin/homePage");
				return;
			} else if (roleId == RoleUtil.USER) {
				System.out.println("session User là user");
				resp.sendRedirect(req.getContextPath() + "/user/homepage");
				return;
			} else {
				resp.sendRedirect(req.getContextPath() + "/teacher/homePage");
				System.out.println("session User là teacher");
				return;
			}
		}

		// Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					session = req.getSession(true);
					String id_user = (String) session.getAttribute("id");

					User u = user_service.findById(Integer.parseInt(id_user));
					session.setAttribute("account", u);
					System.out.println("hungdebug"+u.getId());
					Set<Role> roles = user_service.getRolesByUserId(u.getId());
					System.out.println("User roles: " + roles);
					 int roleId = 0;

					for (Role role : roles) {
					    roleId = role.getId();
					    String roleName = role.getName();
					    System.out.println("session Role ID: " + roleId + ", Name: " + roleName);
					}
					
				 	if (roleId == RoleUtil.ADMIN) {
				 		System.out.println("session User là admin");
						resp.sendRedirect(req.getContextPath() + "/views/hiep.jsp");
						return;
					} else if (roleId == RoleUtil.USER) {
						System.out.println("session User là user");
						resp.sendRedirect(req.getContextPath() + "/views/user/homepage.jsp");
						return;
					} else {
						resp.sendRedirect(req.getContextPath() + "/views/teacher/homepage.jsp");
						System.out.println("session User là teacher");
						return;
					}
					
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
