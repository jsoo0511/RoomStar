<template>
  <div>
    <v-row justify="center">
      <v-dialog v-model="dialog" id="dialog" max-width="350">
        <template v-slot:activator="{ on }">
          <v-btn color="transparent" dark v-on="on">로그인</v-btn>
        </template>

        <v-card z-index="1" id="modalLogin">
          <h2>
            <b>Login with</b>
          </h2>
          <br />

          <v-row justify="center">
            <kakaoLogin />
            <GoogleLogin />
          </v-row>
          <br />
          <p style="text-align:center; color:rgba(0,0,0,0.2);">
            ------------
            <b style="color:rgba(0,0,0,0.6); padding-left:2.6em; padding-right:2.6em;">or</b>------------
          </p>
          <v-text-field
            v-model="email"
            v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
            @keyup.enter="login"
            id="email"
            placeholder="이메일을 입력하세요."
            type="text"
          />

          <div class="error-text" v-if="error.email">{{error.email}}</div>
          <v-text-field
            v-model="password"
            type="password"
            v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
            id="password"
            @keyup.enter="login"
            placeholder="비밀번호를 입력하세요."
          />

          <div class="error-text" v-if="error.password">{{error.password}}</div>

          <button
            class="btn btn--back btn--login"
            v-on:click="login"
            @click="dialog = false"
            :disabled="!isSubmit"
            :class="{disabled : !isSubmit}"
            color="black darken-1"
            text
            small
          >로그인</button>

          <div class="add-option">
            <div class="text-center">
              <v-btn
                text
                small
                color="black"
                dark
                to="/user/password"
                v-on:click="dialog = false"
              >비밀번호 찾기</v-btn>

              <v-dialog v-model="joinDialog" max-width="400px">
                <template v-slot:activator="{ on }">
                  <v-btn text small color="black" dark v-on="on" v-on:click="joinDialog = true">가입하기</v-btn>
                </template>

                <v-card z-index="2">
                  <br />
                  <Join v-on:update="joinDialog = false"/>
                </v-card>
              </v-dialog>

              <!-- <v-btn text small color="black" dark to="/user/join" v-on:click="dialog = false">가입하기</v-btn> -->
              <v-btn text small color="black" dark to="#" v-on:click="dialog = false">서비스소개</v-btn>
            </div>
          </div>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">취소</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import "../../assets/css/style.scss";
import "../../assets/css/user.scss";
import router from "@/routes";
import axios from "axios";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import swal from "sweetalert";

import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../apis/UserApi";
import Join from "./Join.vue";

//https://sweet-modal-vue.adepto.as/ 여기꺼 모달 쓰고싶은데 잘

export default {
  components: {
    KakaoLogin,
    GoogleLogin,
    Join
  },
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
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    login() {
      if (this.isSubmit) {
        let userNickname1 = "";
        let token = "";
        let { email, password } = this;
        let pw = password;
        let userid = email;
        let data = {
          email,
          pw,
          userid
        };
        axios
          .post(process.env.VUE_APP_SERVER_IP+"/login/", data)
          .then(response => {
            userNickname1 = response.data.nickName;
            token = response.data.token;
            let toStore = {
              one: true,
              two: token,
              three: email,
              four: userNickname1
            };
            this.$session.start();
            // session에 저장
            this.$session.set("isUser", true);
            this.$session.set("jwt", token);
            this.$session.set("userId", email);
            this.$session.set("userNickname", userNickname1);
            this.$store.dispatch("checkLogin", token);
            this.$store.dispatch("login", toStore);
            // true, jwt-auth-token, email, nickname
            // 로딩 로직 짜는데 사용
            this.loading = false;

            return true;
          })
          .catch(error => {
            // https://sweetalert.js.org/guides/
            swal({
              title: "다시 시도해보세요.",
              text: "아이디 혹은 비밀번호가 일치하지 않습니다.",
              buttons: "확인"
              // icon: "warning"
            });
            console.log(error.response.config);
            console.log(error.response.config.data);
            // fail인 경우 사용자가 없다고 하기 --> 버튼이 비활성화되므로 알람 안줘도 된다.
            // this.loading = false;
            return false;
          });
        this.$router.push("/");
        //요청 후에는 버튼 비활성화
        this.isSubmit = false;

        UserApi.requestLogin(
          data,
          res => {
            //통신을 통해 전달받은 값 콘솔에 출력
            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
          },
          error => {
            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
          }
        );
      }
    }
  },
  data: () => {
    return {
      dialog: false,
      joinDialog: false,
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  }
};
</script>

<style scoped>
#modalLogin {
  padding: 30px;
  max-width: 350px;
  width: 100% !important;
  margin: 0 auto;
  border-radius: 2px;
  overflow: hidden;
}

#dialog {
  max-width: 350px;
}
</style>