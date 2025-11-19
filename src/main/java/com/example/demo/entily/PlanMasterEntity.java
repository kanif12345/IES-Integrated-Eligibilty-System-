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
public class PlanMasterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String ActiveSw;
	
	private String planCategory;
	
	@CreationTimestamp
	private LocalDate planStartDate;
	
	@UpdateTimestamp
	private LocalDate planEndDate;
	
	private String planName;


}
