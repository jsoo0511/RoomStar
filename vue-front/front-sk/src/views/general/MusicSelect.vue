<template>
  <div>
    <br />
    <br />
    <br />

    <v-dialog v-model="dialog" id="dialog" max-width="500">
      <template v-slot:activator="{ on }">
        <v-btn dark v-on="on">노래고르는버튼</v-btn>
      </template>

      <v-card>
        <div>
          <v-radio-group v-model="musicSelectedOne" :mandatory="false">
            <ul v-for="(music, i) in musicTitle" :key="i">
              <li v-if="music.title != '창모- meteor.mp3'&music.title[music.title.length-1]==='3'">
                <!-- music.url이라는 value가 musicSelectedOne이라는 이름으로 들어간다. -->
                <v-radio color="orange" :value="music" :label="music.title"></v-radio>

                <hr />
              </li>
            </ul>
          </v-radio-group>

          <div class="wrapper">
            <div class="cols" v-for="(music, i) in musicTitle" :key="i">

              <div v-for="(image, ii) in imageUrl" :key="ii">
                <!-- v-if="image.title.substring(0,image.title.length-4)===music.title.substring(0,music.title.length-4)" -->

                <div
                  class="col"
                  v-if="music.title != '창모- meteor.mp3'&music.title[music.title.length-1]==='3'&image.title.substring(0,image.title.length-4)===music.title.substring(0,music.title.length-4)"
                  ontouchstart="this.classList.toggle('hover');"
                >
                  <div class="container">
                    <div class="front" :style="{ 'background-image': 'url(' + image.url + ')' }">
                      <div class="inner">
                        <p>{{ music.title }}</p>
                      </div>
                    </div>

                    <div class="back">
                      <div class="inner">
                        <p>{{ music.title }}</p>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- -->
              </div>
            </div>
          </div>
          <!-- 선택하면 선택한 것만 music.url을 보내기 -->
          <!-- <v-btn type="submit" @click="trigger(musicSelectedOne)">선택완료1</v-btn> -->
          <v-btn type="submit" @click="dialog = false & trigger(musicSelectedOne.url)">선택완료</v-btn>
        </div>
      </v-card>
    </v-dialog>
    <div>
      <br />
      <!-- this.로 접근하든 그냥 접근하든 같은 값이 나온다. -->
      <!-- {{ musicSelectedOne.title }} -->
      <!-- {{ musicSelectedOne.url }} -->
      <!-- musicSelectedOne이 있는 경우에만 -->
      <div v-if="musicSelectedOne">
        <video controls name="media">
          <source :src="musicSelectedOne.url" type="audio/mp3" />
        </video>
      </div>
    </div>
  </div>
</template>

<script>
import { app } from "../../services/FirebaseServices";
import * as firebase from "firebase/app";
//import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";
import axios from "axios";

// socket
import io from "socket.io-client";
import net from "net";

