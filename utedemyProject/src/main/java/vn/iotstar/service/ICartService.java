package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.Cart;

public interface ICartService {
	
	 public Cart findByUserId(int userId);
	 
	 public boolean removeAllCoursesByUserId(int userId);
	 
	 public boolean deleteSelectedCourses(Cart cart, List<Integer> selectedCourseIds);
	 
}
