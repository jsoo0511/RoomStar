<template>
<div class="options" >
   <div class="option active" :style="{'--optionBackground':'url('+profile+')'}">
          <div class="text" STYLE="writing-mode: tb-rl">NickName</div>
      <div class="shadow">
      </div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-walking"></i>
         </div>
         <div class="info">
            <div class="main">{{nick}}</div>
            <div class="sub">{{provider}}</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(https://66.media.tumblr.com/8b69cdde47aa952e4176b4200052abf4/tumblr_o51p7mFFF21qho82wo1_1280.jpg);">
      <div class="text" STYLE="writing-mode: tb-rl">ID</div>
      <div class="shadow" ></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-snowflake"></i>
         </div>
         <div class="info">
            <div class="main">{{id}}</div>
            <div class="sub">ID</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(https://66.media.tumblr.com/5af3f8303456e376ceda1517553ba786/tumblr_o4986gakjh1qho82wo1_1280.jpg);">
      <div class="text" STYLE="writing-mode: tb-rl">Win</div>
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-tree"></i>
         </div>
         <div class="info">
            <div class="main">{{win}} 승</div>
            <div class="sub">승리</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(https://66.media.tumblr.com/5516a22e0cdacaa85311ec3f8fd1e9ef/tumblr_o45jwvdsL11qho82wo1_1280.jpg);">
      <div class="text" STYLE="writing-mode: tb-rl">Lose</div>
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-tint"></i>
         </div>
         <div class="info">
            <div class="main">{{lose}} 패</div>
            <div class="sub">패배</div>
         </div>
      </div>
   </div>
   <div class="option" style="--optionBackground:url(https://66.media.tumblr.com/f19901f50b79604839ca761cd6d74748/tumblr_o65rohhkQL1qho82wo1_1280.jpg);">
      <div class="text" STYLE="writing-mode: tb-rl">Grade</div>
      <div class="shadow"></div>
      <div class="label">
         <div class="icon">
            <i class="fas fa-sun"></i>
         </div>
         <div class="info">
            <div class="main">{{grade}}</div>
            <div class="sub">등급</div>
         </div>
      </div>
   </div>
</div>
</template>

<script>
import router from "@/routes";
import axios from "axios";
import ModalMyPage from "./ModalMyPage.vue";
import $ from "jquery";

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
      .get("http://70.12.247.115:8080/mypage/" + userId)
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
  },
  mounted(){   
     $(function() {
   $(".option").click(function(){
   $(".option").removeClass("active");
   $(this).addClass("active");
   
});
  });
  },

  // viewMypage
};
</script>

<style lang="scss" scoped>
$optionDefaultColours: #ED5565,#FC6E51,#FFCE54,#2ECC71,#5D9CEC,#AC92EC;

body {
   display:flex;
   flex-direction:row;
   justify-content:center;
   align-items:center;
   overflow:hidden;
   height:100vh;
   
   font-family: 'Roboto', sans-serif;
   .credit {
       
      position: absolute;
      bottom:20px;
      left:20px;
      
      color:#000;
   }
   .options {
       
      margin-top:15em;
      margin-left: 17em;
      display:flex;
      flex-direction:row;
      align-items:stretch;
      overflow:hidden;

      min-width:600px;
      max-width:900px;
      width:calc(100% - 100px);
      
      height:400px;
      
      @for $i from 1 through 4 {
         @media screen and (max-width:798px - $i*80) {
            min-width:600px - $i*80;
            .option:nth-child(#{6-$i}) {
               display:none;
            }
         }
      }
      
      .option {
          
         position: relative;
         overflow:hidden;

         min-width:60px;
         margin:10px;
         //border:0px solid --defaultColor;

         background:var(--optionBackground, var(--defaultBackground, #E6E9ED));
         background-size:auto 120%;
         background-position:center;

         cursor: pointer;

         transition:.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);

         @for $i from 1 through length($optionDefaultColours) {
            &:nth-child(#{$i}) {
               --defaultBackground:#{nth($optionDefaultColours, $i)};
            }
         }
         &.active {
            flex-grow:10000;
            transform:scale(1);
            
            max-width:600px;
            margin:0px;
            border-radius:40px;
            background-size:auto 100%;
            .text{
                   color: rgba(0,0,0,0); 
                }
            
            .shadow {
               box-shadow:inset 0 -120px 120px -120px black, inset 0 -120px 120px -100px black;
                
            }
            .label {
               bottom:20px;
               background-color: rgba(0,0,0,0) !important;
               left:20px;
               .info{
                   background-color: rgba(0,0,0,0) !important;
               }
               .info >div {
                  left:0px;
                  opacity:1;
               }
            }
            /*&:active {
               transform:scale(0.9);
            }*/
         }
         &:not(.active) {
            flex-grow:1;
            
            border-radius:30px;
             .text{
                   color: white;
                   border-radius:100%;
                   font-size: 30px;
                   margin-top: 1em;
                   
               }
            .shadow {
               bottom:-40px;
               box-shadow:inset 0 -120px 0px -120px black, inset 0 -120px 0px -100px black;

              
            }
            .label {
               bottom:10px;
               left:10px;
               .info{
                   background-color: rgba(0,0,0,0) !important;
               }
               .info >div {
                  left:20px;
                  opacity:0;
               }
            }
         }
         .shadow {
            position: absolute;
            bottom:0px;
            left:0px;
            right:0px;
            
            height:120px;
            
            transition:.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
         }
         .label {
             
            display:flex;
            position: absolute;
            right:0px;

            height:40px;
            transition:.5s cubic-bezier(0.05, 0.61, 0.41, 0.95);
            .icon {
               display:flex;
               flex-direction:row;
               justify-content:center;
               align-items:center;
               
               min-width:40px;
               max-width:40px;
               height:40px;
               border-radius:100%;

               background-color:white;
               color:var(--defaultBackground);
            }
            .info {
               display:flex;
               flex-direction:column;
               justify-content:center;
               
               margin-left:10px;
               
               color:white;
               
               white-space: pre;
               >div {
                  position: relative;
                  
                  transition:.5s cubic-bezier(0.05, 0.61, 0.41, 0.95), opacity .5s ease-out;
               }
               .main {
                  font-weight:bold;
                  font-size:1.2rem;
               }
               .sub {
                   
                  transition-delay:.1s;
               }
            }
         }
      }
   }
}
</style>