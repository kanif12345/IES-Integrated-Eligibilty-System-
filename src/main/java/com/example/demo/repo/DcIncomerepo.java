package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entily.DcIncomeEntity;
import java.util.List;


@Repository
public interface DcIncomerepo  extends JpaRepository<DcIncomeEntity, Integer>{

	
	public DcIncomerepo  findByCaseNum(Integer caseNum);
}
