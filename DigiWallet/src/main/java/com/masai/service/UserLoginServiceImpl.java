package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exception.LoginException;
import com.masai.model.Customer;
import com.masai.model.CustomerUserSession;
import com.masai.model.UserLogin;
import com.masai.repository.CurrRepo;
import com.masai.repository.CustomerRepo;
import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurrRepo currRepo;

	
	/*-------------------------- Methods for Login -----------------------------*/

	@Override
	public String login(UserLogin userLogin) throws LoginException {
		
        Customer existingCustomer = customerRepo.findByMobileNumber(userLogin.getMobileNumber());
		

		
		if(existingCustomer == null) {
			throw new LoginException("Please Enter a Valid Mobile Number!");
		}
		
		
		
		Optional<CustomerUserSession> optional =  currRepo.findByUserId(existingCustomer.getCustomerId());
		
		if(optional.isPresent()) {
			
			throw new LoginException("User Already Exists.");
			
		}
		
		if(existingCustomer.getPassword().equals(userLogin.getPassword())) {
			
			String key = RandomString.make(6);
			
			CustomerUserSession customerUserSession = new CustomerUserSession(existingCustomer.getCustomerId(),key,LocalDateTime.now());
			
			currRepo.save(customerUserSession);

			return customerUserSession.getUuid();
		}

		throw new LoginException("Password is Invalid");
		
	}

	
	/*--------------------- Methods for Sign Out  -------------------------*/

	@Override
	public String logout(String key) throws LoginException {
		
        CustomerUserSession cr = currRepo.findByUuid(key);
		
		if(currRepo == null) {
			throw new LoginException("Unique UserId is Invalid");
		}
		
		currRepo.delete(cr);
		
		return "Logged Out Successfully!";
	}

}
