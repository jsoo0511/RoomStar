<template>
  <div id="gameRoom">
    GameRoom
    <div>
      <!-- https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8 -->
      <div id="firstWebCam">
        player1 들어갈자리
        <video
          playsinline
          id="p1_video"
          autoplay
          preload="metadata"
          style="width:30%"
          poster="@/assets/images/camPoster.jpg"
        ></video>
      </div>

      <div id="secondWebCam">
        player2 들어갈자리
        <video
          playsinline
          id="p2_video"
          autoplay
          preload="metadata"
          style="width:30%"
          poster="@/assets/images/camPoster.jpg"
        ></video>
      </div>
    </div>
  </div>
</template>

<script>
import io from "socket.io-client";
import axios from "axios";
import router from "@/routes";
import { mapState, mapGetters, mapActions } from "vuex";

const stun_server = "stun.l.google.com:19302";
const pcConfig = {
  iceServers: [
    {
      urls: "stun:" + stun_server
    }
  ]
};

export default {
  name: "GameRoom",
  //props: ["user_id"],
  data() {
    return {
      
      //video, stream
      player_videos:[null,null],
      player_streams:[null,null],
      player_idx:0,


      //대결하는 사람들의 정보
      battle_id:[null,null],
      battle_connections:[null,null],


      //시청자들은 가지고 있을 필요가 없음
      watchers_id: [null, null, null, null, null],
      watchers_connections: [null, null, null, null, null],
      watcher_cnt :0,

      room_id: 1,
      roomInfo: "",
      singer_num: 0,

      user_id: "hajung",
      my_position: "user1", //watcher는 없음
      user_identification: "watcher",

    };
  },
  computed: {
    ...mapState(["singerOrWatcherStatus", "token"])
  },

  methods: {
    sendMessage(message) {
      //서버로 메세지 보내기
      console.log("sendMessage----->5");
      this.socket.emit("message", message);
    },
    async get_stream(stream) {
      console.log("get_stream---->3");
      this.player_videos[this.player_idx].srcObject = stream;
      this.player_streams[this.player_idx] = stream;
    },
    async getPeerConnection(join_id,join_identification,join_idx) {
      console.log("getPeerConnection()----->4");
      console.log("저는 "+this.user_identification+"이제 pc 생성");
      console.log("새로들어온 애는 "+join_identification+"입니다.");
        let t_pc = await new RTCPeerConnection(pcConfig);
        let bat_id = 0;


      if (this.user_identification=="singer"){
        if (join_identification=="singer"){
          bat_id = (this.player_idx+1)%2;
          this.battle_connections[bat_id] = t_pc;
          console.log("여기가가가가가ㅏ각ㄱ t_pc",t_pc);
          this.battle_id[bat_id] = join_id;
        }else{
          this.watchers_connections[this.watcher_cnt] = t_pc;
          this.watchers_id[this.watcher_cnt++] = join_id;

        }
      }else{
        bat_id = join_idx;
        this.battle_connections[bat_id] = t_pc;
        this.battle_id[bat_id] = join_id;

      }
       
      t_pc.onicecandidate = event => {
        if (event.candidate) {
          this.sendMessage({
            message: {
              type: "candidate",
              label: event.candidate.sdpMLineIndex,
              id: event.candidate.sdpMid,
              candidate: event.candidate.candidate
            },
            room_id: this.room_id,
            from: this.user_id,
            from_identification: this.user_identification,
            from_idx:this.player_idx,
            to: join_id,
            to_identification: join_identification
          });
        }
      };

      t_pc.onaddstream = event => {
        this.player_streams[bat_id] = event.stream;

        this.player_videos[bat_id].srcObject = this.player_streams[bat_id];
        
      }

     if (this.user_identification=="singer"){

        t_pc.addStream(this.player_streams[this.player_idx]);
      }
      return t_pc;
    }
  },
  created() {
    console.log("created()---->1",this.battle_id);
    this.socket = io.connect(
      "http://70.12.246.73?room_id=" +
        this.room_id +
        "&user_id=" +
        this.user_id +
        "&user_identification=" +
        this.user_identification+
        "&player_idx="+
        this.player_idx,
      { transports: ["websocket"] }
    );//ip주소 수정 필요
    // this.socket = io.connect(
    //   "http://172.30.123.158:3001",
    //   { transports: ["websocket"] }
    // );
    //console.log("dhdhdh");
  },
  mounted() {
    console.log("mounted()---->2");
    console.log(this);
    if (this.user_identification=="singer"){
        if (this.my_position=="user1"){
          this.player_idx = 0;
        }else{
          this.player_idx = 1;
        }

    }
    this.player_videos[0] = document.getElementById("p1_video");
    this.player_videos[1] = document.getElementById("p2_video");
    
    console.log("가수??????");
    //player1일때,
    if (this.user_identification == "singer") {
      console.log("가수");
      navigator.mediaDevices
        .getUserMedia({
          audio: true,
          video: true
        })
        .then(this.get_stream);

      // if (this.my_position=="user1"){

      // }
    }
    this.socket.on("join", message => {
      console.log(message);
      const join_id = message.user_id;
      console.log(join_id + "들어왔숩다.");
      const join_identification = message.user_identification;
      const join_idx = message.player_idx;
      //const user_id = message.user_id;
      //console.log("새로운 상대가 들어옴!");

      

      if (this.user_identification == "singer") {
        //가수라면,
        console.log(" 저는 가수 입니다.")
        setTimeout(() => {
          this.getPeerConnection(join_id,join_identification,join_idx).then(t_pc => {
            t_pc.createOffer(
              sdp => {
                t_pc.setLocalDescription(sdp);
                this.sendMessage({
                  message: sdp,
                  room_id: this.room_id,
                  from:this.user_id,
                  from_identification: this.user_identification,
                  from_idx:this.player_idx,
                  to: join_id,
                  to_identification: join_identification
                  
                  // study_id: 1,
                  // from: this.user_id,
                  // to: user_id
                });
              },
              e => {
                console.log(e);
              }
            );
          });
        }, 1000);
      } else {
        if (join_identification == "singer") {
          setTimeout(() => {
            this.getPeerConnection(join_id,join_identification,join_idx).then(t_pc => {
              t_pc.createOffer(
                sdp => {
                  t_pc.setLocalDescription(sdp);
                  this.sendMessage({
                    message: sdp,
                    room_id: this.room_id,
                    from:this.user_id,
                    from_identification: this.user_identification,
                    from_idx:this.player_idx,
                    to: join_id,
                    to_identification: join_identification

                  });
                },
                e => {
                  console.log(e);
                }
              );
            });
          }, 1000);
        }
      }
    });

    this.socket.on("leave", message => {
      if (message.from_identification=="singer"){
        this.player_videos[message.player_idx] = null;
        this.player_streams[message.player_idx] = null;
        this.battle_id[message.player_idx] = null;
        this.battle_connections[message.player_idx] = null;
      }
      else{
        if (this.user_identification=="singer"){
          this.watchers_id[--this.watcher_cnt] = null;
          this.watchers_connections[this.watcher_cnt] = null;
        }
      }
    });
    this.socket.on("message", data => {
      if (data.message.type == "offer") {
          //내가 새로 들어왔을 때, join 메세지를 보내면,
          //기존의 사용자들이 나의 join을 받고 
          //자신의 정보를 담은 offer를 보낸다.
        if (this.user_identification=="singer"){
            //내가 가수이고
          if (data.from_identification=="singer"){
              //상대도 가수
              console.log("오퍼를 받앗습니다.답장을 해요",data);
              this.battle_id[(this.player_idx+1)%2] = data.from;
          }else{
            //상대가 시청자일 경우
            this.watchers_id[this.watcher_cnt++] = data.from;
          }

          this.getPeerConnection(data.from,data.from_identification,data.from_idx).then(t_pc => {
                
                t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
                t_pc.createAnswer().then(sdp => {
                    t_pc.setLocalDescription(sdp);
                    this.sendMessage({
                    message: sdp,
                    room_id: this.room_id,
                    from:this.user_id,
                    from_identification: this.user_identification,
                    from_idx:this.player_idx,
                    to: data.from,
                    to_identification: data.from_identification
                    });
                });
            });
        }  else{
            //내가 시청자일때,
            if (data.from_identification=="singer"){
                   this.battle_id[data.from_idx] = data.from;
               
               this.getPeerConnection(data.from,data.from_identification,data.from_idx).then(t_pc => {
                t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
                t_pc.createAnswer().then(sdp => {
                    t_pc.setLocalDescription(sdp);
                    this.sendMessage({
                    message: sdp,
                    room_id: this.room_id,
                    from:this.user_id,
                    from_identification: this.user_identification,
                    from_idx:this.player_idx,
                    to: data.from,
                    to_identification: data.from_identification
                    });
                });
            });
            }
        }
      } else if (data.message.type == "answer") {
         console.log("새로 들어온" +data.from_identification+"한테 answer을 받았습니다."+data);
        let t_pc = null;
        if (this.user_identification=="singer"){
          if (data.from_identification=="singer"){
            t_pc = this.battle_connections[(this.player_idx+1)%2]; 
          }else{
            t_pc = this.watchers_connections[this.watcher_cnt-1];
          }
        }else{
          if (data.from_identification=="singer"){
              t_pc = this.battle_connections[data.from_idx]; 
          
            
          }
        }
        t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
      } else if (data.message.type === "candidate") {
        let candidate = new RTCIceCandidate({
          sdpMLineIndex: data.message.label,
          candidate: data.message.candidate
        });
        // //let t_pc = this.peer_connection;
        // console.log("여기 캔디데이트", cand);
        // console.log("여기는 pc?", this.op_connection);
        let t_pc = null;
          if (this.user_identification=="singer"){
            if (data.from_identification=="singer"){
              t_pc = this.battle_connections[(this.player_idx+1)%2]; 
              console.log("여기가가가가가가가가가 t_pc",t_pc)
              t_pc.addIceCandidate(candidate);
            }else{
              t_pc = this.watchers_connections[this.watcher_cnt-1];
              t_pc.addIceCandidate(candidate);
            }
          }else{
            if (data.from_identification=="singer"){
              if (this.battle_connections[0]){
                t_pc = this.battle_connections[0]; 
                t_pc.addIceCandidate(candidate);
              }else{
                t_pc = this.battle_connections[1];
                t_pc.addIceCandidate(candidate);
              }
            }
          }
        
        
      }
    });
  }
};
</script>