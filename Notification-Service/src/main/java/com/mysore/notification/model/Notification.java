package com.mysore.notification.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "notification_master")
@JsonInclude(Include.NON_NULL)
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private Long notification_id;
	
	@Column(name = "notification_subject")
	private String notification_subject;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "custo_id")
	private Long custo_id;
	
	@Column(name = "account_id")
	private Long account_id;
	
	@Column(name = "loan_id")
	private Long loan_id;
	
	@Column(name = "Creation_Date")
	private Date Creation_Date;

	@Column(name = "Last_Update_Date")
	private Date Last_Update_Date;

	public Long getNotification_id() {
		return notification_id;
	}

	public void setNotification_id(Long notification_id) {
		this.notification_id = notification_id;
	}

	public String getNotification_subject() {
		return notification_subject;
	}

	public void setNotification_subject(String notification_subject) {
		this.notification_subject = notification_subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCusto_id() {
		return custo_id;
	}

	public void setCusto_id(Long custo_id) {
		this.custo_id = custo_id;
	}

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public Long getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}

	public Date getCreation_Date() {
		return Creation_Date;
	}

	public void setCreation_Date(Date creation_Date) {
		Creation_Date = creation_Date;
	}

	public Date getLast_Update_Date() {
		return Last_Update_Date;
	}

	public void setLast_Update_Date(Date last_Update_Date) {
		Last_Update_Date = last_Update_Date;
	}

	@Override
	public String toString() {
		return "Notification [notification_id=" + notification_id + ", notification_subject=" + notification_subject
				+ ", email=" + email + ", message=" + message + ", phone=" + phone + ", status=" + status
				+ ", custo_id=" + custo_id + ", account_id=" + account_id + ", loan_id=" + loan_id + ", Creation_Date="
				+ Creation_Date + ", Last_Update_Date=" + Last_Update_Date + "]";
	}

	

}
