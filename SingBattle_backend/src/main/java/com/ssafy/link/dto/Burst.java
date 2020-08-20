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
public class Burst {
	int id;
	String videoURL;
	String userid;
	String imgURL;
	String nickname;
	String title;
	String contents;
	int like_num;
	public Burst(int id, String videoURL, String userid, String imgURL, String nickname, String title, String contents,
			int like_num) {
		super();
		this.id = id;
		this.videoURL = videoURL;
		this.userid = userid;
		this.imgURL = imgURL;
		this.nickname = nickname;
		this.title = title;
		this.contents = contents;
		this.like_num = like_num;
	}
	public Burst() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	@Override
	public String toString() {
		return "Burst [id=" + id + ", videoURL=" + videoURL + ", userid=" + userid + ", imgURL=" + imgURL
				+ ", nickname=" + nickname + ", title=" + title + ", contents=" + contents + ", like_num=" + like_num
				+ "]";
	}
	
	
}
