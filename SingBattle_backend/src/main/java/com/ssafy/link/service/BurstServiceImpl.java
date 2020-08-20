package com.ssafy.link.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.link.dto.Burst;
import com.ssafy.link.dto.Like_video;
import com.ssafy.link.repository.BurstRepoI;

@Service
public class BurstServiceImpl implements BurstServiceI{
	
	@Autowired
	BurstRepoI repo;

	@Override
	public int insert_burst(Burst b) {
		return repo.insert_burst(b);
	}

	@Override
	public int play_burst(Burst b) {
		return repo.play_burst(b);
	}

	@Override
	public Like_video check_like_video(Like_video l) {
		return repo.check_like_video(l);
	}

	@Override
	public int like_video_insert(Like_video l) {
		return repo.like_video_insert(l);
	}

	@Override
	public int like_video_delete(Like_video l) {
		return repo.like_video_delete(l);
	}

	@Override
	public int count_like_video(String videoURL) {
		return repo.count_like_video(videoURL);
	}

	@Override
	public String get_lyric(String title) {
		return repo.get_lyric(title);
	}

	@Override
	public int plus_like_num(Like_video l) {
		return repo.plus_like_num(l);
	}

	@Override
	public List<Burst> get_burst() {
		return repo.get_burst();
	}

	@Override
	public int minus_like_num(Like_video l) {
		return repo.minus_like_num(l);
	}

	@Override
	public Burst get_burst_id(int id) {
		return repo.get_burst_id(id);
	}

	@Override
	public int delete_burst(int id) {
		return repo.delete_burst(id);
	}

	@Override
	public int delete_like_video(int id) {
		return repo.delete_like_video(id);
	}
}
