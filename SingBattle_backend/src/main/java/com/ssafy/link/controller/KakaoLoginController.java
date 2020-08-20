package com.ssafy.link.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.link.dto.Multiple_RequestBody;
import com.ssafy.link.dto.User;
import com.ssafy.link.dto.User_local;
import com.ssafy.link.service.JwtService;
import com.ssafy.link.service.KakaoServiceI;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
//@RequestMapping("/singBattle")
public class KakaoLoginController {
	
	@Autowired
	KakaoServiceI kakao;

	@Autowired
	JwtService jwtService;
	
	
	@PostMapping("/insert")
	@ApiOperation("insert 통합") //insert 중복 체크
	public ResponseEntity<Map<String, Object>> insert_total(@RequestBody User user){
		System.out.println(user);
		try {
			User check=kakao.getById(user.getUserid());
			System.out.println("insert 중복체크: "+check);
			if(check==null) {				
				kakao.insert_total(user);
				System.out.println("회원가입 success");
				return response("회원가입 success",HttpStatus.OK,true);
			}else {
				return response("존재하는 아이디입니다. 다른 아이디로 가입해주세요",HttpStatus.CONFLICT,false);
			}
		} catch (Exception e) {
			e.printStackTrace();
	//		log.trace("회원가입 error : {}", e);
			return response("회원가입 fail",HttpStatus.CONFLICT,false);
		}
	}
	
