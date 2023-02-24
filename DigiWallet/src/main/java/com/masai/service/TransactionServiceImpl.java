package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.TransactionException;
import com.masai.exception.WalletException;
import com.masai.model.Transaction;
import com.masai.model.Wallet;
import com.masai.repository.TransactionRepo;
import com.masai.repository.WalletRepo;
@Service
public class TransactionServiceImpl implements TransactionService{

	
	 @Autowired
	   private TransactionRepo transactionRepository;
	   
	   @Autowired
	   private WalletRepo walletRepository;

	   
//----------------------------------Add Transaction---------------------------------------------------------//	   
	@Override
	public Transaction addTransaction(Transaction transaction) throws TransactionException, WalletException {
	Optional<Wallet> wallet=	walletRepository.findById(transaction.getWallet().getWalletId());
	if(!wallet.isPresent()) throw new WalletException("Wallet is id not found ");
	if(transactionRepository.save(transaction)!=null)  return transaction;
throw new  TransactionException("Data is null");
	
	}

	
//-------------------------------Find Transaction - wallet------------------------------------------------//
	
	@Override
	public Transaction findByTransactionId(String key, Integer transactionId)
			throws TransactionException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
/*----------------------------------------   Find Transaction - Type  --------------------------------------------*/
	@Override
	public List<Transaction> findByTransactionType(String key, String transactionType)
			throws TransactionException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
/*------------------------------------   View Transaction - Between 2 date ---------------------------------------*/
	@Override
	public List<Transaction> viewTransactionBetweenDate(String key, LocalDate startDate, LocalDate endDate)
			throws TransactionException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
 /*-----------------------------------------   View All Transaction  ----------------------------------------------*/
	@Override
	public List<Transaction> viewAllTransaction() throws TransactionException {
		List<Transaction> transactions= transactionRepository.findAll();
		if(transactions.isEmpty()) {
			throw new TransactionException("There is no any Transtion to show ");
		}
		return transactions;
	}


	@Override
	public List<Transaction> findByWallet(String key) throws TransactionException, WalletException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
