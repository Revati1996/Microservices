package com.bank.team.e.account.AccountService.Service;

import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.team.e.account.AccountService.Common.ResponeceStatus;
import com.bank.team.e.account.AccountService.Dao.CustomerMasterDao;
import com.bank.team.e.account.AccountService.Model.CustomerMaster;
import com.bank.team.e.account.AccountService.Model.Status;

@Service
public class CustomerMasterServicesImp implements CustomerMasterServices, ResponeceStatus {

	@Autowired
	CustomerMasterDao customerMasterDao;

	@Override
	public Status customerRegistration(CustomerMaster customerMaster) throws ServerException {

		Status status = new Status();
		customerMaster.setCreate_date(new Date());
		customerMaster.setLast_update_date(new Date());
		customerMaster.setCust_name(customerMaster.getFirst_name() + " " + customerMaster.getLast_name());
		customerMaster.setUser_type("USER");
		customerMaster.setStatus("ACTIVE");
		customerMaster.setAccount_type("SAVING");

		CustomerMaster usernameObj = customerMasterDao.checkCustomerUsername(customerMaster.getUsername());

		if (usernameObj != null) {
			status.setCode(EXIT);
			status.setMessage(EXIT_MSG);
			status.setData(null);
			return status;
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
		Date date = Calendar.getInstance().getTime();
		// Create Account Number here

		customerMaster.setAccount_number("NA" + dateFormat.format(date));
		customerMaster.setCredit_score("750");

		CustomerMaster saveCustomerMaster = customerMasterDao.saveCustomerRegistration(customerMaster);

		if (saveCustomerMaster != null) {
			status.setCode(SUCCESS);
			status.setMessage(SUCCESS_MSG);
			status.setData(saveCustomerMaster);
			return status;
		}
		return null;
	}
}
