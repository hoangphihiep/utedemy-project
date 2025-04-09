package vn.iotstar.service;

public interface IUserService {
	
	boolean checkExistEmail(String email);
	
	boolean updatePassword(String email, String newPassword);
}
