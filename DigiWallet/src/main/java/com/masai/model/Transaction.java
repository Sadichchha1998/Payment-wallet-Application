package com.masai.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer transactionId;

	   private String transactionType;
@JsonFormat(pattern = "dd-MM-yyyy")
	   private LocalDate transactionDate;

	   private double amount;

	   private String Description;
	   

	   @ManyToOne(cascade = CascadeType.ALL)
	   private Wallet wallet;

}
