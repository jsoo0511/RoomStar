<template>
<div>
 <div v-show="show" id="loading">
           <div class="cssload-box-loading">

              <img
                src="../../assets/images/roomstar.png"
                style="margin-top:10vh; margin-left:-6vw; width:230px; height:auto ;"
              />
           </div>
        </div>

  <section v-show="!show" id="waitingRooms">
    <!-- <v-btn @click="singingButton()">노래하기</v-btn> -->
    <swiper :options="swiperOption">
      <!-- swipter-slide 부분을 v-for로 처리해야 할 것 같은데, -->
      <swiper-slide
        :key="index"
        v-for="(roomInfo, index) in allRoomInfo"
        
      >

        <v-row id="swiper_row" @click="watchingButton(roomInfo.room_id)">
          <div class="card marginR">
            <div class="card-image">
              <v-img
              :src="roomInfo.user1_img"
              id="profile_card_image"
            ></v-img>
            </div>
            <div class="card-text">
              <span class="rank">{{roomInfo.user1_grade}}</span>
              <h2>{{roomInfo.user1}}</h2>
              <h4>{{roomInfo.music1}}</h4>
            </div>
            <div class="card-stats">
              <div class="stat">
                <div class="value">{{roomInfo.win1}}</div>
                <div class="type">win</div>
              </div>
              <div class="stat border">
                <div class="value">{{roomInfo.lose1}}</div>
                <div class="type">lose</div>
              </div>
              <div class="stat">
                <div class="value">{{roomInfo.user1_win_rate}} %</div>
                <div class="type">winrate</div>
              </div>
            </div>
          </div>
          <v-img src="../../assets/images/logo_vs2.png" id="vs_img"></v-img>

          <div class="card marginL">
            <div class="card-image">
              <v-img
              :src="roomInfo.user2_img"
              id="profile_card_image"
            ></v-img>
            </div>
            <div class="card-text">
              <span class="rank">{{roomInfo.user2_grade}}</span>
              <h2>{{roomInfo.user2}}</h2>
              <h4>{{roomInfo.music2}}</h4>
            </div>
            <div class="card-stats">
              <div class="stat">
                <div class="value">{{roomInfo.win2}}</div>
                <div class="type">win</div>
              </div>
              <div class="stat border">
                <div class="value">{{roomInfo.lose2}}</div>
                <div class="type">lose</div>
              </div>
              <div class="stat">
                <div class="value">{{roomInfo.user2_win_rate}} %</div>
                <div class="type">winrate</div>
              </div>
            </div>
          </div>
        </v-row>
      </swiper-slide>
      <!-- 여기까지가 v-for로 반복시킬 부분 -->
    </swiper>

    <!--
     <ul>
          <v-btn @click="watchingButton(roomInfo.room_id, $event)">시청하기</v-btn>
      </ul>
    -->
    <!-- <p>대기인원: {{ waitingNumofPeople }}</p> -->
  </section>
</div>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import router from "@/routes";
import axios from "axios";
import swal from "sweetalert";
var swiper = null;

// var waitingNumofPeople = 0;
export default {
  name: "WaitingRoom",
  components: {},
  data() {
    return {
      show: true,
      allRoomInfo: [],
      waitingNumofPeople: 0,
      swiperOption: {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true
        },
        pagination: {
          el: ".swiper-pagination"
        }
      }
    };
  },
  methods: {
    singingButton() {
      const userid = this.$session.get("userId");
      console.log(userid);
      this.$store.dispatch("changeToSinger", 1);
      this.$session.set("singerOrWatcherStatus", 1);
      // this.$store.dispatch("changeToSinger", 1);

      axios
        .put(process.env.VUE_APP_SERVER_IP + "/Enter_room/" + userid)
        .then(response => {
          this.$session.set("roomId", response.data.room_id);
          this.$router.push("/GameRoom");
        })
        .catch(e => {
          console.log("error: ", e);
          alert("모든 방이 차있습니다.");
        });
    },
    // 시청하기를 누르면 해당 방으로 이동
    watchingButton(room_id) {
      console.log(room_id)
      console.log("===========================");
      
      const userid = this.$session.get("userId");
      const userNickname = this.$session.get("userNickname");
      this.$session.set("roomId", room_id);
      console.log(userid, userNickname);

      let data = {
        userid,
        room_id,
        vote: 0
      };
      this.$store.dispatch("changeToWatcher", 2);
      this.$session.set("singerOrWatcherStatus", 2);

      axios
        .post(process.env.VUE_APP_SERVER_IP + "/Insert_watching", data)
        .then(response => {
          console.log(response);
          // 해당 room으로 이동
          console.log('들어갈때 session확인',this.$session)
          this.$router.push("/GameRoom");
        })
        .catch(e => {
          console.log("error: ", e);
        });
    }
  },
  created() {
    // 유저가 처음 대기방에 들어왔을때 얻을 수 있는 방들의 정보
    let store = this.$store;
    const userid = this.$session.get("userId");
    console.log(userid);
    axios
      .post(process.env.VUE_APP_SERVER_IP + "/Insert_waiting/" + userid)
      .then(response => {
        console.log('insert_waiting', response)
        console.log(response.data.roomViewInfo)
        console.log(response.data.roomViewInfo[0].singer_num)
        for (let i = 0; i < 5; i++) {
          // singer1 or singer2 한명이라도 있으면 보여주기
          if (response.data.roomViewInfo[i].singer_num) {
          this.allRoomInfo.push(response.data.roomViewInfo[i]);
          }
        }
        if (this.allRoomInfo.length == 0) {
                                 swal({
              title: "현재 활성화된 방이 없습니다."
            });
        }
        console.log(this.allRoomInfo[0])
        // 대기인원수
        this.waitingNumofPeople = response.data.waitingList.length;
        console.log(this.waitingNumofPeople);
      })
      .catch(e => {
        console.log("error: ", e);
      });

       setTimeout(() => {
        this.show=false;
}, 1000);
    return this.allRoomInfo;

    
  }

  

  // viewMypage
};
</script>
<style scoped>
body
{
  margin:0;
  padding:0
}

