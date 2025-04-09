package vn.iotstar.dao;

import vn.iotstar.entity.User;

public interface IUserDao {
	boolean checkExistEmail(String email);
	
	boolean updatePassword(String email, String newPassword);
	
	public boolean checkExistPhoneNumber(String phonenumber);
	
	public void insert(User user);
}
