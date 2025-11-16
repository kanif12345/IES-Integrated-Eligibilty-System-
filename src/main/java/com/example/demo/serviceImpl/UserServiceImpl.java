package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EmailUtil.EmailUtil;
import com.example.demo.entily.UserInfoEntity;
import com.example.demo.repo.UserInfoRepo;
import com.example.demo.request.PwdChangeRequest;
import com.example.demo.request.SingUpRequest;
import com.example.demo.request.loginRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.DashboardResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.userService;

import io.swagger.annotations.Example;

@Service
public class UserServiceImpl implements userService {
	
	@Autowired
	private  UserInfoRepo infoRepo;
	
	
	@Autowired
	private EmailUtil emailUtil;
	
	
	@Override
	public ApiResponse saveUser(SingUpRequest request) 
	{
	    UserInfoEntity entity = new UserInfoEntity();

	    // Set all simple fields
	    entity.setName(request.getName());
	    entity.setDob(request.getDob());
	    entity.setEmail(request.getEmail());
	    entity.setGender(request.getGender());
	    entity.setPhNo(request.getPhNo());
	    entity.setSsn(request.getSsn());
	    entity.setUserName(request.getUserName());
	    entity.setUserType(request.getUserType());

	    // Generate temp password
	    String tempPwd = generateTempPwd();

	    // Save temp password in both request and entity
	    request.setPwd(tempPwd);
	    request.setPwdChanged(false);

	    entity.setPwd(tempPwd);          // 
	    entity.setPwdChanged(false);     // 

	    // Save user
	    infoRepo.save(entity);

	    // Email sending
	    String subject = "IES - Account Created";
	    String body = "Your temporary password is: " + tempPwd;
   
	    String email = request.getEmail();
	    
	  

	    emailUtil.sendEmail(email, subject, body);

	    return ApiResponse.builder()
	            .message("User Saved")
	            .status(true)
	            .build();
	}


	@Override
	public LoginResponse userLogin(loginRequest loginRequest) 
	
	
	{
		LoginResponse response=new LoginResponse();
		UserInfoEntity entity =new UserInfoEntity();
		
		entity.setEmail(loginRequest.getEmail());
		entity.setPwd(loginRequest.getPwd());
		
		org.springframework.data.domain.Example<UserInfoEntity> of = org.springframework.data.domain.Example.of(entity);
		
		List<UserInfoEntity> all = infoRepo.findAll(of);
		
		if(!all.isEmpty())
		{
			UserInfoEntity userInfoEntity = all.get(0);
			
			if(userInfoEntity.isPwdChanged())
			{
				// second login
				
				response.setPwdChanged(true);
				response.setValidLogin(true);
				response.setUserId(userInfoEntity.getUserid());
				response.setUserType(userInfoEntity.getUserType());
				
				// set dashboard data
				DashboardResponse dashboardResponse=new DashboardResponse();
				
				dashboardResponse.setPlansCount(6);
				dashboardResponse.setDenefitAmtTotal(3400.00);
				dashboardResponse.setCitizenApCnt(10000l);
				dashboardResponse.setCitizenDpCnt(555222l);
				
				response.setDashboardResponse(dashboardResponse);
			}
			else {
				// first login
				response.setPwdChanged(false);
				response.setValidLogin(true);
				
				
			}
		}
		else {
			response.setValidLogin(false);
		}
		// TODO Auto-generated method stub
		
		return response;
	}

	@Override
	public LoginResponse updatePwd(PwdChangeRequest changeRequest)
	{
		
		LoginResponse response= new LoginResponse();
		Integer userId = changeRequest.getUserId();
		
		Optional<UserInfoEntity> byId = infoRepo.findById(userId);
		
		if(byId.isPresent())
		{
			UserInfoEntity userInfoEntity = byId.get();
			
			userInfoEntity.setPwd(changeRequest.getPwd());
			userInfoEntity.setPwdChanged(true);
			infoRepo.save(userInfoEntity);
			
			
			
			response.setUserType( userInfoEntity.getUserType());
			response.setUserId(userInfoEntity.getUserid());
		      response.setValidLogin(true);
			
			
			DashboardResponse dashboardResponse=new DashboardResponse();
			
			dashboardResponse.setPlansCount(6);
			dashboardResponse.setDenefitAmtTotal(3400.00);
			dashboardResponse.setCitizenApCnt(10000l);
			dashboardResponse.setCitizenDpCnt(555222l);
			
			response.setDashboardResponse(dashboardResponse);
			
			
		}
		
		
		return response;
	}

	@Override
	public ApiResponse recoverPwd(String emial)
	{
		UserInfoEntity byEmail = infoRepo.findByEmail(emial);
		
		if(byEmail==null)
		{
			return ApiResponse.builder().message("Given Email Id not found..").status(false).build();
		}
		
		String subject="IES RecoverPwd";
		
		String body="Your Password"+byEmail.getPwd();
		
		
		emailUtil.sendEmail(emial, subject, body);
		
		return ApiResponse.builder().message("Pwd send to your Email..").status(false).build();
	}
	
	public String generateTempPwd()
	{
		

		    // create a string of all characters
		    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		    // create random string builder
		    StringBuilder sb = new StringBuilder();

		    // create an object of Random class
		    Random random = new Random();

		    // specify length of random string
		    int length = 5;

		    for(int i = 0; i < length; i++) {

		      // generate random index number
		      int index = random.nextInt(alphabet.length());

		      // get character specified by index
		      // from the string
		      char randomChar = alphabet.charAt(index);

		      // append the character to string builder
		      sb.append(randomChar);
		    }

		    String randomString = sb.toString();
		  
			return randomString;

		  }
		
	

}
