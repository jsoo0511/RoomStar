package com.ssafy.link.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.link.dto.Get_vote;
import com.ssafy.link.dto.Room;
import com.ssafy.link.dto.Vote;
import com.ssafy.link.dto.Watching;
import com.ssafy.link.repository.RoomRepoI;

@Service
public class RoomServiceImpl implements RoomServiceI{
	
	@Autowired
	RoomRepoI repo;
	
	@Override
	public List<Room> get_room() {
		return repo.get_room();
	}
	
	@Override
	public int udpate_room(Room room) {
		return repo.update_room(room);
	}

	@Override
	public int out_room(Room room) {
		return repo.out_room(room);
	}

	@Override
	public int insert_watching(Watching watch) {
		return repo.insert_watching(watch);
	}

	@Override
	public int delete_watching(String id) {
		return repo.delete_watching(id);
	}

	@Override
	public int update_watching(Watching watch) {
		return repo.update_watching(watch);
	}

	@Override
	public List<Watching> select_watcing() {
		return repo.select_watcing();
	}

	@Override
	public List<Room> get_room_include_watching_num(int room_id) {
		return repo.get_room_include_watching_num(room_id);
	}

	@Override
	public int plus_watching_num(int room_id) {
		return repo.plus_watching_num(room_id);
	}

	@Override
	public int minus_watching_num(int room_id) {
		return repo.minus_watching_num(room_id);
	}

	@Override
	public Room get_room_info_for_out_room(String userid) {
		return repo.get_room_info_for_out_room(userid);
	}

	@Override
	public int select_music(Room room) {
		return repo.select_music(room);
	}

	@Override
	public int insert_vote_user(Room room) {
		return repo.insert_vote_user(room);
	}

	@Override
	public int delete_vote_user(Room room) {
		return repo.delete_vote_user(room);
	}

	@Override
	public int update_vote(Vote v) {
		return repo.update_vote(v);
	}

	@Override
	public Get_vote get_vote(int room_id) {
		return repo.get_vote(room_id);
	}

	

}
