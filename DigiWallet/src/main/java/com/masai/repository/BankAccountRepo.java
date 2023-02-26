package com.masai.repository;

import com.masai.model.BankAccount;
import com.masai.model.Wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer>{
	
	public BankAccount  findByWallet(Wallet walletId);
	public BankAccount findByAccountNo(Integer accountNo);
}
