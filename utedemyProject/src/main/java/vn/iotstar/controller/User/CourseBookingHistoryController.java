package vn.iotstar.controller.User;

import vn.iotstar.entity.Course;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Orders; 
import vn.iotstar.dao.IOrderDao;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/user/courseBookingHistory")
public class CourseBookingHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Debug: In ra ID người dùng từ session
        System.out.println("User ID from session: " + user.getId());

        int userId = user.getId();
        List<Orders> orders = orderService.getOrdersByUserId(userId);


     // Debug: In ra danh sách các đơn hàng
        System.out.println("Number of orders retrieved: " + orders.size());

     // Lặp qua từng order để lấy danh sách OrderItem và thông tin khóa học
        for (Orders order : orders) {
            Set<OrderItem> orderItems = order.getOrderItems(); // Get all OrderItems for this order

            // Debug: In thông tin của các OrderItem
            System.out.println("Processing order ID: " + order.getId() + " with " + orderItems.size() + " items.");

            for (OrderItem item : orderItems) {
                Course course = item.getCourse(); // Lấy khóa học từ OrderItem
                double finishedFee = item.getFinishedFee(); // Lấy giá khóa học

                // Kiểm tra và áp dụng discount nếu có
                if (item.getDiscount() != null) {
                    double discountPercentage = Double.parseDouble(item.getDiscount().getDecreasedFee().replace("%", "")); // Lấy tỷ lệ giảm giá
                    finishedFee = finishedFee - (finishedFee * discountPercentage / 100); // Áp dụng discount
                }

             // Debug: In thông tin khóa học và giá
                System.out.println("Course ID: " + course.getId() + " | Course Name: " + course.getCourseName() + " | Finished Fee: " + finishedFee);

                // Thêm tên khóa học và giá vào OrderItem (hoặc Order nếu bạn muốn)
                item.setCourse(course);
                item.setFinishedFee(finishedFee);
            }
        }
     // Debug: Trước khi forward request đến JSP
        System.out.println("Forwarding to CourseBookingHistory.jsp with " + orders.size() + " orders.");

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/user/CourseBookingHistory.jsp").forward(request, response);
    }
}