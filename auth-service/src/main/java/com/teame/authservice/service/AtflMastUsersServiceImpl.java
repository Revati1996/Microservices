package com.teame.authservice.service;


import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.teame.authservice.dao.AtflMastUsersDao;
import com.teame.authservice.model.CodeMaster;
import com.teame.authservice.model.CustomerMaster;
import com.teame.authservice.model.ResponceEntity;
import com.teame.authservice.model.Status;



@Service
@PropertySource(value = "classpath:queries/messages.properties", ignoreResourceNotFound = true)
public class AtflMastUsersServiceImpl implements AtflMastUsersService, CodeMaster {
	
	
	@Autowired
	private AtflMastUsersDao ltMastUsersDao;
	
	@Override
	public CustomerMaster getLtMastUsers(String username) {
		return ltMastUsersDao.getLtMastUsersByMobileNumber(username);
	}

	
	@Override
	public ResponceEntity login(String username,String password, HttpServletResponse response) throws ServiceException {
		
		ResponceEntity entity = new ResponceEntity();
		
		Status status = new Status();
		
		CustomerMaster ltMastUser = ltMastUsersDao.getLtMastUsersByMobileNumber(username);
		
		if (ltMastUser.getStatus().equals("INACTIVE")) {
			entity.setCode(FAIL);
			entity.setMessage("User is Inactive");
			return entity;
		}
		

		if (ltMastUser != null) {
			if (ltMastUser.getPassword().equalsIgnoreCase(password)) {
				status.setCode(SUCCESS);
				status.setMessage("Login Success");
				entity.setCode(SUCCESS);
				entity.setRole(ltMastUser.getUser_type());
				entity.setUserId(ltMastUser.getCust_id());
				entity.setStatus(ltMastUser.getStatus());
				entity.setUserName(ltMastUser.getCust_name());

			} else {
				entity.setCode(FAIL);
				entity.setMessage("Please Enter Valid OTP");
			}
		}
		return entity;
	}
	
}
