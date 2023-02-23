package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface WalletService {

    public Customer createAccount(Customer customer,BigDecimal bigDecimal) throws CustomerException;

    public BigDecimal showBalance(String mobileNumber) throws CustomerException;

    public Customer updateAccount(Customer customer)throws CustomerException;
}