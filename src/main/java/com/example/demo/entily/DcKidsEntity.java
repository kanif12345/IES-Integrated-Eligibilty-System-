package com.example.demo.entily;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class DcKidsEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidId;
	
    private String kidName;
	
	private Date kidDob;
	
	private Integer kidSsn;
	
	
	@OneToOne
	@JoinColumn(name="case_num")
	private AppEntity appEntity;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfoEntity entity;
	
	
	

}
