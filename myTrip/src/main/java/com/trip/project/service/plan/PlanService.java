package com.trip.project.service.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.plan.Plan;
import com.trip.project.dto.plan.PlanRepository;
import com.trip.project.dto.plan.PlanRequestDto;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;

	public int planAdd(PlanRequestDto plan) {
		int k =planRepository.planAdd(plan);
		System.out.println(k);
		System.out.println(plan.getPlan_name());
		return planRepository.findPlanId(plan.getPlan_name());
	}

	public int planDelete(int plan_id) {
		
		return planRepository.planDelete(plan_id);
	}

	public int addMember(String plan_name, int userId) {
		Plan plan = planRepository.findPlanNameAnduserId(plan_name,userId);
		return planRepository.addMember(plan_name,userId);
	}
	

}
