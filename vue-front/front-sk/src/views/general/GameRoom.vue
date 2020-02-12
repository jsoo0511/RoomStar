<template>
  <div id="gameRoom">
    GameRoom
   
    <div>
      <!-- https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8 -->
      <div id="firstWebCam">
        firstWebCam 들어갈자리
        <video playsinline id="local_video" autoplay preload="metadata" style="width:30%">
        </video>
         
       

      </div>
      
      <div id="secondWebCam">
        secondWebCam 들어갈자리ㅡㅡㅡㅡㅡ
        <video playsinline id="remote_video" autoplay preload="metadata" style="width:30%">
        </video>
      </div>
    </div>
  </div> 
</template>

<script>
import io from "socket.io-client"


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
      local_video: null,
      local_stream: null,
      //connected_users: [-1, null, null, null, null, null],
      //peer_connections: {},


      isInitiator:false,

      connected_user:null,
      peer_connection:null,

      remote_video: null,
      remote_stream: null,

      isStarted : false,

    };
  },

  methods:{
    sendMessage(message) {
      //서버로 메세지 보내기
     // console.log("message를 보내볼까 ");
      this.socket.emit("message", message);
      // this.socket.broadcast.emit~
      //.broadcase. 를 추가하면 자신을 제외한 나머지 클라이언트에게만 메세지를 전달합니다.  

    },
    async get_stream(stream) {

      //console.log("내 화면 송출 중", stream);
      this.local_video = await document.getElementById("local_video");
      this.local_video.srcObject = stream;
      this.local_stream = stream;
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

      this.peer_connection= await new RTCPeerConnection(pcConfig);

      console.log("start pc",this.peer_connection);
      this.peer_connection.onicecandidate = event => {
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

      this.peer_connection.onaddstream = event => {
       // console.log(event.stream, 'onaddstream')
        //this.remote_video = document.getElementById("")
        this.remote_stream = event.stream;
        this.remote_video.srcObject = this.remote_stream;
        
      }
      //console.log(this.peer_connection, '1111')
      this.peer_connection.addStream(this.local_stream);
      return this.peer_connection;
    }
  },
  created() {
    //console.log("시작");
    this.socket = io.connect('http://70.12.246.73:3001', {transports: ["websocket"]})
  },
  mounted(){
    
    //console.log("뀨?")
    this.remote_video = document.getElementById("remote_video");
    this.local_video = document.getElementById("local_video");
    

    navigator.mediaDevices
      .getUserMedia({
        audio:true,
        video:true
      })
      .then(this.get_stream);

      this.socket.on("join",message => {
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
      this.remote_video = null;
      this.remote_stream = null;
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
        let t_pc = this.peer_connection;
        t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
      } else if (data.message.type === "candidate") {
        let cand = new RTCIceCandidate({
          sdpMLineIndex: data.message.label,
          candidate: data.message.candidate
        });
        //let t_pc = this.peer_connection;
        //console.log("여기 캔디데이트", cand);
        //console.log("여기는 pc?", this.peer_connection);
          this.peer_connection.addIceCandidate(cand);
      }
    });
    }
  


  }
;


</script>

