package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repo.citizenrepo;
import com.example.demo.request.CitizenAppDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.serviceImpl.AppRegServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/citizen")
public class CitizenAppController {
	
	
	@Autowired
	private AppRegServiceImpl appRegServiceImpl;
	
	
	@PostMapping("/app")
	public ResponseEntity<ApiResponse>createApplication(@RequestBody CitizenAppDto appDto)
	{
		ApiResponse citizenApp = appRegServiceImpl.createCitizenApp(appDto);
		
		return new ResponseEntity<>(citizenApp,citizenApp.isStatus()?HttpStatus.CREATED :HttpStatus.BAD_GATEWAY);

	}

	@GetMapping("/getapp/{userid}/{userType}")
	public ResponseEntity<List<CitizenAppDto>> getApplication(@PathVariable int id,@PathVariable String userType)
	{
		List<CitizenAppDto> applications = appRegServiceImpl.getApplications(id, userType);
		
		return new ResponseEntity<>(applications,HttpStatus.OK);

	}
}
