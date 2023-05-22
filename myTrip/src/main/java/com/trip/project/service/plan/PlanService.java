package com.trip.project.service.plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.attraction.AttractionDto;
import com.trip.project.dto.plan.Plan;
import com.trip.project.dto.plan.PlanRepository;
import com.trip.project.dto.plan.PlanRequestDto;
import com.trip.project.dto.plan.UserPlanList;

@Service
public class PlanService {
	
	@Autowired
	private PlanRepository planRepository;

	public int planDelete(int plan_id) {
		
		return planRepository.planDelete(plan_id);
	}

	public int addMember(String plan_name, int userId) {
		Plan plan = planRepository.findPlanNameAnduserId(plan_name,userId);
		return planRepository.addMember(plan_name,userId);
	}

	public void addPlan(String planName, LocalDate startDate, LocalDate endDate, int userId) {
		
		planRepository.addPlan(planName,startDate,endDate,userId);
		
		
	}

	public int findUserId(String name) {
		int id = planRepository.findUserId(name);
		return id;
	}
	
	public List<AttractionDto> getAttraction(double lat, double lng) {
		int EARTH_RADIUS=6371;
		int distance=500;
		double mLat=(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)))/ 1000;
		double mLng=(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)* Math.cos(Math.toRadians(lat))))/ 1000;
		double maxY = mLat +(distance* mLat);
		double minY = mLat -(distance* mLat);
		double maxX = mLng +(distance* mLng);
		double minX = mLng -(distance* mLng);
		
		List<AttractionDto> attractionDtos = planRepository.findAttraction(lat+minY,lat+maxY,lng+minX,lng+maxX);
		System.out.println(attractionDtos);
		
		return attractionDtos;
	}

	public ArrayList<Integer> getUserPlanList(long userId) {
		return planRepository.getUserPlan(userId);
	}

	public UserPlanList getUserplan(int planId) {
		return planRepository.getUserPlanData(planId);
	}
	

}
