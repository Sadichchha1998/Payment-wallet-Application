package com.masai.service;

import java.util.List;

import com.masai.exception.BeneficiaryException;
import com.masai.exception.CustomerException;
import com.masai.exception.WalletException;
import com.masai.model.Beneficiary;
import com.masai.model.BeneficiaryDTO;

public interface BeneficiaryService {

	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key) throws BeneficiaryException, CustomerException, WalletException;
	
	public Beneficiary deleteBeneficiary(String key, BeneficiaryDTO beneficiaryDTO) throws BeneficiaryException, CustomerException;
	
	public Beneficiary viewBeneficiary(String beneficiaryName, String key) throws BeneficiaryException, CustomerException;

	public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryException, CustomerException;
	
	public List<Beneficiary> findAllByWallet(Integer walletId) throws BeneficiaryException;
	
}
