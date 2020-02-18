<template>
  <div>
    <div>득표 수 : {{vote}}</div>
    <v-btn type="submit" @click="plus(vote)" color="rgba(0,0,0,0)" elevation="0"><v-icon color="primary">mdi-thumb-up</v-icon></v-btn>
    <v-btn type="submit" @click="minus(vote)" color="rgba(0,0,0,0)" elevation="0"><v-icon color="primary">mdi-thumb-up-outline</v-icon></v-btn>
  </div>
</template>

<script>
// socket
import io from "socket.io-client";
import net from "net";

export default {
  props: ['socket'],
  data() {
    return { vote: 0 };
  },
  computed: {},
  methods: {
    plus: function(data) {
      data += 1;
      console.log("plus", data);
      this.socket.emit("SEND_VOTE_SELECTED", { data });
      console.log("데이터 선택========", data);
    },
    minus: function(data) {
      data -= 1;
      console.log("minus", data);
      this.socket.emit("SEND_VOTE_SELECTED", { data });
      console.log("데이터 선택========", data);
    }
  },
  mounted() {
    //서버의 변경사항을 수신
    this.socket.on("GET_VOTE_SELECTED", data => {
      console.log('GET_VOTE_SELECTED')
      console.log(this.vote)
      this.vote = data["data"];
    });
  }
};
</script>