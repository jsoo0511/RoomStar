<template>
  <v-row justify="center" align="center">
    <v-dialog v-model="dialog" max-width="400px">
      <template v-slot:activator="{ on }">
        <v-btn color="transparent" dark v-on="on">회원가입</v-btn>
      </template>

      <v-card>
        <br />
        <div style="text-align:center;">
        </div>

        <p style="text-align:center;">또는</p>

        <!-- <span>&nbsp;</span> -->
        <!-- <v-row> -->
        <!-- <div class="input-with-label"> -->
        <v-text-field
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          @keyup.enter="login"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <!-- <label for="email">이메일</label> -->
        <div class="error-text" v-if="error.email">{{error.email}}</div>
        <!-- </div> -->

        <!-- <div class="input-with-label"> -->
        <v-text-field
          v-model="password"
          type="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          @keyup.enter="login"
          placeholder="비밀번호를 입력하세요."
        />
        <!-- <label for="password">비밀번호</label> -->
        <div class="error-text" v-if="error.password">{{error.password}}</div>
        <!-- </div> -->
        <!-- </v-row> -->

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

        <!-- <v-btn text small
          class="btn btn--back btn--login"
          v-on:click="login"
          @click="dialog = false"
          :disabled="!isSubmit"
          :class="{disabled : !isSubmit}"
          color="black darken-1"
        >로그인</v-btn>-->

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
            <v-btn text small color="black" dark to="/user/join" v-on:click="dialog = false">가입하기</v-btn>
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
  components: {
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
  },
  data: () => {
  }
};
</script>

<style scoped>
</style>