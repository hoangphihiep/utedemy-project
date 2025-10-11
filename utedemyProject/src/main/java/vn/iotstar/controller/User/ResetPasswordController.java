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
import vn.iotstar.utils.CSRFUtil;

@WebServlet(urlPatterns = {"/user/resetPassword"})
public class ResetPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String password = req.getParameter("newPassword");
		String email = (String) req.getSession().getAttribute("email");
		
		if (!CSRFUtil.validateToken(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF Token không hợp lệ hoặc bị thiếu");
            return;
        }
		
		String encryptedPassword = null;
		try {
			encryptedPassword = AESUtil.encrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean isSuccess = userService.updatePassword(email, encryptedPassword);
		String newCsrfToken = CSRFUtil.generateToken(req);
        req.setAttribute("csrfToken", newCsrfToken);
		if (isSuccess) {
			resp.sendRedirect(req.getContextPath() + "/homepage");
		} else {
			System.out.println ("Không cập nhật được");
		}
	}
}
