package com.example.demo.serviceImpl;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.BindingClasses.Eduction;
import com.example.demo.BindingClasses.Income;
import com.example.demo.BindingClasses.Kid;
import com.example.demo.BindingClasses.PlanSelection;
import com.example.demo.BindingClasses.Summary;
import com.example.demo.BindingClasses.kids;
import com.example.demo.entily.AppEntity;
import com.example.demo.entily.DcEducationEntity;
import com.example.demo.entily.DcIncomeEntity;
import com.example.demo.entily.DcKidsEntity;
import com.example.demo.entily.PlanMasterEntity;


import com.example.demo.entily.UserInfoEntity;
import com.example.demo.repo.AppRepo;
import com.example.demo.repo.DcEducationrepo;
import com.example.demo.repo.DcIncomerepo;
import com.example.demo.repo.DcKidsrepo;
import com.example.demo.repo.PlanMasterrepo;
import com.example.demo.repo.UserInfoRepo;
import com.example.demo.repo.citizenrepo;
import com.example.demo.service.DcService;

@Service
public class DcServiceImpl implements DcService  {
	
	@Autowired
	private PlanMasterrepo masterrepo;
	
	@Autowired
	private AppRepo appRepo;
	
	
	 @Autowired
	 private DcIncomerepo dcIncomerepo;
	 
	 @Autowired
	 private UserInfoRepo infoRepo;
	 
	 @Autowired
	 private DcEducationrepo dcEducationrepo;
	 
	 @Autowired
	 private DcKidsrepo dcKidsrepo;
	
	

	@Override
	public Map<Integer, String> getPlanName() {
		List<PlanMasterEntity> all = masterrepo.findAll();
		
		Map<Integer, String> plansMap=new HashMap<>();
		
		for(PlanMasterEntity entity :all)
		{
			plansMap.put(entity.getId(), entity.getPlanName());
		}
		
		// TODO Auto-generated method stub
		return plansMap;
	}

	@Override
	public boolean updatePlanSelection(PlanSelection planSelection) { 
		
		Integer caseNum = planSelection.getCaseNum();
		
		Optional<AppEntity> byId = appRepo.findById(caseNum);
		
		UserInfoEntity userInfoEntity = infoRepo.findById(planSelection.getUserId()).get();
		
		if(byId.isPresent())
		{
			AppEntity appEntity = byId.get();
			
			appEntity.setPlanId(planSelection.getPlanId());
			
			appEntity.setUser(userInfoEntity);
			
			appRepo.save(appEntity);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean saveIncome(Income income) {
		
		DcIncomeEntity dcIncomeEntity=new DcIncomeEntity();
		
		BeanUtils.copyProperties(income, dcIncomeEntity);
		
		Integer caseNum = income.getCaseNum();
		Integer userId = income.getUserId();
		
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserInfoEntity userInfoEntity = infoRepo.findById(userId).get();
		
		
		dcIncomeEntity.setAppEntity(appEntity);
		dcIncomeEntity.setEntity(userInfoEntity);
		
		dcIncomerepo.save(dcIncomeEntity);
		
		return true;
	}

	@Override
	public boolean saveEducation(Eduction eduction) {
		
		
		Integer caseNum = eduction.getCaseNum();
		Integer userId = eduction.getUserId();
		
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserInfoEntity userInfoEntity = infoRepo.findById(userId).get();
		
		DcEducationEntity dcEducationEntity=new DcEducationEntity();
		
		BeanUtils.copyProperties(eduction, dcEducationEntity);
		
		dcEducationEntity.setAppEntity(appEntity);

		dcEducationEntity.setEntity(userInfoEntity);
		
		dcEducationrepo.save(dcEducationEntity);
		
		
		
		
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean saveKids(kids kids) {
		
		Integer caseNum = kids.getCaseNum();
		Integer userId = kids.getUserId();
		
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserInfoEntity userInfoEntity = infoRepo.findById(userId).get();
		
		List<Kid> kidsList = kids.getKids();
		
		for(Kid kid:kidsList)
		{
			DcKidsEntity dcKidsEntity=new DcKidsEntity();
			BeanUtils.copyProperties( kid, dcKidsEntity);
			
			dcKidsEntity.setAppEntity(appEntity);
			dcKidsEntity.setEntity(userInfoEntity);
			
			
			dcKidsrepo.save(dcKidsEntity);
		
			
		}	
		return true;
	}

	@Override
	public Summary getSummaryInfo(Integer caseNumInteger) {
		
		
		Summary summary=new Summary();
	 
		 AppEntity appEntity = appRepo.findById(caseNumInteger).get();	
		 
		 Integer planId = appEntity.getPlanId();
		 
		 PlanMasterEntity planMasterEntity = masterrepo.findById(planId).get();
		 
		 DcIncomerepo income = dcIncomerepo.findByCaseNum(caseNumInteger);
		 
		 DcEducationEntity byCaseNum2 = dcEducationrepo.findByAppEntity_CaseNum(caseNumInteger);
		 
		 List<DcKidsEntity> byCaseNum3 = dcKidsrepo.findByAppEntity_CaseNum(caseNumInteger);
		 
		 summary.setCaseNum(caseNumInteger);
		  summary.setPlanName(planMasterEntity.getPlanName());
		 
		  Income income2=new Income();
		  BeanUtils.copyProperties(income, income2);
		 
		  summary.setIncome(income2);
		  
		  Eduction eduction=new Eduction();
		   
		  BeanUtils.copyProperties(byCaseNum2, eduction);
		  
		
		  summary.setEduction(eduction);
		  
		List<Kid> list=new ArrayList();
		  for(DcKidsEntity entity:byCaseNum3)
		  {
			  Kid kids=new Kid();
			  BeanUtils.copyProperties(entity, kids);
			  list.add(kids);
		  }
		 
		  kids kids=new kids();
		  kids.setKids(list);
		  
		  summary.setKids(kids);
		 
		return summary;
	}

}
