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
          참가자1: {{roomInfo.user2}}
          <br />
          참가자 선곡: {{roomInfo.music2}}
          <br />
          참가자 등급: {{roomInfo.user2_grade}}
          <br />
          참가자 승률: {{roomInfo.user2_win_rate}}
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
</style>