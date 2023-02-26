package com.masai.service;

import com.masai.exception.BankAccountException;
import com.masai.exception.BeneficiaryException;
import com.masai.exception.CustomerException;
import com.masai.exception.TransactionException;
import com.masai.exception.WalletException;
import com.masai.model.BankAccount;
import com.masai.model.Beneficiary;
import com.masai.model.Customer;
import com.masai.model.CustomerUserSession;
import com.masai.model.Transaction;
import com.masai.model.Wallet;
import com.masai.model.dto.BankAccountDTO;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.BeneficiaryRepository;
import com.masai.repository.CurrRepo;
import com.masai.repository.CustomerDao;
import com.masai.repository.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements  WalletService{

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private WalletDao walletDao;
    @Autowired
    private CurrRepo currRepo;
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private BankAccountRepo bankAccountRepo;
    
    
    
    @Override
    public Customer createAccount(Customer customer,BigDecimal bigDecimal) throws CustomerException {

        Customer customer1=customerDao.findByMobileNumber(customer.getMobileNumber());

        if(customer1!=null) throw new CustomerException("Mobile Number Already exist");

        Wallet wallet=new Wallet();
        wallet.setBalance(bigDecimal);
        wallet.setCustomer(customer);
        walletDao.save(wallet);
        return customerDao.save(customer);
    }

    @Override
    public BigDecimal showBalance(String mobileNumber,String key) {
    	
    	CustomerUserSession customerUserSession= currRepo.findByUuid(key);
    	if(customerUserSession==null) throw new CustomerException("Login first");
    	
        Customer customer=customerDao.findByMobileNumber(mobileNumber);

        if(customer==null) throw new CustomerException("This mobile number does not exist");

        Wallet wallet=walletDao.findByCustomer(customer);
        return wallet.getBalance();
    }

    @Override
    public Customer updateAccount(Customer customer,String key) throws CustomerException {
    	
    	CustomerUserSession customerUserSession= currRepo.findByUuid(key);
    	if(customerUserSession==null) throw new CustomerException("Login First");
    	
    	
        Optional<Customer> opt=customerDao.findById(customer.getCustomerId());

        Customer existingCustomer=opt.get();
        if(existingCustomer==null) throw new CustomerException("Customer does not exist with customer id : "+customer.getCustomerId());

        return customerDao.save(customer);

    }

	@Override
	public Customer customerDatails(String mobileNumber, String key) throws CustomerException {
		CustomerUserSession customerUserSession= currRepo.findByUuid(key);
		if(customerUserSession==null) throw new CustomerException("Login First");
		
		Customer customer= customerDao.findByMobileNumber(mobileNumber);
		if(customer==null) throw new CustomerException("Wrong mobile Number ");
		
		
		return customer;
	}

	@Override
	public String fundTransfer(String sourceMobileNumber, String targetMobileNumber, BigDecimal amount, String key)
			throws WalletException, BeneficiaryException, CustomerException {
		
		CustomerUserSession customerUserSession= currRepo.findByUuid(key);
		if(customerUserSession==null) throw new CustomerException("Login First");
		
		Customer customer=customerDao.findByMobileNumber(sourceMobileNumber);
		if(customer==null) throw new CustomerException(sourceMobileNumber+" Source mobile number does not exist");
		
		
		Beneficiary beneficiary=beneficiaryRepository.findByBeneficiaryMobileNumber(targetMobileNumber);
		if(beneficiary==null) throw new BeneficiaryException("Beneficiary does not found with this mobile number : "+targetMobileNumber);
		
		Wallet wallet= beneficiary.getWallet();
		if(wallet==null) throw new WalletException("wallet not found ");
		
		Transaction transaction=new Transaction("Wallet Transfer",LocalDate.now(),amount.doubleValue(),amount +" transferred to "+ targetMobileNumber,wallet);
		
		if(wallet.getBalance().doubleValue()<amount.doubleValue()) {
			throw new WalletException("Insufficient Balance ");
		}
		
		
		wallet.setBalance(wallet.getBalance().subtract(amount));
		walletDao.save(wallet);
		
		transactionService.addTransaction(transaction);
		
		return "Fund Transfer successfully from cutomer account name "+customer.getCustomerName()+" to Beneficiary account holder "+beneficiary.getBeneficiaryName()+" through wallet";
	}

	@Override
	public String depositAmount(Integer accountNo, BigDecimal amount, String key)
			throws BankAccountException, WalletException, CustomerException, TransactionException {
		
		CustomerUserSession customerUserSession= currRepo.findByUuid(key);
		if(customerUserSession==null) throw new CustomerException("Login First");
		
		Optional<Customer> opt= customerDao.findById(customerUserSession.getUserId());
		
		if(!opt.isPresent()) throw new CustomerException("Customer not found through key : "+key);
		
		BankAccount bankAccount=bankAccountRepo.findByAccountNo(accountNo);
		if(bankAccount==null) throw new BankAccountException("Bank account does not found ");
		
		Wallet wallet= bankAccount.getWallet();
		if(wallet==null) throw new WalletException("Wallet not found ");
		
		Double walletBanlance= wallet.getBalance().doubleValue();
		Double banckAccountBalance= bankAccount.getBalance();
		
		if(walletBanlance<amount.doubleValue()) throw new BankAccountException("Insufficient Balance");
		
		wallet.setBalance(wallet.getBalance().subtract(amount));
		
		bankAccount.setBalance(banckAccountBalance+amount.doubleValue());
		
		Transaction transaction=new Transaction("Bank Transfer",LocalDate.now(),amount.doubleValue(),amount +" transferred to "+ opt.get().getMobileNumber(),wallet);
		
		walletDao.save(wallet);
		bankAccountRepo.save(bankAccount);
		
		transactionService.addTransaction(transaction);
		
		
		return amount+" deposit to account to "+bankAccount.getAccountNo()+" , deduct from customer "+opt.get().getCustomerName();
	}

}