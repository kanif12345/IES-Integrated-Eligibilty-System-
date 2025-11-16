package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entily.UserInfoEntity;
import java.util.List;


@Repository
public interface UserInfoRepo  extends JpaRepository<UserInfoEntity, Integer>{
	
	
	public UserInfoEntity  findByEmail(String email);

	
	
}
