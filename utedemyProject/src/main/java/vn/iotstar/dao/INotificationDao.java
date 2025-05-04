package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Notification;

public interface INotificationDao {
	
	public List<Notification> findByIdUser (int id);

	public boolean insert(Notification notification); 

}
