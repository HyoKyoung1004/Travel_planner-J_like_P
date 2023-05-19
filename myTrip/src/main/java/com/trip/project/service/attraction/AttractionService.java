package com.trip.project.service.attraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.attraction.Attraction;
import com.trip.project.dto.attraction.AttractionNear;
import com.trip.project.dto.attraction.AttractionRepository;
import com.trip.project.dto.attraction.RouteDistanceDto;
import com.trip.project.dto.attraction.SidoGugunCodeDto;

@Service
public class AttractionService {
	
	@Autowired
	AttractionRepository attractionRepository;
	
	//전체 목록을 가져온다. 
	public List<Attraction> getAttractionList(int type ) {
		  List<Attraction> list =attractionRepository.attractionListByType(type);
		  return list;
	}
	public List<Attraction> getAttractionList(int sido, int gugun, Integer type) {
		if(type== null)
			 return attractionRepository.attractionListByAddr(sido ,gugun);
		else 
			 return attractionRepository.attractionListByAddr_type(sido,gugun, type);
	}
	public List<Attraction> getAttractionList(int sido, int gugun, Integer type, String searchData) {
		if(type== null)
			 return attractionRepository.attractionListByAddr_Title(sido, gugun, "%"+searchData+"%");
		else 
			 return attractionRepository.attractionListByAddr_Type_Title(sido, gugun,type, "%"+searchData+"%");
	}
	
	public List<Attraction> getAttractionList(String searchData, Integer type) {
		if(type== null)
			 return attractionRepository.attractionListByTitle("%"+searchData+"%");
		else 
			 return attractionRepository.attractionListByTitle_Type( type, "%"+searchData+"%");
	}
	
	
	
	//페이징 처리된 리스트를 가져온다
	public List<Attraction> getAttractionListPage(int type, int start, int listsize) {
		  List<Attraction> list =attractionRepository.attractionListPage_type(type, start, listsize);
		  return list;
	}
	
	public List<Attraction> getAttractionListPage(int sido, int gugun, Integer type, int start, int listsize) {
		if(type== null)
			 return attractionRepository.attractionListPage_addr(sido ,gugun, start, listsize);
		else 
			 return attractionRepository.attractionListPage_addr_type(sido,gugun, type, start, listsize);
	}
	
	public List<Attraction> getAttractionListPage(int sido, int gugun, Integer type, String searchData, int start, int listsize) {
		if(type== null)
			 return attractionRepository.attractionListPage_addr_title(sido ,gugun, "%"+searchData+"%", start, listsize);
		else 
			 return attractionRepository.attractionListPage_addr_title_type(sido,gugun,"%"+searchData+"%",type, start, listsize);
	}
	
	
	public List<Attraction> getAttractionListPage(String searchData, Integer type, int start, int listsize) {
		if(type== null)
			 return attractionRepository.attractionListPage_title("%"+searchData+"%", start, listsize);
		else 
			 return attractionRepository.attractionListPage_title_type("%"+searchData+"%",type, start, listsize);
	}
	

	
	//각각 목록의 개수를 가져온다.
	public int getAttractionListCnt(int type) {
		return attractionRepository.attrictionListCnt_type(type);
	}


	public int getAttractionListCnt(int sido, int gugun) {
		return attractionRepository.attrictionListCnt_addr(sido, gugun);
	}


	public int getAttractionListCnt(int sido, int gugun, Integer type) {
		return attractionRepository.attrictionListCnt_addr_type(sido, gugun, type);
	}


	public int getAttractionListCnt(int sido, int gugun, String searchData) {
		return attractionRepository.attrictionListCnt_addr_title(sido, gugun, "%"+searchData+"%");
	}


	public int getAttractionListCnt(int sido, int gugun, String searchData, Integer type) {
		return attractionRepository.attrictionListCnt_addr_title_type(sido, gugun, "%"+searchData+"%", type);
	}


	public int getAttractionListCnt(String searchData) {
		return attractionRepository.attrictionListCnt_title("%"+searchData+"%");
	}


	public int getAttractionListCnt(String searchData, Integer type) {
		return attractionRepository.attrictionListCnt_title_type("%"+searchData+"%",type);
	}



	
	
//	public List<Attraction> getAttractionList(int sido, int gugun,int type) {
//		  List<Attraction> list =attractionRepository.attractionList(sido, gugun, type);
//		  return list;
//	}
	
	
//	public List<Attraction> getAttractionList(String searchData) {
//		  List<Attraction> list =attractionRepository.attractionListByTitle("%"+searchData+"%");
//		  return list;
//	}
//	
//	
//	public List<Attraction> getAttractionList(int sido, int gugun, String searchData) {
//		  List<Attraction> list =attractionRepository.attractionListByaddrAndTitle(sido, gugun, "%"+searchData+"%");
//		  return list;
//	}


	
	
	
    public List<Integer> isRoute(List<Integer> point) {
        List<RouteDistanceDto> distanceDtos = getLatAndLong(point);
        for (RouteDistanceDto route : distanceDtos) {
            System.out.println(route.toString());
        }
        
        List<Integer> dtos=findShorPath(distanceDtos);
        
        /*
         * for(Integer dto: dtos) { System.out.println(dto); }
         */
        return dtos;
    }

