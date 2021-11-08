package com.bank.team.e.account.AccountService.Model;

public class NotificationDto {
			
	private String email;
	private String message;
	private String phone;
	private String subject;
	
	
	private Long custo_id;

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

	public Long getCusto_id() {
		return custo_id;
	}

	public void setCusto_id(Long custo_id) {
		this.custo_id = custo_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "NotificationDto [email=" + email + ", message=" + message + ", phone=" + phone + ", subject=" + subject
				+ ", custo_id=" + custo_id + "]";
	}

}
