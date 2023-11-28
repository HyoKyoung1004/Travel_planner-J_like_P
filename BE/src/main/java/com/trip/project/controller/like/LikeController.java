package com.trip.project.controller.like;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.like.LikeAttractionDto;
import com.trip.project.dto.like.LikeListDto;
import com.trip.project.service.like.LikeService;

@RestController
@CrossOrigin("*")
public class LikeController {
    
    @Autowired
    private LikeService likeService;
    
    @DeleteMapping("/like/{likeId}")
    public int deleteLike(@PathVariable("likeId") long likeId) throws Exception {
        return likeService.deleteLike(likeId);
    }
    
    @GetMapping("/like/{userId}/{contentId}")
    public ResponseEntity<String> likeAdd(@PathVariable("userId") long userId, @PathVariable("contentId") int contentId) throws Exception {        
    	return new ResponseEntity<String>(likeService.likeAdd(userId,contentId), HttpStatus.OK);

    }
    
    @PostMapping("/like/list")
    public List<LikeListDto> showLikeList() throws Exception {
        return likeService.showLikeList();
    }

}