package com.cg.TransactionMS.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.TransactionMS.dto.AccountEntity;
import com.cg.TransactionMS.exception.AccountExistException;
import com.cg.TransactionMS.exception.AccountNotFoundException;
import com.cg.TransactionMS.service.TransactionService;

@RestController(value = "/validator")
@CrossOrigin
@RequestMapping("/accounts")
public class TransactionController {

	@Autowired
	TransactionService service;

	// http://localhost:9090/accounts/accountByAccNo/123412341234
	@RequestMapping(value = "/accountno/{accno}", headers = "Accept=application/json", method = RequestMethod.GET)
	public ResponseEntity<AccountEntity> getUserByAccNo(@PathVariable("accno") long accNo) throws AccountNotFoundException {
		AccountEntity account = service.getAccountByAccNo(accNo);
		return new ResponseEntity<>(account,HttpStatus.OK);
	}

	@RequestMapping(value = "/debit/{accountno}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> debitTransaction(@PathVariable(name="accountno") Long accountno,@PathVariable(name="amount") Double amount) throws AccountNotFoundException {
		String msg= service.debitTransaction(accountno, amount);
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);//
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/credit/{accountno}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> creditTransaction(@PathVariable(name="accountno") Long accountno,@PathVariable(name="amount") Double amount) throws AccountNotFoundException {
		String msg= service.creadiTransaction(accountno, amount);
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);//
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}

}
