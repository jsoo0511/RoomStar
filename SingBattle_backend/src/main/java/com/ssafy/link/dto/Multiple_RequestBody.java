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
public class Multiple_RequestBody {
	User user1;
	User user2;
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public Multiple_RequestBody(User user1, User user2) {
		super();
		this.user1 = user1;
		this.user2 = user2;
	}
	public Multiple_RequestBody() {
		super();
	}
	
	
}
