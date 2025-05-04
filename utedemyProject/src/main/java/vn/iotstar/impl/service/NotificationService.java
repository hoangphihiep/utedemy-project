package vn.iotstar.impl.service;

import java.util.List;

import vn.iotstar.dao.INotificationDao;
import vn.iotstar.entity.Notification;
import vn.iotstar.impl.dao.NotificationDao;
import vn.iotstar.service.INotificationService;

public class NotificationService implements INotificationService {

	public INotificationDao notificationDao = new NotificationDao();
	@Override
	public List<Notification> findByIdUser(int id) {
		return notificationDao.findByIdUser(id);
	}
	@Override
	public boolean insert(Notification notification) {
		return notificationDao.insert(notification);
	}

}