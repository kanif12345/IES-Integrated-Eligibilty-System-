package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entily.CitizenApp;
import java.util.List;
import java.util.Optional;


public interface citizenrepo extends JpaRepository<CitizenApp, Integer> {
	
	
	public Optional<CitizenApp> findBySsnNumber(Long ssnNumber);
	
	public List<CitizenApp> findByCreatedBy(Integer userid);
	

}
