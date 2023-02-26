package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.LoginException;
import com.masai.model.UserLogin;
import com.masai.service.UserLoginService;

@RestController
@RequestMapping("/user")
public class UserLoginController {

	@Autowired
	private UserLoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginMapping(@RequestBody UserLogin userLogin) throws LoginException{
		
		String output = loginService.login(userLogin);
		
		return new ResponseEntity<String>(output,HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logoutMapping(@RequestParam String key) throws LoginException{
		
		String output = loginService.logout(key);
		
		return new ResponseEntity<String>(output,HttpStatus.OK);
	}
}
