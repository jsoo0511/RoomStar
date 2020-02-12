<template>
  <div>
    <br />
    <br />
    <br />
    <v-btn @click="backButton">나가기</v-btn>
    유저상태
    {{this.singerOrWatcherStatus}}
    <br />
    {{this.roomInfo}}
    <br />
    {{ this.roomInfo.data.data[0].room_id }}
    <br />
    {{ this.roomInfo.data.data[0].singer_num }}
    <br />
    부르는사람1
    {{ this.roomInfo.data.data[0].user1 }}
    <br />
    부르는사람2
    {{ this.roomInfo.data.data[0].user2 }}
    <br />
    노래1
    {{ this.roomInfo.data.data[0].music1 }}
    <br />
    노래2
    {{ this.roomInfo.data.data[0].music2 }}
    <br />
    {{ this.roomInfo.data.data[0].watching_num }}
    <hr />

    <p>노래선곡하는곳</p>

    <div class="msg-form">
      msf-form
      <br />
      <button type="submit">
        입력
        <input type="text" />
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/routes";
import { mapState, mapGetters, mapActions } from "vuex";

export default {
  data() {
    return { room_id: 1, roomInfo: "", userState: 0 };
  },
  computed: {
    ...mapState(["singerOrWatcherStatus", "token"])
  },
  methods: {
    backButton() {
      // http://localhost:8080/Out_room/

      // put path variable 보내기
      // 나갈때 user정보 변경 요청
      // 안나가진다!
      // 들어갈때부터 누가 singer고, 누가 watcher인지 확인해서 room db 구성필요
      const userid = this.$session.get("userId");
      let params = {
        userid,
        room_id: 1,
        vote: 0
      };
      console.log(params);

      console.log("router 정보", this.$router);
      switch (this.singerOrWatcherStatus) {
        case 1:
          console.log(userid);
          axios
            .put("http://70.12.247.115:8080/Out_room/" + userid)
            .then(response => {
              console.log(response);
              this.$router.push("/");
            })
            .catch(e => {
              console.log("error: ", e);
            });
          break;
        case 2:
          console.log(this.$router);

          axios
            .put("http://70.12.247.115:8080/Delete_watching/", params)
            .then(response => {
              console.log(response);
              this.$router.push("/");
            })
            .catch(e => {
              console.log("error: ", e);
            });
          break;
        default:
          this.$router.push("/");
          break;
      }
    }
  },

  created() {
    console.log(this.$store.state);
    console.log("---store----", this.singerOrWatcherStatus);
    axios
      .get("http://70.12.247.115:8080/get_room_by_room_id/" + this.room_id)
      .then(response => {
        console.log(response);
        this.roomInfo = response;
      })
      .catch(error => {
        console.error(error);
      });
  }

  // 방정보 받아오는 로직

  // 처음 create 됐을때 방 정보를 가져오고,
  // 방 정보에서
  //http://70.12.247.115:8080/get_room_by_room_id/2
  //get
};
</script>


<style scoped>
div {
  border-radius: 5px;
  border: 3px dashed #bcbcbc;
}
.msg-form {
  left: 0;
  right: 0;
  background: greenyellow;
}
</style>