export default {
  data() {
    return {
      musicUrl: [],
      musicTitle: [],
      musicSelected: [],
      imageUrl: [], //앨범 커버 담는 배열
      dialog: false,
      // 선택되면 null에서 다른 값으로 바뀐다.
      musicSelectedOne: null,
      socket: null
    };
  },
  mounted() {
    //서버의 변경사항을 수신
    this.socket.on("GET_MUSIC_SELECTED", data => {
      this.musicSelectedOne = data["data"];
      console.log(data);
    });
  },
  created() {
    this.socket = io.connect("http://localhost:8080", {
      transports: ["websocket"]
    });
    // list에 있는 항목들을 불러옴

    var storageRef = firebase.storage().ref();

    storageRef
      .listAll()
      .then(result => {
        result.items.forEach(musicRef => {
          let music = {};
          let image = {};
          music.title = musicRef.name;
          image.title = musicRef.name;
          console.log(musicRef);
          musicRef.getDownloadURL().then(url => {
            //앨범 커버 정보를 담음
            image.url = url;

            if (image.title[image.title.length - 1] == "g") {
              this.imageUrl.push(image);
            }

            //음악 정보를 담음
            music.url = url;
            this.musicTitle.push(music);
          });
        });
      })
      .catch(function(error) {});
  },
  methods: {
    trigger: function(data) {
      console.log("trigger 시작했습니다.");
      this.socket.emit("SEND_MUSIC_SELECTED", { data });
      console.log("데이터 선택========", data);

      // musicSelectedOne.title 로 검색해서 lyrics
      // axios 로 받아서 lyrics 보여주기
    }
  }
};
</script>
<style lang="scss"></style>
<style scoped>
* {
  margin: 0;
  padding: 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

h1 {
  font-size: 1.5rem;
  font-family: "Montserrat";
  font-weight: normal;
  color: #444;
  text-align: center;
  margin: 2rem 0;
}

.wrapper {
  width: 90%;
  margin: 0 auto;
  max-width: 100rem;
}

.cols {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
}

.col {
  width: calc(25% - 2rem);
  margin: 1rem;
  cursor: pointer;
}

.container {
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  -webkit-perspective: 1000px;
  perspective: 1000px;
}

.front,
.back {
  background-size: cover;
  background-position: center;
  -webkit-transition: -webkit-transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  transition: -webkit-transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  -o-transition: transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  transition: transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  transition: transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1),
    -webkit-transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  text-align: center;
  min-height: 150px;
  height: auto;
  border-radius: 10px;
  color: #fff;
  font-size: 1.5rem;
}

.back {
  background: #cedce7;
  background: -webkit-linear-gradient(45deg, #cedce7 0%, #596a72 100%);
  background: -o-linear-gradient(45deg, #cedce7 0%, #596a72 100%);
  background: linear-gradient(45deg, #cedce7 0%, #596a72 100%);
}

.front:after {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  width: 100%;
  height: 100%;
  content: "";
  display: block;
  opacity: 0.6;
  background-color: #000;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  border-radius: 10px;
}
.container:hover .front,
.container:hover .back {
  -webkit-transition: -webkit-transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  transition: -webkit-transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  -o-transition: transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  transition: transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
  transition: transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1),
    -webkit-transform 0.7s cubic-bezier(0.4, 0.2, 0.2, 1);
}

.back {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}

.inner {
  -webkit-transform: translateY(-50%) translateZ(60px) scale(0.94);
  transform: translateY(-50%) translateZ(60px) scale(0.94);
  top: 50%;
  position: absolute;
  left: 0;
  width: 100%;
  padding: 2rem;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  outline: 1px solid transparent;
  -webkit-perspective: inherit;
  perspective: inherit;
  z-index: 2;
}

.container .back {
  -webkit-transform: rotateY(180deg);
  transform: rotateY(180deg);
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
}

.container .front {
  -webkit-transform: rotateY(0deg);
  transform: rotateY(0deg);
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
}

.container:hover .back {
  -webkit-transform: rotateY(0deg);
  transform: rotateY(0deg);
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
}

.container:hover .front {
  -webkit-transform: rotateY(-180deg);
  transform: rotateY(-180deg);
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
}

.front .inner p {
  font-size: 1rem;
  margin-bottom: 2rem;
  position: relative;
}

.front .inner p:after {
  content: "";
  width: 4rem;
  height: 2px;
  position: absolute;
  background: #c6d4df;
  display: block;
  left: 0;
  right: 0;
  margin: 0 auto;
  bottom: -0.75rem;
}

.front .inner span {
  color: rgba(255, 255, 255, 0.7);
  font-family: "Montserrat";
  font-weight: 300;
}

@media screen and (max-width: 64rem) {
  .col {
    width: calc(33.333333% - 2rem);
  }
}

@media screen and (max-width: 48rem) {
  .col {
    width: calc(50% - 2rem);
  }
}

@media screen and (max-width: 32rem) {
  .col {
    width: 100%;
    margin: 0 0 2rem 0;
  }
}
</style>