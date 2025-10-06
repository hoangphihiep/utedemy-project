package vn.iotstar.controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import org.json.JSONObject;

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
	
	// Validation patterns
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
	);
	private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9}$");
	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ỹ\\s]{2,100}$");
	private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
		".*(['\";]|--|/\\*|\\*/|xp_|sp_|\\bOR\\b|\\bAND\\b|\\bUNION\\b|\\bSELECT\\b|\\bDROP\\b|\\bINSERT\\b|\\bUPDATE\\b|\\bDELETE\\b).*",
		Pattern.CASE_INSENSITIVE
	);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String path = req.getServletPath();
		if(path.contains("register")) {
			
			
			req.getRequestDispatcher("/views/user/registerpage.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		// Kiểm tra xem có phải AJAX request không
		boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
		
		IUserService service = new UserService();
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		
		if (path.contains("register")) {
			
			// Lấy và trim parameters
			String fullname = req.getParameter("fullname");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String confirmPassword = req.getParameter("confirmPassword");
			
			// LOG để debug
			System.out.println("=== Registration Request ===");
			System.out.println("Fullname: " + fullname);
			System.out.println("Email: " + email);
			System.out.println("Phone: " + phone);
			
			// KIỂM TRA NULL/EMPTY
			if (fullname == null || fullname.trim().isEmpty() ||
				password == null || password.isEmpty() ||
				email == null || email.trim().isEmpty() ||
				phone == null || phone.trim().isEmpty() ||
				confirmPassword == null || confirmPassword.isEmpty()) {
				
				sendErrorResponse(resp, isAjax, 400, "Vui lòng điền đầy đủ thông tin", session, req);
				return;
			}
			
			// Trim inputs
			fullname = fullname.trim();
			email = email.trim();
			phone = phone.trim().replaceAll("\\s", "");
			
			// KIỂM TRA SQL INJECTION - DEBUG VERSION
			System.out.println("--- SQL Injection Check ---");
			System.out.println("Fullname: [" + fullname + "] -> Contains SQL: " + containsSqlInjection(fullname));
			System.out.println("Email: [" + email + "] -> Contains SQL: " + containsSqlInjection(email));
			System.out.println("Phone: [" + phone + "] -> Contains SQL: " + containsSqlInjection(phone));
			System.out.println("Password: [" + password + "] -> Contains SQL: " + containsSqlInjection(password));
			System.out.println("ConfirmPassword: [" + confirmPassword + "] -> Contains SQL: " + containsSqlInjection(confirmPassword));
			
			if (containsSqlInjection(fullname)) {
				System.out.println("❌ SQL Injection BLOCKED in fullname: " + fullname);
				sendErrorResponse(resp, isAjax, 400, "Họ tên chứa ký tự không hợp lệ", session, req);
				return;
			}
			
			if (containsSqlInjection(email)) {
				System.out.println("❌ SQL Injection BLOCKED in email: " + email);
				sendErrorResponse(resp, isAjax, 400, "Email chứa ký tự không hợp lệ", session, req);
				return;
			}
			
			if (containsSqlInjection(phone)) {
				System.out.println("❌ SQL Injection BLOCKED in phone: " + phone);
				sendErrorResponse(resp, isAjax, 400, "Số điện thoại chứa ký tự không hợp lệ", session, req);
				return;
			}
			
			if (containsSqlInjection(password)) {
				System.out.println("❌ SQL Injection BLOCKED in password: " + password);
				sendErrorResponse(resp, isAjax, 400, "Mật khẩu chứa ký tự không hợp lệ", session, req);
				return;
			}
			
			if (containsSqlInjection(confirmPassword)) {
				System.out.println("❌ SQL Injection BLOCKED in confirmPassword: " + confirmPassword);
				sendErrorResponse(resp, isAjax, 400, "Mật khẩu xác nhận chứa ký tự không hợp lệ", session, req);
				return;
			}
			
			System.out.println("✅ All SQL injection checks passed");
			
			// KIỂM TRA LENGTH
			if (fullname.length() > 100 || email.length() > 100 || 
				phone.length() > 15 || password.length() > 100) {
				sendErrorResponse(resp, isAjax, 400, "Dữ liệu quá dài", session, req);
				return;
			}
			
			// KIỂM TRA FORMAT
			if (!NAME_PATTERN.matcher(fullname).matches()) {
				sendErrorResponse(resp, isAjax, 422, "Họ tên chỉ được chứa chữ cái và khoảng trắng", session, req);
				return;
			}
			
			if (!EMAIL_PATTERN.matcher(email).matches()) {
				sendErrorResponse(resp, isAjax, 422, "Email không đúng định dạng", session, req);
				return;
			}
			
			if (!PHONE_PATTERN.matcher(phone).matches()) {
				sendErrorResponse(resp, isAjax, 422, "Số điện thoại không đúng định dạng (10 số, bắt đầu bằng 0)", session, req);
				return;
			}
			
			// KIỂM TRA PASSWORD
			if (!service.checkPasswordAndConfirmPassword(password, confirmPassword)) {
				sendErrorResponse(resp, isAjax, 422, "Mật khẩu và xác nhận mật khẩu không khớp", session, req);
				return;
			}
			
			if (!service.checkPasswordFormat(password)) {
				sendErrorResponse(resp, isAjax, 422, "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt", session, req);
				return;
			}
			
			// KIỂM TRA TỒN TẠI
			if (service.checkExistEmail(email)) {
				sendErrorResponse(resp, isAjax, 409, "Email đã được đăng ký", session, req);
				return;
			}
			
			if (service.checkExistPhoneNumber(phone)) {
				sendErrorResponse(resp, isAjax, 409, "Số điện thoại đã được đăng ký", session, req);
				return;
			}
			
			// SUCCESS - Lưu session và gửi OTP
			session.setAttribute("fullname", fullname);
			session.setAttribute("password", password);
			session.setAttribute("email", email);
			session.setAttribute("phone", phone);
			session.setAttribute("confirmPassword", confirmPassword);
			
			String otpCode = emailService.generateOTP();
			session.setAttribute("otp_code", otpCode);
			session.setAttribute("send", 1);
			session.setAttribute("attempts", 0);
			session.setAttribute("last_send_time", System.currentTimeMillis());
			session.setMaxInactiveInterval(900);
			
			if (emailService.sendOtp(email, otpCode)) {
				System.out.println("✅ Gửi OTP thành công đến: " + email);
			}
			
			// Response khác nhau cho AJAX và normal request
			if (isAjax) {
				resp.setContentType("application/json");
				resp.setStatus(HttpServletResponse.SC_OK);
				PrintWriter out = resp.getWriter();
				JSONObject json = new JSONObject();
				json.put("success", true);
				json.put("redirectUrl", "/utedemyProject/views/user/verifyOTP.jsp");
				out.print(json.toString());
				out.flush();
			} else {
				req.setAttribute("email", email);
				req.getRequestDispatcher("/views/user/verifyOTP.jsp").forward(req, resp);
			}
		}
	}
	
	/**
	 * Helper method để gửi error response
	 */
	private void sendErrorResponse(HttpServletResponse resp, boolean isAjax, 
			int statusCode, String message, HttpSession session, HttpServletRequest req) 
			throws IOException, ServletException {
		
		System.out.println("=== Sending Error Response ===");
		System.out.println("Status Code: " + statusCode);
		System.out.println("Message: " + message);
		System.out.println("Is AJAX: " + isAjax);
		
		// CRITICAL: Đặt status code TRƯỚC khi write response
		resp.setStatus(statusCode);
		resp.setCharacterEncoding("UTF-8");
		
		if (isAjax) {
			// AJAX request - trả về JSON
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("error", message);
			json.put("statusCode", statusCode);
			out.print(json.toString());
			out.flush();
			System.out.println("Response sent as JSON");
		} else {
			// THAY ĐỔI: Trả về HTML error page trực tiếp thay vì forward
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			out.println("<!DOCTYPE html>");
			out.println("<html lang='vi'>");
			out.println("<head>");
			out.println("    <meta charset='UTF-8'>");
			out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
			out.println("    <title>Lỗi đăng ký</title>");
			out.println("    <style>");
			out.println("        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }");
			out.println("        .error-container { max-width: 600px; margin: 50px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
			out.println("        .error-title { color: #d32f2f; font-size: 24px; margin-bottom: 20px; }");
			out.println("        .error-message { color: #555; font-size: 16px; margin-bottom: 20px; padding: 15px; background: #ffebee; border-left: 4px solid #d32f2f; }");
			out.println("        .status-code { color: #999; font-size: 14px; margin-bottom: 20px; }");
			out.println("        .back-button { display: inline-block; padding: 10px 20px; background: #2196F3; color: white; text-decoration: none; border-radius: 4px; }");
			out.println("        .back-button:hover { background: #1976D2; }");
			out.println("    </style>");
			out.println("</head>");
			out.println("<body>");
			out.println("    <div class='error-container'>");
			out.println("        <div class='error-title'>❌ Lỗi đăng ký</div>");
			out.println("        <div class='error-message'>" + escapeHtml(message) + "</div>");
			out.println("        <div class='status-code'>HTTP Status: " + statusCode + "</div>");
			out.println("        <a href='" + req.getContextPath() + "/user/register' class='back-button'>← Quay lại trang đăng ký</a>");
			out.println("    </div>");
			out.println("</body>");
			out.println("</html>");
			
			out.flush();
			System.out.println("Response sent as HTML error page");
		}
		
		System.out.println("=== Error Response Sent ===");
	}
	
	/**
	 * Escape HTML để tránh XSS
	 */
	private String escapeHtml(String input) {
		if (input == null) return "";
		return input.replace("&", "&amp;")
		           .replace("<", "&lt;")
		           .replace(">", "&gt;")
		           .replace("\"", "&quot;")
		           .replace("'", "&#x27;");
	}
	
	/**
	 * Kiểm tra SQL Injection patterns - IMPROVED VERSION
	 */
	private boolean containsSqlInjection(String input) {
		if (input == null || input.isEmpty()) {
			return false;
		}
		
		// Test từng pattern riêng để debug
		boolean hasQuote = input.contains("'");
		boolean hasDoubleQuote = input.contains("\"");
		boolean hasSemicolon = input.contains(";");
		boolean hasDoubleDash = input.contains("--");
		boolean hasSlashStar = input.contains("/*");
		boolean hasStarSlash = input.contains("*/");
		
		// Check SQL keywords (case insensitive)
		String upperInput = input.toUpperCase();
		boolean hasOR = upperInput.matches(".*\\bOR\\b.*");
		boolean hasAND = upperInput.matches(".*\\bAND\\b.*");
		boolean hasUNION = upperInput.matches(".*\\bUNION\\b.*");
		boolean hasSELECT = upperInput.matches(".*\\bSELECT\\b.*");
		boolean hasDROP = upperInput.matches(".*\\bDROP\\b.*");
		boolean hasINSERT = upperInput.matches(".*\\bINSERT\\b.*");
		boolean hasUPDATE = upperInput.matches(".*\\bUPDATE\\b.*");
		boolean hasDELETE = upperInput.matches(".*\\bDELETE\\b.*");
		boolean hasXP = upperInput.contains("XP_");
		boolean hasSP = upperInput.contains("SP_");
		
		if (hasQuote || hasDoubleQuote || hasSemicolon || hasDoubleDash || 
		    hasSlashStar || hasStarSlash || hasOR || hasAND || hasUNION || 
		    hasSELECT || hasDROP || hasINSERT || hasUPDATE || hasDELETE || 
		    hasXP || hasSP) {
			
			System.out.println("    SQL Injection pattern detected:");
			if (hasQuote) System.out.println("    - Contains single quote (')");
			if (hasDoubleQuote) System.out.println("    - Contains double quote (\")");
			if (hasSemicolon) System.out.println("    - Contains semicolon (;)");
			if (hasDoubleDash) System.out.println("    - Contains double dash (--)");
			if (hasSlashStar) System.out.println("    - Contains /*");
			if (hasStarSlash) System.out.println("    - Contains */");
			if (hasOR) System.out.println("    - Contains OR keyword");
			if (hasAND) System.out.println("    - Contains AND keyword");
			if (hasUNION) System.out.println("    - Contains UNION keyword");
			if (hasSELECT) System.out.println("    - Contains SELECT keyword");
			if (hasDROP) System.out.println("    - Contains DROP keyword");
			if (hasINSERT) System.out.println("    - Contains INSERT keyword");
			if (hasUPDATE) System.out.println("    - Contains UPDATE keyword");
			if (hasDELETE) System.out.println("    - Contains DELETE keyword");
			
			return true;
		}
		
		return false;
	}
}