package com.mysore.notification.model;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface NotificationRepository  extends DataTablesRepository<Notification, Long> {

}
