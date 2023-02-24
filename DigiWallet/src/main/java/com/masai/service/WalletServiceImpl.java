package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.model.Customer;
import com.masai.model.CustomerUserSession;
import com.masai.model.Wallet;
import com.masai.repository.CurrRepo;
import com.masai.repository.CustomerDao;
import com.masai.repository.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletServiceImpl implements  WalletService{

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private WalletDao walletDao;
    
    @Autowired
    private CurrRepo currRepo;
    
    
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
}