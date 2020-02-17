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
            v-model="textarea"
            disabled
            v-auto-scroll-bottom
            style="height:100vh; width:100vw; border: 4px"
          ></textarea>
        </field>
        <field>
          <br />
          <label>Your Message</label>

          <input v-model="message" />
          <button type="submit" class="md-primary md-raised" @click="sendMessage()">Submit</button>
        </field>
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
  padding-top:30vh;
}
.md-app {
  height: 400px;
  border: 1px solid rgba(#000, 0.12);
}
</style>
