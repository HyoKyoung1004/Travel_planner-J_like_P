package com.trip.project.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CommentFileDto {
	
	private int fileId;
	private long commentId;
	private String originalFileName;
	private String saveFolder;
	private String saveFileName;
	
	

}
