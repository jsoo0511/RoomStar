package com.ssafy.link.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.link.dto.Room;
import com.ssafy.link.dto.Room_More;
import com.ssafy.link.dto.Room_need;
import com.ssafy.link.dto.User;
import com.ssafy.link.dto.Waiting;
import com.ssafy.link.service.KakaoServiceI;
import com.ssafy.link.service.RoomServiceI;
import com.ssafy.link.service.WaitingServiceI;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
//@RequestMapping("/singBattle")
public class WaitingController {
	
	@Autowired
	KakaoServiceI kService;
	
	@Autowired
	RoomServiceI roomService;
	
	@Autowired
	WaitingServiceI wService;
	//사용자 정보는 KakaoLoginController의 /getUserInfo/{id}에서 출력
	
//	@GetMapping("/Get_waiting_room_info")
//	@ApiOperation("/대기실의 방정보 출력")
//	public ResponseEntity<Map<String, Object>> Get_waitingㅁㅁ(){
//		List<Room> room_list=roomService.get_room();
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		try {			
//			resultMap.put("room_list", room_list);
//			return new ResponseEntity<>(resultMap,HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("error","error");
//			return new ResponseEntity<>(resultMap,HttpStatus.CONFLICT);
//		}
//	}
//	
//	@GetMapping("/Get_waiting_user_info")
//	@ApiOperation("/대기실에 있는 총 대기인원과 명단 출력") //대기인원 명단도 함께 출력해보자
//	public ResponseEntity<Map<String, Object>> Get_waiting_num(){
//		try {
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			int num=wService.get_waiting_num();
//			List<Waiting> list=wService.get_waiting_ids();
//			resultMap.put("userList", list);
//			resultMap.put("user_num", num);
//			resultMap.put("status",true);
//			return new ResponseEntity<>(resultMap,HttpStatus.OK);
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			return response("총 대기인원 조회 실패",HttpStatus.CONFLICT,false);
//		}
//	}
	
	@GetMapping("/Get_waiting")
	@ApiOperation("대기실에 필요한 모든 정보 출력")
	public ResponseEntity<Map<String, Object>> Get_waiting(){
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Room> room=roomService.get_room(); //시청자수 포함한 방정보 가져오기
			List<Waiting> list=wService.get_waiting_ids();
			int num=wService.get_waiting_num();
			resultMap.put("result_text","대기실 입장 성공");
			resultMap.put("roomViewInfo",room);
			resultMap.put("waitingList",list);
			resultMap.put("waitingNum",num);
			return new ResponseEntity<>(resultMap,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return response("대기실 정보 얻어오기 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	//나중에 서버문제시 set이아니라 그냥 list로 바꿈
	//HashSet<String> set=new HashSet<String>();//시청자 중복체크
	
	@PostMapping("/Insert_waiting/{userid}") //singer로 있는 사람의 프로필 이미지 추가
	@ApiOperation("사용자가 대기실에 입장시 대기실DB에 사용자 정보 추가후, 대기실UI에 필요한 모든 정보 출력")
	public ResponseEntity<Map<String, Object>> Insert_waiting(@PathVariable String userid){
		try {
			System.out.println("대기실에 입장하고 싶은 id: "+userid);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			List<Room> room=roomService.get_room(); //시청자수 포함한 방정보 가져오기
			List<Waiting> list=wService.get_waiting_ids();//대기실에 있는 모든 id출력
			//대기방에 중복된 아이디가 존재하는지 체크
			boolean flag=true;
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getUserid().equals(userid)) {
					flag=false;
					break;
				}
			}
			//nickname을 가져오기 위해서 user 정보 가져오기
			User user=kService.getById(userid);
			String nickname=user.getNickname(); //해당 유저의 nickname 출력
			Waiting w=new Waiting(userid, nickname);
			if(flag) /**userid와 id의 nickname추가하도록 변경, 메서드 인자값이나 이런것들 다 변경해야될듯*/
				wService.insert_waiting(w);
			
			List<Waiting> list_after=wService.get_waiting_ids();//대기실 id,nickname추가후 출력
			
			
			List<Room_More> room_more=new ArrayList<Room_More>();
			//user1,user2의 정보 봅아오기, 이젠 user1, user2가 nickname형태임
			for(int i=0;i<room.size();i++) {
				Room temp=room.get(i);
				System.out.println("temp: "+temp);
				int need1_rate=0;
				int need2_rate=0;
				Room_need need1=new Room_need(null,null,null,0,0);
				Room_need need2=new Room_need(null,null,null,0,0);
				System.out.println("check");
				if(room.get(i).getUser1()!=null) {
					need1=wService.get_room_need(room.get(i).getUser1());//user1의 닉네임으로 찾음
					if(need1.getWin()!=0 && need1.getLose()!=0) //전적정보가 없으면 계산하지 않는다.
					   need1_rate=need1.getWin()*100/(need1.getWin()+need1.getLose());					
				}
				if(room.get(i).getUser2()!=null) {
					need2=wService.get_room_need(room.get(i).getUser2());
					if(need2.getWin()!=0 && need2.getLose()!=0) //전적정보가 없으면 계산하지 않는다.
						need2_rate=need2.getWin()*100/(need2.getWin()+need2.getLose());					
				}
				System.out.println("추가전");
				room_more.add(new Room_More(temp.getRoom_id(),temp.getSinger_num(),temp.getUser1(),temp.getUser2(),
						temp.getMusic1(),temp.getMusic2(),temp.getWatching_num(),need1.getProfileimg(), need1.getGrade(),need1.getWin(),need1.getLose(),need1_rate,need2.getProfileimg(),need2.getGrade(),need2.getWin(),need2.getLose(),need2_rate));
			
				System.out.println("추가완료");
			}
			int num=wService.get_waiting_num();
			System.out.println(num);
			resultMap.put("result_text","대기실 입장 성공");
			resultMap.put("roomViewInfo",room_more);
			resultMap.put("waitingList",list_after);
			resultMap.put("waitingNum",num);
			return new ResponseEntity<>(resultMap,HttpStatus.OK);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return response("NullPointerException",HttpStatus.CONFLICT,false);
		}catch (RuntimeException e) {
			e.printStackTrace();
			return response("대기실 입장 실패",HttpStatus.CONFLICT,false);
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
