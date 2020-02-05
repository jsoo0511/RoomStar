<template>
  <div id="kakao-login">
    <KakaoLogin
      api-key="75bdc978e83228e7b1e197264c163a8f"
      :on-success="onSuccess"
      :on-failure="onFailure"
    >
      <!-- <img
        src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium_ov.png"
      /> -->
    </KakaoLogin>
  </div>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import Vue from "vue";
import KakaoLogin from "vue-kakao-login";
// @는 src를 뜻한다.
import router from "@/routes";
import axios from "axios";

let onSuccess = function(data) {
  let userId1 = "";
  let userNickname1 = "";
  let profileImage1 = "";
  let userEmail1 = "";
  let provider1 = "";
  let store = this.$store;
  let session = this.$session;
  console.log(data.access_token); // access_token으로 로그아웃/로그인이 이루어지므로 반드시 저장
  console.log(data.refresh_token);
  Kakao.API.request({
    url: "/v2/user/me",
    success: function(res) {
      userId1 = res.id;
      userNickname1 = res.properties.nickname;
      profileImage1 = res.properties.profile_image;
      userEmail1 = res.kakao_account.email;
      provider1 = "kakao";

      let toStore = {
        one: true,
        two: data.access_token,
        three: userId1,
        four: userNickname1
      };

      store.dispatch("login", toStore).then(res => {
        console.log(store.state);
      });

      // back에 db전달
      let params = {
        userid: userId1,
        nickname: userNickname1,
        profileimg: profileImage1,
        email: userEmail1,
        pw: "",
        provider: provider1
      };

      axios
        .post("http://70.12.247.115:8080/login", params)
        .then(response => {
          let toStore = {
            one: true,
            two: response.data.token,
            three: userEmail1,
            four: userNickname1
          };
          session.start();
          session.set("jwt", response.data.token);
          session.set("isUser", true);
          session.set("userId", userId1);
          session.set("userNickname", userNickname1);
          store.dispatch("checkLogin", response.data.token);
          store.dispatch("login", toStore);
        })

        .catch(e => {
          console.log("error: ", e);
        });
    },
    fail: function(error) {
      console.log(error);
    }
  });
  this.$router.push("/");
};

let onFailure = function(data) {
  this.$router.push("/user/join");
};

export default {
  name: "kakao-login",
  components: {
    KakaoLogin
  },
  methods: {
    onSuccess,
    onFailure
  }
};
</script>
