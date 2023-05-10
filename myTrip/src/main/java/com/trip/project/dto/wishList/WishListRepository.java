package com.trip.project.dto.wishList;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WishListRepository {

	List<WishListDto> selectList(Long userId);

	int count(@Param("contentId") int contentId, @Param("userId") Long userId);

	int insert(@Param("contentId") int contentId, @Param("userId") Long userId);

	int delete(@Param("contentId") int contentId, @Param("userId") Long userId);

}
