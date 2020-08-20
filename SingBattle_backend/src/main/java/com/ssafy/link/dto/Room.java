package com.ssafy.link.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room {
	int room_id;
	int singer_num;
	String user1;
	String user2;
	String music1;
	String music2;
	int watching_num;
	public Room(int room_id, int singer_num, String user1, String user2, String music1, String music2,
			int watching_num) {
		super();
		this.room_id = room_id;
		this.singer_num = singer_num;
		this.user1 = user1;
		this.user2 = user2;
		this.music1 = music1;
		this.music2 = music2;
		this.watching_num = watching_num;
	}
	public Room() {
		super();
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getSinger_num() {
		return singer_num;
	}
	public void setSinger_num(int singer_num) {
		this.singer_num = singer_num;
	}
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public String getMusic1() {
		return music1;
	}
	public void setMusic1(String music1) {
		this.music1 = music1;
	}
	public String getMusic2() {
		return music2;
	}
	public void setMusic2(String music2) {
		this.music2 = music2;
	}
	public int getWatching_num() {
		return watching_num;
	}
	public void setWatching_num(int watching_num) {
		this.watching_num = watching_num;
	}
	@Override
	public String toString() {
		return "Room [room_id=" + room_id + ", singer_num=" + singer_num + ", user1=" + user1 + ", user2=" + user2
				+ ", music1=" + music1 + ", music2=" + music2 + ", watching_num=" + watching_num + "]";
	}
	
	
}
