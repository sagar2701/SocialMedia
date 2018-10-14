package com.backend.colloboration.dao;

import java.util.List;

import com.backend.colloboration.model.Notification;

public interface NotificationDao {
	void addNotification(Notification notification);
	List<Notification> getNotificationNotViewed(String email);
	Notification  getNotification(int id);
	Notification  updateNotification(int id);
}
