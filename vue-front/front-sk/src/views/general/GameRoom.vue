<template>
  <div id="gameRoom">
    GameRoom
   
    <div>
      <!-- https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8 -->
      <div id="firstWebCam">
        player1 들어갈자리
        <video playsinline id="p1_video" autoplay preload="metadata" style="width:30%" poster="@/assets/images/camPoster.jpg">
        </video>``
      </div>
      
      <div id="secondWebCam">
        player2 들어갈자리
        <video playsinline id="p2_video" autoplay preload="metadata" style="width:30%" poster="@/assets/images/camPoster.jpg">
        </video>
      </div>
    </div>
  </div> 
</template>

<script>
import io from "socket.io-client"
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
  props: ["user_id"],
  data() {
    return {
      //localVideo:null,
      p1_video: null,
      p1_stream: null,
      //connected_users: [-1, null, null, null, null, null],
      //peer_connections: {},

      watchers:[null,null,null,null,null],
      peer_connections:{},

      room_id: 1,
      roomInfo: "",
      singer_num:0,

      user_id:"me",
      player1:"me",
      player2:"you",

      user_identification:"singer",

      userState: 0,
      isInitiator:false,

      fight_user:null,
      fight_connection:null,

      p2_video: null,
      p2_stream: null,

      isStarted : false,

    };
  },
  computed: {
    ...mapState(["singerOrWatcherStatus", "token"])
  },

  methods:{
    
    sendMessage(message) {
      //서버로 메세지 보내기
     console.log("sedMessage----->5");
      this.socket.emit("message", message);
      // this.socket.broadcast.emit~
      //.broadcase. 를 추가하면 자신을 제외한 나머지 클라이언트에게만 메세지를 전달합니다.  

    },
    async get_stream(stream) {

      console.log("get_stream---->3");

      if (player1==user_id){
        this.p1_video = await document.getElementById("p1_video");
        this.p1_video.srcObject = stream;
        this.p1_stream = stream;
      }else{
        this.p2_video = await document.getElementById("p2_video");
        this.p2_video.srcObject = stream;
        this.p2_stream = stream;

      }
      
      // if (!this.isInitiator ){
      //   this.maybeStart();
      // }

    },
    // maybeStart(){
    //   console.log("시작쓰");
    //   console.log(this.isStarted)
    //   if(!this.isStarted){
    //       console.log("creating peer connection");
    //       this.getPeerConnection();
    //       this.isStarted = true;
    //       console.log(this.isInitiator)
    //       if (this.isInitiator ){
    //         console.log('오퍼보내기')
    //         this.doCall();
    //       }
    //   } 
    // },
    // setLocalAndSendMessage(sessionDescription){
    //   this.peer_connection.setLocalDescription(sessionDescription);
    //   this.sendMessage(sessionDescription);
    // },
    // doCall(){
    //   this.peer_connection.createOffer(this.setLocalAndSendMessage)

    // },
    async getPeerConnection() {
      console.log("getPeerConnection()----->4");
      this.fight_connection= await new RTCPeerConnection(pcConfig);

      console.log("start pc",this.fight_connection);
      this.fight_connection.onicecandidate = event => {
        if (event.candidate) {
         //console.log("빠빠바");

          this.sendMessage({
            message: {
              type: "candidate",
              label: event.candidate.sdpMLineIndex,
              id: event.candidate.sdpMid,
              candidate: event.candidate.candidate
            },
            //room_id: 1,
          });
        }
      };

      this.fight_connection.onaddstream = event => {
       // console.log(event.stream, 'onaddstream')
        //this.p2_video = document.getElementById("")
        this.p2_stream = event.stream;
        this.p2_video.srcObject = this.p2_stream;
      }
      //console.log(this.fight_connection, '1111')
      this.fight_connection.addStream(this.p1_stream);
      return this.fight_connection;
    }
  },
  created() {
    console.log("created()---->1");
    this.socket = io.connect('http://70.12.246.73:3001?room_id='+this.room_id+'&user_id='+user_id+'&user_identification='+user_identification, {transports: ["websocket"]})
 
  },
  mounted(){
    console.log("mounted()---->2")
    this.p2_video = document.getElementById("p2_video");
    this.p1_video = document.getElementById("p1_video");
    
    //player1일때,
    if (user_identification=="singer"){
      navigator.mediaDevices
      .getUserMedia({
        audio:true,
        video:true
      })
      .then(this.get_stream);
    }
      this.socket.on("join",message => {
        const join_id = message.user_id;
        console.log(join_id+"들어왔숩다.");
        const join_identification = message.identification;

        //const user_id = message.user_id;
        //console.log("새로운 상대가 들어옴!");
        setTimeout(() => {
          this.getPeerConnection()
          .then( t_pc => {
            t_pc.createOffer(sdp => {
              t_pc.setLocalDescription(sdp) 
              this.sendMessage({
                message: sdp,
                
                // study_id: 1,
                // from: this.user_id,
                // to: user_id
              })
            }, e => {console.log(e)})
          })      
        }, (1000));
      })

      this.socket.on('leave', message => {
      this.p2_video = null;
      this.p2_stream = null;
    });
    this.socket.on("message", data => {
      if (data.message.type === "offer") {
        this.getPeerConnection().then(t_pc => {
          t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
          t_pc.createAnswer().then(sdp => {
            t_pc.setLocalDescription(sdp);
            this.sendMessage({
              message: sdp
              //study_id: 1,
             // from: this.user_id,
              //to: from
            });
          });
        });
      } else if (data.message.type === "answer") {
        let t_pc = this.fight_connection;
        t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
      } else if (data.message.type === "candidate") {
        let cand = new RTCIceCandidate({
          sdpMLineIndex: data.message.label,
          candidate: data.message.candidate
        });
        //let t_pc = this.peer_connection;
        //console.log("여기 캔디데이트", cand);
        //console.log("여기는 pc?", this.peer_connection);
          this.fight_connection.addIceCandidate(cand);
      }
    });
    }
  }
;


</script>

