package com.bank.team.e.account.AccountService.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.team.e.account.AccountService.Common.ServiceException;
import com.bank.team.e.account.AccountService.Model.CustomerMaster;
import com.bank.team.e.account.AccountService.Model.Status;
import com.bank.team.e.account.AccountService.Service.CustomerMasterServices;

@RestController
@CrossOrigin
@RequestMapping(value = "/customer")
public class CustomerMasterController {

	@Autowired
	CustomerMasterServices customerMasterServices;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> customerRegistration(@RequestBody CustomerMaster customerMaster)
			throws ServiceException, IOException {
		
		Status status = new Status();

		status = customerMasterServices.customerRegistration(customerMaster);

		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
