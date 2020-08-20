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
public class Room_need {
	String nickname;
	String profileimg;
	String grade;
	int win;
	int lose;
	public Room_need(String nickname, String profileimg, String grade, int win, int lose) {
		super();
		this.nickname = nickname;
		this.profileimg = profileimg;
		this.grade = grade;
		this.win = win;
		this.lose = lose;
	}
	public Room_need() {
		super();
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfileimg() {
		return profileimg;
	}
	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	@Override
	public String toString() {
		return "Room_need [nickname=" + nickname + ", profileimg=" + profileimg + ", grade=" + grade + ", win=" + win
				+ ", lose=" + lose + "]";
	}
	
	
}
