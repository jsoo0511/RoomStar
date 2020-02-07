<template>
  <div id="waitingRoom">
    waitingRoom
    <br />
    <br />
    <section>
      <ul>
        <!-- {{allRoomInfo }} array인 경우 key가 반드시 필요하다.-->
        <li :key="index" v-for="(roomInfo, index) in allRoomInfo">
          방정보: {{roomInfo}} <br>
          방번호:  {{roomInfo.room_id}}<br>
          시청자수: {{roomInfo.watching_num}}<br>
          부르는사람수: {{roomInfo.singer_num}}<br>
          
          참가자1: {{roomInfo.user1}}<br>
          참가자 선곡: {{roomInfo.music1}}<br>
          참가자 등급: {{roomInfo.user1_grade}}<br>
          참가자 승률: {{roomInfo.user1_win_rate}}<br>
          
          참가자1: {{roomInfo.user2}}<br>
          참가자 선곡: {{roomInfo.music2}}<br>
          참가자 등급: {{roomInfo.user2_grade}}<br>
          참가자 승률: {{roomInfo.user2_win_rate}}<br>
          

          <v-btn v-if="roomInfo.singer_num < 2" @click="SingingButton">노래부르기</v-btn>
          
          
          <v-btn @click="WatchingButton">시청하기</v-btn>



        </li>
        
      
      </ul>
    </section>

    <p>대기인원: {{ waitingNumofPeople }}</p>
  </div>
</template>

<script>
import router from "@/routes";
import axios from "axios";

var waitingNumofPeople = 0

export default {
  name: "WaitingRoom",
  components: {},
  data () {
    return {
      allRoomInfo: [],
      // waitingNumofPeople: 0
    };
  },
  methods: {
    WatchingButton(){
      const userId = this.$session.get("userId");
      const userNickname = this.$session.get("userNickname");
      console.log(userId, userNickname)
      let data = {
        userid : userId

      }

      axios.post("http://localhost:8080/Insert_watching/")
      // http://localhost:8080/Insert_watching
      // 이건 넘겨줄때 userid랑 들어가는 room_id랑 vote(투표1,2에 했는지 유무)값 requestbody로 묶어서 보내주면돼
      // post 
      // vote는 그냥 0으로 default한다음 묶어서 보내줘

    
    },
    SingingButton(){
      // http://localhost:8080/Insert_watching
      //
    }


  },

  created() {
    
    // 유저가 처음 대기방에 들어왔을때 얻을 수 있는 방들의 정보
    let store = this.$store;
    const userid = this.$session.get("userId");
    console.log("userId : " + userid);
    axios
      .post("http://70.12.247.115:8080/Insert_waiting/" + userid)
      .then(response => {
        for (let i = 0; i < 5; i++) {
          console.log(response.data.roomViewInfo[i]);
          this.allRoomInfo.push(response.data.roomViewInfo[i]);
        }
        // 대기인원수 
        this.waitingNumofPeople = response.data.waitingList.length;
        console.log(this.waitingNumofPeople);
      })
      .catch(e => {
        console.log("error: ", e);
      });
    return this.allRoomInfo
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
}
</style>