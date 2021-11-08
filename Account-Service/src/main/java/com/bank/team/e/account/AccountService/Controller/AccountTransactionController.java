package com.bank.team.e.account.AccountService.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.team.e.account.AccountService.Common.ServiceException;
import com.bank.team.e.account.AccountService.Model.StatementRequestDto;
import com.bank.team.e.account.AccountService.Model.Status;
import com.bank.team.e.account.AccountService.Model.TransactionRequestDto;
import com.bank.team.e.account.AccountService.Service.AccountTransactionServices;

@RestController
@CrossOrigin
@RequestMapping(value = "/accounts")
public class AccountTransactionController {

	@Autowired
	AccountTransactionServices accountTransactionServices;

	@RequestMapping(value = "/statement/{account_id}/{limit}/{offset}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> getAllStatementById(@PathVariable("account_id") String account_id,
			@PathVariable("limit") Long limit, @PathVariable("offset") Long offset)
			throws ServiceException, IOException {

		Status status = new Status();

		status = accountTransactionServices.statementAllById(account_id, limit, offset);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
//
//	@RequestMapping(value = "/statement/{account_id}/{startDate}/{endDate}/{sort}/{sort_order}/{limit}/{offset}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Status> getAllStatementById(@PathVariable("account_id") Long account_id,
//			@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate,
//			@PathVariable("sort") String sort, @PathVariable("sort_order") String sort_order)
//			throws ServiceException, IOException {
//		Status status = new Status();
//
//		return new ResponseEntity<Status>(status, HttpStatus.OK);
//	}

	@RequestMapping(value = "/statement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> accountStatement(@RequestBody StatementRequestDto statementRequestDto)
			throws ServiceException, IOException {

		Status status = new Status();

		status = accountTransactionServices.getAccountStatement(statementRequestDto);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@RequestMapping(value = "/transaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> accountTransaction(@RequestBody TransactionRequestDto transactionRequestDto)
			throws ServiceException, IOException {
		Status status = new Status();

		status = accountTransactionServices.saveTransaction(transactionRequestDto);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@RequestMapping(value = "/{account_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> getAccountDetailsById(@PathVariable("account_id") String account_id)
			throws ServiceException, IOException {

		Status status = new Status();

		status = accountTransactionServices.getCustomerAccountDetails(account_id);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
