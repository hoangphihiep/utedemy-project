package vn.iotstar.controller;

import java.io.IOException;
import vn.iotstar.utils.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.*;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	
	public static final String COOKIE_REMEMBER = "id";
	
	IUserService user_service = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession(false);
		    
			//check session 
			if (session != null && session.getAttribute("account") != null) {
				resp.sendRedirect(req.getContextPath() + "/waiting");
				return;
				}
			
	        // check cookie
	        Cookie[] cookies = req.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	            	if (cookie.getName().equals("id")) {
	            		//nếu có cookie thì tạo cho người dùng session và redirect họ đến trang waiting
						session = req.getSession(true);
						session.setAttribute("id", cookie.getValue());
						resp.sendRedirect(req.getContextPath() + "/waiting");
						return;
					}
	            }
	        }
	        resp.sendRedirect("/utedemyProject/views/user/loginpage.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession(true);

        String input = req.getParameter("email");
        String password_notEncrypt = req.getParameter("password");
        String password = "";
		try {
			password = AESUtil.encrypt(password_notEncrypt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        boolean isRememberMe = false;
        String remember = req.getParameter("remember");

        if ("on".equals(remember)) {
            isRememberMe = true;
        }
        
        if (input.isEmpty() || password.isEmpty()) {
        	 resp.sendRedirect("/utedemyProject/views/user/loginpage.jsp?error=empty");
        	 return;
        }
        
     // Xác định loại tài khoản người dùng nhập vào
        boolean isEmail = input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        boolean isPhone = input.matches("^0\\d{9,10}$"); // Ví dụ số VN 10-11 số bắt đầu bằng 0

        if (!isEmail && !isPhone) {
            resp.sendRedirect("/utedemyProject/views/user/loginpage.jsp?error=invalid");
            return;
        }
        
        //kiem User
        User user = null;
        if (isEmail) {
            user = user_service.findByEmailAndPassword(input, password);
           // System.out.println("hehe"+ user);
        } else if (isPhone) {
            user = user_service.findByPhoneAndPassword(input, password);
            //System.out.println("haha"+ user);
        }
        
     
        if (user != null) {
            if (user.isActive()) {
                session.setAttribute("account", user);

                if (isRememberMe) {
                    saveRememberMe(resp, Integer.toString(user.getId()));
                }

                // Điều hướng đến trang chờ xử lý role
                resp.sendRedirect(req.getContextPath() + "/waiting");
            } else {
                // Tài khoản bị khóa
                resp.sendRedirect(req.getContextPath() + "/views/user/loginpage.jsp?alert=locked");
            }
        } else {
            // Sai tài khoản hoặc mật khẩu
            resp.sendRedirect(req.getContextPath() + "/views/user/loginpage.jsp?alert=wrong");
        }

	}
    private void saveRememberMe(HttpServletResponse response, String idUser) {
    	Cookie cookie = new Cookie(COOKIE_REMEMBER, idUser);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
    }
}
