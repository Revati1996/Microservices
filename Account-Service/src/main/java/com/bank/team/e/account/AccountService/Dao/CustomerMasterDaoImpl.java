package com.bank.team.e.account.AccountService.Dao;

import java.rmi.ServerException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bank.team.e.account.AccountService.Model.AccountTransactionRepository;
import com.bank.team.e.account.AccountService.Model.CustomerMaster;
import com.bank.team.e.account.AccountService.Model.CustomerMasterRepository;

@Repository
@PropertySource(value = "classpath:queries/AccountTransactionQuery.properties", ignoreResourceNotFound = true)
public class CustomerMasterDaoImpl implements CustomerMasterDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	AccountTransactionRepository accountTransactionRepository;

	@Autowired
	CustomerMasterRepository customerMasterRepository;


	@Override
	public CustomerMaster saveCustomerRegistration(CustomerMaster customerMaster) throws ServerException {
		return customerMasterRepository.save(customerMaster);
	}

	@Override
	public CustomerMaster checkCustomerUsername(String username) throws ServerException {
		
		String query = "SELECT * FROM customer_master where username = ?";

		List<CustomerMaster> list = jdbcTemplate.query(query, new Object[] { username },new BeanPropertyRowMapper<CustomerMaster>(CustomerMaster.class));

		if (!list.isEmpty())
			
			return list.get(0);
		else
			return null;
		
	}

}
