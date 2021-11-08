package com.bank.team.e.account.AccountService.Service;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.team.e.account.AccountService.Common.Notification;
import com.bank.team.e.account.AccountService.Common.ResponeceStatus;
import com.bank.team.e.account.AccountService.Common.ServiceException;
import com.bank.team.e.account.AccountService.Dao.AccountTransactionDao;
import com.bank.team.e.account.AccountService.Dao.CustomerMasterDao;
import com.bank.team.e.account.AccountService.Model.AccountTransaction;
import com.bank.team.e.account.AccountService.Model.CustomerDto;
import com.bank.team.e.account.AccountService.Model.CustomerMaster;
import com.bank.team.e.account.AccountService.Model.NotificationDto;
import com.bank.team.e.account.AccountService.Model.StatementRequestDto;
import com.bank.team.e.account.AccountService.Model.Status;
import com.bank.team.e.account.AccountService.Model.TransactionRequestDto;

@Service
public class AccountTransactionServicesImp implements AccountTransactionServices, ResponeceStatus {

	@Autowired
	AccountTransactionDao accountTransactionDao;

	@Autowired
	CustomerMasterDao customerMasterDao;

	@Autowired
	Notification notification;

	@Override
	public Status statementAllById(String id, long limit, long offset) throws ServiceException {

		Status status = new Status();

		List<AccountTransaction> list = accountTransactionDao.getStatementById(id, limit, offset);

		if (list != null) {
			status.setCode(SUCCESS);
			status.setMessage(DATA_FOUND);
			status.setData(list);
			return status;
		}
		status.setCode(FAILED);
		status.setMessage(DATA_NOT_FOUND);
		return status;
	}

	@Override
	public Status saveTransaction(TransactionRequestDto transactionRequestDto) throws ServerException {

		Status status = new Status();

		if (transactionRequestDto != null) {

			AccountTransaction accountTransaction = new AccountTransaction();
			accountTransaction.setCust_Id(100L);
			accountTransaction.setAccount_Number(transactionRequestDto.getAccount_id());
			accountTransaction.setAmount(transactionRequestDto.getAmount());
			accountTransaction.setTran_Type(transactionRequestDto.getType());

			if (transactionRequestDto.getType().equalsIgnoreCase(CREDIT)) {

				CustomerDto customerDto = accountTransactionDao
						.getCustomerAccountDetails(transactionRequestDto.getAccount_id());

				CustomerMaster customerMaster = accountTransactionDao.getCustomerById(customerDto.getCust_Id());

				Long amount = Long.parseLong(customerMaster.getAccount_balance())
						+ Long.parseLong(accountTransaction.getAmount());

				customerMaster.setAccount_balance(String.valueOf(amount));

				CustomerMaster customerMastera = customerMasterDao.saveCustomerRegistration(customerMaster);

				accountTransaction.setAccount_Balance(Long.parseLong(customerMastera.getAccount_balance()));

			} else if (transactionRequestDto.getType().equalsIgnoreCase(DEBIT)) {

				CustomerDto customerDto = accountTransactionDao
						.getCustomerAccountDetails(transactionRequestDto.getAccount_id());

				CustomerMaster customerMaster = accountTransactionDao.getCustomerById(customerDto.getCust_Id());

				if (Long.parseLong(customerDto.getAccount_Balance()) > Long
						.parseLong(transactionRequestDto.getAmount())) {

					Long amount = Long.parseLong(customerMaster.getAccount_balance())
							- Long.parseLong(accountTransaction.getAmount());

					customerMaster.setAccount_balance(String.valueOf(amount));

					CustomerMaster customerMastera = customerMasterDao.saveCustomerRegistration(customerMaster);

					accountTransaction.setAccount_Balance(Long.parseLong(customerMastera.getAccount_balance()));

				} else {
					status.setCode(FAILED);
					status.setMessage(Amountbalancenotsuficient);
					return status;
				}

			} else {
				return status;

			}
			accountTransaction.setTran_Date(new Date());
			accountTransaction.setCreation_Date(new Date());
			accountTransaction.setLast_Update_Date(new Date());

			AccountTransaction accTransaction = accountTransactionDao.saveTransaction(accountTransaction);
try {
			NotificationDto notificationObj = notification.pushNotification(accTransaction);

			if (notificationObj != null) {
				System.out.println("Notification Send Successfully!!!!!!");
			}
}catch(Exception e) {}
			if (accTransaction != null) {
				status.setCode(SUCCESS);
				status.setMessage(SUCCESS_MSG);
				status.setData(accTransaction);
			}

		}
		return status;
	}

	@Override
	public Status getCustomerAccountDetails(String id) throws ServerException {
		Status status = new Status();

		CustomerDto dto = accountTransactionDao.getCustomerAccountDetails(id);
		List<CustomerDto> list = new ArrayList<CustomerDto>();
		list.add(dto);

		if (dto != null) {
			status.setCode(SUCCESS);
			status.setMessage(DATA_FOUND);
			status.setData(list);
			return status;
		}
		status.setCode(FAILED);
		status.setMessage(DATA_NOT_FOUND);
		status.setData(dto);
		return status;
	}

	@Override
	public Status getAccountStatement(StatementRequestDto statementRequestDto) throws ServerException {

		Status status = new Status();

		if (statementRequestDto != null) {
			List<AccountTransaction> list = accountTransactionDao.getAccountStatement(statementRequestDto);
			if (list != null) {
				status.setCode(SUCCESS);
				status.setMessage(DATA_FOUND);
				status.setData(list);
				return status;
			}
		}
		status.setCode(FAILED);
		status.setMessage(DATA_NOT_FOUND);
		status.setData(null);
		return status;
	}

}
