package com.ssafy.link.dto;

public class Like_video {
	String userid;
	int id;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Like_video [userid=" + userid + ", id=" + id + "]";
	}
	public Like_video(String userid, int id) {
		super();
		this.userid = userid;
		this.id = id;
	}
	public Like_video() {
		super();
	}
	
	
}
