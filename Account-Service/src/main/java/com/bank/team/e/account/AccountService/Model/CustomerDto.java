package com.bank.team.e.account.AccountService.Model;

public class CustomerDto {

	private Long Cust_Id;
	
	private String cust_name;
	
	private String Email;
	
	private String phone_number;
	
	private String Pan_Number;
	
	private String Address;
	
	private String Account_Number;
	
	private String Account_Balance;

	private String credit_score;
	
	private String account_type;
	
	private String username;

	public Long getCust_Id() {
		return Cust_Id;
	}

	public void setCust_Id(Long cust_Id) {
		Cust_Id = cust_Id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPan_Number() {
		return Pan_Number;
	}

	public void setPan_Number(String pan_Number) {
		Pan_Number = pan_Number;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getAccount_Number() {
		return Account_Number;
	}

	public void setAccount_Number(String account_Number) {
		Account_Number = account_Number;
	}

	public String getAccount_Balance() {
		return Account_Balance;
	}

	public void setAccount_Balance(String account_Balance) {
		Account_Balance = account_Balance;
	}

	public String getCredit_score() {
		return credit_score;
	}

	public void setCredit_score(String credit_score) {
		this.credit_score = credit_score;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "CustomerDto [Cust_Id=" + Cust_Id + ", cust_name=" + cust_name + ", Email=" + Email + ", phone_number="
				+ phone_number + ", Pan_Number=" + Pan_Number + ", Address=" + Address + ", Account_Number="
				+ Account_Number + ", Account_Balance=" + Account_Balance + ", credit_score=" + credit_score
				+ ", account_type=" + account_type + ", username=" + username + "]";
	}

	


}
