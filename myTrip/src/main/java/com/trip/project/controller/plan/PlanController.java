package com.trip.project.controller.plan;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.attraction.AttractionDto;
import com.trip.project.dto.plan.Plan;
import com.trip.project.dto.plan.PlanDetail;
import com.trip.project.dto.plan.UserPlanList;
import com.trip.project.dto.wishList.WishListDto;
import com.trip.project.service.attraction.AttractionService;
import com.trip.project.service.plan.PlanListService;
import com.trip.project.service.plan.PlanService;
import com.trip.project.service.wishList.WishListService;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
public class PlanController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PlanService planservice;

	@Autowired
	private PlanListService planListService;

	@Autowired
	private WishListService wishlistservice;

	@Autowired
	AttractionService attractionService;

	@PostMapping("/create")
	public ResponseEntity<List<AttractionDto>> create(@RequestBody Map<String, Double> location) {
		double lat = location.get("lat").doubleValue();
		double lng = location.get("lng").doubleValue();
		List<AttractionDto> attractionDto = planservice.getAttraction(lat, lng);
		return ResponseEntity.ok().body(attractionDto);
	}

	@PostMapping("/create/likeregion")
	public ResponseEntity<List<WishListDto>> likeregion(Principal principal) {
		long userId = planservice.findUserId(principal.getName());
		List<WishListDto> attractionDto = wishlistservice.getWishList(userId);

		return ResponseEntity.ok().body(attractionDto);
	}

	@PostMapping("/realPlanAdd")
	public ResponseEntity<Integer> test(@RequestBody Map<String, Object> test) {

		int userId = (int) test.get("userId");
		String planName = (String) test.get("plan_name");
		LocalDate startDate = LocalDate.parse((String) test.get("startDate"));
		LocalDate endDate = LocalDate.parse((String) test.get("endDate"));
		// System.out.println(userId + " " + planName + " " + startDate + " " +
		// endDate);

		ArrayList<ArrayList<Object>> obj = (ArrayList<ArrayList<Object>>) test.get("planlist");
//        for (int i = 0; i < obj.size(); i++) {
//            ArrayList<Object> innerList = obj.get(i);
//            for (int j = 0; j < innerList.size(); j++) {
//                Object element = innerList.get(j);
//                if (i == 0 && j == 0) {
//                    innerList.remove(element);
//                    j--; // 요소를 제거하면 인덱스를 하나 감소시켜야합니다.
//                }
//            }
//        }
		for (int i = 0; i < obj.size(); i++) {
			ArrayList<Object> innerList = obj.get(i);

			for (int j = innerList.size() - 1; j >= 0; j--) {
				Object element = innerList.get(j);

				if (element instanceof Map) {
					Map<String, Object> map = (Map<String, Object>) element;

					if (!map.containsKey("content_id") || map.isEmpty()) {
						innerList.remove(j);
					}
				}
			}

			if (innerList.isEmpty()) {
				obj.remove(innerList);
			}
		}

		System.out.println("어떻게될까??");
		System.out.println(obj);
		System.out.println("어떻게될까??");
		for (int i = 0; i < obj.size(); i++) {
			for (int j = 0; j < obj.get(i).size(); j++) {
				Integer contentId = ((LinkedHashMap<String, Integer>) (obj.get(i).get(j))).get("content_id");
				Integer num = ((LinkedHashMap<String, Integer>) (obj.get(i).get(j))).get("num");
				System.out.println(contentId + ", " + num);

			}

		}

		planservice.addPlan(planName, startDate, endDate, userId);
		planListService.addPlanList(test);
		return ResponseEntity.ok().body(1);
	}

	@DeleteMapping("/delete/{plan_id}")
	public int planDelete(@PathVariable("plan_id") int plan_id) {
		return planservice.planDelete(plan_id);
	}

