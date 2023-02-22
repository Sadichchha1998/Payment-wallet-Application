package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, String>{

}
