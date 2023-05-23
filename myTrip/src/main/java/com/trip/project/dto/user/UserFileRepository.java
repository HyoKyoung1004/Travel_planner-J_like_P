package com.trip.project.dto.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFileRepository {

	public int insert(UserFileDto file);
	public List<UserFileDto> selectList(long userId);
//	//public CommentFileDto selectOne(int fileId);
//	public int countList(long userId);

}
