package com.ssafy.link.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.link.dto.User;
import com.ssafy.link.dto.User_local;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("jwtService")
public class JwtServiceImpl implements JwtService{

private static final String SALT =  "luvookSecret";
	
	@Override
	public String create(User user){
		String jwt = Jwts.builder()
						 .setHeaderParam("typ", "JWT")
						 .setHeaderParam("regDate", System.currentTimeMillis())
						 .setSubject("로그인 토큰")
						 .claim("User", user)
						 .signWith(SignatureAlgorithm.HS256, this.generateKey())
						 .compact();
	//	log.debug("토큰발행: {}",jwt);
		System.out.println("토큰발행");
		return jwt;
	}
	
	private byte[] generateKey(){
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
		//	if(log.isInfoEnabled()){
				e.printStackTrace();
		//	}else{
		//		log.error("Making JWT Key Error ::: {}", e.getMessage());
		//	}
		}
		
		return key;
	}

	@Override
	public boolean isUsable(String jwt) {
		try{
		//	log.trace("토큰 점검: {}",jwt);
			Jws<Claims> claims = Jwts.parser()
					  .setSigningKey(this.generateKey())
					  .parseClaimsJws(jwt);
			return true;
			
		}catch (Exception e) {
			System.out.println("토큰 검사");
		//	if(log.isInfoEnabled()){
		//		e.printStackTrace();
		//	}else{
		//		log.error(e.getMessage());
		//	}
			throw new UnauthorizedException();

			/*개발환경!!!
			 * return false;*/
			 
		}
	}
	
	@Override
	public Map<String, Object> get(String jwt) {
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		//String jwt = request.getHeader("Authorization");
		Jws<Claims> claims = null;
		System.out.println("jwt get 시작");
		try {
			System.out.println("jwt try 시작");
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (Exception e) {
		//	if(log.isInfoEnabled()){
				e.printStackTrace();
		//	}else{
		//		log.error(e.getMessage());
		//	}
			throw new UnauthorizedException();
			//Map<String,Object> map=new HashMap<String, Object>();
			//map.put("status",false);
			//return map;
			/*개발환경
			Map<String,Object> testMap = new HashMap<>();
			testMap.put("memberId", 2);
			return testMap;*/
		}
//		@SuppressWarnings("unchecked")
//		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
//		return value;
		return claims.getBody();
	}

	@Override
	public int getMemberId() {
		return (int)this.get("member").get("memberId");
	}
}