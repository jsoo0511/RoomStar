<template>
  <div id="naver-login">
    <!-- <NaverLogin
      client-id="ox06DHeVVBUfVG72pV_g"
      callback-url="http://localhost:8080"
      :callbackFunction="callbackFunction"
    > -->
      <img src="../../../assets/icon/naverGreen.png" height="50"/>
    <!-- </NaverLogin> -->
  </div>
</template>

<script>
import Vue from "vue";
// @는 src를 뜻한다.
import router from "@/routes";
import axios from "axios";
// import NaverLogin from "vue-naver-login";

// let callbackFunction = function(status) {
//   console.log(status);
//   if (status) {
//     /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
//     let email = NaverLogin.user.getEmail();
//     let name = NaverLogin.user.getNickName();
//     let profileImage = NaverLogin.user.getNickName();

//     console.log(email);
//     if (email == undefined || email == null) {
//       alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
//       /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
//       NaverLogin.reprompt();

//       return;
//     }

//     window.location.replace("http://localhost:8080/");

//     // http://localhost:8080/callback#/
//     // access_token=AAAAPKZtJQAtr2PF0681tXUS2J3VeLKyv3jMvRrMBed2LbfIhWyPY_Jt4ZHBUJ-dtdV8o5cWHfkT3jSKfUtRBAoLPrA&state=f05fa92a-8c13-4b10-9c64-d9131b0c6e56&
//     // token_type=bearer&
//     // expires_in=3600
//   } else {
//     console.log("callback 처리에 실패하였습니다.");
//   }
// };

export default {
  name: "naver-login",
  // components: {
  //   NaverLogin
  // },
  // methods: {
  //   callbackFunction
  // }
};
</script>
