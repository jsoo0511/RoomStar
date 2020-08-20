package com.ssafy.link.repository;

import java.util.List;

import com.ssafy.link.dto.Burst;
import com.ssafy.link.dto.Like_video;

public interface BurstRepoI {
	int insert_burst(Burst b);
	int play_burst(Burst b);
	Like_video check_like_video(Like_video l);
	int like_video_insert(Like_video l);
	int like_video_delete(Like_video l);
	int count_like_video(String videoURL);
	String get_lyric(String title);
	int plus_like_num(Like_video l);
	int minus_like_num(Like_video l);
	List<Burst> get_burst();
	Burst get_burst_id(int id);
	int delete_burst(int id);
	int delete_like_video(int id);
}
