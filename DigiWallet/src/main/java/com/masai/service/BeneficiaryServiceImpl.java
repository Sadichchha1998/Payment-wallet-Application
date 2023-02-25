package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BeneficiaryException;
import com.masai.exception.CustomerException;
import com.masai.exception.WalletException;
import com.masai.model.Beneficiary;
import com.masai.model.BeneficiaryDTO;
import com.masai.model.Customer;
import com.masai.model.CustomerUserSession;
import com.masai.model.Wallet;
import com.masai.repository.BeneficiaryRepository;
import com.masai.repository.CurrRepo;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerRepo;
import com.masai.repository.WalletDao;
import com.masai.repository.WalletRepo;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{

	@Autowired
	private BeneficiaryRepository beneficiaryRepo;
	
	@Autowired
	private CurrRepo currRepo;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private WalletRepo walletRepo;
	
	@Autowired
	private WalletDao walletDao;
	
	/*=============================================== Add Benificiary =================================================*/
	
	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key)
			throws BeneficiaryException, CustomerException, WalletException {
		
		CustomerUserSession customerUserSession = currRepo.findByUuid(key);
		
		if(customerUserSession==null) {
			throw new CustomerException("Customer is not logged in!");
		}
		
		Optional<Customer> cust = customerDao.findById(customerUserSession.getUserId());
		
		if(!cust.isPresent()) {
			throw new CustomerException("Benificiary is not registered in this app!");
		}
		
		Optional<Wallet> wallet = walletDao.findById(beneficiary.getWallet().getWalletId());
		
		if(!wallet.isPresent()) {
			throw new WalletException("The user is Invalid!");
		}
		
		Customer customer = wallet.get().getCustomer();
		
		Boolean con = beneficiary.getWallet().getWalletId()==beneficiary.getWallet().getCustomer().getCustomerId();
		
		if(!con) {
			throw new CustomerException("Wallet Id is different from existing customer wallet Id!");
		}
		

		
		
		
		Optional<Beneficiary> benefi = beneficiaryRepo.findById(beneficiary.getBeneficiaryMobileNumber());
		
		if(!benefi.isPresent()) {
			return beneficiaryRepo.save(beneficiary);
		}
		
		throw new BeneficiaryException("Benififciary is already Exist!");
	}

	
	/*=============================================== Delete Benificiary =================================================*/
	
	@Override
	public Beneficiary deleteBeneficiary(String key, BeneficiaryDTO beneficiaryDTO)
			throws BeneficiaryException, CustomerException {
		
		CustomerUserSession customerUserSession = currRepo.findByUuid(key);
		
		if(customerUserSession==null) {
			throw new CustomerException("Customer is not Logged in!");
		}
		
		Wallet wallet = walletRepo.showCustomerWalletDetails(customerUserSession.getUserId());
		
		Beneficiary ben = beneficiaryRepo.findByMobileWallet(wallet.getWalletId(), beneficiaryDTO.getBeneficiaryMobileNumber());
		
		if(ben==null) {
			throw new BeneficiaryException("Benificiary is not found!");
		}
		
		beneficiaryRepo.delete(ben);
		
		return ben;
	}

	/*=============================================== View Benificiary =================================================*/
	
	@Override
	public Beneficiary viewBeneficiary(String beneficiaryName, String key)
			throws BeneficiaryException, CustomerException {
		
		CustomerUserSession customerUserSession = currRepo.findByUuid(key);
		
		if(customerUserSession==null) {
			throw new CustomerException("Customer is not Logged in!");
		}
		
		Wallet wallet = walletRepo.showCustomerWalletDetails(customerUserSession.getUserId());
		
		Beneficiary ben = beneficiaryRepo.findByNameWallet(wallet.getWalletId(), beneficiaryName);
		
		if(ben==null) {
			throw new BeneficiaryException("Benificiary is not found!");
		}
		
		return ben;
		
	}

	/*=============================================== View All Benificiary =================================================*/
	
	@Override
	public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryException, CustomerException {
		
		CustomerUserSession customerUserSession = currRepo.findByUuid(key);
		
		if(customerUserSession==null) {
			throw new CustomerException("Customer is not Logged in!");
		}
		
		Wallet wallet = walletRepo.showCustomerWalletDetails(customerUserSession.getUserId());
		
		List<Beneficiary> listOfBen = beneficiaryRepo.findByWallet(wallet.getWalletId());
		
		if(listOfBen.isEmpty()) {
			throw new BeneficiaryException("Benificiary is not found!");
		}
		
		return listOfBen;
	}

	

}
