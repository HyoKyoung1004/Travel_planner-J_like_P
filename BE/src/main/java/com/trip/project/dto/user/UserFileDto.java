package com.trip.project.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserFileDto {
	
	private int fileId;
	private long userId;
	private String originalFileName;
	private String saveFolder;
	private String saveFileName;
	
	

}
