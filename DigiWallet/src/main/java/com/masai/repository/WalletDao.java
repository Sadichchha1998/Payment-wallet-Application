package com.masai.repository;

import com.masai.model.Customer;
import com.masai.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDao extends JpaRepository<Wallet,Integer> {

	 public Wallet findByCustomer(Customer customer);

	public Wallet showCustomerWalletDetails(Integer userId);
}