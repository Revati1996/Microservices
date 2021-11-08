package com.bank.team.e.account.AccountService.Dao;

import java.rmi.ServerException;

import com.bank.team.e.account.AccountService.Model.CustomerMaster;

public interface CustomerMasterDao {

	public CustomerMaster saveCustomerRegistration(CustomerMaster customerMaster) throws ServerException;
	
	public CustomerMaster checkCustomerUsername(String username) throws ServerException;

	
}
