package com.trip.project.service.plan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trip.project.dto.attraction.AttractionRepository;
import com.trip.project.dto.attraction.RouteDistanceDto;
import com.trip.project.dto.plan.PlanListRepository;
import com.trip.project.dto.plan.PlanListRequestDto;
import com.trip.project.dto.plan.PlanRepository;
import com.trip.project.service.attraction.AttractionService;
import com.trip.project.service.team.TeamService;

@Service
public class PlanListService {
	@Autowired
	private PlanListRepository planListRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private AttractionRepository attractionRepository;
	
	@Autowired
	private AttractionService attractionService;
	
	@Autowired
	private TeamService teamservice;
	
	@Transactional
	public int planAdd(PlanListRequestDto pList) {
		for(int i=0; i<pList.getContent_id().size(); i++) {
			//PlanList planList = new PlanList(i+1,pList.getContent_id().get(i),pList.getPlan_id());
			//planListRepository.planAdd(planList);
		}
		return 1;
	}

	public void addPlanList(Map<String, Object> test) {
		String plan_name = (String) test.get("plan_name");
		
		int plan_id = planRepository.findPlanId(plan_name);
		System.out.println(plan_id);
		
		//teamservice.teamAdd(plan_id);
		
		planSave(plan_name,plan_id,test);
		
    	int userId = (int) test.get("userId");
    	planRepository.addPlanUser(userId,plan_id );

		//여기부터가 수정 
//		int teamId = planRepository.findPlanId(plan_name);
		
	}
  

	public List<Integer> isRoute(List<Integer> point) {
        List<RouteDistanceDto> distanceDtos = getLatAndLong(point);
        for (RouteDistanceDto route : distanceDtos) {
            System.out.println(route.toString());
        }
        
        List<Integer> dtos=findShorPath(distanceDtos);
        
        /*
         * for(Integer dto: dtos) { System.out.println(dto); }
         */
        return dtos;
    }
	 private List<Integer> findShorPath(List<RouteDistanceDto> distanceDtos) {
	        List<Integer> dtos = new ArrayList<>();
	        int num=0;
	        boolean[] isCheck = new boolean[distanceDtos.size()];
	        isCheck[0]=true;
	        dtos.add(distanceDtos.get(0).getContent_id());
	        while(true) {
	            boolean isFlag=true;
	            double tmp=Integer.MAX_VALUE;
	            int value=0;
	            for (int i = 0; i < distanceDtos.size(); i++) {
	                if(isCheck[i] || num==i) {
	                    continue;
	                }
	                double distance =harversineCal(distanceDtos.get(num).getLatitude(), distanceDtos.get(num).getLongitude(), distanceDtos.get(i).getLatitude(), distanceDtos.get(i).getLongitude());
	                if(tmp>=distance) {
	                    tmp=distance;
	                    value=i;
	                }
	                
	            }
	            System.out.println("value: "+ value);
	            isCheck[value]=true;
	            num=value;
	            dtos.add(distanceDtos.get(value).getContent_id());
	            for (int i = 0; i < isCheck.length; i++) {
	                if(isCheck[i]==false) {
	                    isFlag=false;
	                    break;
	                }
	                
	            }
	            if(isFlag) {
	                break;
	            }
	        }
	        
	        
	        return dtos;
	    }

	    private List<RouteDistanceDto> getLatAndLong(List<Integer> point) {
	        RouteDistanceDto tmp= new RouteDistanceDto();
	        List<RouteDistanceDto> distanceDtos = new ArrayList<>();
	        for (Integer p:point) {
	            tmp=attractionRepository.isRoute(p);
	            distanceDtos.add(tmp);
	        }
	        return distanceDtos;
	    }
	    
	    private double harversineCal(double x1, double y1, double x2, double y2) {
	        double distance;
	        double radius = 6371; // 지구 반지름(km)
	        double toRadian = Math.PI / 180;

	        double deltaLatitude = Math.abs(x1 - x2) * toRadian;
	        double deltaLongitude = Math.abs(y1 - y2) * toRadian;

	        double sinDeltaLat = Math.sin(deltaLatitude / 2);
	        double sinDeltaLng = Math.sin(deltaLongitude / 2);
	        double squareRoot = Math.sqrt(
	            sinDeltaLat * sinDeltaLat +
	            Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

	        distance = 2 * radius * Math.asin(squareRoot);
	        return distance;
	        
	    }
	    private void planSave(String plan_name, int plan_id, Map<String, Object> test) {
	    	ArrayList<ArrayList<Object>> obj = (ArrayList<ArrayList<Object>>) test.get("planlist");
			int date_num=1;
			for (int i = 0; i < obj.size(); i++) {
				
				
				Comparator<Integer> comparator = (s1,s2) ->s1.compareTo(s2);
				
				Map<Integer, Integer> map = new TreeMap<>(comparator);
				for (int j = 0; j < obj.get(i).size(); j++) {
					Integer contentId = ((LinkedHashMap<String, Integer>) (obj.get(i).get(j))).get("content_id");
					Integer num = ((LinkedHashMap<String, Integer>) (obj.get(i).get(j))).get("num");
					map.put(num, contentId);
				}
				ArrayList<Integer> arr = new ArrayList<>();
				ArrayList<Integer> brr = new ArrayList<>();
				ArrayList<Integer> crr = new ArrayList<>();
				for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
					arr.add(entry.getValue());
					brr.add(entry.getKey());
					System.out.println("entry: "+entry);
				}
				int routeNum=1;
				
				List<Integer> rotAttractionDtos = attractionService.isRoute(arr);
				System.out.println(rotAttractionDtos);
//				System.out.println("a"+brr.get(i));
//				System.out.println("b"+rotAttractionDtos.get(i));
				for (int j = 0; j < rotAttractionDtos.size(); j++) {
					for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
						if(rotAttractionDtos.get(j).equals(entry.getValue())) {
							crr.add(entry.getKey());
						}
					}
				}
				System.out.println(crr);

				for (int idx = 0; idx < rotAttractionDtos.size(); idx++) {
					planListRepository.addPlanList(plan_id,routeNum,crr.get(idx),rotAttractionDtos.get(idx),date_num);
					routeNum++;
				}
				date_num++;	
			}
			
		}


}
