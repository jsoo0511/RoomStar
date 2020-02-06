<template>
  <div class="google-login">
    <GoogleLogin
      :params="params"
      :renderParams="renderParams"
      :onSuccess="onSuccess"
      :onFailure="onFailure"
    ></GoogleLogin>
  </div>
</template>

<script>
import GoogleLogin from "vue-google-login";
import router from "@/routes";
import axios from "axios";

export default {
  name: "google-login",
  data() {
    return {
      // client_id is the only required property but you can add several more params, full list down bellow on the Auth api section
      params: {
        client_id:
          "401101505375-23se56k9sj4d245aaleqgj9m5d6n602k.apps.googleusercontent.com"
      },
      // client_secret : xqVdC-6FJqgLZOSp3JndOKSK
      // only needed if you want to render the button with the google ui
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
        .post("http://70.12.247.115:8080/login", params)
        .then(response => {
          console.log(response)
          let toStore = {
            one: true,
            two: token,
            three: email,
            four: nickname
          };
          this.$session.start();
          this.$session.set("jwt", token);
          this.$session.set("isUser", true);
          this.$session.set("userId", userid);
          this.$session.set("userNickname", nickname);
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
