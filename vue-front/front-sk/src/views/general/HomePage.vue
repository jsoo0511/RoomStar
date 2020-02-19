<template>
<div id="HomePage">
   <v-app light id="hp_video">
    <v-content>
      <section>
		<v-video-parallax height="100vh">
          <v-layout
            column
            align-center
            justify-center
            class="white--text"
          >
            <img src="../../assets/images/roomstar.png" style="margin-top:35vh; width:230px; height:auto;" />
            <!--<h1 class="white--text mb-2 display-1 text-center" style="margin-top:30vh;">ROOM STAR</h1>-->
            <h2 class="subheading mb-1 text-center">        </h2>

            <!-- <v-btn
              class="mt-12"
              color="orange lighten-1"
              opacity="0.5"
              dark  
              large
              href="/"
            >
              로그인하기
            </v-btn> -->
           <a href="#" id="scroll-down" class="scroll-down" address="true"></a>
          </v-layout>
        </v-video-parallax>
      </section>

     

      <section
        id="welcome">
        <FlipCard3 />
      </section>
        <!--<v-layout
          column
          wrap
          class="my-12"
          align-center
        >
          <v-flex xs12>
          <h1 style="text-align:center; margin-bottom:50px;"> Menu Select </h1>
            <v-container grid-list-xl>
              <v-layout row wrap align-center>
                <v-flex xs12 md4>
                  <v-card flat class="mx-auto" elevation="7"  width="90%" id="card-box">
                   <v-img
                      class="white--text align-end"
                      height="500px"
                      width="100%"
                      src="../../assets/images/singBattle.png"
                    >
                      <v-card-title style="margin-left:5px;">Sing Battle</v-card-title>
                    
                      <v-card-text class="white--text" style="height:100px; padding-top:10px; margin-left:10px;">
                        <div>내 노래는 세상을 울릴 노래다ㅏ아!</div>
                        <div>추천을 많이 받은 사람이 승리합니다!</div>
                      </v-card-text>

                      <v-card-actions>
                        <v-btn
                          color="orange"
                          text
                          @click="singingButton()"
                        >
                          경연하기
                        </v-btn>
                      </v-card-actions>
                      </v-img>
                    </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="mx-auto align-center" width="90%" elevation="7" id="card-box">
                   <v-img
                      class="white--text align-end"
                      width="100%"
                      height="500px"
                      src="../../assets/images/watchBattle.png"
                      href="/"
                    >
                      <v-card-title style="margin-left:5px;">Watch Battle</v-card-title>
                    
                      <v-card-text class="white--text"  style="height:100px; margin-left:10px;padding-top:10px; ">
                        <div>그치만.. 듣지 않으면 알 수 없는걸..</div>
                        <div>노래 경연을 듣고 투표해주세요!</div>
                      </v-card-text>

                      <v-card-actions>
                        <v-btn
                          color="orange"
                          text
                        >
                          시청하기
                        </v-btn>
                      </v-card-actions>
                      </v-img>
                    </v-card>
                </v-flex>
                <v-flex xs12 md4>
                  <v-card flat class="mx-auto" width="90%" elevation="7" id="card-box">
                   <v-img
                      class="white--text align-end"
                      height="500px"
                      width="100%"
                      src="../../assets/images/showOff.png"
                    >
                      <v-card-title style="margin-left:5px;">Sing Showoff</v-card-title>
                    
                      <v-card-text class="white--text"  style="height:100px; margin-left:10px;padding-top:10px; ">
                        <div>오늘따라 노래가 너무 잘된다구?!</div>
                        <div>내 노래를 모두에게 자랑해보세요!</div>
                      </v-card-text>

                      <v-card-actions>
                        <v-btn
                          color="orange"
                          text
                        >
                          자랑하기
                        </v-btn>
                      </v-card-actions>
                      </v-img>
                    </v-card>
                </v-flex>
              </v-layout>
              
            </v-container>
          </v-flex>
        </v-layout>-->
