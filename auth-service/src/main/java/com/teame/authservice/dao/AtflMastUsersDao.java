package com.teame.authservice.dao;


import org.hibernate.service.spi.ServiceException;

import com.teame.authservice.model.CustomerMaster;

public interface AtflMastUsersDao {

	CustomerMaster getLtMastUsersByMobileNumber(String mobileNumber) throws ServiceException;
	
	CustomerMaster saveCustomerMaster(CustomerMaster customerMaster) throws ServiceException;
	
	
}
