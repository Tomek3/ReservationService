package com.service.dao;

import java.util.List;

public interface INotificationDAO {
	
	List<Notification> getAllUserNotification(String userId);

}