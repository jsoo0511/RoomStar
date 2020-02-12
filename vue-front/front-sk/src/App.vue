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
    Footer
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

  // computed: {
  //   ...mapState(["isUser", "token"]),
  //   ...mapGetters(["options", "userId", "getIsUser", "getToken"])
  // },
  // methods: {
  //   checkLoggedIn() {
  //     // 1. 세션을 시작해서
  //     this.$session.start();
  //     const token = this.$session.get("jwt");
  //     console.log("--------------", token);
  //     // JWT 에 담겨있는 user_id 값을 추출
  //     const userId = jwtDecode(token).user_id;
  //     // JWT 를 axios 요청에 담아서 보낼 옵션을 정의
  //     const options = {
  //       headers: {
  //         token: "JWT " + token
  //       }
  //     };
  //     // axios.get(URL, 옵션)
  //     axios
  //       .post("http://localhost:8080/info", options)
  //       .then(response => {
  //         console.log("http://localhost:8080/info", options);
  //       })
  //       .catch(error => {
  //         console.error(error);
  //       });
  //     // 2. 'jwt' 가 있는지 확인하겠다.
  //     if (!this.$session.has("jwt")) {
  //       // 로그인 페이지로 보내겠다.
  //       router.push("/login");
  //     }
  //   }
  // },
  // mounted() {
  //   console.log("----------", this.isUser);
  //   if (this.isUser) {
  //     this.checkLoggedIn();
  //   }
  // },
  // watch() {
  //   console.log("----------", this.isUser);
  //   if (this.isUser) {
  //     this.checkLoggedIn();
  //   }
  // },
  // created() {
  //   this.component = this;
  // }
};
</script>

<style>
video {
  width: 100%;
  height: 100%;
}
</style>