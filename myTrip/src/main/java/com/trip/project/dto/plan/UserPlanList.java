package com.trip.project.dto.plan;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlanList {

	private int plan_id;
	private String plan_name;
	private LocalDate plan_start;
	private LocalDate plan_end; 
	private long userId;
	
	private int sido_code;
	private int gugun_code;
	private String sidoName;
	private String gugunName;
	
	private String first_image;
	
	private int userCount;
	private int stopCount;
	
	
	private String Dday;
	
}
