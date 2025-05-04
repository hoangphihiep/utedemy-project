package vn.iotstar.impl.service;

import java.util.List;

import vn.iotstar.dao.IDiscountDao;
import vn.iotstar.entity.Discount;
import vn.iotstar.impl.dao.DiscountDao;
import vn.iotstar.service.IDiscountService;

public class DiscountService implements IDiscountService{
	
	IDiscountDao discountDao = new DiscountDao();
	
	@Override
	 public Discount findByDisCode(String disCode) {
		 return discountDao.findByDisCode(disCode);
	 }
	@Override
	public boolean isCodeAvailable(String disCode) {
		return discountDao.isCodeAvailable(disCode);
	}
	@Override
	 public boolean isCodeValidTime(String disCode) {
		 return  discountDao.isCodeValidTime(disCode);
	 }
	@Override
	public boolean addUserDiscount(int userId, int discountId) {
		return discountDao.addUserDiscount(userId, discountId);
	}

	@Override
	public boolean addDiscountCourse(int courseId, int discountId) {
		return discountDao.addDiscountCourse(courseId, discountId);
	}
	@Override
	public boolean updateDiscountCourse(int courseId, int newDiscountId) {
		return discountDao.updateDiscountCourse(courseId, newDiscountId);
	}
	@Override
	 public boolean updateUserDiscount(int userId, int newDiscountId) {
		 return discountDao.updateUserDiscount(userId, newDiscountId);
	 }
	@Override
	public String getCodeAmountByDiscountId(String discountId) {
		return discountDao.getCodeAmountByDiscountId(discountId);
	}
	@Override
	public boolean updateCodeAmountByDiscountId(int discountId, String newCodeAmount){
		return discountDao.updateCodeAmountByDiscountId(discountId, newCodeAmount);
	}
	@Override
	public int countUsersByDiscountId(int discountId) {
		return discountDao.countUsersByDiscountId(discountId);
	}
	@Override
	public boolean deleteUserDiscount(int userId, int discountId) {
		return discountDao.deleteUserDiscount(userId, discountId);
	}
	@Override
	public String getCodeAmountByDisCode(String disCode) {
		return discountDao.getCodeAmountByDisCode(disCode);
	}
	@Override
	public List<Discount> findByIdUser(int id) {
		return discountDao.findByIdUser(id);
	}
	@Override
	public boolean insert(Discount discount) {
		return discountDao.insert(discount);
	}
	@Override
	public Discount findById(int id) {
		return discountDao.findById(id);
	}
	@Override
	public boolean update(Discount discount) {
		return discountDao.update(discount);
	}

}
