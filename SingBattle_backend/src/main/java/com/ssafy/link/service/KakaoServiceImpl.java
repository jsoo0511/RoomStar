package com.ssafy.link.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.link.dto.User;
import com.ssafy.link.dto.User_local;
import com.ssafy.link.repository.KakaoRepoI;

@Service
public class KakaoServiceImpl implements KakaoServiceI{
	
	@Autowired
	KakaoRepoI kakao;
	

	@Override
	public int insertUser(User user) {
		return kakao.insertUser(user);
	}


	@Override
	public List<User> getAll() {
		return kakao.getAll();
	}


	@Override
	public User getById(String id) {
		return kakao.getById(id);
	}


	@Override
	public int deleteUser(User user) {
		return kakao.deleteUser(user);
	}


	@Override
	public int updateUser(User user) {
		return kakao.updateUser(user);
	}


	@Override
	public User login(User user) {
		return kakao.login(user);
	}


	@Override
	public int insert_total(User user) {
		return kakao.insert_total(user);
	}


	@Override
	public int battle_result(User user) {
		return kakao.battle_result(user);
	}


	@Override
	public int set_grade(User user) {
		return kakao.set_grade(user);
	}
}
