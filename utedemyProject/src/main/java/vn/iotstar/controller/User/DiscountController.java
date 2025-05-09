package vn.iotstar.controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Discount;
import vn.iotstar.entity.OrderItem;
import vn.iotstar.entity.Orders;
import vn.iotstar.entity.User;
import vn.iotstar.impl.service.DiscountService;
import vn.iotstar.impl.service.OrderService;

@WebServlet(urlPatterns = {"/apply-discount","/remove-discount"})
public class DiscountController  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DiscountService discount_service = new DiscountService();
	OrderService order_service = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User)session.getAttribute("account");
		  
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		
		String url = req.getRequestURI();
		if (url.contains("apply-discount")) {
			
			
        PrintWriter out = resp.getWriter();
        JsonObject jsonResponse = new JsonObject();
        
        try {
            // Lấy thông tin từ request
            String discountCode = req.getParameter("discountCode");
            int productId = Integer.parseInt(req.getParameter("productId"));
            
        	int amount_code_used=discount_service.countUsersByDiscountId(0);
        	int amount_code_require =Integer.parseInt(discount_service.getCodeAmountByDisCode(discountCode));
        	
        	int check_amount_code = amount_code_require - amount_code_used;
        	if(check_amount_code<=0) {
        	   jsonResponse.addProperty("success", false);
 	           jsonResponse.addProperty("message", "Có lỗi xảy ra hết số lượng mã có thể sử dụng: ");
 	           out.print(jsonResponse.toString());
        	   return;
        	}
            
            Orders order = (Orders) session.getAttribute("order");
            
            if (order == null) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Không tìm thấy đơn hàng.");
                out.print(jsonResponse.toString());
                return;
            }
            
            // Tìm OrderItem tương ứng với productId
            OrderItem targetItem = null;
            for (OrderItem item : order.getOrderItems()) {
                if (item.getCourse().getId() == productId) {
                    targetItem = item;
                    break;
                }
            }
            
            if (targetItem == null) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Không tìm thấy sản phẩm trong đơn hàng.");
                out.print(jsonResponse.toString());
                return;
            }
            OrderItem lastItem = (OrderItem) session.getAttribute("lastDiscountedItem");
           // targetItem_tmp = targetItem;
            
            // Kiểm tra mã giảm giá
            Discount discount = discount_service.findByDisCode(discountCode);
            
            if (discount == null) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Mã giảm giá không tồn tại.");
                out.print(jsonResponse.toString());
                return;
            }
            if (!discount_service.isCodeAvailable(discountCode)) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Mã giảm giá đã hết lượt sử dụng.");
                out.print(jsonResponse.toString());
                return;
            }

            // Kiểm tra hạn sử dụng
            if (!discount_service.isCodeValidTime(discountCode)) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Mã giảm giá đã hết hạn hoặc chưa đến thời gian áp dụng.");
                out.print(jsonResponse.toString());
                return;
            }
            //kiểm tra coi mã đó áp dụng đúng cho course không
            if (!discount_service.isDiscountApplicableForCourse(targetItem.getCourse().getId(), discount.getId())) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Mã giảm giá không áp dụng cho khóa học này.");
                out.print(jsonResponse.toString());
                return;
            }

            
            // Áp dụng mã giảm giá
            double originalPrice = targetItem.getCourse().getCoursePrice();
            double discountAmount = 0;
            
            if ("PERCENTAGE".equals(discount.getType())) {
                // Giảm giá theo phần trăm
                discountAmount = originalPrice *  Integer.parseInt(discount.getDecreasedFee()) / 100;
            } else if ("AMOUNTMONEY".equals(discount.getType())) {
                // Giảm giá cố định
                discountAmount = Double.parseDouble(discount.getDecreasedFee());
            }
            
            // Đảm bảo giá sau giảm không âm
            double finishedFee = Math.max(0, originalPrice - discountAmount);
            
            // Cập nhật thông tin cho OrderItem
            targetItem.setDiscount(discount);
            targetItem.setFinishedFee(finishedFee);
            
            //cập nhật database
            order_service.updateDiscountAndFinishedFee(targetItem.getId(),discount.getId(),finishedFee);
            
            // Cập nhật session
            session.setAttribute("order", order);
            
            // Tính toán tổng cộng đơn hàng
            double subtotalAmount = 0;
            double totalDiscountAmount = 0;
            
            for (OrderItem item : order.getOrderItems()) {
                subtotalAmount += item.getCourse().getCoursePrice();
                
                if (item.getDiscount() != null) {
                    totalDiscountAmount += (item.getCourse().getCoursePrice() - item.getFinishedFee());
                }
            }
            
            double totalOrderAmount = subtotalAmount - totalDiscountAmount;
            
            // Định dạng các giá trị tiền tệ
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            currencyFormatter.setMaximumFractionDigits(0);
            String formattedDiscountAmount = currencyFormatter.format(discountAmount).replace("₫", "đ");
            String formattedFinishedFee = currencyFormatter.format(finishedFee).replace("₫", "đ");
            String formattedOriginalPrice = currencyFormatter.format(originalPrice).replace("₫", "đ");
            String formattedSubtotal = currencyFormatter.format(subtotalAmount).replace("₫", "đ");
            String formattedTotalDiscountAmount = currencyFormatter.format(totalDiscountAmount).replace("₫", "đ");
            String formattedTotalOrderAmount = currencyFormatter.format(totalOrderAmount).replace("₫", "đ");
            
           
            
            // Tạo phản hồi JSON
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("discountType", discount.getType());
            jsonResponse.addProperty("discountValue", discount.getDecreasedFee());
            jsonResponse.addProperty("formattedDiscountAmount", formattedDiscountAmount);
            jsonResponse.addProperty("formattedFinishedFee", formattedFinishedFee);
            jsonResponse.addProperty("formattedOriginalPrice", formattedOriginalPrice);
            jsonResponse.addProperty("formattedSubtotal", formattedSubtotal);
            jsonResponse.addProperty("formattedTotalDiscountAmount", formattedTotalDiscountAmount);
            jsonResponse.addProperty("formattedTotalOrderAmount", formattedTotalOrderAmount);
            jsonResponse.addProperty("totalDiscountAmount", totalDiscountAmount);
            jsonResponse.addProperty("totalOrderAmount", totalOrderAmount);
            
            session.setAttribute("lastDiscountedItem", targetItem);
            boolean check_discount_user = discount_service.updateUserDiscount(u.getId(), discount.getId());
            if(check_discount_user) {
             	System.out.println("Đã update discount dựa theo user");
             }
            
            //cập nhật user_discount và coursse discount, cập nhật số lượng mã trong database
            // if()
            /* if (lastItem != null && lastItem.getCourse().getId() == productId) {
                 // Đây là đúng OrderItem cũ
                 System.out.println("Đúng item cũ rồi!");
                 boolean check_course_discount = discount_service.updateDiscountCourse(productId, discount.getId());
                 if(check_course_discount) {
                 	System.out.println("Đã update discount dựa theo course");
                 }
                 boolean check_discount_user = discount_service.updateUserDiscount(u.getId(), discount.getId());
                 if(check_discount_user) {
                 	System.out.println("Đã update discount dựa theo user");
                 }
                 
                 if(discount.getId() != lastItem.getDiscount().getId()) {
                 	
                 	String code_amount = discount_service.getCodeAmountByDiscountId(discount.getId());
                     int code_amount_int = Integer.parseInt(code_amount);
                     code_amount_int = code_amount_int -1;
                     boolean check_updated_amount = discount_service.updateCodeAmountByDiscountId(discount.getId(), Integer.toString(code_amount_int));
                     if(check_updated_amount) {
                     	System.out.println("Đã update số lượng của course11111");
                     }
                     
                     int code_amount_int_old = Integer.parseInt(lastItem.getDiscount().getCodeAmount());
                     System.out.println("Đã update số lượng của course11111aaa");
                     code_amount_int = code_amount_int + 1;
                     boolean check_updated_amount_old = discount_service.updateCodeAmountByDiscountId(lastItem.getDiscount().getId(), Integer.toString(code_amount_int_old));
                     System.out.println("Đã update số lượng của course11111bbbbb");
                     if(check_updated_amount_old) {
                     	System.out.println("Đã update số lượng của course cũ");
                     }
                 	
                 }                
             }
             else {
             	 boolean check_course_discount = discount_service.addDiscountCourse(productId, discount.getId());
                  if(check_course_discount) {
                  	System.out.println("Đã thêm course vào discoun23t");
                  }
                  boolean check_discount_user = discount_service.addUserDiscount(u.getId(), discount.getId());
                  if(check_discount_user) {
                  	System.out.println("Đã thêm user sử dụng discount23");
                  }
                  String code_amount = discount_service.getCodeAmountByDiscountId(discount.getId());
                  int code_amount_int = Integer.parseInt(code_amount);
                  code_amount_int = code_amount_int - 1;
                  boolean check_updated_amount = discount_service.updateCodeAmountByDiscountId(discount.getId(), Integer.toString(code_amount_int));
                  if(check_updated_amount) {
                  	System.out.println("Đã update số lượng của course23");
                  }
             }
            */
            
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }
        
        out.print(jsonResponse.toString());
        return;
    }
		if (url.contains("remove-discount")) {
	        
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        PrintWriter out = resp.getWriter();
	        JsonObject jsonResponse = new JsonObject();
	        
	        try {
	            // Lấy thông tin từ request
	            int productId = Integer.parseInt(req.getParameter("productId"));
	            int orderId = Integer.parseInt(req.getParameter("orderId"));
	            
	            Orders order = (Orders) session.getAttribute("order");
	            
	            if (order == null) {
	                jsonResponse.addProperty("success", false);
	                jsonResponse.addProperty("message", "Không tìm thấy đơn hàng.");
	                out.print(jsonResponse.toString());
	                return;
	            }
	            
	            // Tìm OrderItem tương ứng với productId
	            OrderItem targetItem = null;
	            for (OrderItem item : order.getOrderItems()) {
	                if (item.getCourse().getId() == productId) {
	                    targetItem = item;
	                    break;
	                }
	            }
	            
	            if (targetItem == null) {
	                jsonResponse.addProperty("success", false);
	                jsonResponse.addProperty("message", "Không tìm thấy sản phẩm trong đơn hàng.");
	                out.print(jsonResponse.toString());
	                return;
	            }
	            
	            // Kiểm tra xem có mã giảm giá đang áp dụng không
	            if (targetItem.getDiscount() == null) {
	                jsonResponse.addProperty("success", false);
	                jsonResponse.addProperty("message", "Sản phẩm không có mã giảm giá để hủy.");
	                out.print(jsonResponse.toString());
	                return;
	            }
	            
	            // Lưu thông tin mã giảm giá trước khi xóa (nếu cần hoàn lại số lượng)
	            Discount removedDiscount = targetItem.getDiscount();
	            
	            // Đặt lại giá về giá gốc
	            double originalPrice = targetItem.getCourse().getCoursePrice();
	            targetItem.setFinishedFee(originalPrice);
	            
	            // Có thể cần phải cập nhật lại user_discount nếu đã lưu vào database
	            discount_service.deleteUserDiscount(u.getId(), targetItem.getDiscount().getId());
	            // Cập nhật database: xóa discount_id và đặt lại finishedFee	            
	            order_service.removeDiscountFromOrderItem(targetItem.getId());
	            
	            // Có thể cần phải hoàn lại số lượng mã giảm giá
	            // discount_service.increaseCodeAmount(removedDiscount.getId());
	            
	            // Cập nhật session
	            session.setAttribute("order", order);
	            
	            // Tính toán tổng cộng đơn hàng mới
	            double subtotalAmount = 0;
	            double totalDiscountAmount = 0;
	            
	            for (OrderItem item : order.getOrderItems()) {
	                subtotalAmount += item.getCourse().getCoursePrice();
	                
	                if (item.getDiscount() != null) {
	                    totalDiscountAmount += (item.getCourse().getCoursePrice() - item.getFinishedFee());
	                }
	            }
	            
	            double totalOrderAmount = subtotalAmount - totalDiscountAmount;
	            
	            // Định dạng các giá trị tiền tệ
	            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	            currencyFormatter.setMaximumFractionDigits(0);
	            String formattedOriginalPrice = currencyFormatter.format(originalPrice).replace("₫", "đ");
	            String formattedSubtotal = currencyFormatter.format(subtotalAmount).replace("₫", "đ");
	            String formattedTotalDiscountAmount = currencyFormatter.format(totalDiscountAmount).replace("₫", "đ");
	            String formattedTotalOrderAmount = currencyFormatter.format(totalOrderAmount).replace("₫", "đ");
	            
	            // Tạo phản hồi JSON
	            jsonResponse.addProperty("success", true);
	            jsonResponse.addProperty("originalPrice", originalPrice);
	            jsonResponse.addProperty("formattedOriginalPrice", formattedOriginalPrice);
	            jsonResponse.addProperty("formattedSubtotal", formattedSubtotal);
	            jsonResponse.addProperty("formattedTotalDiscountAmount", formattedTotalDiscountAmount);
	            jsonResponse.addProperty("formattedTotalOrderAmount", formattedTotalOrderAmount);
	            jsonResponse.addProperty("totalDiscountAmount", totalDiscountAmount);
	            jsonResponse.addProperty("totalOrderAmount", totalOrderAmount);
	            
	            // Có thể cần phải cập nhật lại user_discount nếu đã lưu vào database
	         
	            
	        } catch (Exception e) {
	            jsonResponse.addProperty("success", false);
	            jsonResponse.addProperty("message", "Có lỗi xảy ra khi hủy mã giảm giá: " + e.getMessage());
	            e.printStackTrace();
	        }
	        
	        out.print(jsonResponse.toString());
		}
	}
}
