package com.trip.project.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.comment.CommentDto;
import com.trip.project.dto.comment.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepo;

	public int insert(CommentDto comment) {
		return commentRepo.insert(comment);
	}
	
	public List<CommentDto> selectList(int contentId){
		return commentRepo.selectList(contentId);
	}

}
