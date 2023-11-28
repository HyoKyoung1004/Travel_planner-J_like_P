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
	public String likeAdd(long userId, int contentId) throws Exception {
		
		
		//존재하는지?
		if(duplicateIslike(userId,contentId)) {
			int n=likeRepository.likeDelete(contentId,userId);
			if(n==1) return "delete";
		}else {
			
			int n = likeRepository.likeAdd(userId,contentId);
			if(n==1) return "insert";
		}
	
		return "fail";
		
		
	}
	
	public boolean duplicateIslike(long userId,int contentId) {
		System.out.println("여기까지인가?");
		if(likeRepository.isLikeCheck(userId,contentId)==null) {
			System.out.println("중복 없음.");
			return false;
		}
		else {
			System.out.println("중복 있음");
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
