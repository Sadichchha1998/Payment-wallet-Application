package com.masai.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;




import org.springframework.http.ResponseEntity;


import com.masai.exception.TransactionException;
import com.masai.model.Transaction;
import com.masai.model.TransactionDTO;
import com.masai.service.TransactionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
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

}
