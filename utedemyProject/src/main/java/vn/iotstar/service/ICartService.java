package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.Course;

public interface ICartService {
	
	 public Cart findByUserId(int userId);
	 
	 public boolean removeAllCoursesByUserId(int userId);
	 
	 public boolean deleteSelectedCourses(Cart cart, List<Integer> selectedCourseIds);
	 
	 public List<Course> getRandomCoursesNotInCartByUserId(int userId, int limit);

	boolean addCourseToCart(int userId, int courseId);
	 
}
