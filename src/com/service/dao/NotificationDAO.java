package com.service.dao;

import java.util.List;

import com.service.jersey.DBConnection;


public class NotificationDAO implements INotificationDAO{
	
	@Override
	public List<Notification> getAllUserNotification(String userId)
	{
		try {
			return DBConnection.getAllUserNotification(userId);
		} catch (Exception e) {
			return null;
		}
	}

}

