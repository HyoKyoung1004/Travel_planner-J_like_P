package com.trip.project.dto.comment;

import java.util.List;

import lombok.Data;

public class CommentDto {

	private long commentId;
	private int contentId;
	private long userId;
	private String userName;
	private String content;
	private String regDate;
	private int rating;
	private List<CommentFileDto> fileInfo;
	
	public CommentDto(){}

	public CommentDto(long commentId, int contentId, long userId, String content, String regDate, int rating) {
		super();
		this.commentId = commentId;
		this.contentId = contentId;
		this.userId = userId;
		this.content = content;
		this.regDate = regDate;
		this.rating = rating;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}



	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}



	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	


	public List<CommentFileDto> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<CommentFileDto> list) {
		this.fileInfo = list;
	}

	@Override
	public String toString() {
		return "CommentDto [commentId=" + commentId + ", contentId=" + contentId + ", userId=" + userId + ", content="
				+ content + ", regDate=" + regDate + ", rating=" + rating + "]";
	}


	
}
