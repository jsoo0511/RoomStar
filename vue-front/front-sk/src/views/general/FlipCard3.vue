<template>
  <v-row id="m">
    <section>
      <article>
        <h2>노래 배틀하기</h2>
        <p>This is some description text for this panel.</p>
      </article>
    </section>
    <section>
      <article>
        <h2>BATTLE</h2>

        <!-- <router-link id="link_btn1" :to="'/MyPage2/'" tag=button>배틀하기</router-link> -->
        <button id="link_btn1" @click="singingButton">대결하기</button>
        <!-- $router.push('/gameRoom/') -->
      </article>
    </section>
    <section>
      <article>
        <h2>WATCH</h2>

        <!-- <button id="link_btn2">시청하기</button> -->
        <button id="link_btn2" @click="pushToWaiting">시청하기</button>
      </article>
    </section>
    <section>
      <article>
        <h2>CONTEST</h2>

        <!-- <button id="link_btn3">자랑하기</button> -->
        <button id="link_btn3" @click="pushToSharing">자랑하기</button>
      </article>
    </section>
    <section>
      <article></article>
    </section>
  </v-row>
</template>

<script>
import router from "@/routes";
import axios from "axios";
import swal from "sweetalert";
export default {
  name: "flipcard3",
  components: {},
  data() {
    return {
      isUser: this.$session.get("isUser")
    };
  },

  methods: {
    // 추가
    singingButton() {
      this.isUser = this.$session.get("isUser")
      console.log("================", this.isUser);
      if (this.isUser) {
        console.log("================", this.isUser);
        const userid = this.$session.get("userId");
        console.log("----", this.userid, userid);
        this.$store.dispatch("changeToSinger", 1);
        this.$session.set("singerOrWatcherStatus", 1);

        axios
          .put(process.env.VUE_APP_SERVER_IP + "/Enter_room/" + userid)
          .then(response => {
            console.log(response);
            this.$session.set("roomId", response.data.room_id);
            this.$session.set("playerIdx", response.data.player_idx);
            this.$router.push("/GameRoom");
          })
          .catch(e => {
            console.log("================", this.isUser);
            console.log("error: ", e);

            swal({
              title: "모든 방이 차있습니다.",
              buttons: "확인"
              // icon: "warning"
            });
          });
      } else {
        swal({
          title: "로그인이 필요합니다.",
          buttons: "확인"
          // icon: "warning"
        });
      }
    },
    pushToWaiting() {
      this.isUser = this.$session.get("isUser")
      console.log("================", this.isUser);
      if (this.isUser) {
        console.log("================", this.isUser);
        this.$router.push("/waitingRoom/");
      } else {
        console.log("================", this.isUser);
        swal({
          title: "로그인이 필요합니다.",
          buttons: "확인"
          // icon: "warning"
        });
      }
    },
    pushToSharing() {
      this.isUser = this.$session.get("isUser")
      console.log("================", this.isUser);
      if (this.isUser) {
        console.log("================", this.isUser);
        this.$router.push("/SharingPage/");
      } else {
        console.log("================", this.isUser);
        swal({
          title: "로그인이 필요합니다.",
          buttons: "확인"
          // icon: "warning"
        });
      }
    }
  }
};
</script>
<style lang="scss" scoped>
$img-dir: "../../assets/images/a";

$section-amount: 5;
// Try messing with the rotate value
$section-rotate: 15;

$mq-desktop: "min-width: 630px";

* {
  box-sizing: border-box;
}
#m {
  background-color: rgba(0, 0, 0, 0.9);
}

main {
  display: flex;
  flex-direction: column;
  width: 100%;
  transform: translate3d(0, 0, 0);
  @media ($mq-desktop) {
    flex-direction: row;
    width: 100% + ($section-rotate * 2);
    margin-left: percentage($section-rotate) * -0.01;
  }
  section:not(:first-child):not(:last-child):hover {
    flex: 2;
    &:after {
      opacity: 0.8;
    }
    article {
      opacity: 1;
      transform: translateY(0);
      transition: opacity 0.2s 0.2s, transform 0.2s 0.2s;
      @media ($mq-desktop) {
        transform: translateY(0) skewX(-#{$section-rotate}deg);
      }
    }
  }
}

section {
  flex: 1;
  position: relative;
  width: 100%;
  min-height: 20vh;
  overflow: hidden;
  z-index: 1;
  transition: flex-grow 0.2s, opacity 0.2s;
  &:before {
    content: "";
    position: absolute;
    width: 100%;
    height: 100%;
    background-position: center;
    background-size: cover;
    transition: transform 0.2s, width 0.2s;
  }
  &:after {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: black;
    opacity: 0;
    transition: opacity 0.2s;
  }
  @media ($mq-desktop) {
    width: 20%;
    height: 100vh;
    margin-right: -1px;
    transform: skewX(#{$section-rotate}deg) translateZ(0);
    &:before {
      left: -100%;
      width: 400%;
      transform: skewX(-#{$section-rotate}deg);
    }
  }
  @for $i from 1 through $section-amount {
    &:nth-child(#{$i}) {
      &:before {
        background-color: darken(red, $i * 5);
        background-image: url(#{$img-dir}#{$i}.jpg);
        .hide-images & {
          background-image: none;
        }
      }
    }
    &:first-child,
    &:last-child {
      &:before {
        background-color: darken(red, 30%);
      }
      &:after {
        opacity: 0.5;
      }
      article {
        display: none;
      }
    }
  }
}

article {
  position: relative;
  padding: 24px;
  width: 100%;
  height: 100%;
  text-align: center;
  color: white;
  z-index: 1;
  transition: opacity 0.2s, transform 0.2s;
  @media ($mq-desktop) {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
    margin: auto;
    opacity: 0;
    transform: translateY(24px) skewX(-#{$section-rotate}deg);
  }
}

h2 {
  font-size: 42px;
  margin-bottom: 12px;
  font-family: Noto Sans;
}

button {
  font-family: inherit;
  font-weight: bold;
  color: var(--white-1);

  letter-spacing: 2px;

  padding: 9px 20px;
  border: 1px solid var(--grey);
  border-radius: 1000px;
  background: transparent;
  transition: 0.3s;

  cursor: pointer;
}

#link_btn1 {
  background-color: rgba(207, 156, 1, 0.8) !important;
}

#link_btn2 {
  background-color: rgba(242, 153, 245, 0.6) !important;
}

#link_btn3 {
  background-color: rgba(1, 171, 207, 0.6) !important;
}

button:hover,
button:focus {
  color: var(--primary);
  background: hsla(var(--hue), 25%, 10%, 0.2);
  border-color: currentColor;
}

button:active {
  transform: translate(2px);
}
</style>