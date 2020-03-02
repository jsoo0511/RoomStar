<template>
  <div id="gameRoom">
      <v-layout row wrap align-center>
        <v-flex xs6 md6>
          <div id="firstWebCam">
            <video
              playsinline
              id="p1_video"
              autoplay
              preload="metadata"
              style="height:100vh; width:auto;"
              poster="@/assets/images/camPoster.jpg"
            ></video>
          </div>
       </v-flex>
          <div class="vote1_component">
            <SelectMusic1/>
            <SelectMusic1 v-if="singerOrWatcherStatus == 1 & player_idx == 0"/>
            <Vote1 :socket="this.socket"/>
          </div>
        <v-flex xs6 md6>
          <div id="secondWebCam">
            <video
              playsinline
              id="p2_video"
              autoplay
              preload="metadata"
              style="height:100vh; width:auto;"
              poster="@/assets/images/camPoster.jpg"
            ></video>
          </div>
        </v-flex>
          <div class="vote2_component">
            <SelectMusic2 v-if="singerOrWatcherStatus == 1 & player_idx == 1"/>
            <Vote2 :socket="this.socket"/>
          </div>
      <button id="outbtn" @click="backButton">나가기</button>
    </v-layout>

    <div class="chat_component">
      <Chat :socket="this.socket" />
    </div>
  </div>
</template>

