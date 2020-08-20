package com.ssafy.link;

import java.util.Arrays;
import com.ssafy.link.Interceptor.JwtInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableCaching
@SpringBootApplication
public class LinkMariaApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(LinkMariaApplication.class, args);
	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	//프론트에서 header에 token을 넣어서 보내주면 얘가 알아서 체크할듯
	@Override    //addPathpatterns -> 토큰 불가능, exclude... -> filter느낌, 어떤 애가 토근을 받을지 결정하는 거
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/inf").excludePathPatterns(Arrays.asList("login","insert"));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").
				allowedOrigins("*").
				allowedMethods("*").
				allowedHeaders("*").
				exposedHeaders("token");
	}

}