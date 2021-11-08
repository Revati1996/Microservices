package com.mysore.notification.services;

import com.mysore.notification.model.NotificationDto;
import com.mysore.notification.model.Status;

public interface NotificationServices {

	public Status pushNotification(NotificationDto notificationDto)throws Exception;

	public Status getNotificationByCustId(Long custId)throws Exception;

	public Status getNotificationById(Long notificationId)throws Exception;

}
