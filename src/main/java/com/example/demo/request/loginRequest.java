package com.example.demo.request;

import lombok.Data;

@Data
public class loginRequest {
	

private String email;
private String pwd;
private boolean pwdChange;
private boolean validlogin;
private String userType;
private int userId;

}
