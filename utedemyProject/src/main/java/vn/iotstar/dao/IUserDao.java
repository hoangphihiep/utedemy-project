package vn.iotstar.dao;

import java.util.Set;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;

public interface IUserDao {
	boolean checkExistEmail(String email);

    boolean updatePassword(String email, String newPassword);

    boolean checkExistPhoneNumber(String phonenumber);

    void insert(User user);

    User findByEmailAndPassword(String email, String password);

    User findByPhoneAndPassword(String phone, String password);

    User findById(int id);

    Set<Role> getRolesByUserId(int userId);

    void update(User user);

    boolean updateUserInformation(User user);
}