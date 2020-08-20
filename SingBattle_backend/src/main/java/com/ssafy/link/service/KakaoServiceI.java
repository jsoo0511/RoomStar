package com.ssafy.link.service;

import java.util.List;

import com.ssafy.link.dto.User;
import com.ssafy.link.dto.User_local;

public interface KakaoServiceI {
	User login(User user);
	int insertUser(User user);
	List<User> getAll();
	User getById(String id);
	int deleteUser(User user);
	int updateUser(User user);
	int insert_total(User user);
	int battle_result(User user);
	int set_grade(User user);
}
