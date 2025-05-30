package vn.iotstar.dao;

import vn.iotstar.entity.Orders;
import java.util.List;
import vn.iotstar.entity.OrderItem;

public interface IOrderDao {

	boolean insertOrder(Orders order);

	Orders findProcessingOrderByUserId(int userId);

	boolean insertOrUpdateOrder(Orders order);

	boolean updateFinishedFee(int orderItemId, double finishedFee);

	boolean updateOrderStatus(int orderId, String newStatus);

	boolean updateDiscountAndFinishedFee(int orderItemId, int discountId, double finishedFee);

	boolean removeDiscountFromOrderItem(int orderItemId);
	
	List<Orders> getOrdersByUserId(int userId);
	
	public List<OrderItem> getAllOrderItems();

}
