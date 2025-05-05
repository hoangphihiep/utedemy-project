package vn.iotstar.service;

import vn.iotstar.entity.Orders;
import java.util.List;

public interface IOrderService {

	boolean insertOrder(Orders order);

	Orders findProcessingOrderByUserId(int userId);

	boolean insertOrUpdateOrder(Orders order);
	
	boolean updateFinishedFee(int orderItemId, double finishedFee);

	boolean updateOrderStatus(int orderId, String newStatus);
	
	boolean updateDiscountAndFinishedFee(int orderItemId, int discountId, double finishedFee);

	boolean removeDiscountFromOrderItem(int orderItemId);
	
	List<Orders> getOrdersByUserId(int userId);

}
