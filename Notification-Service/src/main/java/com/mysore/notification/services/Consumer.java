package com.mysore.notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mysore.notification.model.Notification;
import com.mysore.notification.model.NotificationRepository;

@Service
public class Consumer {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@KafkaListener(topics = "NOTIFICATION_TOPIC", groupId = "mygroup")
	public void consumeFromTopic(Notification notification) {
		
		System.out.println("Customer Id :"+notification.getCusto_id());

		boolean mailFlag = sendMail(notification);

		if (mailFlag) {
			notification.setStatus("SUCCESS");
		} else {
			notification.setStatus("FAILED");
		}

		notificationRepository.save(notification);
		
		

	}

	public boolean sendMail(Notification notification) {
		
		try {
			
			SimpleMailMessage msg = new SimpleMailMessage();
			
			msg.setTo(notification.getEmail().toString());

			msg.setSubject(notification.getNotification_subject().toString());
			
			msg.setText(notification.getMessage());

			javaMailSender.send(msg);
			
			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
//
//	private static KafkaConsumer<String, Notification> createKafkaConsumer() {
//	    Properties props = new Properties();
//	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//	    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.mysore.notification.services.CustomDeserializer");
//
//	    return new KafkaConsumer<>(props);
//	}
}
