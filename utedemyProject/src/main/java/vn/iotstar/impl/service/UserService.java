package vn.iotstar.impl.service;

import vn.iotstar.dao.IUserDao;
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
	public boolean updatePassword(String email, String newPassword) {
		return userDao.updatePassword(email, newPassword);
	}
}
