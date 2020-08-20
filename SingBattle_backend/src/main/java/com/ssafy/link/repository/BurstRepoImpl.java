package com.ssafy.link.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.link.dto.Burst;
import com.ssafy.link.dto.Like_video;

@Repository
public class BurstRepoImpl implements BurstRepoI{
	
	private final String namespace="com.ssafy.link.mapper.BurstMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public int insert_burst(Burst b) {
		return session.insert(namespace+"insert_burst",b);
	}

	@Override
	public int play_burst(Burst b) {
		return session.update(namespace+"play_burst",b);
	}

	@Override
	public Like_video check_like_video(Like_video l) {
		return session.selectOne(namespace+"check_like_video",l);
	}

	@Override
	public int like_video_insert(Like_video l) {
		return session.insert(namespace+"like_video_insert", l);
	}

	@Override
	public int like_video_delete(Like_video l) {
		return session.delete(namespace+"like_video_delete", l);
	}

	@Override
	public int count_like_video(String videoURL) {
		return session.selectOne(namespace+"count_like_video",videoURL);
	}

	@Override
	public String get_lyric(String title) {
		return session.selectOne(namespace+"get_lyric",title);
	}

	@Override
	public int plus_like_num(Like_video l) {
		return session.update(namespace+"plus_like_num",l);
	}

	@Override
	public List<Burst> get_burst() {
		return session.selectList(namespace+"get_burst");
	}

	@Override
	public int minus_like_num(Like_video l) {
		return session.update(namespace+"minus_like_num",l);
	}

	@Override
	public Burst get_burst_id(int id) {
		return session.selectOne(namespace+"get_burst_id",id);
	}

	@Override
	public int delete_burst(int id) {
		return session.delete(namespace+"delete_burst", id);
	}

	@Override
	public int delete_like_video(int id) {
		return session.delete(namespace+"delete_like_video", id);
	}
}
