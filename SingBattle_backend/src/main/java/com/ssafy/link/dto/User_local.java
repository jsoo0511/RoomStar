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
public class User_local {
	String email;
	String pw;
	String nickname;
	String profileimg;
	String grade;
	int game;
	int win;
	int lose;
	public User_local(String email, String pw) {
		this.pw = pw;
		this.email = email;
	}
}
