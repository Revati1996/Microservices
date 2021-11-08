package com.bank.team.e.account.AccountService.Common;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	private Exception exception;

	public ServiceException(int code, String message, Exception exception) {
		super();
		this.code = code;
		this.message = message;
		this.exception = exception;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
