package com.trip.project.dto.attraction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionNear {

	private int contentId;
	private String title;
	private String addr;
	private String firstImage;
	private double latitude; //거리계산을 위해 필요한 정보
	private double longitude;
	
	private double distance; //거리
	private double rating;//댓글 평점
	private int likeCheck; //좋아요 순
}
