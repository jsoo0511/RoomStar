<template>
  <div id="gameRoom">
    GameRoom
    {{ message }}
    <div>
      <!-- https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8 -->
      <div id="firstWebCam">
        firstWebCam 들어갈자리
        <video
          autoplay="autoplay"
          controls="controls"
          style="width:50%"
          playsinline
        >
          <!-- <source src="https://xyxufvchfhks3520734.cdn.ntruss.com/video/ls-20200122105535-tD4G9_1080p_a_l.m3u8" type="application/x-mpegURL" /> -->
        </video>
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

    <div id="Chat">
      채팅
      <input type="text" placeholder="보낼 메세지를 입력하세요." />
      <v-btn text small color="primary">입력</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "GameRoom",
  components: {},
  data() {
    return {
      message: "hello tess"
    };
  }
};
</script>
