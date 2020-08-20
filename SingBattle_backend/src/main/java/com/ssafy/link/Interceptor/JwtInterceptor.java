package com.ssafy.link.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.link.service.JwtService;
import com.ssafy.link.service.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Configuration
//@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
	private static final String HEADER_AUTH = "token";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String token = request.getHeader(HEADER_AUTH);

		if(token != null && jwtService.isUsable(token)){
		//	log.trace("토큰 사용 가능:{}",token);
			return true;
		}else{
			System.out.println("error jwt preHandle in JwtInterceptor");
			throw new UnauthorizedException();
			
		}
	}
}
