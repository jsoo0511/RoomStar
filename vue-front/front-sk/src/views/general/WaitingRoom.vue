<template>
  <div id="waitingRoom">
    <br />
    <br />
    <h2>노래부르는 버튼 임시 구현</h2>
    <v-btn @click="singingButton()">노래부르기</v-btn>
    <br />waitingRoom
    <br />
    <br />

    <div class="swiper-container">
        <div class="swiper-wrapper">

            <div class="swiper-slide">
                <div class="card">
                    <div class="sliderText">
                        <h3>Slider One</h3>
                    </div>
                    <div class="content">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat esse quibusdam, illo perspiciatis placeat magni dolorum consequatur libero temporibus eos vitae ipsum iure, architecto molestias, voluptas numquam repellendus deserunt voluptatum!</p>
                        <a href="#">Read More</a>
                    </div>        
                </div>
            </div>
            <div class="swiper-slide">
                <div class="card">
                    <div class="sliderText">
                        <h3>Slider Two</h3>
                    </div>
                    <div class="content">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat esse quibusdam, illo perspiciatis placeat magni dolorum consequatur libero temporibus eos vitae ipsum iure, architecto molestias, voluptas numquam repellendus deserunt voluptatum!</p>
                        <a href="#">Read More</a>
                    </div>        
                </div>
            </div>
            <div class="swiper-slide">
                <div class="card">
                    <div class="sliderText">
                        <h3>Slider Three</h3>
                    </div>
                    <div class="content">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat esse quibusdam, illo perspiciatis placeat magni dolorum consequatur libero temporibus eos vitae ipsum iure, architecto molestias, voluptas numquam repellendus deserunt voluptatum!</p>
                        <a href="#">Read More</a>
                    </div>        
                </div>
            </div>
            <div class="swiper-slide">
                <div class="card">
                    <div class="sliderText">
                        <h3>Slider Three</h3>
                    </div>
                    <div class="content">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat esse quibusdam, illo perspiciatis placeat magni dolorum consequatur libero temporibus eos vitae ipsum iure, architecto molestias, voluptas numquam repellendus deserunt voluptatum!</p>
                        <a href="#">Read More</a>
                    </div>        
                </div>
            </div>
            


        </div>
    </div>


  <div class="card">
  <div class="card-image"></div>
  <div class="card-text">
    <span class="date">4 days ago</span>
    <h2>Post One</h2>
    <p>Lorem ipsum dolor sit amet consectetur, Ducimus, repudiandae temporibus omnis illum maxime quod deserunt eligendi dolor</p>
  </div>
  <div class="card-stats">
    <div class="stat">
      <div class="value">4<sup>m</sup></div>
      <div class="type">read</div>
    </div>
    <div class="stat border">
      <div class="value">5123</div>
      <div class="type">views</div>
    </div>
    <div class="stat">
      <div class="value">32</div>
      <div class="type">comments</div>
    </div>
  </div>
</div>

     <ul>
        <!-- {{allRoomInfo }} array인 경우 key가 반드시 필요하다.-->
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
          <!-- <hr> -->
        </li>
      </ul>

    <p>대기인원: {{ waitingNumofPeople }}</p>
  </div>
</template>

<script src="swiper.min.js"></script>
<script>
import router from "@/routes";
import axios from "axios";

var waitingNumofPeople = 0;

// var swiper = new Swiper('.swiper-container', {
//   effect: 'coverflow',
//   grabCursor: true,
//   centeredSlides: true,
//   slidesPerView: 'auto',
//   coverflowEffect: {
//     rotate: 30,
//     stretch: 0,
//     depth: 500,
//     modifier: 1,
//     slideShadows : true,
//   },
//   pagination: {
//     el: '.swiper-pagination',
//   },
// });

export default {
  name: "WaitingRoom",
  components: {},
  data() {
    return {
      allRoomInfo: []
      // waitingNumofPeople: 0
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

ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0;
  text-align: left;
}

li {
  display: flex;
  min-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background: white;
  border-radius: 5px;
  border: 3px dashed #bcbcbc;
}

.card {
  display: grid;
  grid-template-columns: 300px;
  grid-template-rows: 210px 210px 80px;
  grid-template-areas: "image" "text" "stats";
  border-radius: 18px;
  background: white;
  box-shadow: 5px 5px 15px rgba(0,0,0,0.9);
  font-family: roboto;
  text-align: center;
    transition: 0.5s ease;
  cursor: pointer;
  margin:30px;
}

.card-image {
  grid-area: image;
}
.card-text {
  grid-area: text;
}
.card-stats {
  grid-area: stats; 
}
.card-image {
  grid-area: image;
  background: url("../../assets/images/bg_solo.png");
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  background-size: cover;
}

.card-text {
  grid-area: text;
  margin: 25px;
}
.card-text .date {
  color: rgb(255, 7, 110);
  font-size:13px;
}
.card-text p {
  color: grey;
  font-size:15px;
  font-weight: 300;
}
.card-text h2 {
  margin-top:0px;
  font-size:28px;
}

.card-stats {
  grid-area: stats; 
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  background: rgb(255, 7, 110);
}

.card-stats .stat {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: white;
  padding:10px;
}

.card:hover {
  transform: scale(1.15);
  box-shadow: 5px 5px 15px rgba(0,0,0,0.6);
}

@import url('https://fonts.googleapis.com/css?family=Poppins:300,400,600,700');

body{
    margin:0;
    padding:0;
    display:flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background: #383838;
    font-family:'Poppins',sans-serif;
}

.card
{
    position:relative;
    background:#fff;
    width:400px;
    height:500px;
    margin:0 auto;
}
.card .content
{
    width:400px;
    padding:30px;
    box-sizing:border-box;
}

.card .content a{
    display:inline-block;
    margin:10px 0 0;
    padding:10px 20px;
    text-decoration:none;
    border:2px solid #000;
    color: #262626;
    font-weight:600;

}

.card .sliderText{
    position:relative;
    width:100%;
    height:200px;
    display:flex;
    justify-content: center;
    align-items: center;
    background:#000;
} 

.card .sliderText h3{
    color:#fff;
    font-size:3em;
} 

.swiper-slide{
    width:400px;
}
.swiper-slide:nth-child(1) .sliderText
{
    background: #2196f3;
}
.swiper-slide:nth-child(2) .sliderText
{
    background: #e91e63;
}
.swiper-slide:nth-child(3) .sliderText
{
    background: #c3d41a;
}
.swiper-slide:nth-child(4) .sliderText
{
    background: #ff9800;
}
</style>