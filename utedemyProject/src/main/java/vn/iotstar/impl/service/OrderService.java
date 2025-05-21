package vn.iotstar.impl.service;

import vn.iotstar.dao.IOrderDao;
import vn.iotstar.entity.Orders;
import vn.iotstar.impl.dao.OrderDao;
import java.util.List;
import vn.iotstar.service.IOrderService;
import vn.iotstar.entity.OrderItem;

public class OrderService implements IOrderService {
    IOrderDao orderDao = new OrderDao();
    
    @Override
    public boolean insertOrder(Orders order) {
    	return orderDao.insertOrder(order);
    }
    @Override
	public Orders findProcessingOrderByUserId(int userId) {
		return orderDao.findProcessingOrderByUserId(userId);
	}

    @Override
	public boolean insertOrUpdateOrder(Orders order) {
		return orderDao.insertOrUpdateOrder(order);
	}
    @Override
    public boolean updateFinishedFee(int orderItemId, double finishedFee){
    	return orderDao.updateFinishedFee(orderItemId, finishedFee);
    }
    
    @Override
	public boolean updateOrderStatus(int orderId, String newStatus) {
		return orderDao.updateOrderStatus(orderId, newStatus);
	}
    @Override
    public boolean updateDiscountAndFinishedFee(int orderItemId, int discountId, double finishedFee) {
    	return orderDao.updateDiscountAndFinishedFee(orderItemId, discountId,finishedFee);
    }
    @Override
    public boolean removeDiscountFromOrderItem(int orderItemId) {
    	return orderDao.removeDiscountFromOrderItem(orderItemId);
    }
    @Override
    public List<Orders> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }
    @Override
    public List<OrderItem> getAllOrderItems()
    {
    	return orderDao.getAllOrderItems();
    }
	@Override
	public boolean checkOrder(int idUser, int idTeacher) {
		return orderDao.checkOrder(idUser, idTeacher);
	}
	@Override
	public boolean isLoyalCustomer(int idUser, int idTeacher) {
		return orderDao.isLoyalCustomer(idUser, idTeacher);
	}
	@Override
	public boolean isHighValueCustomer(int idUser, int idTeacher) {
		return orderDao.isHighValueCustomer(idUser, idTeacher);
	}
	@Override
	public boolean isFirstCourse(int idCourse) {
		return orderDao.isFirstCourse(idCourse);
	}

}
