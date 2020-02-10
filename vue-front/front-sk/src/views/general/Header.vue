<template>
  <div>
    <v-app-bar
    fixed
    hide-on-scroll
      color="rgba(0,0,0,0)"
      dark
      elevation="0"
      v-if="this.isUser === true"
    >
    <v-app-bar-nav-icon 
          @click.stop="drawer = !drawer">
    </v-app-bar-nav-icon>
    <v-spacer></v-spacer>
    <v-toolbar-title v-if="this.isUser === true" style="margin-right:10px;">
        <button @click="headerLogout()" style="color:white">로그아웃</button>
      </v-toolbar-title>
    </v-app-bar>

    <v-app-bar fixed color="rgba(0,0,0,0)" dark elevation="0" v-if="this.isUser === false" 
      hide-on-scroll>
      <!-- 로그인 된 상태이면 false 값이므로 로그인을 보여준다 -->
      <v-spacer></v-spacer>
      <v-toolbar-title  style="margin-right:10px;">
        <ModalLogin />

      </v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>



    <v-navigation-drawer
     v-model="drawer"
        :color="color"
        :expand-on-hover="expandOnHover"
        :mini-variant="miniVariant"
        :src="bg"
        absolute
        temporary
        dark
        floating
      >
        <v-list
          dense
          nav
        >
          <v-list-item two-line :class="miniVariant && 'px-0'">
            <v-list-item-avatar>
              <!-- <img src="https://randomuser.me/api/portraits/men/81.jpg"> -->
              <img :src="this.profile">
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>Application</v-list-item-title>
              <v-list-item-subtitle>Subtext</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-list-item
            v-for="item in items"
            :key="item.title"
            :to="item.links"
            link
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
    </v-navigation-drawer>



    
  </div>
</template>

<script>
// mapState가 Store에 있는 애들을 바로 가져올 수 있음
import { mapState, mapGetters, mapActions } from "vuex";
import axios from "axios";
import jwtDecode from "jwt-decode"; // JWT 의 payload 값을 해석해서 보여주는 library
import router from "@/routes";
import ModalLogin from "../user/ModalLogin.vue";
// import HeaderDrawer from "./HeaderDrawer.vue";

export default {
  data() {
    return {
        drawer: false,
        items: [
          { title: '마이페이지', icon: 'mdi-view-dashboard',links : '/mypage/' },
          { title: '노래 배틀하기', icon: 'mdi-image',links : '/gameRoom/' },
          { title: '배틀 시청하기', icon: 'mdi-help-box',links : '/waitingRoom/' },
          { title: '노래 자랑하기', icon: 'mdi-view-dashboard', links : '/SharingPage/' },
        ],
        color: 'rgba(253,208,23,0.8)',
        miniVariant: false,
        expandOnHover: false,
        background: false,
        profile: '',
    };
  },
  computed: {
    ...mapState(["isUser", "token"]),
    ...mapGetters(["options", "userId", "getIsUser", "getToken"]),
    bg () {
        return this.background ? 'https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg' : undefined
      },
  },
  components: {
    ModalLogin
    // HeaderDrawer
  },

  methods: {
    headerLogout() {
      this.$session.clear();
      this.$session.destroy();
      this.$store.dispatch("logout");
      this.$router.push("/");
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


    // 프로필사진로딩하거나 그냥 SESSION에 저장해서 저장한 URL을 올리자
    // 근데 값이 자주 바뀔 수도 있으니까 그냥 MOUNTED? CREATED하는게 좋을 것 같음
    const userId = this.$session.get("userId");
    axios
      .get("http://70.12.247.115:8080/mypage/" + userId)
      .then(response => {
        console.log("success2: ", response);
        const email = response.data.user_info.email;
        const nick = response.data.user_info.nickname;
        const grade = response.data.user_info.grade;
        const game = response.data.user_info.game;
        const win = response.data.user_info.win;
        const lose = response.data.user_info.lose;
        const winrate = response.data.user_info.win_rate;

        this.profile = response.data.user_info.profileimg;



        
      })
      .catch(e => {
        console.log("error: ", e);
      });
    }
  },

  watch() {
    if (this.isUser) {
      this.checkLoggedIn();
    }
  }
};
</script>
 
<style scoped type="text/css">
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