//	@GetMapping("/callMember/{plan_name}/{userId}")
//	public int addMember(@PathVariable("plan_name") String plan_name, @PathVariable("userId") int userId) {
//		return planservice.addMember(plan_name, userId);
//	}

	@GetMapping("/joinPlanMember/{planId}/{userAccount}")
	public ResponseEntity<?> joinPlanMember(@PathVariable("planId") int planId,
			@PathVariable("userAccount") String userAccount) {
		System.out.println(planId + ", " + userAccount);
		int n = planservice.joinPlanMember(planId, userAccount);
		if (n == -1) {
			return new ResponseEntity<String>("not user", HttpStatus.OK);
		} else if (n == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else if (n == -2) {
			System.out.println("이미 추가된 멤버");
			return new ResponseEntity<String>("already", HttpStatus.OK);

		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/myplanList/{userId}")
	public ResponseEntity<?> myPlanList(@PathVariable("userId") long userId) {

		ArrayList<Integer> userPlanId = planservice.getUserPlanList(userId);

		System.out.println(userPlanId);

		ArrayList<UserPlanList> userPlanList = new ArrayList<UserPlanList>();
		for (int planId : userPlanId) {

			UserPlanList tmp = planservice.getUserplan(planId);
			userPlanList.add(tmp);
			Map<String, Object> addrName = attractionService.getAddrName(tmp.getSido_code(), tmp.getGugun_code());
			System.out.println(addrName);
			tmp.setSidoName((String) addrName.get("sideName"));
			tmp.setGugunName((String) addrName.get("gugunName"));
			tmp.setDday(calDday(tmp.getPlan_start()));
		}

		System.out.println(userPlanList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("planLength", userPlanId.size());
		map.put("UserPlanList", userPlanList);

		System.out.println(map);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	private String calDday(LocalDate plan_start) {
		Calendar cal = Calendar.getInstance();
		System.out
				.println(plan_start.getYear() + ", " + plan_start.getMonthValue() + ", " + plan_start.getDayOfMonth());
		cal.set(plan_start.getYear(), plan_start.getMonthValue() - 1, plan_start.getDayOfMonth());

		long dday = cal.getTimeInMillis() / (24 * 60 * 60 * 1000);
		long today = Calendar.getInstance().getTimeInMillis() / (24 * 60 * 60 * 1000);

		long sub = today - dday;
		System.out.println(sub);

		if (sub > 0)
			return "D+" + sub;
		else if (sub == 0)
			return "D-" + sub;
		else
			return "D" + sub;
	}

	@GetMapping("/planDetail/{planId}")
	public ResponseEntity<Map<String, Object>> planDetail(@PathVariable("planId") int planId) {

		// Day 개수
		int n = planservice.getPlanDate(planId);
		System.out.println(n);

		ArrayList<PlanDetail> planDetailArr[] = new ArrayList[n];
		ArrayList<Double>[] disArr = new ArrayList[n];
		ArrayList<double[]> latlng[] = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			planDetailArr[i] = new ArrayList<PlanDetail>();
			disArr[i] = new ArrayList<Double>();
			latlng[i] = new ArrayList<double[]>();

		}
		for (int i = 0; i < n; i++) {
			planDetailArr[i] = planservice.getPlanDetail(planId, i + 1);
			System.out.println(planDetailArr[i]);

			// 위경도 따로 모아놓기
			for (int j = 0; j < planDetailArr[i].size(); j++) {
				latlng[i].add(
						new double[] { planDetailArr[i].get(j).getLatitude(), planDetailArr[i].get(j).getLongitude() });
			}
		}

		// 거리를 계산할꺼야
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < planDetailArr[i].size() - 1; j++) {
				double distance = attractionService.distanceInKilometerByHaversine(
						planDetailArr[i].get(j).getLatitude(), planDetailArr[i].get(j).getLongitude(),
						planDetailArr[i].get(j + 1).getLatitude(), planDetailArr[i].get(j + 1).getLongitude());
				disArr[i].add(Math.round(distance * 100) / 100.0);
			}
			System.out.println(disArr[i]);
		}

//---------------------------------------------------------------------------------
		Plan planData = planservice.getPlanOne(planId);

		String[] planDate = new String[n];
		LocalDate plan_start = planData.getPlan_start();
		for (int i = 0; i < n; i++) {

			LocalDate tmpDate = plan_start.plusDays(i);
//			System.out
//			.println(tmpDate.getYear() + ", " + tmpDate.getMonthValue() + ", " + tmpDate.getDayOfMonth()+", "+tmpDate.getDayOfWeek());
//		

			String answer = "";
			answer += tmpDate.getYear() + ". " + tmpDate.getMonthValue() + ". " + tmpDate.getDayOfMonth() + "(";
			DayOfWeek dayOfWeek = tmpDate.getDayOfWeek();
			if (dayOfWeek.getValue() == 1) {
				answer += "MON";
			} else if (dayOfWeek.getValue() == 2) {
				answer += "TUE";
			} else if (dayOfWeek.getValue() == 3) {
				answer += "WED";
			} else if (dayOfWeek.getValue() == 4) {
				answer += "THU";
			} else if (dayOfWeek.getValue() == 5) {
				answer += "FRI";
			} else if (dayOfWeek.getValue() == 6) {
				answer += "SAT";
			} else if (dayOfWeek.getValue() == 7) {
				answer += "SUN";
			}
			answer += ")";
			System.out.println(answer);

			planDate[i] = answer;
		}

		UserPlanList plan = planservice.getUserplan(planId);

		// ----------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(map);
		map.put("date", n);
		map.put("planDetailArr", planDetailArr);
		map.put("disArr", disArr);
		map.put("latlng", latlng);
		map.put("planDate", planDate);
		map.put("plan", plan);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	@GetMapping("/deletePlan/{planId}/{ownerUserId}/{userId}")
	public ResponseEntity<?> deletePlan(@PathVariable("planId") int planId,
			@PathVariable("ownerUserId") long ownerUserId, @PathVariable("userId") long userId) {

		// 유저의 아이디가 플랜 생성자이면 플랜을 삭제,
		int n = 0;
		if (ownerUserId == userId) {
			System.out.println("플랜 삭제");
			n = planservice.planDelete(planId);

			if (n == 1)
				return new ResponseEntity<String>("plan delete success", HttpStatus.OK);

		} else {
			System.out.println("사용자 한명 삭제");
			n = planservice.inviteUserDelete(planId, userId);
			if (n == 1)
				return new ResponseEntity<String>("user delete success", HttpStatus.OK);

		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/getplanRandom")
	public ResponseEntity<Map<String, Object>> getplanRandom() {

		// 일단 4개를 가져와
		ArrayList<Plan> planFour = planservice.getPlanFour();

		ArrayList<UserPlanList> PlanList = new ArrayList<UserPlanList>();
		for (Plan plan : planFour) {

			UserPlanList tmp = planservice.getUserplan(plan.getPlan_id());
			PlanList.add(tmp);
			Map<String, Object> addrName = attractionService.getAddrName(tmp.getSido_code(), tmp.getGugun_code());
			System.out.println(addrName);
			tmp.setSidoName((String) addrName.get("sideName"));
			tmp.setGugunName((String) addrName.get("gugunName"));

			Calendar start = Calendar.getInstance();
			start.set(tmp.getPlan_start().getYear(), tmp.getPlan_start().getMonthValue() - 1,
					tmp.getPlan_start().getDayOfMonth());

			Calendar end = Calendar.getInstance();
			end.set(tmp.getPlan_end().getYear(), tmp.getPlan_end().getMonthValue() - 1,
					tmp.getPlan_end().getDayOfMonth());

			Date startDT = start.getTime();
			Date endDT = end.getTime();
			long differenceInMillis = endDT.getTime() - startDT.getTime();
			long days = (differenceInMillis / (24 * 60 * 60 * 1000L)) % 365;

			tmp.setDday(days + "DAYS");

		}

		System.out.println(PlanList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PlanList", PlanList);
		System.out.println(map);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}
}
