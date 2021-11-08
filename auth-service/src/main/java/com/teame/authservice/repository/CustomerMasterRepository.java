package com.teame.authservice.repository;


import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import com.teame.authservice.model.CustomerMaster;
public interface CustomerMasterRepository extends DataTablesRepository<CustomerMaster, Long> {

}
