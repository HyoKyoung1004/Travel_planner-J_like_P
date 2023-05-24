package com.trip.project.dto.plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.trip.project.dto.attraction.AttractionDto;

@Mapper
public interface PlanRepository {

	int planAdd(PlanRequestDto plan);

	int findPlanId(String plan_name);

	int planDelete(int plan_id);

//	int addMember(@Param("plan_name")int plan_name, @Param("userId")long userId);

	Plan findPlanNameAnduserId(@Param("plan_name")String plan_name,@Param("userId")int userId);


	List<AttractionDto> findAttraction(@Param("minY")double minY, @Param("maxY")double maxY, @Param("minX")double minX, @Param("maxX")double maxX);

	void addPlan(@Param("plan_name") String planName, @Param("plan_start")LocalDate startDate, @Param("plan_end")LocalDate endDate, @Param("userId")int userId);
	
	int findUserId(String name);

	int addPlanPlanUser(@Param("planId")int planId, @Param("userId")int userId);

	int addPlanUser(@Param("planId")int planId, @Param("userId")long userId);
	
	ArrayList<Integer> getUserPlan(@Param("userId")long userId);

	UserPlanList getUserPlanData(int planId);

	int getPlanDate(int planId);

	ArrayList<PlanDetail> getPlanDatail(@Param("planId")int planId, @Param("date")int date);

	Plan getPlanOne (int planId);

	int deleteInviteUser(@Param("planId")int planId, @Param("userId")long userId);

	Integer getPlanUser(int planId, long userId);

	ArrayList<Plan> planRandomFour();

}