<script>
import router from "@/routes";
import io from "socket.io-client";
import axios from "axios";
import Chat from "./Chat.vue";
import Vote1 from "./Vote1.vue";
import Vote2 from "./Vote2.vue";
import SelectMusic1 from "./SelectMusic1.vue";
import SelectMusic2 from "./SelectMusic2.vue";
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
  data() {
    return {
      //video, stream
      player_videos: [null, null],
      player_streams: [null, null],
      player_idx: 0,
      local_stream: null,

      //대결하는 사람들의 정보
      battle_id: [null, null],
      battle_connections: [null, null],

      //시청자들은 가지고 있을 필요가 없음
      watchers_id: [null, null, null, null, null],
      watchers_connections: [null, null, null, null, null],
      watcher_cnt: 0,

      room_id: this.$session.get("roomId"),
      roomInfo: "",
      singer_num: 0,

      user_id: this.$session.get("userId"),
      user_identification: "",


      vote1:0,
      vote2:0,
      result:"",

      singerOrWatcherStatus: this.$session.get("singerOrWatcherStatus")
    };
  },
  components: {
    Chat,
    Vote1,
    Vote2,
    //SelectMusic1,
    //SelectMusic2,
  },
  methods: {
    finish(){
      axios
      .get(process.env.VUE_APP_SERVER_IP+"/get_vote/" + this.room_id)
      .then(response => {
        //this.$router.push("/");
        console.log("finish : ", response);
        this.vote1 = response.data.data.vote1;
        this.vote2 = response.data.data.vote2;
      })
      .catch(e => {
        console.log("error: ", e);
      });

      if(this.vote1>this.vote2){
        if (this.user_id==this.battle_id[0]){
          this.result = "win";

        }else if (this.user_id==this.battle_id[1]){
          this.result = "lose;"
        }
        //여기에 화면 변화/??????

      }else{
        if (this.user_id==this.battle_id[0]){
          this.result = "lose";

        }else if (this.user_id==this.battle_id[1]){
          this.result = "win;"
        }

      }


      if (this.user_identification=="singer"){
          axios
          .put(process.env.VUE_APP_SERVER_IP+"/승패 결과 저장 함수/", {user_id: this.user_id, result:this.result})
          .then(response => {
            //this.$router.push("/");
          })
          .catch(error => {
            console.log(error);
            // this.loading = false;
          });
        }
    },
    // leave(){
    //   axios
    //     .put(process.env.VUE_APP_SERVER_IP+"/Out_room/"+ this.user_id)
    //     .then(response => {
    //       console.log(response);
    //       this.$router.push("/");
    //     })
    //     .catch(e => {
    //       console.log("error: ", e);
    //     });
    //   this.socket.emit("leave", {
    //     room_id:this.room_id,
    //     user_identification:this.user_identification,
    //     user_id:this.user_id,
    //     player_idx:this.player_idx
    //   });
    // },
    backButton() {
      console.log("moveeeeeeeeeeeeee");
      let userid = this.user_id;
      this.$session.set("singerOrWatcherStatus", 0);

      let params = {
        userid,
        room_id: this.room_id
      };
      this.socket.emit("leave", {
        room_id:this.room_id,
        user_identification:this.user_identification,
        user_id:this.user_id,
        player_idx:this.player_idx
      });
      switch (this.singerOrWatcherStatus) {
        case 1: // singer
          console.log(userid);
          axios
            .put(process.env.VUE_APP_SERVER_IP + "/Out_room/" + userid)
            .then(response => {
              console.log(response);
              this.$session.set("roomId", 0);
              console.log(response);
              this.$router.push("/");
            })
            .catch(e => {
              console.log("error: ", e);
            });
          break;
        case 2: // watcher
          console.log(this.$router);
          axios
            .put(process.env.VUE_APP_SERVER_IP + "/Delete_watching/", params)
            .then(response => {
              this.$session.set("roomId", 0);
              console.log(response);
              this.$router.push("/");
            })
            .catch(e => {
              console.log("error: ", e);
            });
          break;
        default:
          this.$router.push("/");
          break;
      }
    },

    getData() {
      // 스토어 연결...?
      let store = this.$store;
      this.user_id = this.$session.get("userId");
      this.room_id = this.$session.get("roomId");
     
      console.log("현재 나의 존재 ",this.$session.get("singerOrWatcherStatus"));

      if (this.$session.get("singerOrWatcherStatus")==1){
        this.user_identification = "singer";
      } else {
        this.user_identification = "watcher";
      }

      console.log(this.user_identification);
      if (this.user_identification === "singer") {
        this.player_idx = this.$session.get("playerIdx");

        // if (this.battle_id[0]) {
        //   this.player_idx = 1;
        // } else {
        //   this.player_idx = 0;
        // }
      }

      // this.room_id = 1;
      // this.user_id = "hajung";
      // this.user_identification = "watcher";
    },

    sendMessage(message) {
      //서버로 메세지 보내기
      console.log("sendMessage----->5");
      this.socket.emit("message", message);
    },
    async get_stream(stream) {
      console.log("get_stream---->3",this.player_idx);
      this.player_videos[this.player_idx].srcObject = stream;
      this.player_streams[this.player_idx] = stream;
      this.local_stream = stream;
    },
    async getPeerConnection(join_id, join_identification, join_idx) {
      console.log("getPeerConnection()----->4");
      console.log("저는 " + this.user_identification + "이제 pc 생성");
      console.log("새로들어온 애는 " + join_identification + "입니다.");
      let t_pc = await new RTCPeerConnection(pcConfig);
      let bat_id = 0;

      if (this.user_identification==="singer"){
        if (join_identification==="singer"){
          bat_id = (this.player_idx+1)%2;
          
          this.battle_connections[bat_id] = t_pc;
          console.log(this.battle_connections)
          this.battle_id[bat_id] = join_id;
        } else {
          this.watchers_connections[this.watcher_cnt] = t_pc;
          this.watchers_id[this.watcher_cnt++] = join_id;
        }
      } else {
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
            from_idx: this.player_idx,
            to: join_id,
            to_identification: join_identification
          });
        }
      };

      t_pc.onaddstream = event => {
        let remote_video = document.getElementById("p1_video");
        if (bat_id==1){
          remote_video = document.getElementById("p2_video");
        }
        
        this.player_streams[bat_id] = event.stream;
        remote_video.srcObject = this.player_streams[bat_id];
        console.log("제발                  ㄴㄴㄴ",this.player_streams[bat_id])
      }

     if (this.user_identification==="singer"){

      // if(this.player_streams[this.player_idx]!=null){

      //   t_pc.addStream(this.player_streams[this.player_idx]);

      //   } else{
      //     let player_stream = this.player_streams[this.player_idx];
      //     t_pc.addStream(player_stream);
      //   }
          t_pc.addStream(this.local_stream);
      }
      return t_pc;
    }
  },
  created() {
    this.getData();
    console.log("created()---->1", this.battle_id);
    console.log("주소오오오오오오오옹", process.env.VUE_APP_SOCKET_IP);
    this.socket = io.connect(
      process.env.VUE_APP_SOCKET_IP +
        "?room_id=" +
        this.room_id +
        "&user_id=" +
        this.user_id +
        "&user_identification=" +
        this.user_identification +
        "&player_idx=" +
        this.player_idx,
      { transports: ["websocket"] }
      //{secure:true}
    );
  },
    mounted() {
     window.onbeforeunload = () => {
      this.socket.emit("leave", {
        room_id:this.room_id,
        user_identification:this.user_identification,
        user_id:this.user_id,
        player_idx:this.player_idx
      });
    };
    console.log("mounted()---->2");
    console.log(this);
    
    this.player_videos[0] = document.getElementById("p1_video");
    this.player_videos[1] = document.getElementById("p2_video");
    
    //player1일때,
    if (this.user_identification === "singer") {
      console.log("가수");
      navigator.mediaDevices
        .getUserMedia({
          
          audio: true,
          video: true
        })
        .then(this.get_stream);

    }
    this.socket.on("join", message => {
      console.log(message);
      const join_id = message.user_id;
      console.log(join_id + "들어왔숩다.");
      const join_identification = message.user_identification;
      const join_idx = message.player_idx;

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
                  
                });
              },
              e => {
                console.log(e);
              }
            );
          });
        }, 1000);
      } else {
        if (join_identification === "singer") {
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
      if (message.user_identification==="singer"){
        this.player_videos[message.player_idx] = null;
        this.player_streams[message.player_idx] = null;
        this.battle_id[message.player_idx] = null;
        this.battle_connections[message.player_idx] = null;
      }
      else{
        if (this.user_identification==="singer"){
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
        if (this.user_identification == "singer") {
          //내가 가수이고
          if (data.from_identification == "singer") {
            //상대도 가수
            console.log("오퍼를 받앗습니다.답장을 해요", data);
            this.battle_id[(this.player_idx + 1) % 2] = data.from;
          } else {
            //상대가 시청자일 경우
            this.watchers_id[this.watcher_cnt++] = data.from;
          }

          this.getPeerConnection(
            data.from,
            data.from_identification,
            data.from_idx
          ).then(t_pc => {
            t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
            t_pc.createAnswer().then(sdp => {
              t_pc.setLocalDescription(sdp);
              this.sendMessage({
                message: sdp,
                room_id: this.room_id,
                from: this.user_id,
                from_identification: this.user_identification,
                from_idx: this.player_idx,
                to: data.from,
                to_identification: data.from_identification
              });
            });
          });
        } else {
          //내가 시청자일때,
          if (data.from_identification == "singer") {
            this.battle_id[data.from_idx] = data.from;

            this.getPeerConnection(
              data.from,
              data.from_identification,
              data.from_idx
            ).then(t_pc => {
              t_pc.setRemoteDescription(
                new RTCSessionDescription(data.message)
              );
              t_pc.createAnswer().then(sdp => {
                t_pc.setLocalDescription(sdp);
                this.sendMessage({
                  message: sdp,
                  room_id: this.room_id,
                  from: this.user_id,
                  from_identification: this.user_identification,
                  from_idx: this.player_idx,
                  to: data.from,
                  to_identification: data.from_identification
                });
              });
            });
          }
        }
      } else if (data.message.type == "answer") {
        console.log(
          "새로 들어온" +
            data.from_identification +
            "한테 answer을 받았습니다.",
            data
        );
        //let t_pc = null;
        if (this.user_identification == "singer") {
          if (data.from_identification == "singer") {
            console.log("여기 오는지 확인",this.battle_connections[data.from_idx])
            this.battle_connections[data.from_idx].setRemoteDescription(new RTCSessionDescription(data.message));
          } else {
           this.watchers_connections[this.watcher_cnt - 1].setRemoteDescription(new RTCSessionDescription(data.message));
          }
        } else {
          if (data.from_identification == "singer") {
            this.battle_connections[data.from_idx].setRemoteDescription(new RTCSessionDescription(data.message));
          }
        }
        
        // if (this._identification==="singer"){
        //   console.log("여기는 오는가 ????")
        //   t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
        // }
      } else if (data.message.type === "candidate") {
        let candidate = new RTCIceCandidate({
          sdpMLineIndex: data.message.label,
          candidate: data.message.candidate
        });
        let t_pc = null;
        if (this.user_identification == "singer") {
          if (data.from_identification == "singer") {
            t_pc = this.battle_connections[data.from_idx];

            t_pc.addIceCandidate(candidate);
          } else {
            t_pc = this.watchers_connections[this.watcher_cnt - 1];
            t_pc.addIceCandidate(candidate);
          }
        } else {
          if (data.from_identification == "singer") {
            if (this.battle_connections[0]) {
              t_pc = this.battle_connections[0];
              t_pc.addIceCandidate(candidate);
            } else {
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

<style scoped>
.container {
  width: 100vw;
}
.chat_component {
  margin-top: 50vh;
  margin-left: 25vw;
  position: fixed;
  height: 50%;
  background-color: rgba(0, 0, 0, 0.05);
  width: 50vw;
}
.vote1_component {
  margin-top: 25vh;
  position: fixed;
  margin-left: 8vw;
  background-color: rgba(0, 0, 0, 0);
}
.vote2_component {
  margin-top: 25vh;
  position: fixed;
  margin-left: 85vw;
  background-color: rgba(0, 0, 0, 0);
}
button {
  position: fixed;
  font-family: inherit;
  font-weight: bold;
  color: var(--white-1);

  letter-spacing: 2px;

  margin-left: 1vw;
  margin-top: -39vh;
  font-size: 1.3rem;

  padding: 9px 20px;
  border: 1px solid var(--grey);
  border-radius: 1000px;
  background: transparent;
  transition: 0.3s;

  cursor: pointer;
}
#outbtn {
  background-color: rgba(207, 156, 1, 0.5) !important;
  color: white;
}
button:hover,
button:focus {
  color: black;
  background: hsla(var(--hue), 25%, 10%, 0.2);
  border-color: currentColor;
}

button:active {
  transform: translate(2px);
}

#select1_component {
  position: fixed;
}

#select2_component {
  position: fixed;
}
</style>