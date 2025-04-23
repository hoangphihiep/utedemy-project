package vn.iotstar.dao;

import vn.iotstar.entity.Cart;

public interface ICartDao {
	
	 public Cart findByUserId(int userId);


}
