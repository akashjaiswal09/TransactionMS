package com.cg.TransactionMS.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.TransactionMS.dao.TransactionRepository;
import com.cg.TransactionMS.dto.AccountEntity;
import com.cg.TransactionMS.exception.AccountNotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository repository;

	@Override
	public AccountEntity getAccountByAccNo(long accountno) throws AccountNotFoundException{
		
		Optional<AccountEntity> entity = repository.findById(accountno);
		if(entity.isPresent()) {
			return entity.get();
		}
		else {
			throw new AccountNotFoundException("Account not found for given Account no.");
		}
	}
	
	@Override
	public String debitTransaction(long accountno,double amount) throws AccountNotFoundException {
		double minBalance = 1000;
		Optional<AccountEntity> entity = repository.findById(accountno);
		
		AccountEntity account = entity.get();
		
		if (entity.isPresent()) {
			if ((account.getBalance() >= minBalance && (account.getBalance() - amount) >= minBalance)) {
				account.setBalance(account.getBalance() - amount);
				repository.save(account);
				return "Debited Successfully";
			} else {
				return "Transaction failed";
			}
		} else {
			throw new AccountNotFoundException("Account not found for given Account no.");
		}
	}
	
	@Override
	public String creadiTransaction(long accountno,double amount) throws AccountNotFoundException {
		Optional<AccountEntity> entity = repository.findById(accountno);
		
		AccountEntity account = entity.get();
		
		if (entity.isPresent()) {
			if (amount >100) {
				account.setBalance(account.getBalance() + amount);
				repository.save(account);
				return "Credited Successfully";
			} else {
				return "Amount should be greater than 100";
			}
		} else {
			throw new AccountNotFoundException("Account not found for given Account no.");
		}
	}
	
}
