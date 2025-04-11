package vn.iotstar.impl.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.impl.dao.UserDao;
import vn.iotstar.service.IUserService;

public class UserService implements IUserService {

    public IUserDao userDao = new UserDao();
    public EmailService emailService = new EmailService();

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean checkExistPhoneNumber(String phonenumber) {
        return userDao.checkExistPhoneNumber(phonenumber);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        return userDao.updatePassword(email, newPassword);
    }

    @Override
    public boolean checkPasswordAndConfirmPassword(String password, String confirmPassword) {
        if (password == null || confirmPassword == null) {
            return false;
        }
        return password.equals(confirmPassword);
    }

    @Override
    public boolean checkFormatMail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String regex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}(\\.[0-9]{1,3}){3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return email.matches(regex);
    }

    @Override
    public boolean checkPasswordFormat(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    @Override
    public boolean checkFormatPhone(String phone) {
        if (phone == null) return false;
        String regex = "^0\\d{2}\\s\\d{3}\\s\\d{2}\\s\\d{2}$";
        return phone.matches(regex);
    }

    @Override
    public boolean authenticateUserByEmailAndPassword(String email, String password) {
        User user = userDao.findByEmailAndPassword(email, password);
        return user != null;
    }

    @Override
    public boolean authenticateUserByPhoneAndPassword(String phone, String password) {
        User user = userDao.findByPhoneAndPassword(phone, password);
        return user != null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = userDao.findByEmailAndPassword(email, password);
        return user;
    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        User user = userDao.findByPhoneAndPassword(phone, password);
        return user;
    }

    @Override
    public User findById(int id) {
        User user = userDao.findById(id);
        return user;
    }

    @Override
    public Set<Role> getRolesByUserId(int userId) {
        Set<Role> roles = userDao.getRolesByUserId(userId);
        return roles;
    }

    @Override
    public User getUserById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public boolean updateUserInformation(int userId, String fullname, String email, String phone, String address, String birthdate, String gender) {
        try {
            User user = userDao.findById(userId);
            if (user == null) {
                return false;
            }

            user.setFullname(fullname);
            user.setEmail(email);
            user.setPhoneNumber(phone);
            user.setAddress(address);
            user.setGender(gender);

            if (birthdate != null && !birthdate.trim().isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);
                    java.util.Date parsedDate = dateFormat.parse(birthdate);
                    user.setDateOfBirth(new Date(parsedDate.getTime()));
                } catch (Exception e) {
                    return false;
                }
            } else {
                user.setDateOfBirth(null);
            }

            return userDao.updateUserInformation(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}