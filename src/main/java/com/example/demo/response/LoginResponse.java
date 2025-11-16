package com.example.demo.response;

import com.example.demo.enumpack.UserType;

import lombok.Data;

@Data
public class LoginResponse {
	
	private Integer userId;
	
	private String name;
	
	private UserType userType;
	
	
	
	private boolean isValidLogin;
	
	private boolean pwdChanged;
	
	private DashboardResponse dashboardResponse;
	
	
	
	
	
	
	
	
	

}
