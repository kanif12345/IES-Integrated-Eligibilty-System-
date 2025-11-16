package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@RestController
@RequestMapping("/home")

public class Demo {
	
	
	
	@GetMapping("/get")
	public String getuser()
	{
		return "uses";
	}
}
