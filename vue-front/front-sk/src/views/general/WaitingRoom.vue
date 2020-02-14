<template>
  <div id="waitingRoom">
    <br />
    <br />
    <h2>노래부르는 버튼 임시 구현</h2>
    <v-btn @click="singingButton()">노래부르기</v-btn>
    <br />waitingRoom
    <br />
    <br />
    <section>

  <div class="card-box"
            onmouseenter="hoverCard.handleEnter(this,event)" onmousemove="hoverCard.handleMove(this,event)" onmouseleave="hoverCard.handleLeave(this)">
            <div class="card ani">
                <div class="bg" style="background-image:url('../assets/images/showOff.png')"></div>
                <div class="info">
                    <h1>HOVER ME</h1>
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
    </section>

    <p>대기인원: {{ waitingNumofPeople }}</p>
  </div>
</template>

<script>
import router from "@/routes";
import axios from "axios";

var waitingNumofPeople = 0;

var hoverCard = {
    width: 0,
    height: 0,
    startX:0,
    startY:0,
    mouseX: 0,
    mouseY: 0,
}

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
  mounted:{
   postionX: function () {
        return this.mouseX / this.width;
    },
    positionY: function () {
        return this.mouseY / this.height;
    },
    cardTransform: function (target) {
        var rx = this.postionX() * 30;
        var ry = this.positionY() * -30;
        return target.children[0].style.transform = 'rotateY(' + rx + 'deg)' + ' ' + 'rotateX(' + ry + 'deg)';
    },
    cardBgTransform: function (target) {
        var bx = this.postionX() * -50;
        var by = this.positionY() * -50;
        return target.children[0].children[0].style.backgroundPosition = bx + 'px' + ' ' + by + 'px';
    },
    handleEnter: function (target, e) {
        this.width = target.clientWidth;
        this.height = target.clientHeight;
        this.startX=e.pageX;
        this.startY=e.pageY;
        target.children[0].classList.add('hover');
    },
    handleMove: function (target, e) {
        if(Math.abs(this.startX-e.pageX)>50){
            target.children[0].classList.remove('ani');
        }
        this.mouseX = e.pageX - target.offsetLeft - this.width / 2;
        this.mouseY = e.pageY - target.offsetTop - this.height / 2;
        this.cardTransform(target);
        this.cardBgTransform(target);
    },
    handleLeave: function (target) {
        this.mouseX = 0;
        this.mouseY = 0;
        target.children[0].classList.add('ani');
        target.children[0].classList.remove('hover');
        this.cardTransform(target);
        this.cardBgTransform(target);
    },


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
<script>

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

.card-box {
    margin: 10px;
    -webkit-transform: perspective(1000px);
    transform: perspective(1000px);
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    min-width: 300px;
    max-width: 380px;
}

.card {
    position: relative;
    width: 100%;
    height: 350px;
    overflow: hidden;
    background-color: #000;
    box-shadow: 0px 5px 15px 0px rgba(48, 54, 62, 0.7);
    border-radius: 10px;
}
.card.hover{
    transition:box-shadow 0.5s;
}
.card-box .card.hover{
    z-index:10;
    box-shadow: 0px 20px 40px 10px rgba(48, 54, 62, 0.8);
}

.card.ani{
    transition: 0.8s cubic-bezier(0.23, 1, 0.32, 1);
    -webkit-transition: 0.8s cubic-bezier(0.23, 1, 0.32, 1);
}
.card.ani .bg{
    transition: background-position 0.5s cubic-bezier(0.23, 1, 0.32, 1),opacity 0.5s;
    -webkit-transition: background-position 0.5s cubic-bezier(0.23, 1, 0.32, 1),opacity 0.5s;
}

.card.hover .bg{
    opacity: 1;
}
.card .bg {
    position: absolute;
    top: -30px;
    left: -30px;
    width: calc(100% + 60px);
    height: calc(100% + 60px);
    background-repeat: no-repeat;
    background-position: 0 0;
    background-size: cover;
    opacity: 0.7;
    transition:opacity 0.3s;
}


.card .info {
    position: absolute;
    bottom: 20PX;
    right: 20PX;
    transition:all 0.4s ease-out;
}
.card.hover .info{
    text-shadow:rgba(0, 0, 0, 0.5) 0 5px 10px
}
.card .info h1 {
    display: inline-block;
    color: #fff;
    font-size: 20px;
    font-weight: normal;
}
</style>