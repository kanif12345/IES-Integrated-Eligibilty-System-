package com.example.demo.response;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApiResponse {
	
	
	 private String  message;
	   private boolean status;
	   private Object data;
}
