package com.trip.project.dto.attraction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {
	private int contentId;
	private int contentTypeID;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private int readcount;
	private int sideCode;
	private int gugunCode;
	private double latitude;
	private double longitude;
	private String mlevel;
	
	
}
