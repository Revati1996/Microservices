package com.mysore.notification.services;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysore.notification.model.Notification;

public class CustomDeserializer implements Deserializer<Notification> {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Notification deserialize(String topic, byte[] data) {

		try {
			if (data == null) {
				System.out.println("Null received at deserializing");
				return null;
			}
			System.out.println("Deserializing...");
			return objectMapper.readValue(new String(data, "UTF-8"), Notification.class);
		} catch (Exception e) {
			throw new SerializationException("Error when deserializing byte[] to MessageDto");
		}
	}

	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

}
