package com.trip.project.dto.user;

import java.io.Serializable;
import java.util.List;

import com.trip.project.dto.comment.CommentFileDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class UserDto implements Serializable {
	
	private Long userId;
	private String userAccount;
	private String userEmail;
	private String nickName;
	private String userPassword;
	private String userRole;
	private String joinDate;
	private List<UserFileDto> fileInfo;
	
	public List<UserFileDto> getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(List<UserFileDto> fileInfo) {
		this.fileInfo = fileInfo;
	}
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userAccount=" + userAccount + ", userEmail=" + userEmail + ", nickName="
				+ nickName + ", userPassword=" + userPassword + ", userRole=" + userRole + ", joinDate=" + joinDate
				+ "]";
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	

}