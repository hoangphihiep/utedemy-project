package vn.iotstar.controller.User;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.Course;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Orders;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.CartService;
import vn.iotstar.impl.service.CourseService;
import vn.iotstar.impl.service.OrderService;
import vn.iotstar.service.ICartService;
import vn.iotstar.service.ICourseService;
import vn.iotstar.service.IOrderService;

@WebServlet(urlPatterns = {"/user/viewcheckout"})
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICourseService course_service = new CourseService();
	IOrderService order_service = new OrderService();
	ICartService cart_service = new CartService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("account");
		
		  String url = req.getRequestURI();
		   if (url.contains("viewcheckout")) {
			   // Get selected courses from form
		        String[] selectedCourseIds =  req.getParameterValues("selectedCourses");
		        if (selectedCourseIds == null || selectedCourseIds.length == 0) {
		        	req.setAttribute("errorMessage", "No courses selected for checkout");
		          //  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/cart.jsp");
		        //    dispatcher.forward(request, response);
		        	//gửi sang check out thông báo chưa chọn gì hết
		            return;
		        }
		        Orders order = new Orders();
		        order.setOrderDate(new Date(System.currentTimeMillis()));
		        order.setOrderStatus("PENDING");
		        order.setUser(user); // user lấy từ session hoặc DB
		        
		        Set<OrderItem> orderItems = new HashSet<>();

		        for (String courseIdStr : selectedCourseIds) {
		            int courseId = Integer.parseInt(courseIdStr);

		            // Lấy course từ DB
		            Course course = course_service.findByIdCourse(courseId);
		            if (course != null) {
		                // Tạo OrderItem mới
		                OrderItem orderItem = new OrderItem();
		                orderItem.setCourse(course); // Gán course vào OrderItem
		                orderItem.setFinishedFee(course.getCoursePrice());
		                orderItem.setOrder(order);

		                // Thêm vào set orderItems
		                orderItems.add(orderItem);
		            }
		        }

		        // Gán set orderItems cho order
		        order.setOrderItems(orderItems);

		        boolean insertSuccess = order_service.insertOrder(order);
		        //nếu tạo order với các course được checked ở phía cart thì xóa các courses đó khỏi cart
		        if (insertSuccess) {
		            // Lấy Cart của user
		            Cart cart = cart_service.findByUserId(user.getId());
		            if (cart != null) {
		                // Chuyển selectedCourseIds thành List<Integer> để xóa
		                List<Integer> selectedCourseIdsList = Arrays.asList(selectedCourseIds).stream()
		                                                            .map(Integer::parseInt)
		                                                            .collect(Collectors.toList());

		                // Gọi hàm xóa courses khỏi Cart
		                boolean deleteSuccess = cart_service.deleteSelectedCourses(cart, selectedCourseIdsList);
		                if (deleteSuccess) {
		                    System.out.println("Courses successfully removed from the cart.");
		                } else {
		                    System.out.println("Failed to remove courses from the cart.");
		                }
		            }
		        }
		        
		        // Lưu order vào request attribute
		        req.setAttribute("order", order);
		        req.getRequestDispatcher("/views/user/checkout.jsp").forward(req, resp);
		    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
				super.doGet(req, resp);
	}

}
