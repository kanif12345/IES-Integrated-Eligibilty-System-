package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entily.UserInfoEntity;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserInfoRepo  extends JpaRepository<UserInfoEntity, Integer>{
	
	
	//public UserInfoEntity  findByEmail(String email);
	
	public Optional<UserInfoEntity> findByEmail(String email);

	
	
}
