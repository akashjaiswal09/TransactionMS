package com.cg.TransactionMS.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.TransactionMS.dto.AccountEntity;



public interface TransactionRepository extends JpaRepository<AccountEntity, Long>{

	List<AccountEntity> findByAccountType(String accountType);
}
