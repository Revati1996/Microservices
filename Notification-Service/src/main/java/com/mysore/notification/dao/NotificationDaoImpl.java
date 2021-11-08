package com.mysore.notification.dao;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysore.notification.model.Notification;
import com.mysore.notification.model.NotificationRepository;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public Notification saveNotification(Notification notification) throws Exception {
		return notificationRepository.save(notification);
	}

	@Override
	public Notification getNotificationById(Long notificationId) throws Exception {

		Optional<Notification> notification = notificationRepository.findById(notificationId);

		if (notification.isPresent()) {
			return notification.get();
		}

		return null;
	}

	@Override
	public List<Notification> getNotificationByCustId(Long custId) throws Exception {

		String query = "SELECT * FROM notification_master WHERE custo_id = ? order by notification_id desc limit 10";

		List<Notification> list = jdbcTemplate.query(query, new Object[] { custId },new BeanPropertyRowMapper<Notification>(Notification.class));

		if (list.isEmpty())
			return null;
		else
			return list;
	}

}
