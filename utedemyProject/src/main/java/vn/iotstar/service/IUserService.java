package vn.iotstar.service;

import java.util.Set;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;

public interface IUserService {

    // Kiểm tra sự tồn tại của email hoặc số điện thoại
    boolean checkExistEmail(String email);

    boolean checkExistPhoneNumber(String phoneNumber);

    // Kiểm tra định dạng dữ liệu
    boolean checkFormatMail(String email);

    boolean checkFormatPhone(String phone);

    boolean checkPasswordFormat(String password);

    // Kiểm tra mật khẩu và xác nhận mật khẩu
    boolean checkPasswordAndConfirmPassword(String password, String confirmPassword);

    // Cập nhật mật khẩu
    boolean updatePassword(String email, String newPassword);

    // Xác thực người dùng
    boolean authenticateUserByEmailAndPassword(String email, String password);

    boolean authenticateUserByPhoneAndPassword(String phone, String password);

    // Tìm người dùng
    User findByEmailAndPassword(String email, String password);

    User findByPhoneAndPassword(String phone, String password);

    User findById(int id);

    // Lấy danh sách vai trò của người dùng
    Set<Role> getRolesByUserId(int userId);

    // Thêm người dùng mới
    void insert(User user);

    // Lấy thông tin người dùng theo ID (dùng trong InformationManagementController)
    User getUserById(int userId);

    // Cập nhật thông tin người dùng (dùng trong InformationManagementController)
    boolean updateUserInformation(int userId, String fullname, String email, String phone, String address, String birthdate, String gender);
    
    
    
   

    
    

}