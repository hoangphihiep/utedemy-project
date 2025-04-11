package vn.iotstar.controller.User;

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
import vn.iotstar.impl.service.UserService;
import vn.iotstar.entity.User;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/user/InformationManagement"})
public class InformationManagementController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int userId = 1;
        User user = userService.getUserById(userId);

        if (user != null) {
            if (user.getDateOfBirth() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birthdateStr = dateFormat.format(user.getDateOfBirth());
                req.setAttribute("birthdate", birthdateStr);
            } else {
                req.setAttribute("birthdate", "");
            }
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

        Part filePart = req.getPart("profileImage"); // "profileImage" matches the file input name

        
        String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        System.out.print(filename);
        
     
        if (isValidData(fullname, email, phone, address, birthdate, gender)) {
            int userId = 1;
            boolean isUpdated = userService.updateUserInformation(userId, fullname, email, phone, address, birthdate, gender);

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