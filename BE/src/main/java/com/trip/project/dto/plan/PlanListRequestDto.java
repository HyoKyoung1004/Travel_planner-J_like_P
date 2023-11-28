package com.trip.project.dto.plan;

import java.util.List;

public class PlanListRequestDto {
	private int plan_id;
	private String plan_name;
	private List<Integer>content_id;
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public List<Integer> getContent_id() {
		return content_id;
	}
	public void setContent_id(List<Integer> content_id) {
		this.content_id = content_id;
	}
	public PlanListRequestDto(int plan_id, String plan_name, List<Integer> content_id) {
		this.plan_id = plan_id;
		this.plan_name = plan_name;
		this.content_id = content_id;
	}
	
	public PlanListRequestDto() {
		
	}

}
