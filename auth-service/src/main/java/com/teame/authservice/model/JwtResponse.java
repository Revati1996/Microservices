package com.teame.authservice.model;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
	private List<String> roles;
	private String status;
	private Long custId;
	private String userType;
	private String custName;
	private String notifyToken;
	private String accountNumber;

	public JwtResponse() {
		super();
	}

	public JwtResponse(String token, String type, String username, List<String> roles, String status, Long custId,
			String userType, String custName, String notifyToken, String accountNumber) {
		super();
		this.token = token;
		this.type = type;
		this.username = username;
		this.roles = roles;
		this.status = status;
		this.custId = custId;
		this.userType = userType;
		this.custName = custName;
		this.notifyToken = notifyToken;
		this.accountNumber = accountNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getNotifyToken() {
		return notifyToken;
	}

	public void setNotifyToken(String notifyToken) {
		this.notifyToken = notifyToken;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
