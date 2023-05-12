package com.trip.project.controller.comment;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.attraction.Attraction;
import com.trip.project.dto.comment.CommentDto;
import com.trip.project.dto.user.UserDto;
import com.trip.project.service.comment.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService service;

	//댓글 가져오기
	@GetMapping("/{contentId}")
	public ResponseEntity<?>getComment(@PathVariable("contentId") int contentId){
		List<CommentDto> list = service.selectList(contentId);;
		return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
	}
	
	
	//댓글쓰기
	@PostMapping("/{userId}")
	public String writeComment(@PathVariable("userId") long userId, @RequestBody CommentDto comment, HttpSession session) {	
		comment.setUserId(userId);
		System.out.println("댓글쓰기: "+comment);
		return service.insert(comment)+"";
	}
	
	
}