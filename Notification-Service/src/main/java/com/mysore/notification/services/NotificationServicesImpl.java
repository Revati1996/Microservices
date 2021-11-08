package com.mysore.notification.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysore.notification.dao.NotificationDao;
import com.mysore.notification.model.Notification;
import com.mysore.notification.model.NotificationDto;
import com.mysore.notification.model.Status;

@Service
public class NotificationServicesImpl implements NotificationServices {

	@Autowired
	NotificationDao notificationDao;

	@Autowired
	Producer producer;

	@Override
	public Status pushNotification(NotificationDto notificationDto) {

		Status status = new Status();

		if (notificationDto != null) {

			Notification notification = new Notification();

			notification.setCusto_id(notificationDto.getCusto_id());
			notification.setEmail(notificationDto.getEmail());
			notification.setPhone(notificationDto.getPhone());
			notification.setMessage(notificationDto.getMessage());
			notification.setNotification_subject(notificationDto.getSubject());
			notification.setStatus("Inprocess");
			notification.setCreation_Date(new Date());
			notification.setLast_Update_Date(new Date());

			boolean flag = producer.publishToTopic(notification);

			if (flag) {
				status.setCode(200);
				status.setMessage("Notification Save Successfully");
				status.setData(notification);
				return status;
			}

		}
		status.setCode(201);
		status.setMessage("Notification Failed");
		return status;
	}

	@Override
	public Status getNotificationByCustId(Long custId) throws Exception {

		Status status = new Status();

		if (custId != null) {

			List<Notification> list = notificationDao.getNotificationByCustId(custId);
			if (list != null) {
				status.setCode(200);
				status.setMessage("Data found successfully");
				status.setData(list);
				return status;
			}
		}

		status.setCode(201);
		status.setMessage("Data not found");
		return status;
	}

	@Override
	public Status getNotificationById(Long notificationId) throws Exception {

		Status status = new Status();

		Notification notification = notificationDao.getNotificationById(notificationId);

		if (notification != null) {

			status.setCode(200);
			status.setMessage("Data found successfully");
			status.setData(notification);
			return status;

		}

		status.setCode(120);
		status.setMessage("Data not found");
		return status;

	}

}
