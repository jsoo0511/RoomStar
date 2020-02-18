<template>
  <v-app>
    <Header />
    <router-view />
    <Footer />
  </v-app>
</template>
<script>
import Header from "./views/general/Header.vue";
import Footer from "./views/general/Footer.vue";
import { mapState, mapGetters } from "vuex";
import axios from "axios";
import jwtDecode from "jwt-decode"; // JWT 의 payload 값을 해석해서 보여주는 library
import router from "@/routes";

export default {
  name: "app",
  components: {
    Header,
    Footer,
  },
  computed: {
    ...mapState(["isUser", "token", "userId", "userNickname"])
  },
  mounted() {
    if (this.$session.has("jwt")) {
      // JWT가 존재하면 세션에서 정보를 꺼내 다시 추가
      const token = this.$session.get("jwt");
      const isUser = this.$session.get("isUser");
      console.log(isUser);
      const userId = this.$session.get("userId");
      console.log(userId);
      const userNickname = this.$session.get("userNickname");

      let toStore = {
        one: isUser,
        two: token,
        three: userId,
        four: userNickname
      };
      this.$store.dispatch("checkLogin", token);
      this.$store.dispatch("login", toStore);
    }
  }
};
</script>
