package vn.iotstar.service;

import vn.iotstar.entity.Cart;

public interface ICartService {
	
	 public Cart findByUserId(int userId);
	 
}
