package vn.iotstar.controller.User;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.impl.service.EmailService;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;

@WebServlet(urlPatterns = {"/user/forgotPassword"})
public class ForgotPasswordController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public EmailService emailService = new EmailService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/forgotPassword2.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        
        IUserService userService = new UserService();
        String email = req.getParameter("email");
        
        boolean userExists = userService.checkExistEmail(email);
        
        if (userExists) {
        	String otpCode = emailService.generateOTP();
        	
        	session.setAttribute("otp_code", otpCode);
            session.setAttribute("email", email);  // Lưu email vào session
            session.setAttribute("send", 1);
            session.setAttribute("attempts", 0);
            session.setAttribute("last_send_time", System.currentTimeMillis());
            
            // Thiết lập thời gian hết hạn session (ví dụ: 5 phút)
            session.setMaxInactiveInterval(900);
            
        	if (emailService.sendOtp(email, otpCode)) {
        		System.out.println("tài khoản có tồn tại, gửi mã thành công");
        	}
        	 
        	req.setAttribute("email", email);
        	req.getRequestDispatcher("/views/user/verifyOTP.jsp").forward(req, resp);
        } else {
        	req.setAttribute("error", "Email không tồn tại trong hệ thống.");
        	req.getRequestDispatcher("/views/user/forgotPassword.jsp").forward(req, resp);
        }
        
	}
}
