package vn.iotstar.impl.service;

import vn.iotstar.dao.ICartDao;
import vn.iotstar.entity.Cart;
import vn.iotstar.impl.dao.CartDao;
import vn.iotstar.service.ICartService;

public class CartService implements ICartService{
	
	public ICartDao cartDao = new CartDao();

	@Override
	public Cart findByUserId(int userId) {
		return cartDao.findByUserId(userId);
	}

}
