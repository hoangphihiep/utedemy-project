package vn.iotstar.impl.service;

import java.util.Set;
import java.util.List;
import java.util.regex.Pattern;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Teacher;
import vn.iotstar.impl.dao.UserDao;
import vn.iotstar.service.IUserService;

public class UserService implements IUserService {

	public IUserDao userDao = new UserDao();
	public EmailService emailService = new EmailService();
	
	// Validation patterns
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
		"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
	);
	private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9}$");
	
	/**
	 * QUAN TRỌNG: Không cần sanitize vì đã dùng parameterized queries
	 * Chỉ cần validate format
	 */
	
	@Override
	public boolean checkExistEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			return false;
		}
		email = email.trim().toLowerCase(); // Normalize only
		if (!checkFormatMail(email)) {
			return false;
		}
		return userDao.checkExistEmail(email);
	}
	
	@Override
	public void insert(User user) {
		// Validate before insert
		if (user.getEmail() != null) {
			String email = user.getEmail().trim().toLowerCase();
			if (!checkFormatMail(email)) {
				throw new IllegalArgumentException("Invalid email format");
			}
			user.setEmail(email);
		}
		
		if (user.getPhoneNumber() != null) {
			String phone = user.getPhoneNumber().trim().replaceAll("\\s", "");
			if (!checkFormatPhone(phone)) {
				throw new IllegalArgumentException("Invalid phone format");
			}
			user.setPhoneNumber(phone);
		}
		
		if (user.getFullname() != null) {
			user.setFullname(user.getFullname().trim());
		}
		
		userDao.insert(user);
	}
	
	@Override
	public boolean checkExistPhoneNumber(String phonenumber) {
		if (phonenumber == null || phonenumber.trim().isEmpty()) {
			return false;
		}
		phonenumber = phonenumber.trim().replaceAll("\\s", "");
		if (!checkFormatPhone(phonenumber)) {
			return false;
		}
		return userDao.checkExistPhoneNumber(phonenumber);
	}
	
	@Override
	public boolean updatePassword(String email, String newPassword) {
		if (email == null || email.trim().isEmpty()) {
			return false;
		}
		email = email.trim().toLowerCase();
		if (!checkFormatMail(email)) {
			return false;
		}
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
		return EMAIL_PATTERN.matcher(email).matches();
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
	    phone = phone.replaceAll("\\s", "");
	    return PHONE_PATTERN.matcher(phone).matches();
	}
	
	@Override
	public boolean authenticateUserByEmailAndPassword(String email, String password) {
		if (email == null || email.trim().isEmpty() || password == null) {
			return false;
		}
		email = email.trim().toLowerCase();
		if (!checkFormatMail(email)) {
			return false;
		}
		User user = userDao.findByEmailAndPassword(email, password);
		return user != null;
	}
	
	@Override
	public boolean authenticateUserByPhoneAndPassword(String phone, String password) {
		if (phone == null || phone.trim().isEmpty() || password == null) {
			return false;
		}
		phone = phone.trim().replaceAll("\\s", "");
		if (!checkFormatPhone(phone)) {
			return false;
		}
		User user = userDao.findByPhoneAndPassword(phone, password);
		return user != null;
	}
	
	@Override
    public User findByEmailAndPassword(String email, String password) {
		if (email == null || email.trim().isEmpty()) {
			return null;
		}
		email = email.trim().toLowerCase();
		return userDao.findByEmailAndPassword(email, password);
    }
	
	@Override
	public User findByPhoneAndPassword(String phone, String password) {
		if (phone == null || phone.trim().isEmpty()) {
			return null;
		}
		phone = phone.trim().replaceAll("\\s", "");
		return userDao.findByPhoneAndPassword(phone, password);
	}
	
	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}
	
	@Override
	public Set<Role> getRolesByUserId(int userId) {
		return userDao.getRolesByUserId(userId);
	}
	
	@Override
    public User getUserById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public boolean updateUserInformation(User user) {
    	// Validate before update
    	if (user.getEmail() != null) {
			String email = user.getEmail().trim().toLowerCase();
			if (!checkFormatMail(email)) {
				return false;
			}
			user.setEmail(email);
		}
		if (user.getPhoneNumber() != null) {
			String phone = user.getPhoneNumber().trim().replaceAll("\\s", "");
			if (!checkFormatPhone(phone)) {
				return false;
			}
			user.setPhoneNumber(phone);
		}
		if (user.getFullname() != null) {
			user.setFullname(user.getFullname().trim());
		}
    	return userDao.updateUserInformation(user);
    }
    
	@Override
	public void registerTeacher(User user, Teacher teacher) {
		// Validate teacher data
		if (teacher.getTaxCode() != null) {
			teacher.setTaxCode(teacher.getTaxCode().trim());
		}
		if (teacher.getIdentityCard() != null) {
			teacher.setIdentityCard(teacher.getIdentityCard().trim());
		}
		if (teacher.getDescription() != null) {
			teacher.setDescription(teacher.getDescription().trim());
		}
		if (teacher.getSocialUrl() != null) {
			teacher.setSocialUrl(teacher.getSocialUrl().trim());
		}
		if (teacher.getBankAccountNumber() != null) {
			teacher.setBankAccountNumber(teacher.getBankAccountNumber().trim());
		}
		
		userDao.registerTeacher(user, teacher);
	}
	
	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}
	
	@Override
    public User findTeacherById(int userId) {
        return userDao.findTeacherById(userId);
    }
    
	@Override
    public List<User> getAllUsers() {
    	return userDao.getAllUsers();
    }
    
    @Override
    public List<User> getUsersByRole(int roleId) {
    	return userDao.getUsersByRole(roleId);
    }
}