package vn.iotstar.impl.service;

import vn.iotstar.dao.IOrderDao;
import vn.iotstar.entity.Orders;
import vn.iotstar.impl.dao.OrderDao;
import vn.iotstar.service.IOrderService;

public class OrderService implements IOrderService {
    IOrderDao orderDao = new OrderDao();
    
    @Override
    public boolean insertOrder(Orders order) {
    	return orderDao.insertOrder(order);
    }

}
