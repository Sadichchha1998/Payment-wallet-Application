package com.masai.controller;

import com.masai.exception.BankAccountException;
import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.Wallet;
import com.masai.model.dto.BankAccountDTO;
import com.masai.service.BankAccountService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer/bankaccount")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;


	/*---------------------------------------   Add Bank Account Mapping -------------------------------------------*/
	@PostMapping("/add")
	public ResponseEntity<String> addAccountMapping(@RequestParam String key,@Valid @RequestBody BankAccountDTO bankAccountDTO) throws BankAccountException, CustomerException{
		
		bankAccountService.addAccount(key, bankAccountDTO);
		
		return new ResponseEntity<String>("Bank Account Added Successfully",HttpStatus.CREATED);
		
	}


	/*---------------------------------------   Delete Bank Account Mapping -------------------------------------------*/
	@DeleteMapping("/delete")
	public ResponseEntity<Wallet> removeAccountMapping(@RequestParam String key,@Valid @RequestBody BankAccountDTO bankAccount) throws BankAccountException, CustomerException{

		return new ResponseEntity<>(bankAccountService.removeAccount(key, bankAccount),HttpStatus.OK);
	}


	/*---------------------------------------   View Bank Account Mapping -------------------------------------------*/
	@GetMapping("/details")
	public ResponseEntity<Optional<BankAccount>> getBankAccountDetailsMapping(@RequestParam String key, @RequestParam Integer accountNo) throws BankAccountException, CustomerException{

		return new ResponseEntity<Optional<BankAccount>>(bankAccountService.viewAccount(key, accountNo),HttpStatus.OK);

	}


	/*---------------------------------------   View All Bank Account Mapping -------------------------------------------*/
	@GetMapping("/all")
	public ResponseEntity<List<BankAccount>> getAllBankAccountMapping(@RequestParam String key) throws BankAccountException, CustomerException{
		
		return new ResponseEntity<List<BankAccount>>(bankAccountService.viewAllAccounts(key),HttpStatus.FOUND);
		
	}


}