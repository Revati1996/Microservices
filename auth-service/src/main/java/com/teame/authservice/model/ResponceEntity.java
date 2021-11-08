package com.teame.authservice.model;

public class ResponceEntity {

	private Integer code;
	private  String message;
	private Object data;
	private Long userId;
	private String userName;
	private String role;
	private String status;
	private Long lastLoginId; 
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getLastLoginId() {
		return lastLoginId;
	}
	public void setLastLoginId(Long lastLoginId) {
		this.lastLoginId = lastLoginId;
	}
	
	ResponceEntity setData() {
		ResponceEntity obj=new ResponceEntity();
		
		return obj;
		
	}
		
}
