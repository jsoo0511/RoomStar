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
public class Room_More {
	int room_id;
	int singer_num;
	String user1;
	String user2;
	String music1;
	String music2;
	int watching_num;
	String user1_img;
	String user1_grade;
	int win1;
	int lose1;
	int user1_win_rate;
	String user2_img;
	String user2_grade;
	int win2;
	int lose2;
	int user2_win_rate;
	public Room_More(int room_id, int singer_num, String user1, String user2, String music1, String music2,
			int watching_num, String user1_img, String user1_grade, int win1, int lose1, int user1_win_rate,
			String user2_img, String user2_grade, int win2, int lose2, int user2_win_rate) {
		super();
		this.room_id = room_id;
		this.singer_num = singer_num;
		this.user1 = user1;
		this.user2 = user2;
		this.music1 = music1;
		this.music2 = music2;
		this.watching_num = watching_num;
		this.user1_img = user1_img;
		this.user1_grade = user1_grade;
		this.win1 = win1;
		this.lose1 = lose1;
		this.user1_win_rate = user1_win_rate;
		this.user2_img = user2_img;
		this.user2_grade = user2_grade;
		this.win2 = win2;
		this.lose2 = lose2;
		this.user2_win_rate = user2_win_rate;
	}
	public Room_More() {
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
	public String getUser1_img() {
		return user1_img;
	}
	public void setUser1_img(String user1_img) {
		this.user1_img = user1_img;
	}
	public String getUser1_grade() {
		return user1_grade;
	}
	public void setUser1_grade(String user1_grade) {
		this.user1_grade = user1_grade;
	}
	public int getWin1() {
		return win1;
	}
	public void setWin1(int win1) {
		this.win1 = win1;
	}
	public int getLose1() {
		return lose1;
	}
	public void setLose1(int lose1) {
		this.lose1 = lose1;
	}
	public int getUser1_win_rate() {
		return user1_win_rate;
	}
	public void setUser1_win_rate(int user1_win_rate) {
		this.user1_win_rate = user1_win_rate;
	}
	public String getUser2_img() {
		return user2_img;
	}
	public void setUser2_img(String user2_img) {
		this.user2_img = user2_img;
	}
	public String getUser2_grade() {
		return user2_grade;
	}
	public void setUser2_grade(String user2_grade) {
		this.user2_grade = user2_grade;
	}
	public int getWin2() {
		return win2;
	}
	public void setWin2(int win2) {
		this.win2 = win2;
	}
	public int getLose2() {
		return lose2;
	}
	public void setLose2(int lose2) {
		this.lose2 = lose2;
	}
	public int getUser2_win_rate() {
		return user2_win_rate;
	}
	public void setUser2_win_rate(int user2_win_rate) {
		this.user2_win_rate = user2_win_rate;
	}
	@Override
	public String toString() {
		return "Room_More [room_id=" + room_id + ", singer_num=" + singer_num + ", user1=" + user1 + ", user2=" + user2
				+ ", music1=" + music1 + ", music2=" + music2 + ", watching_num=" + watching_num + ", user1_img="
				+ user1_img + ", user1_grade=" + user1_grade + ", win1=" + win1 + ", lose1=" + lose1
				+ ", user1_win_rate=" + user1_win_rate + ", user2_img=" + user2_img + ", user2_grade=" + user2_grade
				+ ", win2=" + win2 + ", lose2=" + lose2 + ", user2_win_rate=" + user2_win_rate + "]";
	}
	
	
}
