package com.trip.project.service.like;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.like.LikeAttractionDto;
import com.trip.project.dto.like.LikeListDto;
import com.trip.project.dto.like.LikeRepository;

@Service
public class LikeService {
	
	@Autowired
	private LikeRepository likeRepository;
	public int likeAdd(long userId, int contentId) throws Exception {
		if(duplicateIslike(userId,contentId)) {
			throw new Exception("Duplicate Repository");
		}
		return likeRepository.likeAdd(userId,contentId);
	}
	
	public boolean duplicateIslike(long userId,int contentId) {
		System.out.println("여기까지인가?");
		if(likeRepository.isLikeCheck(userId,contentId)==null) {
			return false;
		}
		else {
			return true;
		}
	}

	public List<LikeListDto> showLikeList() {
		return likeRepository.showLikeList();
	}

	public int deleteLike(long likeId) {
		return likeRepository.deleteLike(likeId);
	}

}
