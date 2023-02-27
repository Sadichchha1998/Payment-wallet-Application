package com.masai.service;

import com.masai.exception.BankAccountException;

import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.Wallet;
import com.masai.model.dto.BankAccountDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public interface BankAccountService {
	
	public Wallet addAccount(String key,BankAccountDTO bankAccountDTO) throws BankAccountException,CustomerException;
	
	public Wallet removeAccount(String key,Integer accountNo) throws BankAccountException, CustomerException;
	
	public BankAccount viewAccount(String key, Integer accountNo) throws BankAccountException, CustomerException;
	
	public List<BankAccount> viewAllAccounts(String key) throws BankAccountException, CustomerException;

}
