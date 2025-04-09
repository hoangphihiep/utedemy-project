package vn.iotstar.controller.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.impl.service.EmailService;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;


@WebServlet(urlPatterns = {"/user/verifyOTP","/user/deleteOTP","/user/resendOTP"})
public class verifyOTPController extends HttpServlet{

	IUserService userService = new UserService();
	public EmailService emailService = new EmailService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		if (url.contains("/user/verifyOTP")) 
		{
			// Kiểm tra xem có phải là request AJAX không
		    boolean isAjaxRequest = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
		    
			String otpCode1 = req.getParameter("otp1");
			String otpCode2 = req.getParameter("otp2");
			String otpCode3 = req.getParameter("otp3");
			String otpCode4 = req.getParameter("otp4");
			String otpCode5 = req.getParameter("otp5");
			String otpCode6 = req.getParameter("otp6");
			String otpCode = otpCode1 + otpCode2 + otpCode3 + otpCode4 + otpCode5 + otpCode6;
			
			String email = req.getParameter("email");
			
			System.out.println ("Người dùng nhập: " + otpCode);
	        System.out.println ("gmail của người dùng: " + email);
	        
	        if (isAjaxRequest) {
	            // Xử lý request AJAX và trả về JSON
	            resp.setContentType("application/json");
	            resp.setCharacterEncoding("UTF-8");
	            PrintWriter out = resp.getWriter();
	            
	            if (!isOtpAttemptsExceeded(req.getSession(), 5)) {
	                if (verifyOTP(req.getSession(), otpCode)) {
	                    System.out.println("Nhập mã OTP thành công");
	                    boolean success = deleteOTP(req.getSession()); 
	                    if (success) {
	                    	out.print("{\"success\": true, \"redirectUrl\": \"" + req.getContextPath() + "/user/forgotPassword\"}");
	                    }      
	                } else {
	                    updateOtpAttempts(req.getSession());
	                    System.out.println("Nhập mã OTP thất bại, nhập lại");
	                    out.print("{\"success\": false, \"message\": \"Mã OTP không chính xác. Vui lòng thử lại.\"}");
	                }
	            } else {
	                System.out.println("Số lần nhập quá 5 lần. Nhấn gửi lại mã OTP");
	                out.print("{\"success\": false, \"message\": \"Bạn đã nhập sai quá 5 lần. Vui lòng gửi lại mã OTP mới.\", \"attemptsExceeded\": true}");
	            }
	            
	            out.flush();
	        }
		}
		if (url.contains("/user/deleteOTP")) {
			try {
	            // Đọc JSON từ request body
	            BufferedReader reader = req.getReader();
	            StringBuilder jsonBuilder = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                jsonBuilder.append(line);
	            }
	            
	            // Gọi service để xóa OTP từ database
	            boolean success = deleteOTP(req.getSession());
	            
	            // Trả về kết quả
	            JSONObject responseJson = new JSONObject();
	            responseJson.put("success", success);
	            
	            if (success) {
	                responseJson.put("message", "OTP đã được xóa");
	            } else {
	                responseJson.put("message", "Không thể xóa OTP");
	            }
	            
	            resp.getWriter().write(responseJson.toString());
	            
	        } catch (Exception e) {
	            // Xử lý lỗi
	        	resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            JSONObject errorJson = new JSONObject();
	            errorJson.put("success", false);
	            errorJson.put("message", "Đã xảy ra lỗi: " + e.getMessage());
	            resp.getWriter().write(errorJson.toString());
	        }
		}
		if (url.contains("/user/resendOTP")) {
		    boolean isAjaxRequest = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
		    String email = req.getParameter("email");
		    
		    if (isAjaxRequest) {
		        resp.setContentType("application/json");
		        resp.setCharacterEncoding("UTF-8");
		        PrintWriter out = resp.getWriter();
		        
		        String otpCode = emailService.generateOTP();
		        // Lấy thông tin từ session
		        Integer sendCount = (Integer) session.getAttribute("send");
		        Long lastSendTime = (Long) session.getAttribute("last_send_time");
				
		        if (sendCount == null) {
		            sendCount = 0;
		        }
		        
				// Kiểm tra số lần gửi OTP
		        if (sendCount >= 3) {
		        	
		        	// Kiểm tra xem đã qua thời gian hạn chế 1 giờ chưa
		            long now = System.currentTimeMillis();
		            
		            if (lastSendTime != null) {
		            	
		            	long diffInMillis = now - lastSendTime;
		                long oneHourInMillis = 60 * 60 * 1000; // 1 giờ
		                
		                if (diffInMillis < oneHourInMillis) {
		                    // Tính thời gian còn lại phải đợi
		                    long remainingMillis = oneHourInMillis - diffInMillis;
		                    int remainingMinutes = (int) (remainingMillis / (60 * 1000));
		                    int remainingSeconds = (int) ((remainingMillis % (60 * 1000)) / 1000);
		                    
		                    out.print("{\"success\": false, \"cooldown\": true, \"remainingMinutes\": " 
		                             + remainingMinutes + ", \"remainingSeconds\": " + remainingSeconds 
		                             + ", \"message\": \"Bạn đã gửi quá nhiều lần. Vui lòng đợi " 
		                             + remainingMinutes + " phút " + remainingSeconds + " giây trước khi gửi lại.\"}");
		                    out.flush();
		                    return;
		                }
		                
		                // Nếu đã qua 1 giờ, reset số lần gửi về 0
		                sendCount = 0;
		            }
		        }
		        // Cập nhật thông tin vào session
		        session.setAttribute("otp_code", otpCode);
		        session.setAttribute("send", sendCount + 1);
		        session.setAttribute("attempts", 0);
		        session.setAttribute("last_send_time", System.currentTimeMillis());
		        session.setAttribute("user_email", email);
		        
		        // Thiết lập thời gian tồn tại của session (ví dụ: 15 phút)
		        session.setMaxInactiveInterval(15 * 60);
		        
		        // Gửi OTP qua email
		        boolean sendResult = emailService.sendOtp(email, otpCode);
		        if (sendResult) {
		            out.print("{\"success\": true}");
		        } else {
		            out.print("{\"success\": false, \"message\": \"Không thể gửi mã OTP. Vui lòng thử lại sau.\"}");
		        }
		        out.flush();
		    } else {
		        // Xử lý request thông thường (nếu cần)
		    }
		}
	}
	
	// Phương thức kiểm tra số lần thử OTP có vượt quá giới hạn không
	public boolean isOtpAttemptsExceeded(HttpSession session, int maxAttempts) {
	    Integer attempts = (Integer) session.getAttribute("attempts");
	    if (attempts == null) {
	        return false;
	    }
	    return attempts >= maxAttempts;
	}

	// Phương thức xác thực OTP
	public boolean verifyOTP(HttpSession session, String submittedOtp) {
	    String storedOtp = (String) session.getAttribute("otp_code");
	    Long lastSendTime = (Long) session.getAttribute("last_send_time");
	    
	    if (storedOtp == null || lastSendTime == null) {
	        return false;
	    }
	    
	    long currentTime = System.currentTimeMillis();
	    long otpLifespan = 10 * 60 * 1000;
	    
	    if ((currentTime - lastSendTime) > otpLifespan) {
	        // OTP đã hết hạn
	        return false;
	    }
	    
	    // So sánh OTP được nhập với OTP lưu trong session
	    return storedOtp.equals(submittedOtp);
	}

	// Phương thức xóa OTP sau khi xác thực thành công
	public boolean deleteOTP(HttpSession session) {
	    try {
	        session.removeAttribute("otp_code");
	        session.removeAttribute("last_send_time");
	        session.removeAttribute("attempts");
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	// Phương thức cập nhật số lần thử OTP
	public void updateOtpAttempts(HttpSession session) {
	    Integer attempts = (Integer) session.getAttribute("attempts");
	    if (attempts == null) {
	        attempts = 0;
	    }
	    session.setAttribute("attempts", attempts + 1);
	}

}
