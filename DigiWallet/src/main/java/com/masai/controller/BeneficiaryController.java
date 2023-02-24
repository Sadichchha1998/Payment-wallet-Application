package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.BeneficiaryException;
import com.masai.exception.CustomerException;
import com.masai.exception.WalletException;
import com.masai.model.Beneficiary;
import com.masai.model.BeneficiaryDTO;
import com.masai.service.BeneficiaryService;

import jakarta.validation.Valid;

@RestController
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@PostMapping("/addBeneficiary")
	public ResponseEntity<Beneficiary> addBeneficiaryMapping(Beneficiary beneficiary, String key)throws BeneficiaryException, CustomerException, WalletException{
		
		return new ResponseEntity<Beneficiary>(beneficiaryService.addBeneficiary(beneficiary, key), HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/deleteBeneficiary")
	public ResponseEntity<Beneficiary> deleteBeneficiaryMapping(@Valid @RequestBody BeneficiaryDTO beneficiaryDTO,@RequestParam String key)throws BeneficiaryException, CustomerException, WalletException{
		
		return new ResponseEntity<Beneficiary>(beneficiaryService.deleteBeneficiary(key, beneficiaryDTO), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/viewBeneficiary/name")
	public ResponseEntity<Beneficiary> viewBeneficiaryMapping(@RequestParam String beneficiaryName,@RequestParam String key)throws BeneficiaryException, CustomerException{
		
		return new ResponseEntity<Beneficiary>(beneficiaryService.viewBeneficiary(beneficiaryName, key), HttpStatus.OK);
	}
	
	
	@GetMapping("/viewAllBeneficiary")
	public ResponseEntity<List<Beneficiary>> viewAllBeneficiaryMapping(@RequestParam String key)throws BeneficiaryException, CustomerException{
		
		return new ResponseEntity<List<Beneficiary>>(beneficiaryService.viewAllBeneficiary(key), HttpStatus.OK);
	}
}
