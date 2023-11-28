package com.trip.project.dto.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeAttractionRepository {

	private Long likeId;
	private Long userId;
	private int contentId;
	private int likeCheck;
}
