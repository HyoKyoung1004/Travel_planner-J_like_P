package com.trip.project.dto.plan;

public class PlanList {
	private int plist;
	private int pnum;
	private int content_id;
	private int plan_id;
	public int getPlist() {
		return plist;
	}
	public void setPlist(int plist) {
		this.plist = plist;
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
	public PlanList(int plist, int pnum, int content_id, int plan_id) {
		super();
		this.plist = plist;
		this.pnum = pnum;
		this.content_id = content_id;
		this.plan_id = plan_id;
	}
	public PlanList(int pnum, int content_id, int plan_id) {
		this.pnum = pnum;
		this.content_id = content_id;
		this.plan_id = plan_id;
	}
	public PlanList() {
		
	}
}
