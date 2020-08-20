package com.ssafy.link.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@ToString
public class User {
	String userid;
	String nickname;
	String profileimg;
	String email;
	String pw;
	String provider;
	String grade;
	int game;
	int win;
	int lose;
	public User(String userid, String pw, String provider) {
		this.userid = userid;
		this.pw = pw;
		this.provider = provider;
	}
	public User(String userid, String nickname, String profileimg, String email, String pw, String provider,
			String grade, int game, int win, int lose) {
		super();
		this.userid = userid;
		this.nickname = nickname;
		this.profileimg = profileimg;
		this.email = email;
		this.pw = pw;
		this.provider = provider;
		this.grade = grade;
		this.game = game;
		this.win = win;
		this.lose = lose;
	}
	public User() {
		super();
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getGame() {
		return game;
	}
	public void setGame(int game) {
		this.game = game;
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
		return "User [userid=" + userid + ", nickname=" + nickname + ", profileimg=" + profileimg + ", email=" + email
				+ ", pw=" + pw + ", provider=" + provider + ", grade=" + grade + ", game=" + game + ", win=" + win
				+ ", lose=" + lose + "]";
	}
	
	
	
	
}