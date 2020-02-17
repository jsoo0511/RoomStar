<template>
  <div id="waitingRooms">
       <swiper :options="swiperOption">
        <swiper-slide>
        <v-row id="swiper_row">

        <div class="card marginR">
        <div class="card-image"></div>
        <div class="card-text">
          <span class="rank">bronze IV</span>
          <h2>User 1</h2>
          <h4>아무노래 - 지코</h4>
        </div>
        <div class="card-stats">
          <div class="stat">
            <div class="value">4</div>
            <div class="type">win</div>
          </div>
        <div class="stat border">
          <div class="value">96</div>
          <div class="type">lose</div>
        </div>
          <div class="stat">
            <div class="value">4 %</div>
            <div class="type">winrate</div>
          </div>
        </div>
      </div>
      <v-img src="../../assets/images/battle_vs2.png" id="vs_img"></v-img>

      <div class="card marginL">
        <div class="card-image"></div>
        <div class="card-text">
          <span class="rank">Diamond II</span>
          <h2>User 2</h2>
          <h4>오늘 서울은 하루종일 맑음 - 윤하</h4>
        </div>
        <div class="card-stats">
          <div class="stat">
            <div class="value">4</div>
            <div class="type">win</div>
          </div>
        <div class="stat border">
          <div class="value">96</div>
          <div class="type">lose</div>
        </div>
          <div class="stat">
            <div class="value">4 %</div>
            <div class="type">winrate</div>
          </div>
        </div>
      </div>
      </v-row>
    </swiper-slide>
        <swiper-slide>
        <v-row id="swiper_row">

        <div class="card marginR">
        <div class="card-image"></div>
        <div class="card-text">
          <span class="rank">bronze IV</span>
          <h2>User 1</h2>
          <h4>아무노래 - 지코</h4>
        </div>
        <div class="card-stats">
          <div class="stat">
            <div class="value">4</div>
            <div class="type">win</div>
          </div>
        <div class="stat border">
          <div class="value">96</div>
          <div class="type">lose</div>
        </div>
          <div class="stat">
            <div class="value">4 %</div>
            <div class="type">winrate</div>
          </div>
        </div>
      </div>
      <v-img src="../../assets/images/battle_vs2.png" id="vs_img"></v-img>

      <div class="card marginL">
        <div class="card-image"></div>
        <div class="card-text">
          <span class="rank">Diamond II</span>
          <h2>User 2</h2>
          <h4>오늘 서울은 하루종일 맑음 - 윤하</h4>
        </div>
        <div class="card-stats">
          <div class="stat">
            <div class="value">4</div>
            <div class="type">win</div>
          </div>
        <div class="stat border">
          <div class="value">96</div>
          <div class="type">lose</div>
        </div>
          <div class="stat">
            <div class="value">4 %</div>
            <div class="type">winrate</div>
          </div>
        </div>
      </div>
      </v-row>
</swiper-slide>
        <swiper-slide>
        <v-row id="swiper_row">

        <div class="card marginR">
        <div class="card-image"></div>
        <div class="card-text">
          <span class="rank">bronze IV</span>
          <h2>User 1</h2>
          <h4>아무노래 - 지코</h4>
        </div>
        <div class="card-stats">
          <div class="stat">
            <div class="value">4</div>
            <div class="type">win</div>
          </div>
        <div class="stat border">
          <div class="value">96</div>
          <div class="type">lose</div>
        </div>
          <div class="stat">
            <div class="value">4 %</div>
            <div class="type">winrate</div>
          </div>
        </div>
      </div>
      <v-img src="../../assets/images/battle_vs2.png" id="vs_img"></v-img>

      <div class="card marginL">
        <div class="card-image"></div>
        <div class="card-text">
          <span class="rank">Diamond II</span>
          <h2>User 2</h2>
          <h4>오늘 서울은 하루종일 맑음 - 윤하</h4>
        </div>
        <div class="card-stats">
          <div class="stat">
            <div class="value">4</div>
            <div class="type">win</div>
          </div>
        <div class="stat border">
          <div class="value">96</div>
          <div class="type">lose</div>
        </div>
          <div class="stat">
            <div class="value">4 %</div>
            <div class="type">winrate</div>
          </div>
        </div>
      </div>
      </v-row>
</swiper-slide>
        <swiper-slide>Slide 4</swiper-slide>
        <swiper-slide>Slide 5</swiper-slide>
        <swiper-slide>Slide 6</swiper-slide>
        <swiper-slide>Slide 7</swiper-slide>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
  <!--
    <br />
    <br />
    <h2>노래부르는 버튼 임시 구현</h2>
    <v-btn @click="singingButton()">노래부르기</v-btn>
    <br />waitingRoom
    <br />
    <br />
-->

<!--
     <ul>
        {{allRoomInfo }} array인 경우 key가 반드시 필요하다.
        <li :key="index" v-for="(roomInfo, index) in allRoomInfo">
          방정보: {{roomInfo}}
          <br />
          방번호: {{roomInfo.room_id}}
          <br />
          시청자수: {{roomInfo.watching_num}}
          <br />
          부르는사람수: {{roomInfo.singer_num}}
          <br />

          참가자1: {{roomInfo.user1}}
          <br />
          참가자 선곡: {{roomInfo.music1}}
          <br />
          참가자 등급: {{roomInfo.user1_grade}}
          <br />
          참가자 승률: {{roomInfo.user1_win_rate}}
          <br />

          참가자2: {{roomInfo.user2}}
          <br />
          참가자2 선곡: {{roomInfo.music2}}
          <br />
          참가자2 등급: {{roomInfo.user2_grade}}
          <br />
          참가자2 승률: {{roomInfo.user2_win_rate}}
          <br />

          <v-btn @click="watchingButton(roomInfo.room_id, $event)">시청하기</v-btn>
        </li>
      </ul>
