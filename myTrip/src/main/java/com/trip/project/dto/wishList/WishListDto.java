package com.trip.project.dto.wishList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class WishListDto {

	private long wishId;
	private int content_id;
	private long userId;
	
	public WishListDto() {}

	public WishListDto(long wishId, int content_id, long userId) {
		super();
		this.wishId = wishId;
		this.content_id = content_id;
		this.userId = userId;
	}

	public long getWishId() {
		return wishId;
	}

	public void setWishId(long wishId) {
		this.wishId = wishId;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "WishListController [wishId=" + wishId + ", content_id=" + content_id + ", userId=" + userId + "]";
	}
}
