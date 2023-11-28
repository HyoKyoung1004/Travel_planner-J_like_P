package com.trip.project.dto.plan;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlanListRepository {

	int planAdd(PlanList planList);

	int findPlanId(String plan_name);

	void addPlanList(@Param("plan_id")int plan_id, @Param("routeNum")int routeNum, @Param("pnum")int pnum, @Param("content_id")int content_id,@Param("date_num") int date_num);

}
