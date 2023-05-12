 package com.trip.project.config.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.trip.project.service.user.UserService;
import com.trip.project.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter{

	
	private final UserService userservice;
	private final String secretKey;
	
	//UserName Token에서 꺼내기 
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		final String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("authentication :{}",authentication);
		
		if(authentication == null){
		    SecurityContextHolder.getContext().setAuthentication(null);
		    filterChain.doFilter(request, response);
		    return;
		}
		String token="";
		if (authentication.startsWith("Bearer ")){
		    token = authentication.replace("Bearer ", "");
		} else{
		    log.error("Authorization 헤더 형식이 틀립니다. : {}", authentication);
		    filterChain.doFilter(request, response);
		    return;
		}
		
		if(JWTUtil.isExpired(token, secretKey)) {
			log.error("Token이 만료 되었습니다");
			filterChain.doFilter(request, response);
			return;
		}
		
		String userName=JWTUtil.getUserName(token, secretKey);
		log.info("userName: {}",userName);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userName , null,authorities);
		
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

}
