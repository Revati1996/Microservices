package com.bank.team.e.account.AccountService.Common;

import java.rmi.ServerException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.team.e.account.AccountService.Dao.AccountTransactionDao;
import com.bank.team.e.account.AccountService.Model.AccountTransaction;
import com.bank.team.e.account.AccountService.Model.CustomerDto;
import com.bank.team.e.account.AccountService.Model.NotificationDto;
import com.bank.team.e.account.AccountService.Model.Status;
import com.google.gson.Gson;

@Service
public class Notification implements ResponeceStatus {

	@Autowired
	AccountTransactionDao accountTransactionDao;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private Environment env;

	public NotificationDto pushNotification(AccountTransaction accountTransaction) throws ServerException {

		NotificationDto notificationDto = new NotificationDto();
		RestTemplate RestTemplate1=new RestTemplate();

		CustomerDto customerDto = accountTransactionDao
				.getCustomerAccountDetails(accountTransaction.getAccount_Number());

		if (customerDto != null) {

			String message = new String(NOTIFICATION_DEBIT);

			message = message.replace("##ACCOUNT_NUMBER##", accountTransaction.getAccount_Number());
			message = message.replace("##AMOUNT##", accountTransaction.getAmount());
			message = message.replace("##DATE##", new Date().toString());
			message = message.replace("##TRAN_NUMBER##", accountTransaction.getTran_Id().toString());

			notificationDto.setMessage(message);
			notificationDto.setCusto_id(customerDto.getCust_Id());
			notificationDto.setEmail(customerDto.getEmail());
			notificationDto.setPhone(customerDto.getPhone_number());
			notificationDto.setSubject("ACCOUNT TRANSACTION");

			String url = env.getProperty("NotificationURL");

			String pushNotificationUrl = url + "/notification-api/notifications/customer/" + customerDto.getCust_Id();

			System.out.println(pushNotificationUrl);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			System.out.println(new Gson().toJson(notificationDto));    
			

			
			 HttpEntity<NotificationDto> request = 
				      new HttpEntity<NotificationDto>(notificationDto);

			ResponseEntity<Status> response = RestTemplate1.exchange(pushNotificationUrl, HttpMethod.POST, request,
					Status.class);
			
			System.out.println(response.getBody());
			
			return notificationDto;
		}

		return null;
	}

}
