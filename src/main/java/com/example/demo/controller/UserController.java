package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.request.PwdChangeRequest;
import com.example.demo.request.SingUpRequest;
import com.example.demo.request.loginRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.serviceImpl.UserServiceImpl;

import org.springframework.web.bind.annotation.RequestBody;  


@RestController
@RequestMapping("/user")
public class UserController {

    
	
	@Autowired
	private UserServiceImpl serviceImpl;


	@PostMapping("/save")
	public ResponseEntity<ApiResponse>userRespose (@RequestBody SingUpRequest application)
	{
		System.out.println(application.getEmail());
		
		ApiResponse saveUser = serviceImpl.saveUser(application);
		
		return ResponseEntity.ok(saveUser);
		

	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody loginRequest loginRequest)
	{
		LoginResponse userLogin = serviceImpl.userLogin(loginRequest);
		
		return ResponseEntity.ok(userLogin);
		
		
	}
	
	
	@GetMapping("/recover-pwd")
	public ResponseEntity<ApiResponse> recoverPwd(@PathVariable String pwd)
	{
		ApiResponse recoverPwd = serviceImpl.recoverPwd(pwd);
		
		return ResponseEntity.ok(recoverPwd);
		
		
	} 
	
	
	@PostMapping("/updatePwd")
	public ResponseEntity<LoginResponse> updatePwd( PwdChangeRequest changeRequest)
	{
		
		LoginResponse updatePwd = serviceImpl.updatePwd(changeRequest);
		
		return ResponseEntity.ok(updatePwd);
		
	}

}
