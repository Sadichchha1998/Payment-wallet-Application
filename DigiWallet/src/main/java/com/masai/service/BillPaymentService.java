package com.masai.service;

import java.time.LocalDate;

import com.masai.exception.BillPaymentException;
import com.masai.exception.CustomerException;
import com.masai.exception.WalletException;
import com.masai.model.BillPayment;

import jakarta.transaction.TransactionalException;

public interface BillPaymentService {
	
	public String addBillPayment(String mobile, String Name, double amount, String billType, LocalDate paymentDate, Integer walletId, String key) throws WalletException, BillPaymentException, CustomerException, TransactionalException;

	public BillPayment viewBillPayment(String mobile, Integer walletId, String key)throws BillPaymentException, CustomerException, WalletException;
}