	@PostMapping("/login") //토큰만 받을까?
	@ApiOperation("로그인")//프로필 이미지
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user,HttpServletResponse res){
		System.out.println("login start");
		System.out.println(user);
		try {
			System.out.println("탐색");
			User result=kakao.login(user);
			System.out.println("result: "+result.toString());
			if(user.getUserid().equals(result.getUserid())) { //local일 경우는 token 생성 후 return
				Map<String, Object> resultMap = new HashMap<String, Object>();
					//로그인 성공시 토큰 생성
				if(result.getProfileimg().equals("") || result.getProfileimg()==null) {
					result.setProfileimg("https://static.wixstatic.com/media/a04e38_498a9895aaab46ce834d4865059e47f9~mv2_d_1920_1242_s_2.png/v1/fill/w_784,h_507,al_c,q_95,usm_0.66_1.00_0.01/a04e38_498a9895aaab46ce834d4865059e47f9~mv2_d_1920_1242_s_2.webp");
				}
				System.out.println("설정후: "+result.toString());
					String token=jwtService.create(result);
					res.setHeader("token", token);
					resultMap.put("result", "login success");
					resultMap.put("nickname",result.getNickname());
					resultMap.put("status", HttpStatus.OK);
					resultMap.put("token",token);			
					return  new ResponseEntity<>(resultMap, HttpStatus.OK);
			}else{
				return response("no data",HttpStatus.CONFLICT,false);
			}
		} catch (NullPointerException e) {
			if(user.getProvider().equals("local")) {
				e.printStackTrace();
				Map<String,Object> resultMap=new HashMap<String, Object>();
				resultMap.put("msg","아이디/비밀번호를 다시 확인해주세요");
				resultMap.put("status",false);
				return  new ResponseEntity<>(resultMap, HttpStatus.CONFLICT);
			} else {
				insert_total(user);
				return response("회원가입 success",HttpStatus.OK,true);
			}
		} catch(RuntimeException e) {
			e.printStackTrace();
			return response("error",HttpStatus.CONFLICT,false);
		}
	}
	
	@PostMapping("/info") //프론트가 token이란 이름으로 request헤더에 넣어줘서 보내줘야됨
	@ApiOperation("jwt token의 정보 획득")
	public ResponseEntity<Map<String, Object>> Info(/*HttpServletRequest req*/@RequestParam String token){
		Map<String,Object> map=new HashMap<>();
		System.out.println("jwt 시작!!!!!!!!!!!!!!!!!!!!!");
		try {
			System.out.println("/info시작 ");
			//map.putAll(jwtService.get(req.getHeader("token")));
			map.putAll(jwtService.get(token));
			map.put("status", true);
			return new ResponseEntity<Map<String, Object>> (map,HttpStatus.OK);
		} catch (Exception e) {
			return response("key정보 획득 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("/getUserInfo")
	@ApiOperation("모든 사용자 정보 출력")
	public ResponseEntity<Map<String, Object>> get() {
		try {
			return response(kakao.getAll(),HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("fail",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("/getUserInfo/{id}")
	@ApiOperation("Id에 해당하는 사용자 정보 출력")
	public ResponseEntity<Map<String, Object>> getByNickname(@PathVariable String id){
		try {
			return response(kakao.getById(id),HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("fail",HttpStatus.CONFLICT,false);
		}
	}

	
	@PutMapping("/update")
	@ApiOperation("사용자 정보 수정, nickname, img, email")
	public ResponseEntity<Map<String, Object>> Update(@RequestBody User user){
		try {
			System.out.println("update check: "+user);
			kakao.updateUser(user);
			return response("update success",HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("fail",HttpStatus.CONFLICT,false);
		}
	}
	
	@DeleteMapping("/logout")
	@ApiOperation("사용자 정보 삭제")
	public ResponseEntity<Map<String, Object>> Delete(@RequestBody User user){
		try {
			User logout_user=new User(user.getUserid(),user.getPw(),user.getProvider());
			kakao.deleteUser(logout_user);
			return response("logout success",HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("fail",HttpStatus.CONFLICT,false);
		}
	}
	
	@Cacheable(value="get-mypage", key="#id")
	@GetMapping("/mypage/{id}")
	@ApiOperation("마이페이지 정보 가져오기")
	public ResponseEntity<Map<String, Object>> Mypage(@PathVariable String id){
		System.out.println("getget: "+id);
		User user=kakao.getById(id);
		int win_rate=0;
		System.out.println("mypage "+user);
		Map<String,Object> map=new HashMap<>();
		try {
			if(user.getGame()!=0)
				  win_rate=(user.getWin()*100/(user.getGame()));
			if(user.getProfileimg().equals("") || user.getProfileimg()==null)
				user.setProfileimg("https://static.wixstatic.com/media/a04e38_498a9895aaab46ce834d4865059e47f9~mv2_d_1920_1242_s_2.png/v1/fill/w_784,h_507,al_c,q_95,usm_0.66_1.00_0.01/a04e38_498a9895aaab46ce834d4865059e47f9~mv2_d_1920_1242_s_2.webp");
			map.put("user_info",user);
			map.put("win_rate",win_rate);
			map.put("status",true);
			System.out.println("mypage data 전송 성공");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return response("mypage get information fail",HttpStatus.CONFLICT,false);
		}
	}
	
	@PutMapping("/battle_result") //비기면 둘다 승리(비길시 승리와 똑값이 win=1, lose=0을 받음)
	@ApiOperation("배틀 결과 승패적용")
	public ResponseEntity<Map<String, Object>> Battle_Result(@RequestBody Multiple_RequestBody mBody){
		try {
			User user1=mBody.getUser1();//첫번째 유저
			kakao.battle_result(user1);//user1이 승인지 패인지 경기결과 적용,경기수 +1
			User user1_after=kakao.getById(user1.getUserid());//경기결과 적용 후 해당 id의 정보 추출
			if(user1_after.getGame()<10) {//경기후에도 10경기이하면  계속 Bronze
				user1_after.setGrade("Bronze");
				kakao.set_grade(user1_after);
			} else {
				int win_rate=(user1_after.getWin()*100)/user1_after.getGame();
				if(win_rate>=0 && win_rate<20) {
					user1_after.setGrade("Bronze");
					kakao.set_grade(user1_after);
				} else if(win_rate>=20 && win_rate<40) {
					user1_after.setGrade("Silver");
					kakao.set_grade(user1_after);
				}else if(win_rate>=40 && win_rate<60) {
					user1_after.setGrade("Gold");
					kakao.set_grade(user1_after);
				} else if(win_rate>=60 && win_rate<80) {
					user1_after.setGrade("Platinum");
					kakao.set_grade(user1_after);
				} else {
					user1_after.setGrade("Diamond");
					kakao.set_grade(user1_after);
				}
			}
			
			User user2=mBody.getUser2();
			kakao.battle_result(mBody.getUser2());
			User user2_after=kakao.getById(user2.getUserid());//경기결과 적용 후 해당 id의 정보 추출
			if(user2_after.getGame()<10) {
				user2_after.setGrade("Bronze");
				kakao.set_grade(user2_after);
			} else {
				int win_rate=(user2_after.getWin()*100)/user2_after.getGame();
				if(win_rate>=0 && win_rate<20) {
					user2_after.setGrade("Bronze");
					kakao.set_grade(user2_after);
				} else if(win_rate>=20 && win_rate<40) {
					user2_after.setGrade("Silver");
					kakao.set_grade(user2_after);
				}else if(win_rate>=40 && win_rate<60) {
					user2_after.setGrade("Gold");
					kakao.set_grade(user2_after);
				} else if(win_rate>=60 && win_rate<80) {
					user2_after.setGrade("Platinum");
					kakao.set_grade(user2_after);
				} else {
					user2_after.setGrade("Diamond");
					kakao.set_grade(user2_after);
				}
			}
			return response("battle_result 결과 반영 성공",HttpStatus.OK,true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return response("battle_result 결과 반영 실패",HttpStatus.OK,true);
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