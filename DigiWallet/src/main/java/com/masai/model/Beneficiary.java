package com.masai.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {
	@Id
	   @NotNull
	   @Size(min = 10, max = 10 ,message = "Invalid Mobile Number [ 10 Digit Only ] ")
	   private String beneficiaryMobileNumber;
	   
	   @NotNull
	   @Size(min = 3, message = "Invalid Beneficiary name [ contains at least 3 characters ]")
	   private String beneficiaryName;


	   @ManyToOne(cascade = CascadeType.ALL)
	   @JoinColumn(name = "walletId" ,referencedColumnName = "walletId")
	   private Wallet wallet;
}
