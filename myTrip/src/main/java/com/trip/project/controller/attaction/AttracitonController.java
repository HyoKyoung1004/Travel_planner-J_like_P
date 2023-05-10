package com.trip.project.controller.attaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.attraction.Attraction;
import com.trip.project.service.attraction.AttractionService;

@RestController
@RequestMapping("/attract")
public class AttracitonController {
	
	@Autowired
	AttractionService attractionService;
	
	@GetMapping
	public void test() {
		System.out.println("");

	}
	
	  @GetMapping("/attraction/route")
	    public List<Integer> isRoute(@RequestParam("point") List<Integer> point){
	        List<Integer> rotAttractionDtos = attractionService.isRoute(point);
	        return rotAttractionDtos;
	    }
	  
	
	@GetMapping("/search/{sido}/{gugun}/{type}")
	public ResponseEntity<?> serarch(@PathVariable("sido") int sido, @PathVariable("gugun") int gugun, @PathVariable("type") int type  ) {
		
		System.out.println("들어오닝?");
		System.out.println(sido+" "+gugun+" "+type);
		List<Attraction> list = attractionService.getAttractionList(sido, gugun, type);
		System.out.println(list);
		if(list != null && !list.isEmpty()) {
			System.out.println("리스트가 있당");
			return new ResponseEntity<List<Attraction>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/search/{searchData}")
	public ResponseEntity<?> serarch(@PathVariable("searchData") String searchData  ) {
		
		System.out.println(searchData);
		List<Attraction> list = attractionService.getAttractionList(searchData);
		System.out.println(list);
		if(list != null && !list.isEmpty()) {
			System.out.println("리스트가 있당");
			return new ResponseEntity<List<Attraction>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
}
