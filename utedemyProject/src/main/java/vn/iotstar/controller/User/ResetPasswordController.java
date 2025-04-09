package vn.iotstar.controller.User;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;
import vn.iotstar.utils.AESUtil;

@WebServlet(urlPatterns = {"/user/resetPassword"})
public class ResetPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String password = req.getParameter("newPassword");
		String email = (String) req.getSession().getAttribute("email");
		
		String encryptedPassword = null;
		try {
			encryptedPassword = AESUtil.encrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean isSuccess = userService.updatePassword(email, encryptedPassword);
		if (isSuccess) {
			resp.sendRedirect(req.getContextPath() + "/home?showLoginModal=true");
		} else {
			System.out.println ("Không cập nhật được");
		}
	}
}
