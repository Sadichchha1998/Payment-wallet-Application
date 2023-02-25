package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.TransactionException;
import com.masai.exception.WalletException;
import com.masai.model.CustomerUserSession;
import com.masai.model.Transaction;
import com.masai.model.Wallet;
import com.masai.repository.CurrRepo;
import com.masai.repository.CustomerDao;
import com.masai.repository.TransactionRepo;
import com.masai.repository.WalletRepo;
@Service
public class TransactionServiceImpl implements TransactionService{

	
	 @Autowired
	   private TransactionRepo transactionRepository;
	   
	   @Autowired
	   private WalletRepo walletRepository;

		@Autowired
		private CurrRepo currRepo;
		
		@Autowired
		private CustomerDao customerDao;
		
		@Autowired
		private WalletRepo walletRepo;
	   
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
		CustomerUserSession customerUserSession = currRepo.findByUuid(key);


	      if(customerUserSession==null) {
	         throw new CustomerException("No Customer LoggedIn");
	      }

	      Optional<Transaction> transaction = transactionRepository.findById(transactionId);

	      if(!transaction.isPresent()){
	         throw new TransactionException("Invalid transactionId");
	      }
	      return transaction.get();
	}
/*----------------------------------------  Find Transaction - wallet --------------------------------------------*/
	@Override
	public List<Transaction> findByTransactionType(String key, String transactionType){
	CustomerUserSession customerUserSession = currRepo.findByUuid(key);
    if(customerUserSession==null) {
       throw new CustomerException("No Customer LoggedIn");
    }

    Wallet wallet = walletRepository.showCustomerWalletDetails(customerUserSession.getUserId());

    Optional<Wallet> optional = walletRepository.findById(wallet.getWalletId());
    if(!optional.isPresent()){
       throw new WalletException("Invalid walletId");
    }

    List<Transaction> transactions = transactionRepository.findByWallet(wallet.getWalletId());
    if(transactions.isEmpty()){
       throw new TransactionException("No Transactions to Show");
    }
    return transactions;
	}
/*------------------------------------   View Transaction - Between 2 date ---------------------------------------*/
	@Override
	public List<Transaction> viewTransactionBetweenDate(String key, LocalDate startDate, LocalDate endDate)
			throws TransactionException, CustomerException {
		CustomerUserSession customerUserSession = currRepo.findByUuid(key);
		if(customerUserSession==null) {
	         throw new CustomerException("No Customer LoggedIn");
	      }

	      LocalDate localDate = LocalDate.now();
	      if(startDate.isAfter(localDate)){
	         throw new TransactionException("Invalid Start Date [ Future Date ]");
	      }
	      if(endDate.isAfter(localDate)){
	         throw new TransactionException("Invalid End Date [ Future Date ]");
	      }
	      if(startDate.isAfter(endDate)) {
	         throw new TransactionException("Invalid Start Date ");
	      }

	      List<Transaction> transactions= transactionRepository.findByTransactionDateBetween(startDate, endDate);
	      if(transactions.isEmpty()){
	         throw new TransactionException("No Transactions to Show");
	      }
	      return transactions;
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
