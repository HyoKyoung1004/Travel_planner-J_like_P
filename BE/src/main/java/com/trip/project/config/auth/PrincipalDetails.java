package com.trip.project.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.trip.project.dto.user.UserDto;

import lombok.Data;
//시큐리티가 /login 주소 요청이 낚아채소 로그인을 진행시킨다.
//로그인 진행이 완료가되면 시큐리티 session을 만들어  줍니다.
//Security contextHolder에 저장
//오브젝트 => Authentication 타입 객체
//Authentication안에 User정보가 있어야 됨
//User오브젝트타입 -> UserDetail타입 객체


//Security session => Authentication => UserDetails(PrincipalDetails)

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

	private UserDto user;
	
	private Map<String, Object> attributes;
	
	
	//일반로그인
	public PrincipalDetails(UserDto user) {
		this.user=user;
	}
	
	
	//OAuth로그인
	public PrincipalDetails(UserDto user,  Map<String, Object> attributes) {
		this.user=user;
		this.attributes =attributes;
	}
	
	
	//해당 User의 권한을 리턴하는 곳!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getUserRole(); //권한 String으로 리턴
			}
		});
		return collect;
		
	}

	@Override
	public String getPassword() {
		return user.getUserPassword();
	}

	@Override
	public String getUsername() {
		return user.getNickName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		//우리사이트 1년동안 회원이 로그인 안하면 휴면계정으로 하기로 함
		//현재시간 -로그인 시간 => 1년지나면 return false
		return true;
	}

//-------
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}


	@Override
	public String getName() {
		return (String) attributes.get("sub");
	}
	
	

}
