package com.trip.project.dto.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeAttractionDto {

	private Long likeId;
	private Long userId;
	private int contentId;
	private int likeCheck;
}