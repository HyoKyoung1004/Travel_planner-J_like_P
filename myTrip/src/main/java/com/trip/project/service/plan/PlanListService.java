package com.trip.project.service.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.project.dto.plan.Plan;
import com.trip.project.dto.plan.PlanList;
import com.trip.project.dto.plan.PlanListRepository;
import com.trip.project.dto.plan.PlanListRequestDto;
import com.trip.project.dto.plan.PlanRequestDto;

@Service
public class PlanListService {
	@Autowired
	private PlanListRepository planListRepository;
	
	@Transactional
	public int planAdd(PlanListRequestDto pList) {
		for(int i=0; i<pList.getContent_id().size(); i++) {
			PlanList planList = new PlanList(i+1,pList.getContent_id().get(i),pList.getPlan_id());
			planListRepository.planAdd(planList);
		}
		return 1;
	}

}
