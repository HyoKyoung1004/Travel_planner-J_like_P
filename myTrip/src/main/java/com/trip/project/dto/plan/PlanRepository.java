package com.trip.project.dto.plan;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlanRepository {

	int planAdd(PlanRequestDto plan);

	int findPlanId(String plan_name);

	int planDelete(int plan_id);

	int addMember(@Param("plan_name")String plan_name,@Param("userId")int userId);

	Plan findPlanNameAnduserId(@Param("plan_name")String plan_name,@Param("userId")int userId);

}
