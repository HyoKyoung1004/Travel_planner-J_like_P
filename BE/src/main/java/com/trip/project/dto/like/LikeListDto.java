package com.trip.project.dto.like;

public class LikeListDto {
	public int likeCount;
	public String content_id;
	public String title;
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LikeListDto(int likeCount, String content_id, String title) {
		super();
		this.likeCount = likeCount;
		this.content_id = content_id;
		this.title = title;
	}
	public LikeListDto() {
	}
	
}
