package com.ssafy.link.service;

import java.util.List;

import com.ssafy.link.dto.Get_vote;
import com.ssafy.link.dto.Room;
import com.ssafy.link.dto.Vote;
import com.ssafy.link.dto.Watching;

public interface RoomServiceI {
	List<Room> get_room();
	int udpate_room(Room room);
	int out_room(Room room);
	int insert_watching(Watching watch);
	int delete_watching(String id);
	int update_watching(Watching watch);
	List<Watching> select_watcing();
	List<Room> get_room_include_watching_num(int room_id);
	int plus_watching_num(int room_id);
	int minus_watching_num(int room_id);
	Room get_room_info_for_out_room(String userid);
	int select_music(Room room);
	int insert_vote_user(Room room);
	int delete_vote_user(Room room);
	int update_vote(Vote v);
	Get_vote get_vote(int room_id);
}
