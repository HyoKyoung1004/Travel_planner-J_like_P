package com.trip.project.dto.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentFileRepository {

	public int insert(CommentFileDto file);
	public List<CommentFileDto> selectList(long commentId);
	//public CommentFileDto selectOne(int fileId);
	public int countList(long commentId);

}
