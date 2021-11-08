package com.bank.team.e.account.AccountService.Dao;

import java.rmi.ServerException;
import java.util.Date;
import java.util.List;

import com.bank.team.e.account.AccountService.Common.ServiceException;
import com.bank.team.e.account.AccountService.Model.AccountTransaction;
import com.bank.team.e.account.AccountService.Model.CustomerDto;
import com.bank.team.e.account.AccountService.Model.CustomerMaster;
import com.bank.team.e.account.AccountService.Model.StatementRequestDto;

public interface AccountTransactionDao {
	
	public List<AccountTransaction> getStatementById(String id,long limit,long offset) throws ServiceException;

	public List<AccountTransaction> getStatementAllById(Long id, Date startDate, Date endDate, String sort, String sortOrder)
			throws ServerException;

	public AccountTransaction saveTransaction(AccountTransaction accountTransaction) throws ServerException;

	public CustomerDto getCustomerAccountDetails(String id) throws ServerException;
	
	public List<AccountTransaction> getAccountStatement(StatementRequestDto statementRequestDto) throws ServerException;
	
	public CustomerMaster getCustomerById(Long id) throws ServerException;

}
