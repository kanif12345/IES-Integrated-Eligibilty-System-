package com.example.demo.entily;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "User_Infoo")
@Data
public class UserInfoEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	
	private String name;
	
	private String email;
	
	private String pwd;
	
	private String gender;
	
	private LocalDate dob;
	
	private Long phNo;
	
	private Long ssn;
	
	private String userName;
	
	private boolean pwdChanged;
	
	private String userType;
	
	
	
	
	
	

}
