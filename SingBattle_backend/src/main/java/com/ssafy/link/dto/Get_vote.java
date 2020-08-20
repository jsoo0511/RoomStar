package com.ssafy.link.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Get_vote {
	int room_id;
	String user1;
	String user2;
	int vote1;
	int vote2;
	public Get_vote(int room_id, String user1, String user2, int vote1, int vote2) {
		super();
		this.room_id = room_id;
		this.user1 = user1;
		this.user2 = user2;
		this.vote1 = vote1;
		this.vote2 = vote2;
	}
	public Get_vote() {
		super();
	}
	@Override
	public String toString() {
		return "Get_vote [room_id=" + room_id + ", user1=" + user1 + ", user2=" + user2 + ", vote1=" + vote1
				+ ", vote2=" + vote2 + "]";
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
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
	public int getVote1() {
		return vote1;
	}
	public void setVote1(int vote1) {
		this.vote1 = vote1;
	}
	public int getVote2() {
		return vote2;
	}
	public void setVote2(int vote2) {
		this.vote2 = vote2;
	}
	
	
}
