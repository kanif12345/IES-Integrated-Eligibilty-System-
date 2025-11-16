package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.controller.UserController;
import com.example.demo.entily.CitizenApp;
import com.example.demo.repo.citizenrepo;
import com.example.demo.request.CitizenAppDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AppRegService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service

public class AppRegServiceImpl implements AppRegService{

    private final UserController userController;
	
	@Autowired
	private citizenrepo citizenrepo;
	
	
	private String SSA_API_URL="HTTT";

    AppRegServiceImpl(UserController userController) {
        this.userController = userController;
    }

	@Override
	public ApiResponse createCitizenApp(CitizenAppDto app)
	{
		
		
		Long ssnNumber = app.getSsnNumber();
		
		Optional<CitizenApp> bySsnNumber = citizenrepo.findBySsnNumber(ssnNumber);
		
		if(bySsnNumber.isPresent())
		{
			
			return ApiResponse.builder().message("Duplicate application").status(false).build();
		}
		
		else
		{
		
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> forEntity = restTemplate.getForEntity(SSA_API_URL, String.class,ssnNumber);
		
		String body = forEntity.getBody();
		
		if(body.equals("MH"))
		{
			CitizenApp entity=new CitizenApp();
			
			// Copy Data to entity
			BeanUtils.copyProperties(app, entity);
			
			
		CitizenApp save = citizenrepo.save(entity);
			
			return ApiResponse.builder().message("Application Created with case Number"+save.getCaseNum()).status(true).build();
			
			
		}
		else
		{
			return ApiResponse.builder().message("Invalid SSn Number....").status(false).build();
		}
		}
		
	}

	@Override
	public List<CitizenAppDto> getApplications(int id, String userType) {
		
		List<CitizenApp> entities=null;
		
		List<CitizenAppDto> apps= new ArrayList<>();
		
		
		// if it admin get all 
		if("Admin".equals(userType))
		{
			 entities=  citizenrepo.findAll();
		}
//			 if it admin get all 
		else {
			 entities= citizenrepo.findByCreatedBy(id);
		}
		
		for(CitizenApp entity:entities)
		{
			CitizenAppDto appDto=new CitizenAppDto();
			BeanUtils.copyProperties(entity, appDto);
			apps.add(appDto);
		}		
		return apps;
	}

}
