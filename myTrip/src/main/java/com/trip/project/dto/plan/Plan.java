package com.trip.project.dto.plan;

import java.util.Date;

public class Plan {
	
	private int plan_id;
	private String plan_name;
	private Date plan_start;
	private Date plan_end;
	private int userId;
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
	public Date getPlan_start() {
		return plan_start;
	}
	public void setPlan_start(Date plan_start) {
		this.plan_start = plan_start;
	}
	public Date getPlan_end() {
		return plan_end;
	}
	public void setPlan_end(Date plan_end) {
		this.plan_end = plan_end;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Plan(int plan_id, String plan_name, Date plan_start, Date plan_end, int userId) {
		super();
		this.plan_id = plan_id;
		this.plan_name = plan_name;
		this.plan_start = plan_start;
		this.plan_end = plan_end;
		this.userId = userId;
	}
	public Plan() {
		super();
	}
	
	
}
