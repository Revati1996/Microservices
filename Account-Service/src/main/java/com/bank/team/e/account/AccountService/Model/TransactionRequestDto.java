package com.bank.team.e.account.AccountService.Model;

import java.util.Date;

public class TransactionRequestDto {

	String account_id;
	String amount;
	String type;
	Date from;

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "TransactionRequestDto [account_id=" + account_id + ", amount=" + amount + ", type=" + type + ", from="
				+ from + "]";
	}

}
