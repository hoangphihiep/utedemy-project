package vn.iotstar.dao;

import vn.iotstar.entity.Discount;

public interface IDiscountDao {

	Discount findByDisCode(String disCode);

	boolean isCodeAvailable(String disCode);

	boolean isCodeValidTime(String disCode);

	boolean addUserDiscount(int userId, int discountId);

	boolean addDiscountCourse(int courseId, int discountId);

	boolean updateDiscountCourse(int courseId, int newDiscountId);

	boolean updateUserDiscount(int userId, int newDiscountId);

	String getCodeAmountByDiscountId(String discountId);

	boolean updateCodeAmountByDiscountId(int discountId, String newCodeAmount);

	int countUsersByDiscountId(int discountId);

	boolean deleteUserDiscount(int userId, int discountId);

	String getCodeAmountByDisCode(String disCode);

}
