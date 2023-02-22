package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	
	
//--------------------------------Exception For wallet ExceptionClass--------------------------------------------------------------//
	
	
	@ExceptionHandler(WalletException.class)
	public ResponseEntity<ErrorDetails> DigiwalletExceptionHandler(WalletException walletException,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), walletException.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	

//-------------------------------- Exception For BankAccount ExceptionClass ------------------------------------------------------------//
	
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<ErrorDetails> DigiwalletExceptionHandler(TransactionException transactionException,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), transactionException.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
//-------------------------------- Exception For Customer ExceptionClass ------------------------------------------------------------//
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> DigiwalletExceptionHandler(CustomerException customerException,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), customerException.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
//-------------------------------- Exception For BillPayment ExceptionClass ------------------------------------------------------------//	
	
	
	@ExceptionHandler(BillPaymentException.class)
	public ResponseEntity<ErrorDetails> DigiwalletExceptionHandler(BillPaymentException billPaymentException,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), billPaymentException.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
//-------------------------------- Exception For  Beneficiary ExceptionClass ------------------------------------------------------------//	
	
	
	@ExceptionHandler(BeneficiaryException.class)
	public ResponseEntity<ErrorDetails> DigiwalletExceptionHandler(BeneficiaryException beneficiaryException ,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), beneficiaryException.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
//-------------------------------- Exception For  BankAccount ExceptionClass ------------------------------------------------------------//		


	@ExceptionHandler(BankAccountException.class)
	public ResponseEntity<ErrorDetails> DigiwalletExceptionHandler(BankAccountException bankAccountException,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), bankAccountException.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	//-------------------------------- Exception For  Login  ExceptionClass ------------------------------------------------------------//		
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorDetails> DigiwalletExceptionHandler(LoginException loginException,WebRequest request){
		
		ErrorDetails err=new ErrorDetails(LocalDateTime.now(), loginException.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		
	}
	



}
