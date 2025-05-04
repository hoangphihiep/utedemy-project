package vn.iotstar.controller.Admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;

@WebServlet(urlPatterns = {"/admin/accountManagement"})
public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idUserStr = req.getParameter("id");
		if (idUserStr != null) {
			int idUser = Integer.parseInt(idUserStr);
			User user1 = userService.findById(idUser);
			if (user1.getIsActive() == true) {
				user1.setIsActive(false);
				boolean check = userService.updateUserInformation(user1);
				if (check) {
					System.out.println("Đã khóa tài khoản");
				}
			}
			else
			{
				user1.setIsActive(true);
				boolean check = userService.updateUserInformation(user1);
				if (check) {
					System.out.println("Đã mở khóa tài khoản");
				}
			}
		}
		List<User> listUser = userService.findAllUser();
		req.setAttribute("listuser", listUser);
		req.getRequestDispatcher("/views/admin/listAccount.jsp").forward(req, resp);
	}
}