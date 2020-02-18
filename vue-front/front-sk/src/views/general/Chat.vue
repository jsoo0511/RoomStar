<template>
  <div class="page-container">
    <app>
      <app-toolbar class="md-primary">
        <div class="md-toolbar-row">
        </div>
      </app-toolbar>
      <app-content>
        <field>
          <textarea
            class="chat_text"
            v-model="textarea"
            disabled
            v-auto-scroll-bottom
          ></textarea>
        </field>
        <v-row>
          <input v-model="message" class="chat_input" />
          <v-btn type="submit" class="md-primary md-raised secondary" @click="sendMessage()">Submit</v-btn>
        </v-row>
      </app-content>
    </app>
  </div>
</template> 
<script>
import io from "socket.io-client";
import net from "net";
export default {
  name: "Chat",
  data() {
    return {
      textarea: "",
      message: ""
    };
  },
  created() {
    const userId = this.$session.get("userId");
    const userNickname = this.$session.get("userNickname");
    this.socket = io.connect("http://localhost:8082", {
      transports: ["websocket"]
    });
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
        message: this.message
      });
      console.log(this.message);
      this.textarea += this.message + "\n";
      this.message = "";
    }
  }
};
</script> 
<style scoped>
.page-container{
}
.md-app {
  height: 50%;
  border: 1px solid rgba(#000, 0.12);
}
.chat_input{
  width:91vw;
  background-color:white; 
  border-radius: 4px; 
  height:35px;
  margin-right:5px;
  margin-left:1.5vw;
}
.chat_text{
  height:35vh; 
  width:100vw; 
  border: 1px; 
  border-color:red; 
  border-style: none;
  padding:1.5vw 2vw;
  overflow-y: scroll;
}
</style>
