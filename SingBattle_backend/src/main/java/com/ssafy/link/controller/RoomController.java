package com.ssafy.link.controller;

import java.util.HashMap;
import java.util.HashSet;
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

import com.ssafy.link.dto.Get_vote;
import com.ssafy.link.dto.Room;
import com.ssafy.link.dto.Vote;
import com.ssafy.link.dto.Watching;
import com.ssafy.link.service.KakaoServiceI;
import com.ssafy.link.service.RoomServiceI;
import com.ssafy.link.service.WaitingServiceI;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
//@RequestMapping("/singBattle")
public class RoomController {
	
	@Autowired
	RoomServiceI service;
	
	@Autowired
	WaitingServiceI wService;
	
	@Autowired
	KakaoServiceI kService;
	/**음악 선택시 그 음악으로 DB변경하는 것도 만들어줘야됨*/
	
	/**방 번호 찾기*/
	int arr[][]=new int[6][3];
	String name[][]=new String[6][3];
	int singer_num[]=new int[6];
	public int[] roomCheck() {
		int room_id=0;
		int user_location=0;
		int inform[]=new int[2];
		outer:for(int i=1;i<6;i++) {
			for(int j=1;j<3;j++) {
				if(arr[i][j]==0) {//i번째 방의 인원이 비어있으면
					arr[i][j]=1;
					room_id=i;
					user_location=j;
					singer_num[i]+=1;//해당 방 인원 추가
					break outer;
				}
			}
		}
		inform[0]=room_id;
		inform[1]=user_location;
		return inform;
	}
		
	/**방 나갈 사람의 위치 찾기*/
	public int[] getout(String id) {
		int []temp=new int[2];
		outer: for(int i=1;i<6;i++) {
			for(int j=1;j<3;j++) {
				if(name[i][j].equals(id)) {
					arr[i][j]=0;
					temp[0]=i;
					temp[1]=j;
					break outer;
				}
			}
		}
		return temp;
	}
	
