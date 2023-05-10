package com.trip.project.dto.attraction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDescriptionDto {

	private int contentId;
	private String homepage;
	private String overview;
	private String telname;
}
