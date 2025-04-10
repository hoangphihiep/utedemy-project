package vn.iotstar.dao;

import java.util.Set;

import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;

public interface IUserDao {
	boolean checkExistEmail(String email);
	
	boolean updatePassword(String email, String newPassword);
	
	public boolean checkExistPhoneNumber(String phonenumber);
	
	public void insert(User user);
	
	public User findByEmailAndPassword(String email, String password);
	
	public User findByPhoneAndPassword(String phone, String password);
	
	public User findById(int id);

	Set<Role> getRolesByUserId(int userId);
	
}
