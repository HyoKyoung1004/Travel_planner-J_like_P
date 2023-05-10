package com.trip.project.dto.attraction;

public class RouteResponseDto {
	private int content_id;

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public RouteResponseDto() {
		
	}
	public RouteResponseDto(int content_id) {
		super();
		this.content_id = content_id;
	}
	
}
