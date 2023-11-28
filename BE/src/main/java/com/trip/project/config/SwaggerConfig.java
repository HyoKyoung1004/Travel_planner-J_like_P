package com.trip.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	//http://localhost:9999/trip/swagger-ui.html
	
	private String version = "V1";
	private String title = "Every Trip Planner " + version;

	private ApiInfo apiInfo() {
		String descript = "EveryTripPlanner API Reference for Developers<br>";
		//descript += "<img src=\"http://localhost:9999/vue/static/assets/img/ssafy_logo.png\">";
		return new ApiInfoBuilder().title(title).description(descript)
//				.termsOfServiceUrl("https://edu.ssafy.com")
				.contact(new Contact("Every Trip Planner", "https://edu.ssafy.com", "ssafy@ssafy.com")).version("1.0").build();
	}
	
	// API마다 구분짓기 위한 설정.
	@Bean
	public Docket attractionApi() {
		return getDocket("attraction", Predicates.or(PathSelectors.regex("/attract.*")));
	}
	
	
	@Bean
	public Docket userApi() {
		return getDocket("user", Predicates.or(PathSelectors.regex("/user.*")));
	}
	
	
	@Bean
	public Docket planApi() {
		return getDocket("plan", Predicates.or(PathSelectors.regex("/plan.*")));
	}
	

	@Bean
	public Docket allApi() {
		return getDocket("전체", Predicates.or(PathSelectors.regex("/*.*")));
	}

	public Docket getDocket(String groupName, Predicate<String> predicate) {
//		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		return new Docket(DocumentationType.SWAGGER_2).groupName(groupName).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.trip.project.controller")).paths(predicate)
				.apis(RequestHandlerSelectors.any()).build();

	}

	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}
	
}
