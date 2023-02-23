package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUserSession {

	@Id	
	@Column(unique = true)
	private Integer userId;
	
	private String uuid;
	
	private LocalDateTime localDateTime;

	public void delete(CustomerUserSession currRepo) {
		// TODO Auto-generated method stub
		
	}

	public CustomerUserSession findByUuid(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
