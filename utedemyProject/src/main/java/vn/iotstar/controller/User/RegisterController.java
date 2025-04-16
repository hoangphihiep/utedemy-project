package vn.iotstar.controller.User;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.impl.service.*;
import vn.iotstar.service.*;


@WebServlet(urlPatterns = {"/user/register"})
public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public EmailService emailService = new EmailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		if(path.contains("register")) {
		resp.sendRedirect("/utedemyProject/views/user/registerpage.jsp");	
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		IUserService service = new UserService();
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		if (path.contains("register")) {
			
			String fullname = req.getParameter("fullname");
			session.setAttribute("fullname",fullname);
			
			String password = req.getParameter("password");
			session.setAttribute("password",password);
			
			String email = req.getParameter("email");
			session.setAttribute("email",email);
			
			String phone = req.getParameter("phone");
			session.setAttribute("phone",phone);
		
			
			String confirmPassword = req.getParameter("confirmPassword");
			session.setAttribute("confirmPassword", confirmPassword);
			
			
			if(service.checkPasswordAndConfirmPassword(password,confirmPassword)==false) {
				session.setAttribute("alert", "Mật khẩu và xác nhận mật khẩu không khớp");
				 resp.sendRedirect("/utedemyProject/user/register");
				    return;
			}
			else {
			  if(service.checkPasswordFormat(confirmPassword)==false) {
				 session.setAttribute("alert", "Mật khẩu không đúng định dạng");
				 resp.sendRedirect("/utedemyProject/user/register");
		         return;
		     	}
			}
			
			if (service.checkFormatMail(email)==false) {
				session.setAttribute("alert", "Email không đúng định dạng");
			    resp.sendRedirect("/utedemyProject/user/register");
			    return;
			}
			if (!service.checkFormatPhone(phone)==false) {
				session.setAttribute("alert", "Số điện thoại không đúng định dạng");
			    resp.sendRedirect("/utedemyProject/user/register");
			    return;
			}
		
			if (service.checkExistEmail(email)) {
				session.setAttribute("alert", "Email đã được đăng kí");
			    resp.sendRedirect("/utedemyProject/user/register");
			    return;
			}
			
			if (service.checkExistPhoneNumber(phone)) {
				session.setAttribute("alert", "Số điện thoại đã được đăng kí");
				 resp.sendRedirect("/utedemyProject/user/register?error=phone_exist");
				 return;
			}
			
			//call otp controller 
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
				
		}
		

	}

}