	@GetMapping("/get_room")
	@ApiOperation("방 점보 가져오기")
	public ResponseEntity<Map<String, Object>> get_room(){
		try {
			List<Room> room=service.get_room();
			return response(room,HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("get_room error",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("/get_room_by_room_id/{room_id}")
	public ResponseEntity<Map<String, Object>> get_room_w(@PathVariable int room_id){
		try {
			List<Room> room=service.get_room_include_watching_num(room_id);
			return response(room,HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("get_room_include_watching_num 실패",HttpStatus.CONFLICT,false);
		}
	}
//	
//	@PutMapping("/Out_room_temp_nonono/{id}")
//	@ApiOperation("방에서 나가기, 만약 모든 player가 방에서 나갔다면 방을 폐쇄하고 시청자 를 0으로 만듦") //모든 singer가 사라지면 watcing_num도 0으로 만들어야됨.
//	public ResponseEntity<Map<String, Object>> Out_room(@PathVariable String id){
//		int [] room_info=getout(id);
//		int room_id=room_info[0];
//		try {			
//			if(room_info[1]==1) {
//				Room room=new Room(room_id,singer_num[room_id]-1,"1",null,null,null,0);
//				service.out_room(room);
//				name[room_id][room_info[1]]=null;
//			} else {
//				Room room=new Room(room_id,singer_num[room_id]-1,null,"1",null,null,0);
//				service.out_room(room);
//				name[room_id][room_info[1]]=null;
//			}
//			
//			//만약 모든 player가 방에서 나가서 singer_num이 0이라면 방을 폐쇄하고 시청자를 0으로 만듦
//			if(singer_num[room_id]-1 ==0) {
//				System.out.println("방에 player가 없어요.");
//				wService.set_0_watching_num(room_id);
//				wService.delete_watching_in_room(room_id);
//			}
//			
//			return response(room_id+"방에서 "+id+"가 user "+room_info[1]+"에서 방 나감",HttpStatus.OK,true);
//		} catch (Exception e) {
//			log.trace("Room out error : {}", e);
//			return response("방 탈출 실패",HttpStatus.CONFLICT,false);
//		}
//		
//	}
//	

	@PutMapping("/Out_room/{id}")
	@ApiOperation("/방탈출 DB로 정보 확인후 탈출")
	public ResponseEntity<Map<String, Object>> Out_room_Temp(@PathVariable String id){
		try {
			System.out.println("out_room_id 확인: "+id);
			String nickname=kService.getById(id).getNickname();
			Room temp=service.get_room_info_for_out_room(nickname);
			System.out.println(temp);
			int room_id=temp.getRoom_id();
			int check=0; //나간 사용자가 1인지 2인지 체크
			if(temp.getUser1()!=null && temp.getUser1().equals(nickname)) {
				check=1;
				Room room=new Room(room_id,temp.getSinger_num()-1,"1",null,null,null,0);
				service.out_room(room);
				service.delete_vote_user(room);
			} 
			else if(temp.getUser2()!=null && temp.getUser2().equals(nickname)) {
				check=2;
				Room room=new Room(room_id,temp.getSinger_num()-1,null,"1",null,null,0);
				service.out_room(room);
				service.delete_vote_user(room);
			}
			return response(room_id+"방에서 "+nickname+"가 user "+check+"에서 방 나감",HttpStatus.OK,true);
		} catch(NullPointerException e) {
			e.printStackTrace();
			return response("NullPointerException",HttpStatus.CONFLICT,false);
		} catch(RuntimeException e) {
			e.printStackTrace();
			return response("RuntimeException",HttpStatus.CONFLICT,false);
		}
	}
	
	@PutMapping("/Enter_room/{id}") /**nickname error생기면 id로 그냥 복구하기*/
	@ApiOperation("/방접속 DB로 정보 확인후 접속") //새로고침시 DB접속 방어 구현
	public ResponseEntity<Map<String, Object>> Enter_room(@PathVariable String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//모든 room정보 가져온후 singer_num=2이면 skip, 2보다 작으면 위치 찾은 후 거기에 입장
		List<Room> list=service.get_room();
		String nickname=kService.getById(id).getNickname();
		//방에 중복된 아이디가 존재하는지 체크
	/*	for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
			if(list.get(i).getUser1()==null) {
				if(list.get(i).getUser2()==null) {
					continue;
				} else {
					if(list.get(i).getUser2().equals(nickname))
						return response("중복된 아이디가 방에 존재합니다.",HttpStatus.CONFLICT,false);
				}
			} else {
				if(list.get(i).getUser1().equals(nickname)) {
					return response("중복된 아이디가 방에 존재합니다.",HttpStatus.CONFLICT,false);
				}
				if(list.get(i).getUser2()==null)
					continue;
				else //user1,user2 모두 존재
				{
					if(list.get(i).getUser2().equals(nickname))
						return response("중복된 아이디가 방에 존재합니다.",HttpStatus.CONFLICT,false);
				}
			}
		}*/
		
		int room_id=0;
		int position=0;
		try {
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getUser1()==null) {
					room_id=list.get(i).getRoom_id();
					position=1;
					break;
				} else if(list.get(i).getUser2()==null) {
					room_id=list.get(i).getRoom_id();
					position=2;
					break;
				}else
					continue;
			}
			if(room_id==0) {
				System.out.println("풀방");
				return response("풀방입니다.",HttpStatus.CONFLICT,false);
			} else { /**user1 user2에 아이디 대신 nickname 삽입으로 변경*/
				if(position==1) {//1번 유저로 입장
					Room room=new Room(room_id,list.get(room_id-1).getSinger_num()+1,nickname,null,null,null,0);
					service.udpate_room(room);
					service.insert_vote_user(room);
				} else {
					Room room=new Room(room_id,list.get(room_id-1).getSinger_num()+1,null,nickname,null,null,0);
					service.udpate_room(room);
					service.insert_vote_user(room);
				}
				System.out.println("Enter room test: "+service.get_room_include_watching_num(room_id));
				if(list.get(room_id-1).getSinger_num()+1==2) {
					resultMap.put("full_token"+room_id, "full");
				}
				position-=1;
				resultMap.put("player_index",position);
				resultMap.put("room_id",room_id);
				resultMap.put("identification","singer");
				resultMap.put("message",room_id+"방에 "+id+"가 "+(list.get(room_id-1).getSinger_num()+1)+"번째 유저로 입장");
				resultMap.put("status", HttpStatus.OK);
				return new ResponseEntity<>(resultMap,HttpStatus.OK);
			}
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return response("NullPointerException",HttpStatus.CONFLICT,false);
		} catch(RuntimeException e) {
			e.printStackTrace();
			return response("RuntimeException",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("/My_position/{id}")
	@ApiOperation("해당 유저가 몇번방의 몇번째 유저인지 확인")
	public ResponseEntity<Map<String, Object>> My_position(@PathVariable String id){
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			//모든 room정보 가져온후 singer_num=2이면 skip, 2보다 작으면 위치 찾은 후 거기에 입장
			List<Room> list=service.get_room();
			String nickname=kService.getById(id).getNickname();
			for(int i=0;i<list.size();i++) {
				
				if(list.get(i).getUser1() !=null && list.get(i).getUser1().equals(nickname)) {
					resultMap.put("user1","user1");
					resultMap.put("room_id",list.get(i).getRoom_id());
					return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
				}
				else if(list.get(i).getUser2()!=null && list.get(i).getUser2().equals(nickname)) {
					resultMap.put("user2","user2");
					resultMap.put("room_id",list.get(i).getRoom_id());
					return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
				}
				else
					continue;				
			}
			return response("해당 유저가  게임방에 존재하지 않습니다.",HttpStatus.CONFLICT,false);
			
		}  catch (NullPointerException e) {
			e.printStackTrace();
			return response("NullPointerException",HttpStatus.CONFLICT,false);
		} catch(RuntimeException e) {
			e.printStackTrace();
			return response("RuntimeException",HttpStatus.CONFLICT,false);
		}
	}
	
	
	@GetMapping("/Get_watching")
	@ApiOperation("시청자 목록 가져오기")
	public ResponseEntity<Map<String, Object>> Get_watching(){
		try {
			List<Watching> list=service.select_watcing();
			for(int i=0;i<list.size();i++) {
				String nickname=kService.getById(list.get(i).getUserid()).getNickname();
				list.get(i).setUserid(nickname);
			}
			return response(list,HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("get watching list fail",HttpStatus.CONFLICT,false);
		}
	}
	
	@PostMapping("/Insert_watching") //시청자 중복 처리 시작
	@ApiOperation("방에 시청자로 접속한 사용자들 DB에 추가") //시청자가 방에 접속시 대기실에 있던 시청자 정보 삭제
	public ResponseEntity<Map<String, Object>> Insert_watching(@RequestBody Watching watch){
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			System.out.println(watch);
			List<Watching> list=service.select_watcing();
			boolean flag=true;
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getUserid().equals(watch.getUserid())) {
					flag=false;
					break;
				}
			}
			if(flag) {
				service.insert_watching(watch);
				service.plus_watching_num(watch.getRoom_id());
				wService.delete_waiting(watch.getUserid());//사용자가 방에 입장시 대기실에 있던 정보 삭제
			}
			resultMap.put("identification","watcher");
			resultMap.put("msg","시청자 등록완료");
			resultMap.put("status",true);
			return new ResponseEntity<>(resultMap,HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return response("시청자 등록 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	@PutMapping("/Update_watching")
	@ApiOperation("투표시  투표번호 변경")
	public ResponseEntity<Map<String, Object>> Update_watching(@RequestBody Watching watch){
		try {
			service.update_watching(watch);
			return response("투표 완료",HttpStatus.OK,true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return response("투표 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	@PutMapping("/Delete_watching")//사용자 이름과 방번호를 정확히 입력받아야됨
	@ApiOperation("방에서 나간 시청자들 DB에서 삭제") //방에서 나가면 대기실로 이동 -> 대기실에 명단 추가
	public ResponseEntity<Map<String, Object>> Delete_watching(@RequestBody Watching watch){
		try {
			System.out.println("게임방에서 대기실로 이동");
			service.delete_watching(watch.getUserid());
			service.minus_watching_num(watch.getRoom_id());
			//wService.insert_waiting(watch.getUserid());//방에서 나간 사용자 대기실로 이동(추가)
			return response("시청자 방 나감",HttpStatus.OK,true);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return response("NullPointerException",HttpStatus.CONFLICT,false);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return response("시청자 방 못나감",HttpStatus.CONFLICT,false);
		}
	}
	
	@PutMapping("/select_music")
	@ApiOperation("음악 선택") //혹시 user1,user2를 프론트에서 구분하기 힘들면
	//                        백엔드에서 room_list를 뽑아온 후 아이디와 비교하여 user1,user2를 판단하기
	public ResponseEntity<Map<String, Object>> Select_music(@RequestBody Room room){
		try {
			System.out.println(room);
			service.select_music(room);
			return response("음악 선택 성공",HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("음악 선택 실패",HttpStatus.CONFLICT,false);
		}
	}
	
	@PutMapping("/update_vote")
	@ApiOperation("투표수 변동")
	public ResponseEntity<Map<String, Object>> update_vote(@RequestBody Vote v){
		try {
			service.update_vote(v);
			return response("vote success",HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("vote error",HttpStatus.CONFLICT,false);
		}
	}
	
	@GetMapping("/get_vote/{room_id}")
	public ResponseEntity<Map<String, Object>> get_vote(@PathVariable int room_id){
		try {
			Get_vote v=service.get_vote(room_id);
			return response(v,HttpStatus.OK,true);
		} catch (Exception e) {
			e.printStackTrace();
			return response("get vote error",HttpStatus.CONFLICT,false);
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