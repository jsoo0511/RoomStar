<template>
  <div>
    <div id="btn_loc" v-if="finish===false">
      <div id="vote_num">득표 수 : {{vote}}</div>

      <v-btn  @click="plus(vote)" color="rgba(0,0,0,0)" elevation="0"><v-icon color="red">mdi-heart-outline</v-icon></v-btn>
      <!-- <v-btn type="submit" @click="minus(vote)" color="rgba(0,0,0,0)" elevation="0"><v-icon color="primary">mdi-thumb-up-outline</v-icon></v-btn> -->
    </div>
    <div id="btn_loc" v-if="finish===true">
      <div id="vote_num">득표 수 : {{vote}}</div>
      <v-btn @click="plus(vote)" color="rgba(0,0,0,0)" elevation="0"><v-icon color="red">mdi-heart</v-icon></v-btn>
    </div>
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
      roomId: "",
      player_idx: 0
      };
  },
  computed: {},
  methods: {
    plus: function(data) {
      //let voteIcon = document.getElementById("thumb");
     

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
          .put(process.env.VUE_APP_SERVER_IP+"/update_vote/", {room_id: this.roomId, player_idx:this.player_idx, vote_num:data})
          .then(response => {
            //this.$router.push("/");
          })
          .catch(error => {
            console.log(error);
            // this.loading = false;
          });
      this.socket.emit("VOTE_SELECTED", { 
        vote : data,
        player_idx : this.player_idx
      });
    }
  },
  created(){
    // this.roomId = this.$session.roomId;
    this.roomId = 2;
    console.log(this.roomId)
     axios
      .get(process.env.VUE_APP_SERVER_IP+"/get_vote/" + this.roomId)
      .then(response => {
        console.log("success: ", response);
        if (this.player_idx==0){

          this.vote = response.data.data.vote1;
        }else{
          this.vote = response.data.data.vote2;
        }
        console.log("투표수========",this.vote)
      })
      .catch(e => {
        console.log("error: ", e);
      });
  },
  mounted() {
    //서버의 변경사항을 수신

    this.socket.on("VOTE_SELECTED", data => {
      if (data.player_idx==this.player_idx){
        this.vote = data.vote;
      console.log('GET_VOTE_SELECTED')
      console.log(this.vote)
      }
    });
  }
};
</script>

<style scoped>
  #btn_loc{
    margin-left:1vw;
  }

  #vote_num{
    margin-left:0;
    font-size:1.5rem;
    color:rgba(255,255,255);
  }
</style>