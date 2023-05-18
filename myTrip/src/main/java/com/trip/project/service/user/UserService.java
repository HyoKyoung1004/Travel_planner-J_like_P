package com.trip.project.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.trip.project.dto.user.UserDto;
import com.trip.project.dto.user.UserRepository;
import com.trip.project.util.JWTUtil;

@Service
public class UserService {

	@Value("${jwt.secret}")
	private String secretkey;
	
	private Long expiredMs = 1000*60*60l;
	
	@Autowired
	private UserRepository userRepository;
	public int join(UserDto user) throws Exception {
		if(userRepository.find(user)!=null ) {
			throw new Exception("이미 있는 회원입니다");
		}
		if(user.getUserAccount()=="") {
			return 0;
		}
		return userRepository.join(user);
	}
	public String login(UserDto user) throws Exception {

		if(userRepository.findLogin(user)==null) {
			return "x";
		}
		UserDto userDto = userRepository.login(user);
		System.out.println(userDto.getUserAccount());
		return JWTUtil.createJwt(userDto.getUserAccount(), secretkey, expiredMs);
	}
	public UserDto viewMypage(long userId) throws Exception {
		if(userRepository.findUserId(userId)==null) {
			throw new Exception("잘못된 회원페이지입니다");
		}

		System.out.println("여기까진오냐?");
		UserDto user = userRepository.viewMypage(userId);
		return user;
	}
	public int deleteLike(long userId) {
		return userRepository.deleteLike(userId);
	}
	
	
	public long getUser(String userAccount) {
		return userRepository.getUserOne(userAccount);
	}

}
