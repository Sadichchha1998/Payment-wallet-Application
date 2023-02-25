package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
