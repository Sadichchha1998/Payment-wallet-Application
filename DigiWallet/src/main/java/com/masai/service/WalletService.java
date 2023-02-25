package com.masai.service;

import com.masai.exception.BeneficiaryException;
import com.masai.exception.CustomerException;
import com.masai.exception.TransactionException;
import com.masai.exception.WalletException;
import com.masai.model.Customer;

import jakarta.validation.constraints.AssertFalse.List;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface WalletService {

    public Customer createAccount(Customer customer,BigDecimal bigDecimal) throws CustomerException;

    public BigDecimal showBalance(String mobileNumber,String key) throws CustomerException;
    
    public String fundTransfer(String sourceMobileNumber, String targetMobileNumber, BigDecimal amount, String key) throws WalletException, BeneficiaryException, CustomerException;
    
    public Customer customerDatails(String mobileNumber,String key) throws CustomerException;

    public Customer updateAccount(Customer customer,String key)throws CustomerException;
}