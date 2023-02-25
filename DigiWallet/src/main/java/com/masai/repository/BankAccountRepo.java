package com.masai.repository;

import com.masai.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer>{
	
	public BankAccount  findByWallet(Integer walletId);
	public BankAccount findAllByWallet(Integer walletId);

}