    private List<Integer> findShorPath(List<RouteDistanceDto> distanceDtos) {
        List<Integer> dtos = new ArrayList<>();
        int num=0;
        boolean[] isCheck = new boolean[distanceDtos.size()];
        isCheck[0]=true;
        dtos.add(distanceDtos.get(0).getContent_id());
        while(true) {
            boolean isFlag=true;
            double tmp=Integer.MAX_VALUE;
            int value=0;
            for (int i = 0; i < distanceDtos.size(); i++) {
                if(isCheck[i] || num==i) {
                    continue;
                }
                double distance =harversineCal(distanceDtos.get(num).getLatitude(), distanceDtos.get(num).getLongitude(), distanceDtos.get(i).getLatitude(), distanceDtos.get(i).getLongitude());
                if(tmp>=distance) {
                    tmp=distance;
                    value=i;
                }
                
            }
            System.out.println(value);
            isCheck[value]=true;
            num=value;
            dtos.add(distanceDtos.get(value).getContent_id());
            for (int i = 0; i < isCheck.length; i++) {
                if(isCheck[i]==false) {
                    isFlag=false;
                    break;
                }
                
            }
            if(isFlag) {
                break;
            }
        }
        
        
        return dtos;
    }

    private List<RouteDistanceDto> getLatAndLong(List<Integer> point) {
        RouteDistanceDto tmp= new RouteDistanceDto();
        List<RouteDistanceDto> distanceDtos = new ArrayList<>();
        for (Integer p:point) {
            tmp=attractionRepository.isRoute(p);
            distanceDtos.add(tmp);
        }
        return distanceDtos;
    }
    
    
    private double harversineCal(double x1, double y1, double x2, double y2) {
        double distance;
        double radius = 6371; // 지구 반지름(km)
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(x1 - x2) * toRadian;
        double deltaLongitude = Math.abs(y1 - y2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
            sinDeltaLat * sinDeltaLat +
            Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);
        return distance;
    }
    

    //content_Id에 대한 좋아요 수를 반환한다.
	public int getLikeCnt(int contentId) {
		return attractionRepository.likeCount(contentId);
	}
	
	//content_id에 대한 Attraction한개를 반환한다.
	public Attraction getAttractionOne(int contentId) {
		return attractionRepository.selectOne(contentId);
	}
	
	public List<AttractionNear> getNearAttractionList(Attraction nowAttraction) {
		
		double lat = nowAttraction.getLatitude(), lng = nowAttraction.getLongitude();
        double range = 0.03;        
        
        Map<String,Double> map = new HashMap<String, Double>();
        map.put("min_lat", lat - range);
        map.put("max_lat",  lat + range);
        map.put("min_lng", lng - range);
        map.put("max_lng", lng + range);

        List<AttractionNear> nearListResult =new ArrayList<AttractionNear>();
        List<AttractionNear> nearList = attractionRepository.nearList(map);
        
        for(AttractionNear tmp : nearList) {
        	double dis = distanceInKilometerByHaversine(lat, lng, tmp.getLatitude(), tmp.getLongitude());
        	if(dis<=3.0){
        		tmp.setDistance(Math.round(dis*100)/100.0);
        		nearListResult.add(tmp);
        	}
        }
        //System.out.println(nearListResult);
		
		return nearListResult;
	}

	public static double distanceInKilometerByHaversine(double x1, double y1, double x2, double y2) {
	    double distance;
	    double radius = 6371; // 지구 반지름(km)
	    double toRadian = Math.PI / 180;

	    double deltaLatitude = Math.abs(x1 - x2) * toRadian;
	    double deltaLongitude = Math.abs(y1 - y2) * toRadian;

	    double sinDeltaLat = Math.sin(deltaLatitude / 2);
	    double sinDeltaLng = Math.sin(deltaLongitude / 2);
	    double squareRoot = Math.sqrt(
	        sinDeltaLat * sinDeltaLat +
	        Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

	    distance = 2 * radius * Math.asin(squareRoot);

	    return distance;
	}

	public ArrayList<Integer> getContentIdLike() {
		
		return attractionRepository.getTopLike();
	}
	public List<SidoGugunCodeDto> getSido() {
		// TODO Auto-generated method stub
		System.out.println(attractionRepository.getSido());
		return attractionRepository.getSido();
	}
	public List<SidoGugunCodeDto> getGugunInSido(String sido) {
		// TODO Auto-generated method stub
		return attractionRepository.getGugunInSido(sido);}
		
	public Map<String,Object> getAddrName(int sido, int gugun) {
		// TODO Auto-generated method stub
		return attractionRepository.getAddrName(sido, gugun);
	}






}
