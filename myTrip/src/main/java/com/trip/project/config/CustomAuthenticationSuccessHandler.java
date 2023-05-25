package com.trip.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.project.config.oauth.provider.NaverUserInfo;
import com.trip.project.dto.user.UserDto;
import com.trip.project.dto.user.UserRepository;
import com.trip.project.service.jwt.JwtServiceImpl;
import com.trip.project.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;

	private final ObjectMapper objectMapper;
//
//    public CustomAuthenticationSuccessHandler(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//    
	private OAuth2User oAuth2User;

	public CustomAuthenticationSuccessHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 로그인 완료 후 처리할 로직을 작성합니다.
		// ...
		Object principal = authentication.getPrincipal();
		OAuth2User oauth2User = (OAuth2User) principal;

		System.out.println(oauth2User.getAttributes());
		System.out.println("로그인 해야함");

		Map<String, Object> map = oauth2User.getAttributes();
		System.out.println(map);
		Map<String, Object> res = (Map<String, Object>) map.get("response");
		
		
		UserDto userEntity;
		if(res == null) {
			System.out.println( map.get("email"));
			userEntity = userRepository.findByUserEmail((String) map.get("email"));
			
		}else {
			System.out.println(res);			
			userEntity = userRepository.findByUserEmail((String) res.get("email"));
		}
		

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		HttpSession session = request.getSession();
		session.setAttribute("test", "test");
		Cookie cookie = null;
		try {
			UserDto loginUser = userService.login(userEntity);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserAccount(), "access-token");// key, data,
																										// subject
				resultMap.put("access-token", token);
				resultMap.put("message", "sucess");
				status = HttpStatus.ACCEPTED;
				response.addHeader("access-token", token);
				String responseData = "로그인 완료!";
				System.out.println("로그인 성공");

				cookie = new Cookie("token", token);
				cookie.setMaxAge(60 * 60 * 5);
			} else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
				System.out.println("로그인 실패");
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		System.out.println(resultMap);
		System.out.println("세센에 넣었음");
		session.setAttribute("loginData", resultMap);
		
		
		response.addCookie(cookie);
		cookie.setDomain("localhost:8080");
		cookie.setPath("/");
		// JSON 형식으로 응답 전송
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(resultMap));

		Cookie cookie2 = new Cookie("myCookie", "myValue");
		cookie2.setMaxAge(3600); // 쿠키의 유효 시간 설정 (예: 1시간)
		response.addCookie(cookie2);

		response.sendRedirect("http://localhost:8080/oauth2/redirect");

	}

}