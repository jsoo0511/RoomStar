package com.ssafy.link.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.link.dto.Burst;
import com.ssafy.link.dto.Like_video;
import com.ssafy.link.service.BurstServiceI;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
//@RequestMapping("/singBattle")
public class BurstController {
	
	
	@Autowired
	BurstServiceI bService;
	
	@GetMapping("get_burst")
	private ResponseEntity<Map<String, Object>> get_burst(){
		try {
			List<Burst> list=bService.get_burst();
			Collections.reverse(list);
			return response(list,HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("get_burst_error",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("get_burst/{id}")
	private ResponseEntity<Map<String, Object>> get_burst_id(@PathVariable int id){
		try {
			Burst b=bService.get_burst_id(id);
			return response(b,HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("get_burst_id_error",HttpStatus.CONFLICT,false);
		}
	}
	
	@PostMapping("insert_burst")
	@ApiOperation("자랑하기 등록")
	private ResponseEntity<Map<String, Object>> insert_burst(@RequestBody Burst b){
		System.out.println(b);
		try {
			bService.insert_burst(b);
			return response("자랑하기 등록 성공",HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("자랑하기 등록 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	@PostMapping("check")
	@ApiOperation("해당 유저가 동영상을 좋아요 체크했는지 검사")
	private ResponseEntity<Map<String, Object>> check(@RequestBody Like_video l){
		try {
			Like_video check=bService.check_like_video(l);
			if(check==null)
				return response("false",HttpStatus.OK,true);
			else
				return response("true",HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("error",HttpStatus.CONFLICT,false);
		}
	}
	
//	@PutMapping("play_burst")
//	@ApiOperation("동영상 play시 조회수 추가")
//	private ResponseEntity<Map<String, Object>> play_burst(@RequestBody Burst b){
//		try {
//			int num=bService.play_burst(b);
//			if(num==1)
//			   return response("play 1회 추가",HttpStatus.OK,true);
//			else
//			   return response("play 1회 추가 실패",HttpStatus.CONFLICT,false);
//		} catch(NullPointerException e) {
//			e.printStackTrace();
//			return response("NullpointerException",HttpStatus.CONFLICT,false);
//		}catch (RuntimeException e) {
//			e.printStackTrace();
//			return response("play 1회 추가 실패",HttpStatus.CONFLICT,false);
//		}
//	}
	
	@PutMapping("update_like_video")//좋아하는 영상에 추가하거나 삭제 
	//좋아하는 동영상목록에 존재하면 삭제 없으면 추가
	private ResponseEntity<Map<String, Object>> check_like_video(@RequestBody Like_video l){
		System.out.println(l);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Like_video video=bService.check_like_video(l);
			System.out.println("video: "+video);
			if(video==null) {
				System.out.println("Like video insert start");
				bService.like_video_insert(l);
				bService.plus_like_num(l);
				Burst b=bService.get_burst_id(l.getId());
				System.out.println(b.getLike_num());
				resultMap.put("num",b.getLike_num());
				resultMap.put("data","true");
				return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
			} else {
				bService.like_video_delete(l);
				bService.minus_like_num(l);
				Burst b=bService.get_burst_id(l.getId());
				resultMap.put("num",b.getLike_num());
				resultMap.put("data","false");
				return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
			}
		} catch(NullPointerException e) {
			bService.like_video_insert(l);
			bService.plus_like_num(l);
			return response("true",HttpStatus.OK,true);
		}catch (RuntimeException e) {
			e.printStackTrace();
			return response("좋아하는 영상 update 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("count_like_video/{videoURL}")
	private ResponseEntity<Map<String, Object>> count_like_video(@PathVariable String videoURL){
		try {
			int num=bService.count_like_video(videoURL);
			return response(num,HttpStatus.OK,true);
		} catch(NullPointerException e) {
			e.printStackTrace();
			return response("NullpointerException",HttpStatus.CONFLICT,false);
		}catch (RuntimeException e) {
			e.printStackTrace();
			return response("play횟수 가져오기 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("get_lyric/{title}")
	@ApiOperation("title을 주면 해당 가사를 반환")
	private ResponseEntity<Map<String, Object>> Get_lyric(@PathVariable String title){
		try {
			String lyric=bService.get_lyric(title);
			return response(lyric,HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("가사 가져오기 실패",HttpStatus.CONFLICT,false);
		}
	}

	@DeleteMapping("delete_burst/{id}")
	@ApiOperation("자랑하기 목록 삭제")
	private ResponseEntity<Map<String, Object>> Delete_burst(@PathVariable int id){
		try {
			int num=bService.delete_burst(id);
			bService.delete_like_video(id);
			return response("delete_burst_success",HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("delete_burst_fail",HttpStatus.CONFLICT,false);
		}
	}
	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpStatus, boolean status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", data);
		resultMap.put("status", status);

		// 상태와 함께 Map반환
		return new ResponseEntity<>(resultMap, httpStatus);
	}
}
