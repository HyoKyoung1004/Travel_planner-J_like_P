package com.trip.project.dto.plan;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanListRepository {

	int planAdd(PlanList planList);

}
