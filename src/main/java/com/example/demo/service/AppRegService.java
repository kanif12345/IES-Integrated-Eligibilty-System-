package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entily.CitizenApp;
import com.example.demo.request.CitizenAppDto;
import com.example.demo.response.ApiResponse;
@Repository
public interface AppRegService {
	
	public ApiResponse createCitizenApp(CitizenAppDto app);
	
	public List<CitizenAppDto> getApplications(int id,String userType);
	
	
	
	
	
	

}
