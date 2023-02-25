package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.masai.exception.BankAccountException;
import com.masai.exception.CustomerException;
import com.masai.model.BankAccount;
import com.masai.model.CustomerUserSession;
import com.masai.model.Wallet;
import com.masai.model.dto.BankAccountDTO;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.CurrRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.WalletDao;


@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CurrRepo currRepo;
	@Autowired
	private WalletDao walletDao;
	@Autowired
	private BankAccountRepo bankAccountRepo;
	
	
//	-----------------------------  Method for Adding Bank Account  -------------------------
	@Override
	public Wallet addAccount(String key, BankAccountDTO bankAccountDTO) throws BankAccountException, CustomerException {
		CustomerUserSession cr = currRepo.findByUuid(key);
        if(cr==null) {
    		throw new CustomerException("Customer is not logged in");
		}
        
        Optional<BankAccount> optional= bankAccountRepo.findById(bankAccountDTO.getAccountNo());
        if(!optional.isPresent()) {
        	
			Optional<Wallet> opt =  walletDao.findById(cr.getUserId());
			Wallet wallet=opt.get();
			
			BankAccount account=new BankAccount(bankAccountDTO.getAccountNo(),bankAccountDTO.getIFSCCode(),bankAccountDTO.getBankName(),bankAccountDTO.getBalance(),wallet);
			bankAccountRepo.save(account);
			
			return wallet;
        	
        }
        throw new BankAccountException("Customer's Bank Account Already exist with given Account Number... Please try different account number");
	}

	
	
	
	
	
	
	
	
	
//  ------------------------------Method for Removing Bank Account --------------------------------
	@Override
	public Wallet removeAccount(String key, BankAccountDTO bankAccountDTO)
			throws BankAccountException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	
//  ------------------------------Method for View User Bank Account --------------------------------
	@Override
	public Optional<BankAccount> viewAccount(String key, Integer accountNo)
			throws BankAccountException, CustomerException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	
	
	
	
//  ------------------------------Method to View All Bank Account --------------------------------
	@Override
	public List<BankAccount> viewAllAccounts(String key) throws BankAccountException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
