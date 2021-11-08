package com.bank.team.e.account.AccountService.Dao;

import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bank.team.e.account.AccountService.Common.ServiceException;
import com.bank.team.e.account.AccountService.Model.AccountTransaction;
import com.bank.team.e.account.AccountService.Model.AccountTransactionRepository;
import com.bank.team.e.account.AccountService.Model.CustomerDto;
import com.bank.team.e.account.AccountService.Model.CustomerMaster;
import com.bank.team.e.account.AccountService.Model.CustomerMasterRepository;
import com.bank.team.e.account.AccountService.Model.StatementRequestDto;

@Repository
@PropertySource(value = "classpath:queries/AccountTransactionQuery.properties", ignoreResourceNotFound = true)
public class AccountTransactionDaoImpl implements AccountTransactionDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	CustomerMasterRepository customerMasterRepository;

	@Autowired
	private Environment env;

	@Autowired
	AccountTransactionRepository accountTransactionRepository;

	@Override
	public List<AccountTransaction> getStatementById(String id, long limit, long offset) throws ServiceException {

		String query = env.getProperty("getStatementByAccountId");

		List<AccountTransaction> list = jdbcTemplate.query(query, new Object[] { id, limit, offset },
				new BeanPropertyRowMapper<AccountTransaction>(AccountTransaction.class));

		if (list.isEmpty())
			return null;
		else
			return list;

	}

	@Override
	public List<AccountTransaction> getStatementAllById(Long id, Date startDate, Date endDate, String sort,
			String sortOrder) throws ServerException {
		return null;
	}

	@Override
	public AccountTransaction saveTransaction(AccountTransaction accountTransaction) throws ServerException {
		return accountTransactionRepository.save(accountTransaction);
	}

	@Override
	public List<AccountTransaction> getAccountStatement(StatementRequestDto statementRequestDto)
			throws ServerException {

		if (statementRequestDto.getAccNumber() != null) {

			String query = "Select a.* from ( Select atm.* from account_transaction_master atm \r\n" + " where 1=1 \r\n"
					+ " and atm.Account_Number = ?\r\n"
					+ " and COALESCE(CONCAT(atm.account_balance ,atm.amount),'xx') like COALESCE(?,COALESCE(concat(atm.account_balance,atm.amount),'xx')) ";

			String sortAmmount = null;

			if (statementRequestDto.getSort() != null) {
				sortAmmount = "%" + statementRequestDto.getSort().toUpperCase().trim() + "%";
			}

			if (statementRequestDto.getStartDate() != null && statementRequestDto.getEndDate() != null) {

				SimpleDateFormat reportStartEndDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String strStartDate = reportStartEndDateFormat.format(statementRequestDto.getStartDate());
				String strEndDate = reportStartEndDateFormat.format(statementRequestDto.getEndDate());

				query = query + "and date_format(atm.Tran_Date,'%Y-%m-%d') >= '" + strStartDate
						+ "' and date_format(atm.Tran_Date,'%Y-%m-%d') <= '" + strEndDate + "' ";
			}

			if (statementRequestDto.getLimit() == 0) {
				statementRequestDto.setLimit(10L);
			}

			if (statementRequestDto.getSortOrder() != null) {
				query = query + " order by atm.Tran_Id "+statementRequestDto.getSortOrder()+") a limit ? offset ?";
			} else {
				query = query + " order by atm.Tran_Id asc) a limit ? offset ?";
			}

			List<AccountTransaction> dataList = jdbcTemplate.query(query,
					new Object[] { statementRequestDto.getAccNumber(), sortAmmount, statementRequestDto.getLimit(),
							statementRequestDto.getOffset() },
					new BeanPropertyRowMapper<AccountTransaction>(AccountTransaction.class));

			if (!dataList.isEmpty()) {
				return dataList;
			}

		}

		return null;
	}

	@Override
	public CustomerDto getCustomerAccountDetails(String id) throws ServerException {

		String query = "Select cm.cust_id,cm.cust_name,cm.email,cm.phone_number,cm.pan_number,cm.pan_number,cm.address,cm.account_number,cm.account_type,cm.username,cm.account_balance,cm.credit_score"
				+ " from customer_master cm where cm.account_number = ?";

		List<CustomerDto> list = jdbcTemplate.query(query, new Object[] { id },
				new BeanPropertyRowMapper<CustomerDto>(CustomerDto.class));

		if (list.isEmpty())
			return null;
		else
			return list.get(0);

	}

	@Override
	public CustomerMaster getCustomerById(Long id) throws ServerException {

		Optional<CustomerMaster> customerMaster = customerMasterRepository.findById(id);
		if (customerMaster.isPresent()) {
			return customerMaster.get();
		}
		return null;
	}

}
