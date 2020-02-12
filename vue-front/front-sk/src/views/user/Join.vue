
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->




 
<template>
  <div>
    <h1>가입하기</h1>
    <div>
      <div>
        <input v-model="nickName" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
        <label for="nickname">닉네임</label>
      </div>

      <div>
        <input
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <label for="email">이메일</label>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>

      <div>
        <input
          v-model="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          :type="passwordType"
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password">비밀번호</label>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>

      <div>
        <input
          v-model="passwordConfirm"
          v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&passwordConfirm.length!==0}"
          :type="passwordConfirmType"
          id="password-confirm"
          placeholder="비밀번호를 다시한번 입력하세요."
        />
        <label for="password-confirm">비밀번호 확인</label>
        <div class="error-text" v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
      </div>
    </div>

    <v-row justify="center">
      <v-dialog v-model="dialog" width="600px">
        <template v-slot:activator="{ on }">
          <v-btn color="primary" dark v-on="on">약관보기</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">개인정보처리방침</span>
          </v-card-title>

          <v-card-text>
            <!-- 약관내용 -->
            <TermPopup />
          </v-card-text>
          <label>
            <input v-model="isTerm" type="checkbox" id="term" />
            <span>약관을 동의합니다.</span>
          </label>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green darken-1" text @click="dialog = false">동의하지않음</v-btn>
            <v-btn color="green darken-1" text @click="dialog = false">동의함</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

    <button type="submit" @click="signUp()">가입하기</button>
  </div>
</template>


<script>
import router from "@/routes";
import axios from "axios";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import TermPopup from "@/components/modal/TermPopup.vue";

// https://vuetifyjs.com/en/components/dialogs#modal
// Overflowed

export default {
  name: "Join",
  components: {
    TermPopup
  },
  created() {
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

  methods: {
    signUp() {
      // 제대로된 값을 넣고 확인을 누르면 DB로 전송한다.
      if (this.checkForm()) {
        // true라면,
        // this.loading = true;
        const SERVER_IP = "http://70.12.247.115:8080/insert/";
        console.log(this.email, this.password, this.nickName);
        //email, nickname, profileimg, pw
        // provider = local
        // user db 수정
        // provider로 구분
        let data = {
          userid: this.email,
          nickname: this.nickName,
          profileimg: "",
          email: this.email,
          pw: this.password,
          provider: "local"
        };

        axios
          .post(SERVER_IP, data)
          .then(response => {
            this.$router.push("/login");
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

      // 8 글자 아래인 경우
      if (
        this.password.length < 8 &&
        !this.passwordSchema.validate(this.password)
      ) {
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
        this.errors.push("비밀번호는 8글자 이상 입력해주세요.");
      } else this.error.password = false;

      if (this.password != this.passwordConfirm) {
        this.error.passwordConfirm = "비밀번호가 일치하지 않습니다.";
        this.error.push("비밀번호가 일치하지 않습니다.");
      } else this.error.passwordConfirm = false;

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
      email: "",
      password: "",
      passwordSchema: new PV(),
      passwordConfirm: "",
      nickName: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickName: false,
        passwordConfirm: false,
        term: false
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
      dialog: false
    };
  }
};
</script>


