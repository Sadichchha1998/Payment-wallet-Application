package com.masai.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.exception.TransactionException;
import com.masai.exception.WalletException;
import com.masai.model.Transaction;
import com.masai.model.TransactionDTO;
import com.masai.service.TransactionService;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	
	//-------------------------------Find Transaction into the wallet------------------------------------//
	
	@PostMapping("/wallet/{key}")
	public ResponseEntity<List<TransactionDTO>> viewByWallet(@PathVariable String key) throws TransactionException, WalletException, CustomerException{

		List<Transaction> transactions= transactionService.findByWallet(key);

		List<TransactionDTO> transactionDTOS = new ArrayList<>();

		for(Transaction t:transactions) {

			TransactionDTO transactionDTO = new TransactionDTO(t.getTransactionId(), t.getTransactionType(), t.getTransactionDate(),t.getAmount(), t.getDescription() );
			transactionDTOS.add(transactionDTO);
		}
		return new ResponseEntity<List<TransactionDTO>>(transactionDTOS,HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TransactionDTO>>  viewAllTransactionByAdmin() throws TransactionException{

		List<Transaction>  transactions = transactionService.viewAllTransaction();

		List<TransactionDTO> transactionDTOS = new ArrayList<>();

		for(Transaction t:transactions) {
			TransactionDTO transactionDTO = new TransactionDTO(t.getTransactionId(), t.getTransactionType(), t.getTransactionDate(),t.getAmount(), t.getDescription() );

			transactionDTOS.add(transactionDTO);
		}
		return new ResponseEntity<List<TransactionDTO>>(transactionDTOS, HttpStatus.OK);
	}
	//------------------------------------   View Transaction - Between two dates ---------------------------------------//
	
	@GetMapping("/between/{key}/{one}/{two}")
	public ResponseEntity<List<TransactionDTO>> viewByTwoDate(@PathVariable("key") String key, @PathVariable("one") String one, @PathVariable("two")  String two) throws TransactionException, CustomerException{

		LocalDate firstDate= LocalDate.parse(one);
		LocalDate secondDate = LocalDate.parse(two);
		List<Transaction> transactions = transactionService.viewTransactionBetweenDate(key,firstDate, secondDate);
		
		List<TransactionDTO> transactionDTOS = new ArrayList<>();
		for(Transaction t:transactions) {

			TransactionDTO transactionDTO = new TransactionDTO(t.getTransactionId(), t.getTransactionType(), t.getTransactionDate(),t.getAmount(), t.getDescription() );

			transactionDTOS.add(transactionDTO);
		}
		return new ResponseEntity<List<TransactionDTO>>(transactionDTOS, HttpStatus.ACCEPTED);
	
	}
	

	//----------------------------------------  Get Transaction By Type  --------------------------------------------//
	@GetMapping("type/{key}")
	public ResponseEntity<List<TransactionDTO>> viewAllTransacationByType(@PathVariable("key") String key, @RequestParam("type") String type) throws TransactionException, CustomerException{

		List<Transaction> transactions = transactionService.findByTransactionType(key,type);

		List<TransactionDTO> transactionDTOS = new ArrayList<>();
		for(Transaction t:transactions) {

			TransactionDTO transactionDTO = new TransactionDTO(t.getTransactionId(), t.getTransactionType(), t.getTransactionDate(),t.getAmount(), t.getDescription() );

			transactionDTOS.add(transactionDTO);
		}
		return new ResponseEntity<List<TransactionDTO>>(transactionDTOS, HttpStatus.ACCEPTED);

	}
	


	//-----------------------------------------   Find Transaction by using Transaction ID---------------------------------------------//
	@GetMapping("/transactionId")
	public ResponseEntity<TransactionDTO> findById(@RequestParam String key, @RequestParam Integer transactionId) throws TransactionException, CustomerException{

		Transaction t = transactionService.findByTransactionId(key,transactionId);
		TransactionDTO transactionDTO = new TransactionDTO(t.getTransactionId(), t.getTransactionType(), t.getTransactionDate(),t.getAmount(), t.getDescription() );


		return new ResponseEntity<TransactionDTO>(transactionDTO, HttpStatus.CREATED);
	}

}
