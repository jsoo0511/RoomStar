<template>
  <div>
    <div>득표 수 : {{vote}}</div>
    <v-btn id="thumb" type="submit" @click="plus(vote)" color="rgba(0,0,0,0)" elevation="0"><v-icon color="primary">mdi-thumb-up-outline</v-icon></v-btn>
    <!-- <v-btn type="submit" @click="minus(vote)" color="rgba(0,0,0,0)" elevation="0"><v-icon color="primary">mdi-thumb-up-outline</v-icon></v-btn> -->
  </div>
</template>

<script>
// socket
import io from "socket.io-client";
import net from "net";
import axios from "axios";

export default {
  props: ['socket'],
  data() {
    return { 
      vote: 0,
      finish: false,
      roomId: ""
      };
  },
  computed: {},
  methods: {
    plus: function(data) {
      let voteIcon = document.getElementById("thumb");
      const SERVER_IP = "";

      if (!this.finish){
        data += 1;
        //voteIcon.disabled=true;
        this.finish = true;
        console.log("plus", data);
        
      }else{
        data -= 1;
        //voteIcon.disabled = false;
        this.finish = false;
        console.log("minus", data);
        

      }
      axios
          .put(SERVER_IP, {player_idx:0, data:data})
          .then(response => {
            this.$router.push("/");
          })
          .catch(error => {
            console.log(error);
            // this.loading = false;
          });
        this.socket.emit("VOTE_SELECTED", { 
          vote : data,
          player_idx : 0
        });
    }
  },
  created(){

     axios
      .get("http://70.12.247.115:8080/투표함수/" + this.roomId)
      .then(response => {
        console.log("success: ", response);
        const vote = response.data.vote1;
        this.vote = vote;
      })
      .catch(e => {
        console.log("error: ", e);
      });
  },
  mounted() {
    //서버의 변경사항을 수신

    this.socket.on("VOTE_SELECTED", data => {
      if (data.player_idx==0){
        this.vote = data.vote;
      console.log('GET_VOTE_SELECTED')
      console.log(this.vote)
      }
    });
  }
};
</script>