package com.masai.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccount {
	@Id
	@NotNull
	private Integer accountNo;

	@NotNull
	@Size(min = 5, max = 10,message = "Invalid IFSC Code [ 5-10 Characters only ]")
	private String IFSCCode;
	
	@NotNull
	@Size(min = 3, max = 15,message = "Invalid Bank Name [ 3-15 characters only ]")
	private String bankName;
	
	@NotNull
	private Double balance;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
	private Wallet wallet;

}
