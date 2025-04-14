package vn.iotstar.controller.User;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int userId = 1; 
        User user = userService.getUserById(userId);

        if (user != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birthdateStr = user.getDateOfBirth() != null ? 
                dateFormat.format(user.getDateOfBirth()) : "";

            req.setAttribute("birthdate", birthdateStr);
            req.setAttribute("user", user);
        } else {
            req.setAttribute("error", "Không tìm thấy thông tin người dùng.");
        }

        req.getRequestDispatcher("/views/user/InformationManagement.jsp").forward(req, resp);
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
        Part filePart = req.getPart("profileImage");

        int userId = 1; 
        User currentUser = userService.getUserById(userId);

        if (currentUser == null) {
            req.setAttribute("error", "Người dùng không tồn tại");
            req.getRequestDispatcher("/views/user/InformationManagement.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra dữ liệu và lấy danh sách lỗi
        List<String> errors = validateData(fullname, email, phone, address, birthdate, gender);

        if (errors.isEmpty()) {
            try {
                // Cập nhật thông tin cơ bản
                currentUser.setFullname(fullname);
                currentUser.setEmail(email);
                currentUser.setPhoneNumber(phone);
                currentUser.setAddress(address);
                currentUser.setGender(gender);

                // Xử lý ngày sinh
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                java.util.Date parsedDate = dateFormat.parse(birthdate);
                currentUser.setDateOfBirth(new Date(parsedDate.getTime()));

                // Xử lý ảnh đại diện
                if (filePart != null && filePart.getSize() > 0) {
                    // Tạo thư mục nếu chưa tồn tại
                    String uploadPath = Constant.DIR;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdir();

                    // Xóa ảnh cũ nếu có
                    if (currentUser.getAvatarUrl() != null && !currentUser.getAvatarUrl().isEmpty()) {
                        File oldFile = new File(uploadPath + File.separator + currentUser.getAvatarUrl());
                        if (oldFile.exists()) oldFile.delete();
                    }

                    // Lưu ảnh mới
                    String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String ext = filename.substring(filename.lastIndexOf(".") + 1);
                    String newFilename = System.currentTimeMillis() + "." + ext;

                    filePart.write(uploadPath + File.separator + newFilename);
                    currentUser.setAvatarUrl(newFilename);
                }

                // Cập nhật vào CSDL
                boolean isUpdated = userService.updateUserInformation(currentUser);

                if (isUpdated) {
                    req.setAttribute("message", "Thông tin đã được cập nhật thành công!");
                    // Format lại ngày sinh để hiển thị
                    String birthdateStr = dateFormat.format(currentUser.getDateOfBirth());
                    req.setAttribute("birthdate", birthdateStr);
                } else {
                    req.setAttribute("error", "Cập nhật thông tin không thành công");
                }

            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Lỗi khi xử lý: " + e.getMessage());
            }
        } else {
            // Truyền danh sách lỗi đến JSP
            req.setAttribute("errors", errors);
        }

        // Luôn trả về thông tin user mới nhất
        User updatedUser = userService.getUserById(userId);
        if (updatedUser.getDateOfBirth() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birthdateStr = dateFormat.format(updatedUser.getDateOfBirth());
            req.setAttribute("birthdate", birthdateStr);
        }
        req.setAttribute("user", updatedUser);

        req.getRequestDispatcher("/views/user/InformationManagement.jsp").forward(req, resp);
    }

    private List<String> validateData(String fullname, String email, String phone, 
            String address, String birthdate, String gender) {
		List<String> errors = new ArrayList<>();

		// Kiểm tra các trường bắt buộc
		if (fullname == null || fullname.trim().isEmpty()) {
		errors.add("Họ tên không được để trống.");
		}
		if (email == null || email.trim().isEmpty()) {
		errors.add("Email không được để trống.");
		}
		if (phone == null || phone.trim().isEmpty()) {
		errors.add("Số điện thoại không được để trống.");
		}
		if (address == null || address.trim().isEmpty()) {
		errors.add("Địa chỉ không được để trống.");
		}
		if (birthdate == null || birthdate.trim().isEmpty()) {
		errors.add("Ngày sinh không được để trống.");
		}
		if (gender == null || gender.trim().isEmpty()) {
		errors.add("Giới tính không được để trống.");
		}

		// Validate email format
		if (email != null && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+(com|org|net|edu|gov|mil|biz|info|vn)$")) {
		errors.add("Email không đúng định dạng (ví dụ: example@domain.com).");
		}

		// Validate phone number (phải đúng 10 số)
		if (phone != null && !phone.matches("\\d{10}")) {
		errors.add("Số điện thoại phải đúng 10 số.");
		}

		return errors;
		}
}