package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.BankAccountException;
import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.Wallet;
import com.masai.model.dto.BankAccountDTO;
import com.masai.service.BankAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
		
	@PostMapping("/addaccount/{key}")
	public ResponseEntity<Wallet> addBankAccount(@PathVariable String key,@RequestBody BankAccountDTO bankAccountDTO){
		Wallet wallet=bankAccountService.addAccount(key, bankAccountDTO);
		return new ResponseEntity<>(wallet,HttpStatus.CREATED);
	}
	
	/*---------------------------------------   Delete Bank Account Mapping -------------------------------------------*/
	@DeleteMapping("/delete/{key}/{accountNo}")
	public ResponseEntity<Wallet> removeAccountMapping(@PathVariable String key,@Valid @PathVariable Integer accountNo) throws BankAccountException, CustomerException{

		return new ResponseEntity<>(bankAccountService.removeAccount(key, accountNo),HttpStatus.OK);
	}
	
	

	/*---------------------------------------   View Bank Account Mapping -------------------------------------------*/
	@GetMapping("/details/{key}/{accountNo}")
	public ResponseEntity<BankAccount> getBankAccountDetailsMapping(@PathVariable String key, @PathVariable Integer accountNo) throws BankAccountException, CustomerException{

		return new ResponseEntity<>(bankAccountService.viewAccount(key, accountNo),HttpStatus.OK);

	}
	
	/*---------------------------------------   View All Bank Account Mapping -------------------------------------------*/
	@GetMapping("/all/{key}")
	public ResponseEntity<List<BankAccount>> getAllBankAccountMapping(@PathVariable String key) throws BankAccountException, CustomerException{
		
		return new ResponseEntity<List<BankAccount>>(bankAccountService.viewAllAccounts(key),HttpStatus.FOUND);
		
	}
	
}


