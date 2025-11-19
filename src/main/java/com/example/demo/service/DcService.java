package com.example.demo.service;

import java.util.Map;

import com.example.demo.BindingClasses.Eduction;
import com.example.demo.BindingClasses.Income;
import com.example.demo.BindingClasses.PlanSelection;
import com.example.demo.BindingClasses.Summary;
import com.example.demo.BindingClasses.kids;

public interface DcService {

	
	public Map<Integer, String> getPlanName();
	
	public boolean updatePlanSelection(PlanSelection planSelection);
	
	public boolean saveIncome(Income income);
	
	public boolean saveEducation(Eduction eduction);
	
	public boolean saveKids(kids kids);
	
	public Summary getSummaryInfo(Integer caseNum);
}
