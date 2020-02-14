<template>
  <div id="gameRoom">
    GameRoom
    {{ message }}
    <div>
      <!-- https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8 -->
      <div id="firstWebCam">
        firstWebCam 들어갈자리
        <video playsinline id="local_video" autoplay preload="metadata" width="100%" height="150">

         <!-- <video autoplay playsinline> -->
         </video>
         
        <!-- <video
          autoplay="autoplay"
          controls="controls"
          style="width:50%"
          playsinline
        > -->
          <!-- <source src="https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8" type="application/x-mpegURL" /> -->
        <!-- </video> -->
        <!-- <object
        src="https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8"
        ></object>-->
      </div>
      <!-- <div v-html="embedHtml"> </div> -->
      <div id="secondWebCam">
        secondWebCam 들어갈자리
        <video preload="auto" controls="controls" loop="loop" style="width:50%"></video>
      </div>
    </div>

<webchat/>
    <div id="Chat">
      채팅
      <input type="text" placeholder="보낼 메세지를 입력하세요." />
      <v-btn text small color="primary">입력</v-btn>
    </div>
  </div>
</template>

<script>

// const localVideo = document.getElementById('left_cam');
// navigator.mediaDevices
//       .getUserMedia({
//         audio: true,
//         video: true
//       })
//       .then(this.getStream)
//       .catch(function(e){
//         alert('getUserMedia() error:'+e.name);
//       });

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
  props: ["socket", "user_id"],
  data() {
    return {
      //localVideo:null,
      local_video: null,
      local_stream: null,

      connected_users: [-1, null, null, null, null, null],
      peer_connections: {},

      remote_videos: [null],
      remote_streams: [null, null, null, null, null, null],

      fav: false,
      showProfile: false,
      x: 0,
      y: 0,
      items: [{ title: "Hello" }],
      message: "hello tess"
    };
  },
  merhods:{
    sendMessage(message) {
      this.socket.emit("message", message);
    },
    async get_stream(stream) {
      this.local_video = await document.getElementById("local_video");
      this.local_video.srcObject = stream;
      this.local_stream = stream;
    },

    async getPeerConnection(user_id) {
      let video_num,
        temp_null = 10,
        existed_num = null;
      for (let idx in this.connected_users) {
        if (this.connected_users[idx] == user_id) { existed_num = idx }
        else if (!this.connected_users[idx]) {
          temp_null = temp_null > idx ? idx : temp_null
        }
      }

      video_num = existed_num ? existed_num : temp_null;
      if (this.peer_connections[user_id]) {
        return this.peer_connections[user_id];
      }
      let t_pc = await new RTCPeerConnection(pcConfig);

      this.peer_connections[user_id] = t_pc;

      t_pc.onicecandidate = event => {
        if (event.candidate) {
          this.sendMessage({
            message: {
              type: "candidate",
              label: event.candidate.sdpMLineIndex,
              id: event.candidate.sdpMid,
              candidate: event.candidate.candidate
            },
            //room_id: 1,
            from: this.user_id,
            to: user_id
          });
        }
      };

      t_pc.onaddstream = event => {
        let remote_video = document.createElement("video");
        this.remote_streams[video_num] = event.stream;

        // remote_video.width = "100%"
        // remote_video.height = 150
        remote_video.playsinline = true
        remote_video.srcObject = this.remote_streams[video_num]
        remote_video.autoplay = true
        remote_video.style.width = "100%"
        remote_video.style.height = "150"
        
        const remote_block = this.remote_videos[video_num]
        this.remote_videos[video_num].childNodes[0] ? remote_block.removeChild(this.remote_videos[video_num].childNodes[0]) : 0
        remote_block.appendChild(remote_video)
      }


      t_pc.addStream(this.local_stream);
      return t_pc;
    }
  },
  created() {
    console.log("내아이디 : ", this.user_id);
  },
  mounted(){
    for (let i=1; i<=5; i++){
      this.remote_videos.push(document.getElementById(`remote_block_${i}`));

    }
    this.local_video = document.getElementById("local_video");

    navigator.mediaDevices
      .getUserMedia({
        audio:true,
        video:true
      })
      .then(this.get_stream);

      this.socket.on("join",message => {
        const user_id = message.user_id;
        console.log(message.members)
        if (user_id == this.user_id) return;
        for (let idx in this.connected_users){
          if (!this.connected_users[idx]){
          this.connected_users[idx] = user_id;
          break;
          }
        }
        setTimeout(() => {
          this.getPeerConnection(user_id)
          .then( t_pc => {
            t_pc.createOffer(sdp => {
              t_pc.setLocalDescription(sdp) 
              this.sendMessage({
                message: sdp,
              // study_id: 1,
                from: this.user_id,
                to: user_id
              })
            }, e => {console.log(e)})
          })      
        }, (1000));
      })

      this.socket.on('leave', message => {
      const video_num = this.connected_users.indexOf(message.user_id)
      this.connected_users[video_num] = null
      this.remote_videos[video_num].removeChild(this.remote_videos[video_num].childNodes[0])
      //const post_img = document.createElement('img')
      //post_img.src = pengsoo.src
      //post_img.style.width = "100%"
      //post_img.style.height = 150
     // this.remote_videos[video_num].appendChild(post_img)
      this.remote_streams[video_num] = null
      delete this.peer_connections[message.user_id]
    })
    this.socket.on("message", data => {
      if (data.message.type === "offer") {
        const from = data.from;

        for (let idx in this.connected_users) {
          if (!this.connected_users[idx]) {
            this.connected_users[idx] = from;
            break;
          }
        }
        this.getPeerConnection(from).then(t_pc => {
          t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
          t_pc.createAnswer().then(sdp => {
            t_pc.setLocalDescription(sdp);
            this.sendMessage({
              message: sdp,
              //study_id: 1,
              from: this.user_id,
              to: from
            });
          });
        });
      } else if (data.message.type === "answer") {
        let t_pc = this.peer_connections[data.from];
        t_pc.setRemoteDescription(new RTCSessionDescription(data.message));
      } else if (data.message.type === "candidate") {
        let candidate = new RTCIceCandidate({
          sdpMLineIndex: data.message.label,
          candidate: data.message.candidate
        });
        let t_pc = this.peer_connections[data.from];
        t_pc.addIceCandidate(candidate);
      }
    });
  }



  //여기부터========================================================== 끝까지
  // methods:{
  //    async getStream(stream) {
  //      console.log('Adding local stream');
  // //this.localVideo = await document.getElementById("local_video");
  //     this.localVideo.srcObject = stream;
  //     this.localStream = stream;
  //     if (isInitiator){
  //       maybeStart();
  //     }
  //   },
  //   maybeStart(){
  //     console.log('>>>>>>>>>>>>maybeStart()',isStarted, localStream, isChannelReady);
  //   }
  // }
 };
</script>