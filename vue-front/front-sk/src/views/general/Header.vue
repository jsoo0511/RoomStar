<template>
  <div>
    <v-app-bar fixed color="rgba(0,0,0,0)" dark elevation="0">
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>
        <router-link to="/">RoomSTAR</router-link>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <!-- 로그인 된 상태이면 true 값이므로 로그아웃을 보여준다 -->
      <v-toolbar-title v-if="this.isUser === true">
        <button @click='headerLogout()' style="color:black">로그아웃</button>

        <router-link to="/waitingRoom/">대기실</router-link>
        <router-link to="/mypage/">마이페이지</router-link>
        <router-link to="/gameRoom/">게임룸</router-link>
      </v-toolbar-title>

      <!-- 로그인 된 상태이면 false 값이므로 로그인을 보여준다 -->
      <v-toolbar-title v-if="this.isUser === false">
        <router-link to="/login/">로그인</router-link>
      </v-toolbar-title>

      <v-menu left bottom>
        <template v-slot:activator="{ on }">
          <v-btn icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="n in 5" :key="n" @click="() => {}">
            <v-list-item-title>Option {{ n }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>

<script>
// mapState가 Store에 있는 애들을 바로 가져올 수 있음
import { mapState, mapGetters, mapActions } from "vuex";
import axios from "axios";
import jwtDecode from "jwt-decode"; // JWT 의 payload 값을 해석해서 보여주는 library
import router from "@/routes";


export default {
  computed: {
    ...mapState(["isUser", "token"]),
    ...mapGetters(["options", "userId", "getIsUser", "getToken"]),
  },

  methods: {
    headerLogout(){
      this.$store.dispatch("logout")
      this.$router.push('/')
    },

    checkLoggedIn() {
      // 1. 세션을 시작해서
      this.$session.start();
      const token = this.$session.get("jwt");
      console.log("--------------", token);
      // JWT 에 담겨있는 user_id 값을 추출
      const userId = jwtDecode(token).user_id;
      // JWT 를 axios 요청에 담아서 보낼 옵션을 정의
      const options = {
        headers: {
          token: "JWT " + token
        }
      };
      // axios.get(URL, 옵션)
      axios
        .post("http://localhost:8080/info", options)
        .then(response => {
          // Django 에서 응답받은 todo 목록을 todos 데이터에 할당
          console.log("http://localhost:8080/info", options);
        })
        .catch(error => {
          console.error(error);
        });
      // 2. 'jwt' 가 있는지 확인하겠다.
      if (!this.$session.has("jwt")) {
        // 로그인 페이지로 보내겠다.
        router.push("/login");
      }
    }
  },

  mounted() {
    if (this.isUser) {
      this.checkLoggedIn();
    }
  },
  watch() {
    if (this.isUser) {
      this.checkLoggedIn();
    }
  }
};
</script>
 
<style type="text/css">
a {
  color: white !important;
  text-decoration: none !important;

}
a:link {
  color: white;
  text-decoration: none;
}
a:visited {
  color: white;
  text-decoration: none;
}
a:hover {
  color: white;
  text-decoration: none;
}
</style>