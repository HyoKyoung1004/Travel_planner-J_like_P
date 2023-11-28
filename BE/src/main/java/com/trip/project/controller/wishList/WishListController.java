package com.trip.project.controller.wishList;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.attraction.Attraction;
import com.trip.project.dto.user.UserDto;
import com.trip.project.dto.wishList.WishListDto;
import com.trip.project.service.attraction.AttractionService;
import com.trip.project.service.wishList.WishListService;

@RestController
@RequestMapping("/wishList")
public class WishListController {
	
	@Autowired
	WishListService service;
	
	@Autowired
	AttractionService attractionService;
	
	//위시리스트에 담기 또는 취소하기, 가져와야 하는 건 content_id, user_id, 반환해야 하는건?
	@GetMapping("/{userId}/{contentId}")
	public ResponseEntity<String> insertOrDeleteWish(@PathVariable("userId") long userId, @PathVariable("contentId") int contentId, HttpSession session){
		
		int n = service.getCount(contentId, userId);
		
		System.out.println(n);
		if(n==0) { //insert
			System.out.println("추가:");
			service.insert(contentId, userId);
			return new ResponseEntity<String>("insert", HttpStatus.OK);
		}else { //delete
			System.out.println("삭제:");
			System.out.println(contentId+", "+userId);
			int k= service.delete(contentId, userId);
			System.out.println(k);
			return new ResponseEntity<String>("delete", HttpStatus.OK);

		}
		
	}
	
	//사용자 id -> 위시리스트 목록 가져오기
	@GetMapping("/{userId}")
	public ResponseEntity<?> getWishList(@PathVariable("userId") long userId, HttpSession session){
		
		List<WishListDto> list = service.getWishList(userId);
		System.out.println(list);
		List<Attraction> attractionList = new ArrayList<Attraction>();
		for(WishListDto wish :list) {
			attractionList.add(attractionService.getAttractionOne(wish.getContent_id()));
		}
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<Attraction>>(attractionList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/getCount/{contentId}")
	public ResponseEntity<?> getWishCount(@PathVariable("contentId") int contentId){
		
		int count = service.getWishCount(contentId);
		System.out.println("wish개수 "+count);
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
		
	}
	

}
