package com.trip.project.dto.team;

public class Team {
	
	private int team_id;
	private int plan_id;
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public Team(int team_id, int plan_id) {
		super();
		this.team_id = team_id;
		this.plan_id = plan_id;
	}
	public Team()
	{
		
	}
}
