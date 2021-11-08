package com.bank.team.e.account.AccountService.Model;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface AccountTransactionRepository extends DataTablesRepository<AccountTransaction, Long> {

}