#loading {
    background: #DA22FF;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #9733EE, #DA22FF);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #9733EE, #DA22FF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

    height: 100%;
    width: 100%;
    position: fixed;
    z-index: 1;
    margin-top: 0px;
    top: 0px;
    z-index: 999999;
    display: block;
}


/*-------------------------
    33.Preloader CSS
---------------------------*/
.loadding-page {
  width: 100%;
  height: 100%;
  background: #fdd245;
  overflow: hidden;
  position: fixed;
  top: 0;
  z-index: 9999;
}
.cssload-box-loading {
  width: 49px;
  height: 49px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}
.cssload-box-loading:before {
  content: '';
  width: 49px;
  height: 5px;
  background: #000000;
  opacity: 0.1;
  position: absolute;
  top: 58px;
  left: 0;
  border-radius: 50%;
  animation: shadow 0.58s linear infinite;
  -o-animation: shadow 0.58s linear infinite;
  -ms-animation: shadow 0.58s linear infinite;
  -webkit-animation: shadow 0.58s linear infinite;
  -moz-animation: shadow 0.58s linear infinite;
}
.cssload-box-loading:after {
  content: '';
  width: 49px;
  height: 49px;
  background: #fdd245;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 3px;
  animation: cssload-animate 0.58s linear infinite;
  -o-animation: cssload-animate 0.58s linear infinite;
  -ms-animation: cssload-animate 0.58s linear infinite;
  -webkit-animation: cssload-animate 0.58s linear infinite;
  -moz-animation: cssload-animate 0.58s linear infinite;
}
@keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    transform: translateY(0) rotate(90deg);
  }
}
@-o-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -o-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -o-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -o-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -o-transform: translateY(0) rotate(90deg);
  }
}
@-ms-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -ms-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -ms-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -ms-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -ms-transform: translateY(0) rotate(90deg);
  }
}
@-webkit-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -webkit-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -webkit-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -webkit-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(90deg);
  }
}
@-moz-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -moz-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -moz-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -moz-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -moz-transform: translateY(0) rotate(90deg);
  }
}
@keyframes shadow {
  0%,
  100% {
    transform: scale(1, 1);
  }
  50% {
    transform: scale(1.2, 1);
  }
}
@-o-keyframes shadow {
  0%,
  100% {
    -o-transform: scale(1, 1);
  }
  50% {
    -o-transform: scale(1.2, 1);
  }
}
@-ms-keyframes shadow {
  0%,
  100% {
    -ms-transform: scale(1, 1);
  }
  50% {
    -ms-transform: scale(1.2, 1);
  }
}
@-webkit-keyframes shadow {
  0%,
  100% {
    -webkit-transform: scale(1, 1);
  }
  50% {
    -webkit-transform: scale(1.2, 1);
  }
}
@-moz-keyframes shadow {
  0%,
  100% {
    -moz-transform: scale(1, 1);
  }
  50% {
    -moz-transform: scale(1.2, 1);
  }
}

.card {
  display: grid;
  grid-template-columns: 100%;
  grid-template-rows: 40vh 8vw 4vw;
  grid-template-areas: "image" "text" "stats";
  border-radius: 1rem;
  background: white;
  box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.9);
  font-family: roboto;
  text-align: center;
  transition: 0.5s ease;
  cursor: pointer;
  min-width: 20vw;
}

.marginL {
  margin-left: 1em;
}

.marginR {
  margin-right: 1em;
}
.card-text {
  grid-area: text;
}
.card-image {
  grid-area: image;
  background: url("../../assets/images/bg_solo2.png");
  border-top-left-radius: 1rem;
  border-top-right-radius: 1rem;
  background-size: cover
}

.card-image #profile_card_image{
    width:307.2px !important;
    height:310.06px !important;
  }

.card-text {
  grid-area: text;
  margin: 0.5rem;
}
.card-text .rank {
  color: rgb(255, 7, 110);
  font-size: 0.9rem;
}
.card-text h2 {
  margin-top: 0px;
  font-size: 1.6rem;
}

.card-text h4 {
  margin-top: 0.8rem;
  font-size: 0.9rem;
  overflow: hidden;
}

.card-stats {
  font-size: 0.85rem;
  font-weight: 500;
  grid-area: stats;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr;
  border-bottom-left-radius: 1rem;
  border-bottom-right-radius: 1rem;
  background:rgba(253,175,15);
}

.card-stats .stat {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: white;
  padding: 1rem;
}
/*
.card:hover {
  transform: scale(1.15);
  box-shadow: 5px 5px 15px rgba(0,0,0,0.6);
}
*/
#waitingRooms {
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width:100vw;
  background-image: url("../../assets/images/waitingroom_back.png");
  font-family: "Poppins", sans-serif;
}

.swiper-inner {
  width: 100%;
  height: 400px;
}
.swiper-slide {
  background-position: center;
  background-size: center;
  width: auto;
  padding-top: 6vh;
  height: 76vh;
}
#vs_img {
  z-index: 1 !important;
  position: fixed;
  margin-top: 41%;
  margin-left: 44%;
}
#swiper_row {
  transition: 0.5s ease;
  width: auto !important;
}
#swiper_row:hover {
  transform: scale(1.15);
  box-shadow: 5px 5px 15px rgba(0, 0, 0, 0);
}
.swiper-container{
  min-width:80vw;
}
.v-responsive{
  max-height:100%;
}
</style>