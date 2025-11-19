package com.example.demo.entily;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "IES_APPS")
public class AppEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer caseNum;
	
	private String fullName;
	
	private String emailId;
	
	private Long phno;
	
	private String gender;
	
	private LocalDate dob;
	
	private Long ssn;
	
	private Integer planId;
	
	@ManyToOne
	@JoinColumn(name="user-id")
	private UserInfoEntity user;
	
	 
	
	

}
