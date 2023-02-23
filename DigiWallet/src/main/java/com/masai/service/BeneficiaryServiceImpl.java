package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.BeneficiaryException;
import com.masai.exception.CustomerException;
import com.masai.exception.WalletException;
import com.masai.model.Beneficiary;
import com.masai.model.BeneficiaryDTO;
import com.masai.repository.BeneficiaryRepository;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{

	@Autowired
	private BeneficiaryRepository beneficiaryRepo;
	
	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key)
			throws BeneficiaryException, CustomerException, WalletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beneficiary deleteBeneficiary(String key, BeneficiaryDTO beneficiaryDTO)
			throws BeneficiaryException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beneficiary viewBeneficiary(String beneficiaryName, String key)
			throws BeneficiaryException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Beneficiary> findAllByWallet(Integer walletId) throws BeneficiaryException {
		// TODO Auto-generated method stub
		return null;
	}

}
