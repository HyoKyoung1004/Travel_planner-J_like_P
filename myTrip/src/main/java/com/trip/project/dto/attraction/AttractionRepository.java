package com.trip.project.dto.attraction;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface AttractionRepository {
	
	public List<Attraction>attractionList(@Param("sido")int sido, @Param("gugun")int gugun, @Param("type")int type);

	public List<Attraction> attractionListByTitle(String searchData);
	
	RouteDistanceDto isRoute(Integer point);

	public List<Attraction> attractionListByaddrAndTitle(@Param("sido")int sido, @Param("gugun")int gugun, @Param("searchData")String searchData);

}
