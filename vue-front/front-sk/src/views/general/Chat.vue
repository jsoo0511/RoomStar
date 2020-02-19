<template>
  <div class="page-container">
    
    <div class="md-toolbar-row">
    </div>
  
      <textarea
        class="chat_text"
        v-model="textarea"
        disabled
        v-auto-scroll-bottom
      ></textarea>
    
    <v-row>
      <input v-model="message" class="chat_input" placeholder="대화내용을 입력하세요" @keyup.enter="submit()"/>
      <v-btn type="submit" class="md-primary md-raised secondary" @click="sendMessage()">Submit</v-btn>
    </v-row>
    
  </div>
</template> 
<script>
import Vue from 'vue';
import io from "socket.io-client";
import net from "net";
import VueScrollTo from 'vue-scrollto';
import locomotiveScroll from 'locomotive-scroll';
import infiniteScroll from 'vue-infinite-scroll';

Vue.use(infiniteScroll)

Vue.use(VueScrollTo, {
  easing: 'ease-out'
});

const scroll = new locomotiveScroll();

export default {
  name: "Chat",
  props: ['socket'],
  data() {
    return {
      textarea: "",
      message: "",
      userNickname:"",
      userId:""
    };
  },
  created() {
    this.userId = this.$session.get("userId");
    this.userNickname = this.$session.get("userNickname");
    
  },
  mounted() {
    this.socket.on("chat", data => {
      this.textarea += this.userNickname + ":" + data.message + "\n";
    });
  },
  // 그냥 다 왼쪽으로,
  // 아이디로 구분되므로
  methods: {
    sendMessage() {
      this.socket.emit("chat", {
        userNickname: this.userNickname,
        message: this.message
      });
      console.log(this.message);
      this.textarea += "나 : "+this.message + "\n";
      this.message = "";
    }
  }
};
</script> 
<style scoped>
/* .page-container{
} */
.md-app {
  height: 50%;
  border: 1px solid rgba(#000, 0.12);
}
.chat_input{
  width:40vw;
  background-color:white; 
  border-radius: 4px; 
  height:35px;
  margin-left:2.5vw;
}
.chat_text{
  height:35vh; 
  width:100%;
  border: 1px; 
  border-color:red; 
  border-style: none;
  padding:1.5vw 2vw;
  overflow-y: scroll;
  color:#fff;
  font-size:1.5em;
}
</style>
