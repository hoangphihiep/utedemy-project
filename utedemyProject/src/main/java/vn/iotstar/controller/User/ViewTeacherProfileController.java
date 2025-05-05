package vn.iotstar.controller.User;

import vn.iotstar.entity.User;
import vn.iotstar.impl.service.UserService;
import vn.iotstar.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewTeacherProfile")
public class ViewTeacherProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        System.out.println("ViewTeacherProfileController initialized. userService: " + userService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet method called. Request URL: " + request.getRequestURL());
        System.out.println("All request parameters: " + request.getParameterMap());

        // Kiểm tra tham số "id" trong request
        String userIdStr = request.getParameter("id");
        System.out.println("userIdStr from request: " + userIdStr);

        // Nếu không có tham số id hoặc id không hợp lệ, gán id mặc định là 1
        if (userIdStr == null || userIdStr.isEmpty()) {
            userIdStr = "1"; // Gán id mặc định là 1 nếu không có giá trị
        }

        try {
            int userId = Integer.parseInt(userIdStr);
            System.out.println("Parsed userId: " + userId);

            // Tìm giáo viên theo userId
            User teacher = userService.findTeacherById(userId);
            System.out.println("Teacher retrieved: " + (teacher != null ? "ID: " + teacher.getId() + ", Fullname: " + teacher.getFullname() : "null"));

            if (teacher == null) {
                System.out.println("Teacher not found for userId: " + userId + ". Redirecting to error page.");
                response.sendRedirect(request.getContextPath() + "/error");
                return;
            }

            // Nếu tìm thấy giáo viên, đưa thông tin vào request
            request.setAttribute("teacher", teacher);
            System.out.println("Forwarding to JSP: /views/user/viewTeacherProfile.jsp");
            request.getRequestDispatcher("/views/user/viewTeacherProfile.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Nếu id không hợp lệ, redirect về trang lỗi
            System.out.println("Error parsing userIdStr to int: " + userIdStr);
            System.out.println("Exception: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error");
        } catch (Exception e) {
            // Bắt tất cả các lỗi khác
            System.out.println("Unexpected error in doGet: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}