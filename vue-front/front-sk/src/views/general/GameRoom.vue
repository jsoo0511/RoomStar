<template>
  <div id="gameRoom">
    GameRoom
    {{ message }}
    <div>
      <!-- https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8 -->
      <div id="firstWebCam">
        firstWebCam 들어갈자리
       <!-- <video playsinline id ="left_cam" controls preload="metadata" autoplay> -->

         <video autoplay playsinline>
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

import webchat from './webChat.js';

export default {
  
  name: "GameRoom",
  components: {webchat},
  data() {
    return {
      //localVideo:null,
      message: "hello tess"
    };
  }
  
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
