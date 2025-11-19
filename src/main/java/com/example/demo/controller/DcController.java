package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.BindingClasses.Eduction;
import com.example.demo.BindingClasses.Income;
import com.example.demo.BindingClasses.PlanSelection;
import com.example.demo.BindingClasses.kids;
import com.example.demo.entily.DcIncomeEntity;
import com.example.demo.entily.PlanMasterEntity;

import com.example.demo.response.ApiResponse;

import com.example.demo.serviceImpl.DcServiceImpl;


import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/dc")
public class DcController {

   
	
	@Autowired
	private DcServiceImpl dcService;

	@GetMapping("/plan-names")
	public ResponseEntity<Map<Integer, String>> getPlanNames()
	{
		Map<Integer, String> planName = dcService.getPlanName();
		
		return new ResponseEntity<>(planName,HttpStatus.OK);
	}
	
	@PostMapping("/plan-selection")
	public ResponseEntity<String> updatePlanSelection(@RequestBody PlanSelection entity)
	{
		
		boolean updatePlanSelection = dcService.updatePlanSelection(entity);
		
		if(updatePlanSelection)
		{
			return new ResponseEntity<>("plan Selection Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("plan Selection failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/income")
	public ResponseEntity<ApiResponse> saveIncome(@RequestBody Income dcIncomeEntity) {

	    boolean saveIncome = dcService.saveIncome(dcIncomeEntity);

	    if (saveIncome) {
	      
	    	ApiResponse response =new ApiResponse(null, true, "Income saved successfully");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        ApiResponse response = new ApiResponse(null,false, "Failed to save income");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}
	
	@PostMapping("/eduction")
	public ResponseEntity<ApiResponse> saveEduction(@RequestBody Eduction eduction) {

	    boolean saveIncome = dcService.saveEducation(eduction);

	    if (saveIncome) {
	      
	    	ApiResponse response =new ApiResponse(null, true, "Eduction saved successfully");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        ApiResponse response = new ApiResponse(null,false, "Failed to save Eduction");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}
	
	@PostMapping("/kids")
	public ResponseEntity<ApiResponse> saveKids(@RequestBody kids kids) {

	    boolean saveIncome = dcService.saveKids(kids);

	    if (saveIncome) {
	      
	    	ApiResponse response =new ApiResponse(null, true, "kids saved successfully");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        ApiResponse response = new ApiResponse(null,false, "Failed to save kids");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	}

	

}
