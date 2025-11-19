package com.example.demo.entily;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class DcIncomeEntity {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer caseNum;
	
	private Integer useId;
	
	private Double salaryIncome;
	
	private Double rentIncome;
	
	private Double propertyIncome;
	
	@OneToOne
	@JoinColumn(name="case_num")
	private AppEntity appEntity;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfoEntity entity;
	

}
