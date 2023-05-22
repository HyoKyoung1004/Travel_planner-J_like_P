package com.trip.project.controller.plan;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.plan.PlanListRequestDto;
import com.trip.project.dto.plan.PlanRequestDto;
import com.trip.project.service.plan.PlanListService;
import com.trip.project.service.plan.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PlanService planservice;
	
	@Autowired
	private PlanListService planListService;
	
	
	@PostMapping("/planadd")
	public int planAdd(@RequestBody PlanRequestDto plan) {
		System.out.println(plan.getPlan_name());
		int k = planservice.planAdd(plan);
		PlanListRequestDto pList = new PlanListRequestDto(k,plan.getPlan_name(),plan.getContent_id());
		planListService.planAdd(pList);
		return 1;
	}
	
	@PostMapping("/test")
	public void test(@RequestBody Map<String, Object> test) {
		System.out.println(test.get("startDate").getClass());
		
		ArrayList<ArrayList<Object>> obj = (ArrayList<ArrayList<Object>>) test.get("planlist");
		System.out.println(obj);
		for(int i=0;i<obj.size();i++) {
            for(int j=0;j<obj.get(i).size();j++) {
                Integer contentId = ((LinkedHashMap<String, Integer>)(obj.get(i).get(j))).get("content_id");
                Integer num = ((LinkedHashMap<String, Integer>)(obj.get(i).get(j))).get("num");
                //System.out.println(contentId+", "+num);

            }
        }
	}
	
	@DeleteMapping("/delete/{plan_id}")
	public int planDelete(@PathVariable("plan_id") int plan_id) {
		return planservice.planDelete(plan_id);
	}
	
	@GetMapping("/callMember/{plan_name}/{userId}")
	public int addMember(@PathVariable("plan_name")String plan_name,@PathVariable("userId")int userId) {
		return planservice.addMember(plan_name,userId);
	}
	
	
	@GetMapping("/myplanList/${userId}")
	public ResponseEntity<?> myPlanList(@PathVariable("userId") long userId){
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	


}
