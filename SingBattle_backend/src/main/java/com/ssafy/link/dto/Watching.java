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
public class Watching {
	String userid;
	int room_id;
	int vote;
	public Watching(String userid, int room_id, int vote) {
		super();
		this.userid = userid;
		this.room_id = room_id;
		this.vote = vote;
	}
	public Watching() {
		super();
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	@Override
	public String toString() {
		return "Watching [userid=" + userid + ", room_id=" + room_id + ", vote=" + vote + "]";
	}
	
	
}