package vn.iotstar.service;

import java.util.List;
import java.util.Set;

import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Teacher;

public interface IUserService {
	
	public boolean checkExistEmail(String email);
	
	public boolean checkExistPhoneNumber(String phonenumber);
	
	public boolean updatePassword(String email, String newPassword);
	
	public boolean checkPasswordAndConfirmPassword(String password, String confirmPassword);
	
	public boolean checkFormatMail(String email);
	
	public boolean checkPasswordFormat(String password);
	
	public boolean checkFormatPhone(String phone);
	
	public boolean authenticateUserByEmailAndPassword(String email, String password);
	
	public boolean authenticateUserByPhoneAndPassword(String phone, String password);
	
	public void insert(User user);
	
	public User findByEmailAndPassword(String email,String password);
	
	public User findByPhoneAndPassword(String phone, String password);
	
	public User findById(int id);

	Set<Role> getRolesByUserId(int userId);

	User getUserById(int userId);

    boolean updateUserInformation(User use);
    
    public void registerTeacher(User user, Teacher teacher);

    public List<User> findAllUser();
    
    User findTeacherById(int userId);
    
    public List<User> getAllUsers();
    
	public List<User> getUsersByRole(int roleId);
}
