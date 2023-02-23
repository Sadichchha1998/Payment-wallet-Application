package com.masai.repository;

import com.masai.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {

    public Customer findByMobileNumber(String mobileNumber);
}