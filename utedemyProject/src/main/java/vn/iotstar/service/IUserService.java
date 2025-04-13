package vn.iotstar.service;

import java.util.Set;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;

public interface IUserService {

    boolean checkExistEmail(String email);

    boolean checkExistPhoneNumber(String phoneNumber);

    boolean checkFormatMail(String email);

    boolean checkFormatPhone(String phone);

    boolean checkPasswordFormat(String password);

    boolean checkPasswordAndConfirmPassword(String password, String confirmPassword);

    boolean updatePassword(String email, String newPassword);

    boolean authenticateUserByEmailAndPassword(String email, String password);

    boolean authenticateUserByPhoneAndPassword(String phone, String password);

    User findByEmailAndPassword(String email, String password);

    User findByPhoneAndPassword(String phone, String password);

    User findById(int id);

    Set<Role> getRolesByUserId(int userId);

    void insert(User user);

    User getUserById(int userId);

    boolean updateUserInformation(User use);
}