package vn.iotstar.controller.User;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.service.IUserService;
import vn.iotstar.utils.Constant;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.entity.User;

import java.nio.file.Paths;
import java.sql.Date;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,  // 10 MB
	    maxRequestSize = 1024 * 1024 * 15 // 15 MB
	)
@WebServlet(urlPatterns = {"/user/InformationManagement"})
public class InformationManagementController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();
    
    @Override
    public void init() throws ServletException {
        Constant.initUploadDirectory(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int userId = 6;
        User user = userService.getUserById(userId);
        String birthdateStr = null;
        if (user != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthdateStr = dateFormat.format(user.getDateOfBirth());
            req.setAttribute("birthdate", birthdateStr);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/user/InformationManagement.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Không tìm thấy thông tin người dùng.");
            req.getRequestDispatcher("/views/user/InformationManagement.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String birthdate = req.getParameter("birthdate");
        String gender = req.getParameter("gender");
        
        
     
        if (isValidData(fullname, email, phone, address, birthdate, gender)) {
            int userId = 6;
            
            String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			
			
            Part filePart = req.getPart("profileImage");
            if (filePart.getSize() > 0) {
				String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				int index = filename.lastIndexOf(".");
				System.out.println (filename);
				String ext = filename.substring(index + 1);
				fname = System.currentTimeMillis() + "." + ext;

				filePart.write(uploadPath + fname);
				
				User user = userService.findById(userId);
				user.setFullname(fullname);
				user.setEmail(email);
				user.setPhoneNumber(phone);
				user.setAddress(address);
				try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                dateFormat.setLenient(false);
	                java.util.Date parsedDate = dateFormat.parse(birthdate);
	                user.setDateOfBirth(new Date(parsedDate.getTime()));
	            } catch (Exception e) {
	               
	            }
				user.setAvatarUrl(fname);
				user.setGender(gender);
				
				boolean isUpdated = userService.updateUserInformation(user);
				
				if (isUpdated) {
	                req.setAttribute("message", "Thông tin đã được cập nhật thành công!");
	                User updatedUser = userService.getUserById(userId);
	                if (updatedUser.getDateOfBirth() != null) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                    String birthdateStr = dateFormat.format(updatedUser.getDateOfBirth());
	                    req.setAttribute("birthdate", birthdateStr);
	                } else {
	                    req.setAttribute("birthdate", "");
	                }
	                req.setAttribute("user", updatedUser);
	            } else {
	                req.setAttribute("error", "Cập nhật thông tin không thành công. Vui lòng thử lại.");
	                User currentUser = userService.getUserById(userId);
	                if (currentUser.getDateOfBirth() != null) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                    String birthdateStr = dateFormat.format(currentUser.getDateOfBirth());
	                    req.setAttribute("birthdate", birthdateStr);
	                } else {
	                    req.setAttribute("birthdate", "");
	                }
	                req.setAttribute("user", currentUser);
	            }
			} 
			
            
        } else {
            req.setAttribute("error", "Thông tin không hợp lệ. Vui lòng kiểm tra lại.");
            User currentUser = userService.getUserById(1);
            if (currentUser.getDateOfBirth() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birthdateStr = dateFormat.format(currentUser.getDateOfBirth());
                req.setAttribute("birthdate", birthdateStr);
            } else {
                req.setAttribute("birthdate", "");
            }
            req.setAttribute("user", currentUser);
        }

        req.getRequestDispatcher("/views/user/InformationManagement.jsp").forward(req, resp);
        
    }

    private boolean isValidData(String fullname, String email, String phone, String address, String birthdate, String gender) {
        return fullname != null && !fullname.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") &&
               phone != null && !phone.trim().isEmpty() && phone.matches("\\d{10,11}") &&
               address != null && !address.trim().isEmpty() &&
               birthdate != null && !birthdate.trim().isEmpty() &&
               gender != null && !gender.trim().isEmpty();
    }
}