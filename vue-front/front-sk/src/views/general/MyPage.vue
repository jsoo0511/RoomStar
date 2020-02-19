<template>
  <div id="mypages">
  <h1 class="mypage_text">마 이 페 이 지</h1>
  <!--
    <v-content>
      <section>
        <v-parallax src="../../assets/images/mypageBg.jpg" height="300">
          <v-layout
            column
            align-center
            justify-center
            class="white--text"
          >
            <h1 class="white--text mb-2 display-1 text-center">마이 페이지</h1>
            <div class="subheading mb-4 text-center"></div>
            
          </v-layout>
        </v-parallax>
      </section>

         <section>
        <v-layout
          column
          wrap
          class="my-12"
          align-center
        >
          <v-flex xs12 sm4 class="my-4">
            <div class="text-center">
              <h2 class="headline">The best way to start developing</h2>
              <span class="subheading">
                Cras facilisis mi vitae nunc
              </span>
            </div>
          </v-flex>
          <v-flex xs12>
            <v-container grid-list-xl>
              <v-layout row wrap align-center>
                <v-flex xs12 md4>
                  <v-card flat class="transparent">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-palette</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-center">Material Design</div>
                    </v-card-title>
                    <v-card-text>
                      Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
                      Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                      Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti.
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="transparent">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-flash</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline">Fast development</div>
                    </v-card-title>
                    <v-card-text>
                      Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
                      Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                      Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti.
                    </v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="transparent">
                    <v-card-text class="text-center">
                      <v-icon x-large class="blue--text text--lighten-2">mdi-wrench</v-icon>
                    </v-card-text>
                    <v-card-title primary-title class="layout justify-center">
                      <div class="headline text-center">Completely Open Sourced</div>
                    </v-card-title>
                    <v-card-text>
                      Cras facilisis mi vitae nunc lobortis pharetra. Nulla volutpat tincidunt ornare.
                      Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                      Nullam in aliquet odio. Aliquam eu est vitae tellus bibendum tincidunt. Suspendisse potenti.
                    </v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
          </v-flex>
        </v-layout>
      </section>
    </v-content> 
-->
  <!-- v-card -->
  <v-container id="full_container" fluid ma-15 pa-0 fill-height>
   <v-row>
    <v-row>
      <v-col cols="12" md="4" sm="4">
          <v-card
            id="profile_card"
            color="rgba(56,95,115,0.85)"
            dark
          >
            <v-img
              :src="profile"
              id="profile_card_image"
            ></v-img>
          </v-card>
        </v-col>

      <v-col cols="12" md="8" xs="4">
        <v-col>
          <v-card
            id="name_card"
            color="rgba(176,168,11,0.9)"
            dark
          >
            <v-card-title class="headline nick_title">{{nick}}</v-card-title>
            <v-card-subtitle class="nick_subtitle">{{provider}}</v-card-subtitle>

          </v-card>
        </v-col>
        <v-col>
          <v-card
            id="basic_card"
            color="rgba(180,40,135,0.7)"
            dark
          >
            <v-card-title class="headline nick_title">{{id}}</v-card-title>
            <v-card-subtitle class="nick_subtitle">유저 id</v-card-subtitle>

          </v-card>
        </v-col>
      </v-col>
    </v-row>
        <v-col cols="12" md="6" xs="4">
          <v-card
            id="name_card"
            color="rgba(47,138,99,0.85)"
            dark
          >
            <v-card-title class="headline rank_title">Bronze V</v-card-title>
            <v-card-subtitle class="rank_subtitle">하위 1%</v-card-subtitle>
            <v-avatar
                class="ma-3"
                size="125"
                tile
             >
            <v-img src="../../assets/images/bronze.png"></v-img>
            </v-avatar>

          </v-card>
        </v-col>
                <v-col cols="12" md="6" xs="4">
          <v-card
            id="name_card"
            color="rgba(56,95,115,0.85)"
            dark
          >
            <v-card-title class="headline winlose_title">{{win}} 승 {{lose}} 패</v-card-title>
            <v-card-subtitle class="winlose_subtitle">{{winrate}} %</v-card-subtitle>

          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import router from "@/routes";
import axios from "axios";
import ModalMyPage from "./ModalMyPage.vue";

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
     items: [
        {
          color: 'rgba(31, 112,135,0.85)',
          src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
          title: '브론즈 V',
        },
        {
          color : 'rgba(149, 33,117,0.85)',
          src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
          title: 'Halcyon Days',
          artist: 'Ellie Goulding',
        },
      ],
    id: "",
        nick: "",
        profile: "",
        email: "",
        provider: "",
        grade: "",
        game: "",
        win: "",
        lose: "",
        winrate: "",
        thumbnail:""
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
      .get(process.env.VUE_APP_SERVER_IP+"/mypage/" + userId)
      .then(response => {
        console.log("success2: ", response);
        const id = response.data.user_info.userid;
        console.log(id);
        const nick = response.data.user_info.nickname;
        const profile = response.data.user_info.profileimg;
        const thumbnail = response.data.user_info.thumbnail;
        const email = response.data.user_info.email;
        const provider = response.data.user_info.provider;
        const grade = response.data.user_info.grade;
        const game = response.data.user_info.game;
        const win = response.data.user_info.win;
        const lose = response.data.user_info.lose;
        const winrate = response.data.win_rate;


        this.id = id;
        console.log(this.id);
        
        
        this.nick = nick;
        this.profile = profile;
        this.thumbnail = thumbnail;
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
.container{
  width:50vw;
}
#full_container{
  margin-top:-8em;
}
#mypages{
background-image:url("../../assets/images/bg_solo.png");
-webkit-filter: grayscale(100%);
filter: gray;
} 
:hover #mypages{
background-image:url("../../assets/images/bg_solo.png");
-webkit-filter: grayscale(0%);
filter: none;
}
.mypage_text{
  text-align:center;
  color:white;
  padding-top:3em;
}
#profile_card{
  min-height:44vh;
  max-height:44vh;
  left:1.7vh;
  top:0.8vw;
}
#profile_card_image{
  width: auto;
  height: 100%;
}
.nick_title{
  padding-top:2em;
  padding-left:1em;
}
.nick_subtitle{
  padding-left:2em;
}
.v-card{
  height:20vh;
}
.rank_avatar{
  float:left;
}
.rank_title{
  float:right;
  padding-top:2em;
  padding-right:3em;
}
.rank_subtitle{
  float:right;
  padding-top:1em;
  padding-right:8em;
}
.winlose_title{
  float:center;
  padding-top:2em;
  padding-left:5.5em;
}
.winlose_subtitle{
  padding-top:1em;  
  padding-left:11.5em;
}
</style>
