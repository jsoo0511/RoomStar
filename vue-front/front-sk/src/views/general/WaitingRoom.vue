<template>
  <div id="waitingRoom">
    waitingRoom
    


  </div>
</template>

<script>

import router from "@/routes";
import axios from "axios";

export default {
  name: "WaitingRoom",
  components: {},
  data() {
    return {
        대기명단:"",
        userid: "",
        room_id: "",
        singer_num: "",
        user1: "",
        user2: "",
        music1: "",
        music2: "",
        watching_num: "",
        대기인원:"",
    };
  },
  methods: {},
  created() {
    let store = this.$store;
    const userId = this.$session.get("userId");
    axios
      .post("http://70.12.247.115:8080/Insert_waiting/"+ userId)
      .then(response => {
        console.log("success2: ", response);
        const id = response.data.user_info.userid;
        console.log(id);
        const nick = response.data.user_info.nickname;
        const profile = response.data.user_iidnfo.profileimg;
        const email = response.data.user_info.email;
        const provider = response.data.user_info.provider;
        const grade = response.data.user_info.grade;
        const game = response.data.user_info.game;
        const win = response.data.user_info.win;
        const lose = response.data.user_info.lose;
        const winrate = response.data.user_info.win_rate;

        this.id = id;
        console.log(this.id);
        this.nick = nick;
        this.profile = profile;
        this.email = email;
        this.provider = provider;
        this.grade = grade;
        this.game = game;
        this.win = win;
        this.lose = lose;
        this.winrate = winrate;
      })
      .catch(e => {
        console.log("error: ", e);
      });
  }

  // viewMypage
};
</script>