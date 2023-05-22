package com.trip.project.dto.plan;

import java.util.Date;

public class PlanList {
	private int plist;
	private int route_num;
	private int pnum;
	private int content_id;
	private int plan_id;
	private Date date_num;
	

	public PlanList(int plist, int route_num, int pnum, int content_id, int plan_id, Date date_num) {
		super();
		this.plist = plist;
		this.route_num = route_num;
		this.pnum = pnum;
		this.content_id = content_id;
		this.plan_id = plan_id;
		this.date_num = date_num;
	}


	public int getPlist() {
		return plist;
	}


	public void setPlist(int plist) {
		this.plist = plist;
	}


	public int getRoute_num() {
		return route_num;
	}


	public void setRoute_num(int route_num) {
		this.route_num = route_num;
	}


	public int getPnum() {
		return pnum;
	}


	public void setPnum(int pnum) {
		this.pnum = pnum;
	}


	public int getContent_id() {
		return content_id;
	}


	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}


	public int getPlan_id() {
		return plan_id;
	}


	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}


	public Date getDate_num() {
		return date_num;
	}


	public void setDate_num(Date date_num) {
		this.date_num = date_num;
	}


	public PlanList() {
		
	}
}
