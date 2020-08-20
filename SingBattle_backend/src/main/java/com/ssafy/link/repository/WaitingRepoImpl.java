package com.ssafy.link.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.link.dto.Room_need;
import com.ssafy.link.dto.Waiting;

@Repository
public class WaitingRepoImpl implements WaitingRepoI{

private final String namespace="com.ssafy.link.mapper.WaitingMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public int insert_waiting(Waiting w) {
		return session.insert(namespace+"insert_waiting", w);
	}

	@Override
	public int get_waiting_num() {
		return session.selectOne(namespace+"get_waiting_num");
	}

	@Override
	public List<Waiting> get_waiting_ids() {
		return session.selectList(namespace+"get_waiting_ids");
	}

	@Override
	public int delete_waiting(String id) {
		return session.delete(namespace+"delete_waiting",id);
	}

	@Override
	public int set_0_watching_num(int room_id) {
		return session.update(namespace+"set_0_watching_num",room_id);
	}

	@Override
	public int delete_watching_in_room(int room_id) {
		return session.delete(namespace+"delete_watching_in_room",room_id);
	}

	@Override
	public Room_need get_room_need(String userid) {
		return session.selectOne(namespace+"get_room_need",userid);
	}
	
	
}
