package com.ssafy.link.service;

import java.util.List;

import com.ssafy.link.dto.Room_need;
import com.ssafy.link.dto.Waiting;

public interface WaitingServiceI {
	int insert_waiting(Waiting w);
	int get_waiting_num();
	List<Waiting> get_waiting_ids();
	int delete_waiting(String id);
	int set_0_watching_num(int room_id);
	int delete_watching_in_room(int room_id);
	Room_need get_room_need(String userid);
}
