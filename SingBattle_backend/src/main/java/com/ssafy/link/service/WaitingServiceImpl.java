package com.ssafy.link.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.link.dto.Room_need;
import com.ssafy.link.dto.Waiting;
import com.ssafy.link.repository.WaitingRepoI;

@Service
public class WaitingServiceImpl implements WaitingServiceI{
	
	@Autowired
	WaitingRepoI waiting;

	@Override
	public int insert_waiting(Waiting w) {
		return waiting.insert_waiting(w);
	}

	@Override
	public int get_waiting_num() {
		return waiting.get_waiting_num();
	}

	@Override
	public List<Waiting> get_waiting_ids() {
		return waiting.get_waiting_ids();
	}

	@Override
	public int delete_waiting(String id) {
		return waiting.delete_waiting(id);
	}

	@Override
	public int set_0_watching_num(int room_id) {
		return waiting.set_0_watching_num(room_id);
	}

	@Override
	public int delete_watching_in_room(int room_id) {
		return waiting.delete_watching_in_room(room_id);
	}

	@Override
	public Room_need get_room_need(String userid) {
		return waiting.get_room_need(userid);
	}
}
