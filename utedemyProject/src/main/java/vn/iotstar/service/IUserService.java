package vn.iotstar.service;

import vn.iotstar.entity.User;

public interface IUserService {
	
	public boolean checkExistEmail(String email);
	
	public boolean checkExistPhoneNumber(String phonenumber);
	
	public boolean updatePassword(String email, String newPassword);
	
	public boolean checkPasswordAndConfirmPassword(String password, String confirmPassword);
	
	public boolean checkFormatMail(String email);
	
	public boolean checkPasswordFormat(String password);
	
	public boolean checkFormatPhone(String phone);
	
	public void insert(User user);
}