<!--
 <section class="hide-overflow">
  <v-container grid-list-xl>
        <v-layout>
          <v-flex
            hidden-sm-and-down
            md8
          >
            <v-img
              src="../../assets/images/welcome.png"
              height="100vh"
            />
          </v-flex>

          <v-flex
            xs12
            md12
            align-content-space-between
            layout
            :pa-5="$vuetify.breakpoint.smAndDown"
            wrap
            color="rgba(0,0,0,0.5)"
          >
            <base-bubble-1
              style="transform: rotate(180deg) translateY(25%)"
            />

            <v-layout
              align-center
              justify-center
            >
              <v-flex
                xs10
                md6
              >
                <h2 style="margin-top:13vh;"><b>Sing Battle<br></b></h2><br>
                <p style="padding-left:2vw;">
                  내 노래는 세상을 울릴 노래다ㅏ아!<br>
                  추천을 많이 받은 사람이 승리합니다!<br>
                </p>
                <h2><b>Sing Battle<br></b></h2><br>
                <p style="padding-left:2vw;">
                  그치만.. 듣지 않으면 알 수 없는걸..<br>
                  노래 경연을 듣고 투표해주세요!<br>
                </p>
                <h2><b>Sing Showoff<br></b></h2><br>
                <p style="padding-left:2vw;">
                  오늘따라 노래가 너무 잘된다구?!<br>
                  내 노래를 모두에게 자랑해보세요!<br>
                </p>
              </v-flex>
          <a href="#" id="scroll-down2" class="scroll-down" address="true"></a>
            </v-layout>

            <base-bubble-2
              style="transform: rotate(180deg) translate(-200px, -15%)"
            />
          </v-flex>
        </v-layout>
      </v-container>
    </section>
-->
<!--
      <section>
      <v-container grid-list-xl>
        <v-parallax src="../../assets/images/hero.jpeg" height="380">
          <v-layout column align-center justify-center>
            <div class="headline white--text mb-4 text-center">Web development has never been easier</div>
            <em>Kick-start your application today</em>
            <v-btn
              class="mt-12"
              color="blue lighten-2"
              dark
              large
              href="/pre-made-themes"
            >
              Get Started
            </v-btn>
          </v-layout>
        </v-parallax>
        </v-container>
      </section>

      <section>
        <v-container grid-list-xl>
          <v-layout row wrap justify-center class="my-12">
            <v-flex xs12 sm4>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">Information</div>
                </v-card-title>
                <v-card-text>
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformation
                </v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm4 offset-sm1>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">Contact us</div>
                </v-card-title>
                <v-card-text>
                  삼성 청년 SW 아카데미 2기 4반 ★7조★ 1등 ㄱㄱ!!
                </v-card-text>
                <v-list class="transparent">
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-phone</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>010-9559-5296</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-map-marker</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>서울특별시 강남구 역삼동 테헤란로 212</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-email</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>qpalzmfghtgv@naver.com</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list>
              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </section>-->
      <section id="paral_section" style="padding-top:50px;">
        <v-parallax src="../../assets/images/welcome.png" id="paral"> 
        <h1 style="color:#fff; text-align:center;">ROOM ☆ STAR</h1>
        </v-parallax>
        <v-container grid-list-xl>
        <v-layout column wrap>
        <v-flex>
         </v-flex>
         <v-flex>
          <v-layout row wrap justify-center class="my-12">
            <v-flex xs12 sm4>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">Information</div>
                </v-card-title>
                <v-card-text>
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformationInformationInfo
                  InformationInformationInformation
                </v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm4 offset-sm1>
              <v-card flat class="transparent">
                <v-card-title primary-title class="layout justify-center">
                  <div class="headline">Contact us</div>
                </v-card-title>
                <v-card-text>
                  삼성 청년 SW 아카데미 2기 4반 ★7조★ 1등 ㄱㄱ!!
                </v-card-text>
                <v-list class="transparent">
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-phone</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>010-9559-5296</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-map-marker</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>서울특별시 강남구 역삼동 테헤란로 212</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-action>
                      <v-icon class="blue--text text--lighten-2">mdi-email</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                      <v-list-item-title>qpalzmfghtgv@naver.com</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list>
              </v-card>
            </v-flex>
          </v-layout>
          </v-flex>
          </v-layout>
        </v-container>
      </section>

    </v-content>
  </v-app>
 </div>


