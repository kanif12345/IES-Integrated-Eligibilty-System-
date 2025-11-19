package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    
	
	@Autowired
	private UserServiceImpl serviceImpl;

	@PostMapping("/save")
	public ResponseEntity<ApiResponse> userResponse(@RequestBody SingUpRequest application) {

	    ApiResponse saveUser = serviceImpl.saveUser(application);

	    if (saveUser != null) {
	        return ResponseEntity.ok(saveUser);  
	    } else {
	        return new ResponseEntity<>(saveUser, HttpStatus.INTERNAL_SERVER_ERROR); 
	    }
	}

	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody loginRequest loginRequest)
	{
		LoginResponse userLogin = serviceImpl.userLogin(loginRequest);
		
		return new ResponseEntity<>(userLogin,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/recover-pwd")
	public ResponseEntity<ApiResponse> recoverPwd(@PathVariable String email)
	{
		ApiResponse recoverPwd = serviceImpl.recoverPwd(email);
		
		return new ResponseEntity<>( recoverPwd, recoverPwd.isStatus()?HttpStatus.CREATED :HttpStatus.BAD_GATEWAY);

		
		
	} 
	
	
	@PostMapping("/updatePwd")
	public ResponseEntity<LoginResponse> updatePwd( PwdChangeRequest changeRequest)
	{
		
		LoginResponse updatePwd = serviceImpl.updatePwd(changeRequest);
		
		return ResponseEntity.ok(updatePwd);
		
	}

}
