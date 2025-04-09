package vn.iotstar.dao;


public interface IUserDao {
	boolean checkExistEmail(String email);
	
	boolean updatePassword(String email, String newPassword);
}