</template>
 <script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 

<script>
import Vue from 'vue'
// import ModalLogin from '../user/ModalLogin.vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
Vue.use(Vuetify, {
  iconfont: 'mdi',
  breakpoint: {
    thresholds: {
      xs: 340,
      sm: 540,
      md: 800,
      lg: 1280,
    },
    scrollBarWidth: 24,
  }
})
import VuetifyVideoParallax from './VuetifyVideoParallax.vue'
Vue.component('v-video-parallax', VuetifyVideoParallax)
import VueLoading from 'vue-loading-overlay'
import 'vue-loading-overlay/dist/vue-loading.css';
import $ from 'jquery';
import FlipCard from './FlipCard.vue'
import FlipCard2 from './FlipCard2.vue'
import FlipCard3 from './FlipCard3.vue'
Vue.use(VueLoading);
Vue.component('loading', VueLoading)
export default {
  name: 'HomePage',
  vuetify: new Vuetify(),
  components: {
    // ModalLogin
    FlipCard,
    FlipCard2,
    FlipCard3,
  },
  data () {
    return {
      title: '',
      loader: null,      
      drawer: null,
      items: [
        { title: "Home", icon: "dashboard" },
        { title: "About", icon: "question_answer" }
      ]
    }
  },
  mounted(){   
     $(function() {
    $('#scroll-down').click (function() {
      $('html, body').animate({scrollTop: $('#welcome').offset().top }, 'slow');
      return false;
    });
  });
  },
  created: function() {
    // let loader = Vue.$loading.show({
    //   container: this.$refs.loadingContainer,
    //   canCancel: true, // default false
    //   //onCancel: this.yourMethodName,
    //   color: '#ffffff',
    //   loader: 'spinner',
    //   width: 64,
    //   height: 64,
    //   backgroundColor: '#ffffff',
    //   zIndex: 999,
    // })
    let self = Object.assign(this, {})
    window.addEventListener("load", function(event) {
      console.log('document ready')
      // loader.hide();
    });
  }
}
</script>

<style scoped>
.container {
  width:100vw;
  float: center;
  box-sizing: border-box;
  min-height: auto;
  }
.container > div {
  padding-top: 0px; }
#welcome{
  background-color:#fff;
}
section{
  background-color:#fff;
  min-height:100vh;
  overflow:hidden;
}
/*
#introduce{
  padding-top:100px;
  min-height:120vh;
  max-height:120vh;
  overflow:visible;
}*/
v-content__wrap{
  background-color:black;
}

#thanks {
  background-color: #fff;
}
#thanks::after {
  content: none;
}
#thanks div {
  position: absolute;
  top: 50%;
  left: 50%;
  z-index: 2;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
}
#thanks h2 {
  margin-bottom: 60px;
  color: #333;
  font : normal 300 64px/1 'Josefin Sans', sans-serif;
  text-align: center;
  white-space: nowrap;
}
#thanks p {
  color: #333;
  font : normal 400 20px/1 'Josefin Sans', sans-serif;
}
#thanks p a {
  color: #333;
  text-decoration: none;
  transition: color .3s;
}
#thanks p a:hover {
  color: #888;
}/*
.demo a {
  position: absolute;
  bottom: 20px;
  left: 48%;
  z-index: 2;
  display: inline-block;
  -webkit-transform: translate(0, -50%);
  transform: translate(0, -50%);
  color: #fff;
  font : normal 400 20px/1 'Josefin Sans', sans-serif;
  letter-spacing: .1em;
  text-decoration: none;
  transition: opacity .3s;
}
.demo a:hover {
  opacity: .5;
}


#section05 a {
  padding-top: 170px;
}
#section05 a span {
  position: absolute;
  top: 0;
  left: 50%;
  width: 24px;
  height: 24px;
  margin-left: -12px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
  -webkit-animation: sdb05 1.5s infinite;
  animation: sdb05 1.5s infinite;
  box-sizing: border-box;
}
@-webkit-keyframes sdb05 {
  0% {
    -webkit-transform: rotate(-45deg) translate(0, 0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    -webkit-transform: rotate(-45deg) translate(-20px, 20px);
    opacity: 0;
  }
}
@keyframes sdb05 {
  0% {
    transform: rotate(-45deg) translate(0, 0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    transform: rotate(-45deg) translate(-20px, 20px);
    opacity: 0;
  }
}
*/

