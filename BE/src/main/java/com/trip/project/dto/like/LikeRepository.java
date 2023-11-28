package com.trip.project.dto.like;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRepository {

	int likeAdd(long userId, int contentId);

	Integer isLikeCheck(@Param("userId")long userId, @Param("contentId")int contentId);

	List<LikeListDto> showLikeList();

	int deleteLike(long likeId);

	int likeDelete(@Param("contentId") int contentId, @Param("userId") long userId);

}
