package com.trip.project.dto.attraction;

public class RouteDistanceDto {
	private int content_id;
	private double latitude;
	private double longitude;
	
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public RouteDistanceDto(int content_id, double latitude, double longitude) {
		super();
		this.content_id = content_id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public RouteDistanceDto() {
		super();
	}
}
