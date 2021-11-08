package com.mysore.notification.dao;

import java.util.List;

import com.mysore.notification.model.Notification;

public interface NotificationDao {

	public Notification saveNotification(Notification notification) throws Exception;

	public Notification getNotificationById(Long notificationId) throws Exception;

	public List<Notification> getNotificationByCustId(Long custId) throws Exception;

}
