package com.trip.project.service.attraction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.attraction.Attraction;
import com.trip.project.dto.attraction.AttractionRepository;
import com.trip.project.dto.attraction.RouteDistanceDto;

@Service
public class AttractionService {
	
	@Autowired
	AttractionRepository attractionRepository;
	
	public List<Attraction> getAttractionList(int sido, int gugun,int type) {
		  List<Attraction> list =attractionRepository.attractionList(sido, gugun, type);
		  return list;
	}
	
	public List<Attraction> getAttractionList(String serarchData) {
		  List<Attraction> list =attractionRepository.attractionListByTitle("%"+serarchData+"%");
		  return list;
	}
	
    
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

}