*,
:after,
:before {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.scroll-down {
  opacity: 1;
  -webkit-transition: all .5s ease-in 3s;
  transition: all .5s ease-in 3s;
}

.scroll-down {
  position: absolute;
  bottom: 150px;
  left: 50%;
  margin-left: -16px;
  display: block;
  width: 32px;
  height: 32px;
  border: 2px solid #FFF;
  background-size: 14px auto;
  border-radius: 50%;
  z-index: 2;
  -webkit-animation: bounce 2s infinite 2s;
  animation: bounce 2s infinite 2s;
  -webkit-transition: all .2s ease-in;
  transition: all .2s ease-in;
  transform: scale(1)
}

.scroll-down:before {
    position: absolute;
    top: calc(50% - 8px);
    left: calc(50% - 6px);
    transform: rotate(-45deg);
    display: block;
    width: 12px;
    height: 12px;
    content: "";
    border: 2px solid white;
    border-width: 0px 0 2px 2px;
}

.scroll-down2 {
  opacity: 1;
  -webkit-transition: all .5s ease-in 3s;
  transition: all .5s ease-in 3s;
}

.scroll-down2 {
  position: absolute;
  bottom: 150px;
  left: 50%;
  margin-left: -16px;
  display: block;
  width: 32px;
  height: 32px;
  border: 2px solid #000;
  background-size: 14px auto;
  border-radius: 50%;
  z-index: 2;
  -webkit-animation: bounce 2s infinite 2s;
  animation: bounce 2s infinite 2s;
  -webkit-transition: all .2s ease-in;
  transition: all .2s ease-in;
  transform: scale(1)
}

.scroll-down2:before {
    position: absolute;
    top: calc(50% - 8px);
    left: calc(50% - 6px);
    transform: rotate(-45deg);
    display: block;
    width: 12px;
    height: 12px;
    content: "";
    border: 2px solid black;
    border-width: 0px 0 2px 2px;
}

@keyframes bounce {
  0%,
  100%,
  20%,
  50%,
  80% {
    -webkit-transform: translateY(0);
    -ms-transform: translateY(0);
    transform: translateY(0);
  }
  40% {
    -webkit-transform: translateY(-10px);
    -ms-transform: translateY(-10px);
    transform: translateY(-10px);
  }
  60% {
    -webkit-transform: translateY(-5px);
    -ms-transform: translateY(-5px);
    transform: translateY(-5px);
  }
}
#card-box {
  transition: 0.5s ease;
  filter:grayscale(40%);
}
#card-box:hover{
  transform: scale(1.1);
  box-shadow: 5px 5px 15px rgba(0,0,0,0);
  filter:grayscale(0%);
}
#paral_section{
  background-color:#eee;
}
#paral{
   height:200px !important;
   margin-left:0vw;
   width:100vw;
}

</style>

<style scoped lang="scss">
 * {
  box-sizing:border-box;
}

.loading_wrap{
  padding-top:5vh;
  margin-bottom:-15vh;
}
.h2_loading {
    text-align:center;
    font-size:3em;
    color :rgba(0,0,0,0.3);
    position:relative;
    margin:0px;
    &:after {
      text-align:left;
      content:attr(data-back);
      width:0%;
      height:100%;
      background:rgba(0,0,0,0.8);
      -webkit-text-fill-color: transparent;
      -webkit-background-clip: text;
      position:absolute;
      left:42.1%;
      top:0px;
      animation: backMasking 4s infinite;
    }
}


@keyframes backMasking {
  0% {
    width:0%;
  }
  100% {
    width:100%;
  }
  
}

@-webkit-keyframes backMasking {
  0% {
    width:0%;
  }
  100% {
    width:100%;
  }
  
}

</style>