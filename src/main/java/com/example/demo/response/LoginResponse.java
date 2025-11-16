package com.example.demo.response;

import lombok.Data;

@Data
public class LoginResponse {
	
	private Integer userId;
	
	private String name;
	
	private String userType;
	
	
	
	private boolean isValidLogin;
	
	private boolean pwdChanged;
	
	private DashboardResponse dashboardResponse;
	
	
	
	
	
	
	
	
	

}
