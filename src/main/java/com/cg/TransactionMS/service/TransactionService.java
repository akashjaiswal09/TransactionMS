package com.cg.TransactionMS.service;

import com.cg.TransactionMS.dto.AccountEntity;
import com.cg.TransactionMS.exception.AccountExistException;
import com.cg.TransactionMS.exception.AccountNotFoundException;

public interface TransactionService {

	public AccountEntity getAccountByAccNo(long accountno) throws AccountNotFoundException;

	public String debitTransaction(long accountno, double amount) throws AccountNotFoundException;

	String creadiTransaction(long accountno, double amount) throws AccountNotFoundException;	

}
