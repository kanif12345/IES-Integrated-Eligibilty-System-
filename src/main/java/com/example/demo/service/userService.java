package com.example.demo.service;

import com.example.demo.request.PwdChangeRequest;
import com.example.demo.request.SingUpRequest;
import com.example.demo.request.loginRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.LoginResponse;

public interface userService {
	
	
		public ApiResponse saveUser(SingUpRequest request);
		
		public LoginResponse userLogin(loginRequest loginRequest);
		
		
		// only login first time updatepwd method
		public LoginResponse updatePwd(PwdChangeRequest changeRequest);
		
		
		public ApiResponse recoverPwd(String emial);
		
		
		
		

}
