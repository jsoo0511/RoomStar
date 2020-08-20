package com.ssafy.link.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.link.dto.User;
import com.ssafy.link.dto.User_local;

@Repository
public class KakaoImpl implements KakaoRepoI{
	private final String namespace="com.ssafy.link.mapper.KakaoMapper.";
	@Autowired
	SqlSession session;
	
	@Override
	public int insertUser(User user) {
		return this.session.insert(namespace+"insert",user);
	}

	@Override
	public List<User> getAll() {
		return session.selectList(namespace+"getAll");
	}

	@Override
	public User getById(String id) {
		return session.selectOne(namespace+"getById",id);
	}

	@Override
	public int deleteUser(User user) {
		return session.delete(namespace+"delete",user);
	}

	@Override
	public int updateUser(User user) {
		return session.update(namespace+"update", user);
	}

	@Override
	public User login(User user) {
		System.out.println("중간체크: "+user);
		return session.selectOne(namespace+"login",user);
	}

	@Override
	public int insert_total(User user) {
		System.out.println("user_insert_impl: "+user);
		return session.insert(namespace+"insert_total",user);
	}

	@Override
	public int battle_result(User user) {
		return session.update(namespace+"battle_result",user);
	}

	@Override
	public int set_grade(User user) {
		return session.update(namespace+"set_grade",user);
	}
}