package com.masai.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {

//	@NotNull
	private Integer accountNo;

//	@NotNull
	@Size(min = 5, max = 10,message = "Invalid IFSC Code [ 5-10 Characters only ]")
	private String IFSCCode;

//	@NotNull
	@Size(min = 3, max = 15,message = "Invalid Bank Name [ 3-15 characters only ]")
	private String bankName;

//	@NotNull
	private Double balance;

	
}
