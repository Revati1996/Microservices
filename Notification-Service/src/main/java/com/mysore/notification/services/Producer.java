package com.mysore.notification.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mysore.notification.model.Notification;

@Service
public class Producer {

	@Autowired
	private KafkaTemplate<String, Notification> kafkaTemp;

	public static String topic_name = "NOTIFICATION_TOPIC";

	public static Map<Long, List<Notification>> notificationMap = new HashMap<Long, List<Notification>>();

	public boolean publishToTopic(Notification notification) {

		if (notification.getCusto_id() != null) {

			String key = String.valueOf(notification.getCusto_id());

			this.kafkaTemp.send(topic_name, key, notification);

			if (notificationMap.containsKey(notification.getCusto_id())) {
				List<Notification> list = notificationMap.get(notification.getCusto_id());
				list.add(notification);
			} else {
				List<Notification> list = new ArrayList<Notification>();
				list.add(notification);
				notificationMap.put(notification.getCusto_id(), list);
			}

			return true;

		} else {

			return false;

		}
		
//		KafkaProducer<String, Notification> producer = createKafkaProducer();
//		producer.send(new ProducerRecord<String, Notification>(topic_name, notification.getCusto_id().toString(), notification));
//		System.out.println("Message sent " + notification);
//		producer.close();
//		return true;

	}

//	private static KafkaProducer<String, Notification> createKafkaProducer()  {
//		Properties props = new Properties();
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.mysore.notification.services.CustomSerializer");
//
//		return new KafkaProducer(props);
//	}

}
