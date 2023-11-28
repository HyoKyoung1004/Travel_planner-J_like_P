package com.trip.project.service.wishList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.wishList.WishListDto;
import com.trip.project.dto.wishList.WishListRepository;

@Service
public class WishListService {
	
	@Autowired
	WishListRepository wishRepo;

	public List<WishListDto> getWishList(Long userId) {
		return wishRepo.selectList(userId);
	}

	public int getCount(int contentId, Long userId) {
		return wishRepo.count( contentId,  userId);
	}

	public int insert(int contentId, Long userId) {
		return wishRepo.insert( contentId,  userId);
	
	}

	public int delete(int contentId, Long userId) {
		return wishRepo.delete( contentId,  userId);
		
	}

	public int getWishCount(int contentId) {
	
		return wishRepo.getWishCount(contentId);
	}
	
	

}
