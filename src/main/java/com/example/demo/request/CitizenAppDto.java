package com.example.demo.request;

import java.time.LocalDate;



import lombok.Data;

@Data
public class CitizenAppDto {
	

	private String name;
	
	private String emailId;
	
	private Long phno;
	
	private String gender;
	
	private LocalDate citizenDob;
	
	private Long ssnNumber;
	
	
	private LocalDate createbDate;
	
	
	private LocalDate updateDate;
	
	private Integer createdBy;

}
