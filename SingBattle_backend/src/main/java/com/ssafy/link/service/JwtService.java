package com.ssafy.link.service;

import java.util.Map;

import com.ssafy.link.dto.User;
import com.ssafy.link.dto.User_local;

public interface JwtService {
	String create(User user);
	Map<String, Object> get(String key);
	int getMemberId();
	boolean isUsable(String jwt);
	
}