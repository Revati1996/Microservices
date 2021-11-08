package com.bank.team.e.account.AccountService.Common;

public interface ResponeceStatus {

	public static String CREDIT = "CREDIT";
	public static String DEBIT = "DEBIT";
	public static int SUCCESS = 200;
	public static int FAILED = 201;
	public static int EXIT = 202;
	public static String EXIT_MSG = "Username already exists.";
	public static String SUCCESS_MSG = "DATA SAVE SUCCESSFULLY";
	public static String Amountbalancenotsuficient = "Amount balance not sufficient";
	public static String DATA_FOUND = "DATA FOUND SUCCESSFULLY";
	public static String DATA_NOT_FOUND = "DATA NOT FOUND";
	
	
	public static String NOTIFICATION_DEBIT = "State Bank Of Mysore Account ##ACCOUNT_NUMBER## "
			+ "debited for INR ##AMOUNT## on ##DATE## & Transaction Number ##TRAN_NUMBER##";
	
	public static String NOTIFICATION_CREDIT = "State Bank Of Mysore Account ##ACCOUNT_NUMBER## "
			+ "credited for INR ##AMOUNT## on ##DATE## & Transaction Number ##TRAN_NUMBER##";
	
	
	
	
	
	
}
