<template>
  <v-row justify="center" align="center">
    <v-dialog v-model="dialog" max-width="400px">
      <template v-slot:activator="{ on }">
        <v-icon dark v-on="on">mdi-pencil</v-icon>
      </template>

      <v-card>
        마이페이지

        <div>
          <input
            v-model="nickName"
            id="nickname"
            placeholder="닉네임을 입력하세요."
            type="text"
          />
          <label for="nickname">닉네임</label>
        </div>

        <div>
          <input
            v-model="email"
            v-bind:class="{
              error: error.email,
              complete: !error.email && email.length !== 0
            }"
            id="email"
            placeholder="이메일을 입력하세요."
            type="text"
          />
          <label for="email">이메일</label>
          <div class="error-text" v-if="error.email">{{ error.email }}</div>
        </div>

        <button type="submit" @click="signUp()">수정하기</button>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import "../../assets/css/style.scss";
import "../../assets/css/user.scss";
import router from "@/routes";
import axios from "axios";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import swal from "sweetalert";
import UserApi from "../../apis/UserApi";

export default {
  components: {},
  created() {
    this.component = this;

    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    }
  },
  methods: {
    signUp() {
      const userId = this.$session.get("userId");
      const userNickname = this.$session.get("userNickname");
      const profileImg = this.$session.get("profileImg");
      //const email=this.$session.get("email");
      console.log(this.$session.get("profileImg"));
      console.log(profileImg);
      
      if (this.checkForm()) {
        // true라면,
        // this.loading = true;
        const SERVER_IP = "http://192.168.0.9:8080/update";
        //console.log(this.email, this.password, this.nickName);
        //email, nickname, profileimg, pw
        // provider = local
        // user db 수정
        // provider로 구분
        let data = {
          userid: userId,
          nickname: this.nickName,
          profileimg: encodeURIComponent(profileImg),
          email: this.email
        };

        axios
          .put(SERVER_IP, data)
          .then(response => {
            this.$router.push("/");
          })
          .catch(error => {
            console.log(error);
            // this.loading = false;
          });
      }
      this.$nextTick(() => {
        console.log("error");
      });
    },

    checkForm() {
      this.errors = [];
      if (!this.nickName) {
        this.errors.push("ID를 입력하세요.");
      }
      if (this.email.length >= 0 && !EmailValidator.validate(this.email)) {
        this.error.email = "이메일 형식이 아닙니다.";
        this.errors.push("이메일을 입력해주세요.");
      } else this.error.email = false;

      if (this.errors.length === 0) {
        return true;
      }
    }
    
  },
  data: () => {
    return {
      dialog: false,
      email: "",
    
      error: {
        email: false,
      },
      isSubmit: false,
      component: this
    };
  }
};
</script>

<style scoped></style>
