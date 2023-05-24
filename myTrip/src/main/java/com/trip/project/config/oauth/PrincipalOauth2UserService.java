package com.trip.project.config.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.trip.project.config.CustoBCryptPasswordEncoder;
import com.trip.project.config.auth.PrincipalDetails;
import com.trip.project.config.oauth.provider.GoogleUserInfo;
import com.trip.project.config.oauth.provider.NaverUserInfo;
import com.trip.project.config.oauth.provider.OAuth2UserInfo;
import com.trip.project.dto.user.UserDto;
import com.trip.project.dto.user.UserRepository;
import com.trip.project.service.jwt.JwtServiceImpl;
import com.trip.project.service.user.UserService;



@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	CustoBCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private UserService userService;
    
    @Autowired
	private JwtServiceImpl jwtService;
	
	//구글로부터 받은 userRequest데이터에 대한 후처리되는 함수, userRequest에는 엑세스토큰 + 사용자프로필정보가 담겨있음
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration: "+userRequest.getClientRegistration()); //registrationId로 어떤 OAuth로 로그인했는지 확인 가능
		System.out.println("getAccessToken: "+userRequest.getAccessToken().getTokenValue());
		
		
		//구글로그인버튼 클릭 -> 구글로그인 창-> 로그인 완료-> code를 리턴(OAuth-client 라이브러리가 받아) -> Access Token요청 : userRequest정보
		//userRequest정보 -> 회원프로필을 받아야함 :loadUser함수 호출 -> 구글로부터 회원프로필을 받아준다.		
		//System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes());

		OAuth2User oAuth2User  = super.loadUser(userRequest);
		System.out.println("getAttributes: "+oAuth2User.getAttributes());
//		System.out.println("getAttributes: "+ oAuth2User.getAttributes());

		
		//회원가입 강제 진행
		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청~~");
			System.out.println("getAttributes: "+oAuth2User.getAttributes());

			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
			System.out.println("네이버 로그인 요청~~");
			System.out.println("getAttributes: "+oAuth2User.getAttributes());
			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		} 
		
		
//		String provider = userRequest.getClientRegistration().getClientId();//google
//		String providerId = oAuth2User.getAttribute("sub"); //google id pk
//		String username = userRequest.getClientRegistration().getRegistrationId()+"_"+providerId;
//		String password = bCryptPasswordEncoder.encode("OAuth2Login");//의미 없음
//		String email = oAuth2User.getAttribute("email");
//		String role = "ROLE_USER";
		
		//UserDto userEntity = userRepository.findByUsername(oAuth2UserInfo.getName());

		UserDto userEntity = userRepository.findByUserEmail(oAuth2UserInfo.getEmail());
		if(userEntity ==null) { //이미 등록한 사용자가 없다, 회원가입 진행
			System.out.println("로그인 최조");
			userEntity = new UserDto();
			userEntity.setUserAccount(oAuth2UserInfo.getProvider()+"_"+oAuth2UserInfo.getProviderId());
			userEntity.setUserEmail(oAuth2UserInfo.getEmail());
			userEntity.setNickName(oAuth2UserInfo.getName());
			userEntity.setUserPassword( bCryptPasswordEncoder.encode("OAuth2"));
			userEntity.setUserRole("USER");
			userRepository.join(userEntity);
			
		}else {
			System.out.println("구글로그인을 한적이 있음. 당신은 자동최원가입이 되어 있습니다.");
			System.out.println(userEntity);
		}

		return new PrincipalDetails(userEntity, oAuth2User.getAttributes() );
	}
}
