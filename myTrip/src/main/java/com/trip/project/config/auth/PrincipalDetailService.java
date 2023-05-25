package com.trip.project.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trip.project.dto.user.UserDto;
import com.trip.project.dto.user.UserRepository;



//시큐리티 설정에서 loginProcessingUrl("/login");
// /login요청이 자동으로 오면 UserDetailsService 타입으로 loc되어 있는 loadUserByUsername 함수가 실행

//함수 종료시 @AuthenticationPricipal 어노테이션이 만들어진다. 

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//
	
	//시큐리티 session = Authentication = UserDetail
	//시큐리티 session(내부 Authenticatio(내부 UserDetail)) //알아서 다 해줘
	
	@Override //파라미터로 아이디가져온다.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto userEntity = userRepository.findByUsername(username);
		if(userEntity!=null)
			return new PrincipalDetails(userEntity);
		
		return null;
	}

	
	
}
