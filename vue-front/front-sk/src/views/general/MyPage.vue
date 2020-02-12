<template>
  <div id="mypages">
  <v-card
    max-width="375"
    class="mx-auto"
  >
    <v-img
      :src="profile"
      height="300px"
      dark
    >
      <v-row class="fill-height">
        <v-card-title>
          <v-btn dark icon>
            <v-icon>mdi-chevron-left</v-icon>
          </v-btn>

          <v-spacer></v-spacer>

          <!-- 프로필 편집 위해 사용 -->
          <v-btn dark icon class="mr-4" to="/gameRoom/">
            <v-icon >mdi-pencil</v-icon>
          </v-btn>

          <v-btn dark icon>
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </v-card-title>

        <v-spacer></v-spacer>

        <v-card-title class="white--text pl-12 pt-12">
          <div class="display-1 pl-12 pt-12">{{nick}}</div>
        </v-card-title>
      </v-row>
    </v-img>

    <v-list two-line>
      

      <v-list-item v-onclick="">
        <v-list-item-icon>
          <v-icon color="indigo">mdi-email</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{email}}</v-list-item-title>
          <v-list-item-subtitle>Personal</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider inset></v-divider>

      <v-list-item v-onclick="">
        <v-list-item-icon>
          <v-icon color="indigo">mdi-map-marker</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Provider</v-list-item-title>
          <v-list-item-subtitle>{{provider}}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider inset></v-divider>

      <v-list-item v-onclick="">
        <v-list-item-icon>
          <v-icon color="indigo">mdi-map-marker</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Grade</v-list-item-title>
          <v-list-item-subtitle>{{grade}}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider inset></v-divider>

      <v-list-item v-onclick="">
        <v-list-item-icon>
          <v-icon color="indigo">mdi-map-marker</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Win</v-list-item-title>
          <v-list-item-subtitle>{{win}}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider inset></v-divider>

      <v-list-item v-onclick="">
        <v-list-item-icon>
          <v-icon color="indigo">mdi-map-marker</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Lose</v-list-item-title>
          <v-list-item-subtitle>{{lose}}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

       <v-list-item v-onclick="">
        <v-list-item-icon>
          <v-icon color="indigo">mdi-map-marker</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Rate</v-list-item-title>
          <v-list-item-subtitle>{{winrate}}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>




    </v-list>
  </v-card>
  </div>
</template>


<script>
import router from "@/routes";
import axios from "axios";

// let viewMypage =function(response){
//   let userId1 = response.userid;
//   let userNickname1 = response.nickname;
//   let profileImage1 = response.profileimg;
//   let userEmail1 = response.email;
//   let provider=response.provider;
//   let game=response.game;
//   let win=response.win;
//   let lose=response.lose;

//   console.log("-----------");
//   console.log(userId1);
//   console.log("-----------");
// }

export default {
data(){
  return {
    id: "",
        nick: "",
        profile: "",
        email: "",
        provider: "",
        grade: "",
        game: "",
        win: "",
        lose: "",
        winrate: ""
  }
},

  name: "mypage",
  // components: {
  // },

  created() {
    let store = this.$store;
    // 승원 수정
    const userId = this.$session.get("userId");
    axios
      .get("http://70.12.247.115:8080/mypage/" + userId)
      .then(response => {
        console.log("success2: ", response);
        const id = response.data.user_info.userid;
        console.log(id);
        const nick = response.data.user_info.nickname;
        const profile = response.data.user_info.profileimg;
        const email = response.data.user_info.email;
        const provider = response.data.user_info.provider;
        const grade = response.data.user_info.grade;
        const game = response.data.user_info.game;
        const win = response.data.user_info.win;
        const lose = response.data.user_info.lose;
        const winrate = response.data.user_info.win_rate;

        this.id = id;
        console.log(this.id);
        this.nick = nick;
        this.profile = profile;
        this.email = email;
        this.provider = provider;
        this.grade = grade;
        this.game = game;
        this.win = win;
        this.lose = lose;
        this.winrate = winrate;
      })
      .catch(e => {
        console.log("error: ", e);
      });
  }

  // viewMypage
};
</script>

<style scoped>
  #mypages{
    margin-top:70px !important;
    padding-top:50px !important;
  }
</style>