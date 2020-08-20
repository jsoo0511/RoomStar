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
public class Vote {
	int room_id;
	int player_idx;
	int vote_num;
	public Vote(int room_id, int player_idx, int vote_num) {
		super();
		this.room_id = room_id;
		this.player_idx = player_idx;
		this.vote_num = vote_num;
	}
	public Vote() {
		super();
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getPlayer_idx() {
		return player_idx;
	}
	public void setPlayer_idx(int player_idx) {
		this.player_idx = player_idx;
	}
	public int getVote_num() {
		return vote_num;
	}
	public void setVote_num(int vote_num) {
		this.vote_num = vote_num;
	}
	@Override
	public String toString() {
		return "Vote [room_id=" + room_id + ", player_idx=" + player_idx + ", vote_num=" + vote_num + "]";
	}
	
	
}
