package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entily.DcKidsEntity;

@Repository
public interface DcKidsrepo  extends JpaRepository<DcKidsEntity, Integer> {
	
	List<DcKidsEntity> findByAppEntity_CaseNum(Integer caseNum);

}
