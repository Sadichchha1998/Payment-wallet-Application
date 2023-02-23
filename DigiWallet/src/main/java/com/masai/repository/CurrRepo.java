package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.CustomerUserSession;

@Repository
public interface CurrRepo extends JpaRepository<CustomerUserSession, Integer>{

	public CustomerUserSession findByUuid(String uuid);

	@Query("FROM CustomerUserSession a WHERE a.userId=?1")
	public Optional<CustomerUserSession> findByUserId(Integer userId);
	
}
