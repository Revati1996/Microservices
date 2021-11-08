package com.mysore.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysore.notification.model.NotificationDto;
import com.mysore.notification.model.Status;
import com.mysore.notification.services.NotificationServices;

@RestController
@CrossOrigin
@RequestMapping(value = "/notifications")
public class NotificationController {

	@Autowired
	NotificationServices notificationServices;

	@RequestMapping(value = "/customer/{customerid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> pushNotification(@PathVariable("customerid") Long customerid,
			@RequestBody NotificationDto notificationDto) throws Exception {

		Status status = new Status();

		status = notificationServices.pushNotification(notificationDto);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> getNotificationByCustId(@PathVariable("customerid") Long customerid) throws Exception {

		Status status = new Status();

		status = notificationServices.getNotificationByCustId(customerid);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customer/{customerid}/notification/{notificationid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> getAllStatementById(@PathVariable("customerid") Long customerid,@PathVariable("notificationid") Long notificationid) throws Exception {

		Status status = new Status();

		status = notificationServices.getNotificationById(notificationid);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
