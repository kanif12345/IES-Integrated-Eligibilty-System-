package com.example.demo.entily;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CitizenApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int caseNum;
	
	private String name;
	
	private String emailId;
	
	private Long phno;
	
	private String gender;
	
	private LocalDate citizenDob;
	
	private Long ssnNumber;
	
	@CreationTimestamp
	private LocalDate createbDate;
	
	@UpdateTimestamp
	private LocalDate updateDate;
	
	private Integer createdBy;
	
	
	
	
	

}
