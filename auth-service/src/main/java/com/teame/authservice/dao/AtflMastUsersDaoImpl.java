package com.teame.authservice.dao;


import java.util.List;

import javax.sql.DataSource;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teame.authservice.model.CustomerMaster;
import com.teame.authservice.repository.CustomerMasterRepository;

@Repository
public class AtflMastUsersDaoImpl implements AtflMastUsersDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Autowired
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	CustomerMasterRepository customerMasterRepository;

	@Override
	public CustomerMaster getLtMastUsersByMobileNumber(String username) throws ServiceException {
		
		String query = "select cm.* from customer_master cm where cm.username = ?";
		
		List<CustomerMaster> list = jdbcTemplate.query(query, new Object[] { username.trim() },
				new BeanPropertyRowMapper<CustomerMaster>(CustomerMaster.class));
		
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public CustomerMaster saveCustomerMaster(CustomerMaster customerMaster) throws ServiceException {
		return customerMasterRepository.save(customerMaster);
	}

}
