package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.UserLogin;

@Service
public interface UserLoginService {

	 public String login (UserLogin userLogin) throws LoginException;
		
		public String logout (String Key) throws LoginException;
}
