package com.ssafy.link.dto;

import java.util.List;

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
public class Waiting {
	String userid;
	String nickname;
	public Waiting(String userid, String nickname) {
		super();
		this.userid = userid;
		this.nickname = nickname;
	}
	public Waiting() {
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
	@Override
	public String toString() {
		return "Waiting [userid=" + userid + ", nickname=" + nickname + "]";
	}
	
	
}
