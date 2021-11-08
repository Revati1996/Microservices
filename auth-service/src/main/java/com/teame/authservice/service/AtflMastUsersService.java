package com.teame.authservice.service;


import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;

import com.teame.authservice.model.CustomerMaster;
import com.teame.authservice.model.ResponceEntity;

public interface AtflMastUsersService {

	ResponceEntity login(String username, String password, HttpServletResponse response)  throws ServiceException;
	
	public CustomerMaster getLtMastUsers(String username);
	
	
	
}
