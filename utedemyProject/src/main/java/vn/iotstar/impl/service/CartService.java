package vn.iotstar.impl.service;

import java.util.List;

import vn.iotstar.dao.ICartDao;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.Course;
import vn.iotstar.impl.dao.CartDao;
import vn.iotstar.service.ICartService;

public class CartService implements ICartService{
	
	public ICartDao cartDao = new CartDao();

	@Override
	public Cart findByUserId(int userId) {
		return cartDao.findByUserId(userId);
	}
	public boolean removeAllCoursesByUserId(int userId) {
		return cartDao.removeAllCoursesByUserId(userId);
	}
	public boolean deleteSelectedCourses(Cart cart, List<Integer> selectedCourseIds) {
		return cartDao.deleteSelectedCourses(cart, selectedCourseIds);
	}
	public List<Course> getRandomCoursesNotInCartByUserId(int userId, int limit){
		return cartDao.getRandomCoursesNotInCartByUserId(userId, limit);
	}
	@Override
	public boolean addCourseToCart(int userId, int courseId) {
		return cartDao.addCourseToCart(userId, courseId);
	}

}
