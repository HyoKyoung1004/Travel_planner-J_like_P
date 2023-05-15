package com.trip.project.controller.attaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trip.project.dto.attraction.Attraction;
import com.trip.project.dto.comment.CommentDto;
import com.trip.project.service.attraction.AttractionService;
import com.trip.project.service.comment.CommentService;
import com.trip.project.util.PageNavigation;

@RestController
@RequestMapping("/attract")
public class AttracitonController {

	@Autowired
	AttractionService attractionService;
	
	@Autowired
	CommentService commentService;

	final int naviSize = 10; // 네비게이션 크기
	final int sizePerPage = 10; // 페이지에 보여 줄 게시글 수
	
	@GetMapping("/attraction/route")
	public List<Integer> isRoute(@RequestParam("point") List<Integer> point) {
		List<Integer> rotAttractionDtos = attractionService.isRoute(point);
		return rotAttractionDtos;
	}

	
	// 타입을 눌렀을 떄,
	@GetMapping(value = { "/searchType/{type}/{page}/{orderType}" })
	public ResponseEntity<?> serarch(@PathVariable("type") int type, @PathVariable("page") Integer page, 
			 @PathVariable(value="orderType", required = false) String orderType  ) {

		System.out.println("타입네이베이션: "+ type + ", " + page);
		int totalCount = attractionService.getAttractionListCnt(type);
		System.out.println(totalCount);
		
		Map<String, Object> map = list(page, totalCount);
		System.out.println(map);
		List<Attraction> list =null;
		
		if(orderType!=null || orderType.equals("")|| orderType.equals("latest"))
			list = attractionService.getAttractionListPage(type, (int)map.get("start"),sizePerPage );
		else if(orderType.equals("like")) {
			List<Attraction> listALL = attractionService.getAttractionList(type); 
			list = likeSort(listALL,(int)map.get("start"));
		}else if(orderType.equals("title")){
			List<Attraction> listALL = attractionService.getAttractionList(type);	
			list = titleSort(listALL,(int)map.get("start"));
		}

		map.put("list", list);
		if(list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
	}
	


	//검색바에서 시, 구만으로 검색
	@GetMapping(value = { "/search/{sido}/{gugun}/{page}/{orderType}", "/searchType/{sido}/{gugun}/{type}/{page}/{orderType}" } )
	public ResponseEntity<?> serarch(@PathVariable("sido") int sido, @PathVariable("gugun") int gugun, @PathVariable(value = "type",required =false) Integer type, @PathVariable(value = "page") Integer page, @PathVariable(value="orderType", required = false) String orderType    ) {
		
		System.out.println("시,구로 검색: "+sido+" "+gugun+" "+type+" "+page+", "+orderType);
		System.out.println(orderType);
		int totalCount=0;
		if(type==null)  totalCount = attractionService.getAttractionListCnt(sido, gugun);
		else totalCount = attractionService.getAttractionListCnt(sido, gugun, type);
		System.out.println(totalCount);
		
		Map<String, Object> map = list(page, totalCount);
		System.out.println(map);
		
		List<Attraction> list =null;
		
		if(orderType==null || orderType.equals("")||orderType.equals(" ")|| orderType.equals("latest")) {
			list = attractionService.getAttractionListPage(sido,gugun,type, (int)map.get("start"), sizePerPage );
		}else if(orderType.equals("like")) {
			List<Attraction> listALL = attractionService.getAttractionList(sido,gugun,type); 
			list = likeSort(listALL,(int)map.get("start"));
		}else if(orderType.equals("title")){
			List<Attraction> listALL = attractionService.getAttractionList(sido,gugun,type);	
			list = titleSort(listALL,(int)map.get("start"));
		}
	
		//List<Attraction> list = attractionService.getAttractionList(sido,gugun,type, (int)map.get("start"), sizePerPage );
		//System.out.println(list);
		map.put("list", list);
		if(list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	
	}
	
	

	//검색바로 시,군,검색데이터로 검색,
	@GetMapping(value = {"/search/{sido}/{gugun}/{searchData}/{page}/{orderType}", "/searchType/{sido}/{gugun}/{searchData}/{type}/{page}/{orderType}" })
	public ResponseEntity<?> serarchBar(@PathVariable("sido") int sido, @PathVariable("gugun") int gugun,
			@PathVariable(value = "type", required = false) Integer type, @PathVariable("searchData") String searchData,
			@PathVariable(value = "page") Integer page, @PathVariable(value="orderType", required = false) String orderType  ) {

		System.out.println("주소, 검색데이터: "+sido + " " + gugun + " "  + searchData+" " +type + " "+ page + " ");
		int totalCount=0;
		if(type==null) totalCount = attractionService.getAttractionListCnt(sido, gugun,searchData);
		else totalCount = attractionService.getAttractionListCnt(sido, gugun, searchData,type);
		System.out.println(totalCount);
		
		Map<String, Object> map = list(page, totalCount);
		System.out.println(map);
	
		
		List<Attraction> list =null;
		
		if(orderType==null || orderType.equals("")|| orderType.equals("latest"))
			list = attractionService.getAttractionListPage(sido, gugun, type, searchData, (int)map.get("start"), sizePerPage );
		else if(orderType.equals("like")) {
			List<Attraction> listALL = attractionService.getAttractionList(sido, gugun, type, searchData); 
			list = likeSort(listALL,(int)map.get("start"));
		}else if(orderType.equals("title")){
			System.out.println("여기로 들어옴?");
			List<Attraction> listALL = attractionService.getAttractionList(sido, gugun, type, searchData);	
			list = titleSort(listALL,(int)map.get("start"));
		}
		
			
		//List<Attraction> list = attractionService.getAttractionListPage(sido, gugun, type, searchData, (int)map.get("start"), sizePerPage );
		System.out.println(list);
		map.put("list", list);
		if(list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	

	//@GetMapping("/search/{searchData}")
	//검색바에서 검색데이터로만 검색했을 때, 
	@GetMapping(value = {"/search/{searchData}/{page}/{orderType}", "/searchType/{searchData}/{type}/{page}/{orderType}" })
	public ResponseEntity<?> serarch(@PathVariable("searchData") String searchData, @PathVariable(value = "type", required = false) Integer type, @PathVariable(value = "page") Integer page, @PathVariable(value="orderType", required = false) String orderType  ) {

		System.out.println("검색데이터만: "+searchData+", "+type);
		int totalCount=0;
		if(type==null) 
			totalCount = attractionService.getAttractionListCnt(searchData);
		else 
			totalCount = attractionService.getAttractionListCnt(searchData,type);
		System.out.println(totalCount);
		
		Map<String, Object> map = list(page, totalCount);
		System.out.println(map);
	
		List<Attraction> list =null;
		
		if(orderType==null || orderType.equals("")|| orderType.equals("latest"))
			list =  attractionService.getAttractionListPage(searchData, type, (int)map.get("start"), sizePerPage );
		else if(orderType.equals("like")) {
			List<Attraction> listALL = attractionService.getAttractionList(searchData, type); 
			list = likeSort(listALL,(int)map.get("start"));
		}else if(orderType.equals("title")){
			System.out.println("여기로 들어옴?");
			List<Attraction> listALL = attractionService.getAttractionList(searchData, type);	
			list = titleSort(listALL,(int)map.get("start"));
		}
		
		//List<Attraction> list = attractionService.getAttractionListPage(searchData, type, (int)map.get("start"), sizePerPage );
		
		System.out.println(list);
		map.put("list", list);
		if(list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
	}

	
	// 페이지 번호와, 전체 글개수를 입력받는다.
	public Map<String, Object>  list(int pgno, int totalCnt) {

		// 페이지 처리
		PageNavigation page = new PageNavigation();

		// int totalCnt = boardService.getCount(); //전체 글 수
		int totalPageCnt = (totalCnt - 1) / sizePerPage + 1; // 전체 페이지 갯수
		boolean startRange = pgno <= naviSize;
		boolean endRange = (totalPageCnt - 1) / naviSize * naviSize < pgno;

		page.setNaviSize(10);
		page.setPageSize(sizePerPage);
		page.setCurrentPage(pgno);
		page.setTotalCnt(totalCnt);
		page.setTotalPageCnt((totalCnt - 1) / sizePerPage + 1);
		page.setStartRange(startRange);
		page.setEndRange(endRange);

		return page.calPage(pgno, sizePerPage);
	}
	
	
	private List<Attraction> titleSort(List<Attraction> list, int start) {
		
		Collections.sort(list, new Comparator<Attraction>() {
			@Override
			public int compare(Attraction o1, Attraction o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		});
		
		List<Attraction> result = new ArrayList<Attraction>();
		for(int i=start; i<start+sizePerPage;i++) {
			result.add(list.get(i));
		}
		//System.out.println(result);
		
		return result;
	}


	private List<Attraction> likeSort(List<Attraction> list, int start) {
		
		for(Attraction attraction :list) {

			int likeCnt =  attractionService.getLikeCnt(attraction.getContentId());
			attraction.setLikeCheck(likeCnt);
		}
		
		Collections.sort(list, new Comparator<Attraction>() {
			@Override
			public int compare(Attraction o1, Attraction o2) {
				return o2.getLikeCheck() - o1.getLikeCheck();
			}
		});
		
		List<Attraction> result = new ArrayList<Attraction>();
		for(int i=start; i<start+sizePerPage;i++) {
			result.add(list.get(i));
		}
		
		return result;
	}
	
	
	//상세정보
	//보여줘야 할 것, 
	//정보, 댓글, 근처거리, 댓글 평점, 좋아요 순
	@GetMapping("/view/{contentId}")
	public ResponseEntity<?> view(@PathVariable("contentId") int contentId){
		
		Attraction attraction = attractionService.getAttractionOne(contentId);
		attraction.setLikeCheck(attractionService.getLikeCnt(attraction.getContentId()));
		attraction.setRating(commentService.getCommentRating(attraction.getContentId()));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attraction", attraction);
		
		List<CommentDto> commentList = commentService.selectList(contentId);
		map.put("comment", commentList);
		
		List<Attraction> nearAttraction = attractionService.getNearAttractionList(attraction);
			
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	

}
