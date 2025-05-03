package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.Notification;

public interface INotificationService {
	public List<Notification> findByIdUser (int id);
	
	public boolean insert(Notification notification); 
}
