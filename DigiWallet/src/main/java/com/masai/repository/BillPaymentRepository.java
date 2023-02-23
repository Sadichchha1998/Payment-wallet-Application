package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.BillPayment;

public interface BillPaymentRepository extends JpaRepository<BillPayment, Integer>{

}
