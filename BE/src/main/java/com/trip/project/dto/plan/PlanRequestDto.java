package com.trip.project.dto.plan;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PlanRequestDto {
	
	private String plan_name;
	private LocalDate plan_start;
	private LocalDate plan_end;
	private int userId;
	private List<Integer>content_id;
	
	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public LocalDate getPlan_start() {
		return plan_start;
	}
	public void setPlan_start(LocalDate plan_start) {
		this.plan_start = plan_start;
	}
	public LocalDate getPlan_end() {
		return plan_end;
	}
	public void setPlan_end(LocalDate plan_end) {
		this.plan_end = plan_end;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Integer> getContent_id() {
		return content_id;
	}
	public void setContent_id(List<Integer> content_id) {
		this.content_id = content_id;
	}
	public PlanRequestDto(String plan_name, LocalDate plan_start, LocalDate plan_end, int userId, List<Integer> content_id) {
		this.plan_name = plan_name;
		this.plan_start = plan_start;
		this.plan_end = plan_end;
		this.userId = userId;
		this.content_id = content_id;
	}
	public PlanRequestDto(LocalDate plan_start, LocalDate plan_end, int userId,List<Integer> content_id) {
		this.plan_start = plan_start;
		this.plan_end = plan_end;
		this.userId = userId;
		this.content_id = content_id;
	}
	public PlanRequestDto() {
		
	}
}
