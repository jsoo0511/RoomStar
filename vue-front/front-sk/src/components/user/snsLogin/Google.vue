<template>

    <GoogleLogin
      :params="params"
      :renderParams="renderParams"
      :onSuccess="onSuccess"
      :onFailure="onFailure"
    ></GoogleLogin>

</template>

<script>
import GoogleLogin from "vue-google-login";
import router from "@/routes";
import axios from "axios";
import jwt_decode from 'jwt-decode';

export default {
  data() {
    return {
      params: {
        client_id:
          "263255205373-rts238jq78d4fekjcikjgchcou6g2gii.apps.googleusercontent.com"
      },
      renderParams: {
        width: 50,
        height: 49,
        longtitle: true,
      }
    };
  },
  components: {
    GoogleLogin
  },
  methods: {
    onSuccess(googleUser) {
      // This only gets the user information: id, name, imageUrl and email
      let res = googleUser.getBasicProfile()
      let userid = res.getId();
      let nickname = res.getName();
      let profileimg = res.getImageUrl();
      let email = res.getEmail();
      let provider = "google";
      let token = googleUser.getAuthResponse().id_token;
      
      let toStore = {
        one: true,
        two: token,
        three: userid,
        four: nickname
      };

      console.log(toStore)

      this.$store.dispatch("login", toStore).then(res => {
        console.log(this.$store.state);
      });
      console.log('----', this.$store);
      // back에 db전달
      let params = {
        userid: userid,
        nickname: nickname,
        profileimg: profileimg,
        email: email,
        pw: "",
        provider: provider
      };
      console.log('----', params);


      axios
        .post(process.env.VUE_APP_SERVER_IP+"/login", params)
        .then(response => {
          console.log(response)
          let toStore = {
            one: true,
            two: token,
            three: email,
            four: nickname
          };
          this.$session.start();
          console.log('using jwt_decode',jwt_decode(token))
          this.$session.set("jwt", token);
          this.$session.set("isUser", true);
          this.$session.set("userId", userid);
          this.$session.set("userNickname", nickname);
          this.$session.set("profileImg", profileimg);
          
          localStorage.setItem('jwt', token)
          this.$store.dispatch("checkLogin", token);
          this.$store.dispatch("login", toStore);
        })

        .catch(e => {
          console.log("error: ", e);
        });




      this.$router.push("/").catch(err => {});
    },

    onFailure(error) {
      this.$router.push("/user/join").catch(err => {});
    }
  }
};
</script>

<style scoped>
</style>