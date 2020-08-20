package com.ssafy.link.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.link.dto.Get_vote;
import com.ssafy.link.dto.Room;
import com.ssafy.link.dto.Vote;
import com.ssafy.link.dto.Watching;

@Repository
public class RoomRepoImpl implements RoomRepoI{

	private final String namespace="com.ssafy.link.mapper.RoomMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public int update_room(Room room) {
		System.out.println(room);
		return session.update(namespace+"update_room", room);
	}
	@Override
	public int out_room(Room room) {
		return session.update(namespace+"out_room",room);
	}
	@Override
	public int insert_watching(Watching watch) {
		return session.insert(namespace+"insert_watching",watch);
	}
	@Override
	public int delete_watching(String id) {
		return session.delete(namespace+"delete_watching",id);
	}
	@Override
	public int update_watching(Watching watch) {
		return session.update(namespace+"update_watching",watch);
	}
	@Override
	public List<Watching> select_watcing() {
		return session.selectList(namespace+"select_watching");
	}
	@Override
	public List<Room> get_room() {
		return session.selectList(namespace+"get_room");
	}
	@Override
	public List<Room> get_room_include_watching_num(int room_id) {
		return session.selectList(namespace+"get_room_include_watching_num",room_id);
	}
	@Override
	public int plus_watching_num(int room_id) {
		return session.update(namespace+"plus_watching_num", room_id);
	}
	@Override
	public int minus_watching_num(int room_id) {
		return session.update(namespace+"minus_watching_num", room_id);
	}
	@Override
	public Room get_room_info_for_out_room(String userid) {
		return session.selectOne(namespace+"get_room_info_for_out_room",userid);
	}
	@Override
	public int select_music(Room room) {
		return session.update(namespace+"select_music",room);
	}
	@Override
	public int insert_vote_user(Room room) {
		System.out.println("insert_vote_user_중간점검: "+room);
		return session.update(namespace+"insert_vote_user",room);
	}
	@Override
	public int delete_vote_user(Room room) {
		return session.update(namespace+"delete_vote_user",room);
	}
	@Override
	public int update_vote(Vote v) {
		return session.update(namespace+"update_vote",v);
	}
	@Override
	public Get_vote get_vote(int room_id) {
		return session.selectOne(namespace+"get_vote",room_id);
	}
}
