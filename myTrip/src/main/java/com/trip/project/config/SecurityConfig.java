package com.trip.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.project.config.oauth.PrincipalOauth2UserService;


@Configuration
@EnableWebSecurity //스프링 시큐리티 필터(지금 작성하는 것)가 기본 스프링 필터 체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화, proAuthorize,postAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	PrincipalOauth2UserService principalOauth2UserService;
	
	//해당 메서드의 리턴되는 오브젝트를 loc로 등록해준다.
//	@Bean
//	public BCryptPasswordEncoder encodePwd() {
//		return new BCryptPasswordEncoder();
//	}
	
	
	//소셜로그인 구현 단계
	//1.코드받기(인증), 2. 엑세스토큰(권한), 3,사용자프로필정보를 가져와서
	// 4-1. 그 정보를 토대로 회원가입을 자동으로 진행시키기도 함 
	//4-2(이메일, 전화번호, 이름 , 아이디) 쇼핑몰-> 집주소, 백화점몰->vip등급, 일반등급 필요 -> 추가적인 정보 회원가입으로 받아야 함
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/oauth2/**").authenticated() //user로 들어오는 입력이면 인증 필요, 인증만 되면 들어갈 수있는 주소
//			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")//권한 확인 필요
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()//다른 요청은 허가
			.and()
			.formLogin()
			.loginPage("/loginForm")//권한,인증이 없으면 로그인페이지로 이동 url적어줌
			.usernameParameter("username") //로그인폼에서 id로 넘겨주는 name이름 설정 기본이 username인데 바꾸고 싶으면 변경해야함
			.loginProcessingUrl("/login") // login주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줍니다
//			.defaultSuccessUrl("/")//로그인되면 갈 url : 특정페이지를 요청해서 로그인하면 그 페이지로 자동으로 간다
			.and()
			.oauth2Login().successHandler(authenticationSuccessHandler) //구글로그인하면 이동페이지
			.authorizationEndpoint()
//			.baseUri("/oauth2/authorization")
			//.loginPage("/loginForm")
			.and()
			.userInfoEndpoint()
			.userService(principalOauth2UserService)//구글로그인이 완료된 뒤에 후처리가 필요함. tip. 코드XX (엑세스토큰 + 사용자프로필 정보 O)
			.and()
			.redirectionEndpoint()
//			.baseUri("/login/oauth2/code/**")
			;
			
	}
	
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
    
    

    
//    @Bean
//    public OAuth2User getOAuth2User() {
//        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest());
//        return oAuth2User;
//    }
    
//    @Bean
//    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
//        return new CustomAuthenticationSuccessHandler(principalOauth2UserService.loadUser(userRequest));
//    }
}
