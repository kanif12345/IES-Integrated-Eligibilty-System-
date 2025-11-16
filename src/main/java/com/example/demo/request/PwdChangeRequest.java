package com.example.demo.request;

import lombok.Data;

@Data
public class PwdChangeRequest {
	
	private Integer userId;
	
	private String email;
	
	private String pwd;
	
	private String confirmPwd;

}
