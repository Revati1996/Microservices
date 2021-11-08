package com.bank.team.e.account.AccountService.Service;

import java.rmi.ServerException;

import com.bank.team.e.account.AccountService.Common.ServiceException;
import com.bank.team.e.account.AccountService.Model.StatementRequestDto;
import com.bank.team.e.account.AccountService.Model.Status;
import com.bank.team.e.account.AccountService.Model.TransactionRequestDto;

public interface AccountTransactionServices {

	public Status statementAllById(String id,long limit,long offset) throws ServiceException ;

//	public Status statementAllById(long id, Date startDate, Date endDate, String sort, String sortOrder)
//			throws ServerException;

	public Status saveTransaction(TransactionRequestDto transactionRequestDto) throws ServerException;
	
	public Status getAccountStatement(StatementRequestDto statementRequestDto) throws ServerException;

	public Status getCustomerAccountDetails(String id) throws ServerException;
	
//	public Status getCustomerDetailsByAccId(Long id) throws ServerException;

}
