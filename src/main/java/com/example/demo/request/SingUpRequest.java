package com.example.demo.request;

import java.time.LocalDate;

import lombok.Data;



@Data
public class SingUpRequest {

    private Integer userid;

    private String name;

    private String email;

    private String pwd;

    private String gender;

    private LocalDate dob;

    private Long phNo;

    private Long ssn;

    private String userName;

    private boolean pwdChanged;

    private String userType;
}
