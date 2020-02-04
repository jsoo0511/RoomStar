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
//   created() {
//     let store = this.$store;
//     const userId = this.$session.get("userId");
//     console.log("userId : "+userId);
//     axios
//       .post("http://70.12.247.115:8080/Insert_waiting",{id:userId})
//       .then(response => {
//         console.log("success2: ", response);
//         //const userid = response.data.user_info.userid;
//         const room_id = response.data.roomViewInfo.room_id;
//         const user1 = response.data.roomViewInfo.user1;
//         const user2 = response.data.roomViewInfo.user2;
//         const music1 = response.data.roomViewInfo.music1;
//         const music2 = response.data.roomViewInfo.music2;
//         const watching_num = response.data.roomViewInfo.watching_num;

//         //this.userid = userid;
//         this.room_id = room_id;
//         this.user1 = user1;
//         this.user2 = user2;
//         this.music1 = music1;
//         this.music2 = music2;
//         this.watching_num = watching_num;

//         //console.log(userid);
//         console.log(room_id);
//         console.log(user1);
//         console.log(user2);
//       })
//       .catch(e => {
//         console.log("error: ", e);
//       });
//   }

  // viewMypage
};
</script>