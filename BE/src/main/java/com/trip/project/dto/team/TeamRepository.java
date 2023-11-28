package com.trip.project.dto.team;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamRepository {

	void teamAdd(int plan_id);

}
