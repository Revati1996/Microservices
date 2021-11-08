package com.bank.team.e.account.AccountService.Service;

import java.rmi.ServerException;

import com.bank.team.e.account.AccountService.Model.CustomerMaster;
import com.bank.team.e.account.AccountService.Model.Status;

public interface CustomerMasterServices {
	
	public Status customerRegistration(CustomerMaster customerMaster) throws ServerException;
	
}
