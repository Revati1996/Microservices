package com.bank.team.e.account.AccountService.Model;

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
@Table(name = "account_transaction_master")
@JsonInclude(Include.NON_NULL)
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Tran_Id")
	private Long Tran_Id;
	
	@Column(name = "Cust_Id")
	private Long Cust_Id;
	
	@Column(name = "Account_Number")
	private String Account_Number;
	
	@Column(name = "Amount")
	private String Amount;
	
	@Column(name = "Tran_Type")
	private String Tran_Type;
	
	@Column(name = "Tran_Date")
	private Date Tran_Date;
	
	@Column(name = "Account_Balance")
	private Long Account_Balance;
	
	@Column(name = "Creation_Date")
	private Date Creation_Date;

	@Column(name = "Last_Update_Date")
	private Date Last_Update_Date;

	public Long getTran_Id() {
		return Tran_Id;
	}

	public void setTran_Id(Long tran_Id) {
		Tran_Id = tran_Id;
	}

	public Long getCust_Id() {
		return Cust_Id;
	}

	public void setCust_Id(Long cust_Id) {
		Cust_Id = cust_Id;
	}

	public String getAccount_Number() {
		return Account_Number;
	}

	public void setAccount_Number(String account_Number) {
		Account_Number = account_Number;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getTran_Type() {
		return Tran_Type;
	}

	public void setTran_Type(String tran_Type) {
		Tran_Type = tran_Type;
	}

	public Date getTran_Date() {
		return Tran_Date;
	}

	public void setTran_Date(Date tran_Date) {
		Tran_Date = tran_Date;
	}
	public Long getAccount_Balance() {
		return Account_Balance;
	}

	public void setAccount_Balance(Long account_Balance) {
		Account_Balance = account_Balance;
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
		return "AccountTransaction [Tran_Id=" + Tran_Id + ", Cust_Id=" + Cust_Id + ", Account_Number=" + Account_Number
				+ ", Amount=" + Amount + ", Tran_Type=" + Tran_Type + ", Tran_Date=" + Tran_Date + ", Account_Balance="
				+ Account_Balance + ", Creation_Date=" + Creation_Date + ", Last_Update_Date=" + Last_Update_Date + "]";
	}

	
	

}
