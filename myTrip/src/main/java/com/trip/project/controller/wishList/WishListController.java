package com.trip.project.controller.wishList;

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
import com.trip.project.service.wishList.WishListService;

@RestController
@RequestMapping("/wishList")
public class WishListController {
	
	@Autowired
	WishListService service;
	
	//위시리스트에 담기 또는 취소하기, 가져와야 하는 건 content_id, user_id, 반환해야 하는건?
	@GetMapping("/{userId}/{contentId}")
	public void insertOrDeleteWish(@PathVariable("userId") long userId, @PathVariable("contentId") int contentId, HttpSession session){
		
		int n = service.getCount(contentId, userId);
		
		System.out.println(n);
		if(n==0) { //insert
			System.out.println("추가:");
			service.insert(contentId, userId);
		}else { //delete
			System.out.println("삭제:");
			service.delete(contentId, userId);
		}
		
	}
	
	//사용자 id -> 위시리스트 목록 가져오기
	@GetMapping("/{userId}")
	public ResponseEntity<?> getWishList(@PathVariable("userId") long userId, HttpSession session){
		
		List<WishListDto> list = service.getWishList(userId);
		System.out.println(list);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<WishListDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	

}