-->
    <p>대기인원: {{ waitingNumofPeople }}</p>
  </div>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import router from "@/routes";
import axios from "axios";

var swiper = null;

var waitingNumofPeople = 0;
export default {
  name: "WaitingRoom",
  components: {
  },
  data() {
    return {
      allRoomInfo: [],
      // waitingNumofPeople: 0
      swiperOption: {
          effect: 'coverflow',
          grabCursor: true,
          centeredSlides: true,
          slidesPerView: 'auto',
          coverflowEffect: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows : true
          },
          pagination: {
            el: '.swiper-pagination'
          }
        },
    };
  },
  methods: {
    singingButton() {
      const userid = this.$session.get("userId");
      console.log(userid);
      this.$store.dispatch("changeToSinger", 1);
      axios
        .put("http://70.12.247.115:8080/Enter_room/" + userid)
        .then(response => {
          switch (response.data.room_id) {
            case 1:
              this.$router.push("/firstGameRoom"); // firstGameRoom
              break;
            case 2:
              this.$router.push("/secondGameRoom");
              break;
            case 3:
              this.$router.push("/thirdGameRoom");
              break;
            case 4:
              this.$router.push("/fourthGameRoom");
              break;
          }
        })
        .catch(e => {
          console.log("error: ", e);
          alert('모든 방이 차있습니다.')
        });
    },
    // 시청하기를 누르면 해당 방으로 이동
    watchingButton(room_id) {
      const userid = this.$session.get("userId");
      const userNickname = this.$session.get("userNickname");
      console.log(userid, userNickname);

      let data = {
        userid,
        room_id,
        vote: 0
      };
      this.$store.dispatch("changeToWatcher", 2);
      axios
        .post("http://70.12.247.115:8080/Insert_watching", data)
        .then(response => {
          console.log(response);
          // 해당 room으로 이동
          switch (room_id) {
            case 1:
              this.$router.push("/firstGameRoom"); // firstGameRoom
              break;
            case 2:
              this.$router.push("/secondGameRoom");
              break;
            case 3:
              this.$router.push("/thirdGameRoom.vue");
              break;
            case 4:
              this.$router.push("/fourthGameRoom.vue");
              break;
          }
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
    console.log(userid)
    axios
      .post("http://70.12.247.115:8080/Insert_waiting/" + userid)
      .then(response => {
        for (let i = 0; i < 4; i++) {
          this.allRoomInfo.push(response.data.roomViewInfo[i]);
        }
        // 대기인원수
        this.waitingNumofPeople = response.data.waitingList.length;
        console.log(this.waitingNumofPeople);
      })
      .catch(e => {
        console.log("error: ", e);
      });
    return this.allRoomInfo;
  }

  // viewMypage
};
</script>
<style scoped>

.card {
  display: grid;
  grid-template-columns: 100%;
  grid-template-rows: 40vh 8vw 4vw;
  grid-template-areas: "image" "text" "stats";
  border-radius: 1rem;
  background: white;
  box-shadow: 5px 5px 15px rgba(0,0,0,0.9);
  font-family: roboto;
  text-align: center;
  transition: 0.5s ease;
  cursor: pointer;
  min-width:20vw;
}

.marginL {
  margin-left:1em;
}

.marginR{
  margin-right:1em;
}

.card-image {
  grid-area: image;
}
.card-text {
  grid-area: text;
}
.card-image {
  grid-area: image;
  background: url("../../assets/images/bg_solo2.png");
  border-top-left-radius: 1rem;
  border-top-right-radius: 1rem;
  background-size: cover;
}

.card-text {
  grid-area: text;
  margin: 0.5rem;
}
.card-text .rank {
  color: rgb(255, 7, 110);
  font-size:0.9rem;
}
.card-text h2 {
  margin-top:0px;
  font-size:1.6rem;
}

.card-text h4 {
  margin-top:0.8rem;
  font-size:0.9rem;
  overflow:hidden;
}

.card-stats {
  font-size:0.85rem;
  font-weight:500;
  grid-area: stats; 
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr;
  border-bottom-left-radius: 1rem;
  border-bottom-right-radius: 1rem;
  background: rgb(255, 7, 110);
}

.card-stats .stat {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: white;
  padding:1rem;
}
/*
.card:hover {
  transform: scale(1.15);
  box-shadow: 5px 5px 15px rgba(0,0,0,0.6);
}
*/
#waitingRooms{
    margin:0;
    padding:0;
    display:flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-image:url("../../assets/images/see_bg70.png");
    font-family:'Poppins',sans-serif;
}

  .swiper-inner {
    width: 100%;
    height: 400px;
  }
  .swiper-slide {
    background-position: center;
    background-size: center;
    width: auto;
    padding-top:6vh;
    height: 76vh;
  }
  #vs_img{
    position:fixed;
    margin-top:41%;
    margin-left:43%;
  }
  #swiper_row{
  transition: 0.5s ease;
  width:auto !important;
  }
  #swiper_row:hover{
  transform: scale(1.15);
  box-shadow: 5px 5px 15px rgba(0,0,0,0);
  }
</style>