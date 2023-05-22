package com.trip.project.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PlanDetail {
	private int planList_id; 
	private int plan_id; 
	private int content_id; 
	private int route_num; 
	private int date_num; 
	private int pnum; 
	private int content_type_id; 
	private String title; 
	private String addr1;
	private String first_image;
	private double latitude; 
	private double longitude;

}
