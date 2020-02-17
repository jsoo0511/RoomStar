<template>
  <v-card max-width="400" class="mx-auto">
    <v-system-bar color="pink darken-2" dark>
      <v-spacer></v-spacer>

      <v-icon>mdi-window-minimize</v-icon>

      <v-icon>mdi-window-maximize</v-icon>

      <v-icon>mdi-close</v-icon>
    </v-system-bar>

    <v-app-bar dark color="pink">
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>My Music</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-app-bar>

    <!-- 업로드 부분-->
    <div id="testt">
      <input
        type="file"
        multiple
        accept="image/jpeg"
        @change="detectFiles($event.target.files)"
      />
      <div class="progress-bar">{{ progressUpload }}% 완료</div>
    </div>
    <!--             -->

    <v-container>
      <v-row dense>
        <v-col v-for="(music, i) in musicTitle" :key="i" cols="12">
          <div v-for="(video, ii) in videoUrl" :key="ii">
            <v-card
              :color="'purple'"
              dark
              v-if="
                video.title.substring(0, video.title.length - 4) ===
                  music.title.substring(0, music.title.length - 4)
              "
            >
              <div class="d-flex flex-no-wrap justify-space-between">
                <div>
                  <v-card-title
                    class="headline"
                    v-text="music.title.substring(0, music.title.length - 4)"
                  ></v-card-title>

                  <!-- <v-card-subtitle v-text="music.title.substring(0,music.title.length-4)"></v-card-subtitle> -->

                  <!-- video에는 mp4 관련 정보 music에는 jpg관련 정보가 들어있음-->

                  <a
                    v-if="
                      video.title.substring(0, video.title.length - 4) ===
                        music.title.substring(0, music.title.length - 4)
                    "
                    v-bind:href="video.url"
                    >링크</a
                  >
                  <br />
                  <!-- 해당 링크의 비디오가 그려지는 곳으로 이동 // 모달을 띄우자 -->
                  <v-dialog v-model="dialog" id="dialog" max-width="350">
                    <template v-slot:activator="{ on }">
                      <v-btn
                        color="transparent"
                        dark
                        v-on="on"
                        >보러가기</v-btn
                      >
                    </template>

                    <v-card>
                      <div>
                        <!-- 비디오 src 추가 -->
                        <video></video>
                        <!-- axios 좋아요 연결 -->
                        <v-btn></v-btn>
                      동영상 좋아요
                      <br>
                      </div>
                    </v-card>
                  </v-dialog>
                </div>

                <v-avatar class="ma-3" size="125" tile>
                  <!-- 앨범 커버 가져오는 부분-->
                  <v-img :src="music.url"></v-img>
                </v-avatar>
              </div>
            </v-card>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import { app } from "../../services/FirebaseServices";

import * as firebase from "firebase/app";
//import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";
import axios from "axios";
import router from "@/routes";

export default {
  data() {
    return {
      musicTitle: [],
      videoUrl: [],
      progressUpload: 0,
      file: File,
      uploadTask: "",
      dialog: false
    };
  },

  methods: {
    detectFiles(fileList) {
      Array.from(Array(fileList.length).keys()).map(x => {
        this.upload(fileList[x]);
      });
    },
    upload(file) {
      this.uploadTask = firebase
        .storage(app)
        .ref(file.name)
        .put(file);
    }
  },

  watch: {
    uploadTask: function() {
      const userId = this.$session.get("userId");
      this.uploadTask.on(
        "state_changed",
        sp => {
          this.progressUpload = Math.floor(
            (sp.bytesTransferred / sp.totalBytes) * 100
          );
        },
        null,
        () => {
          this.uploadTask.snapshot.ref.getDownloadURL().then(downloadURL => {
            this.$emit("url", downloadURL);
            console.log("여기가 ");
            console.log(downloadURL);

            // DB에 사용자, 동영상의 URL 전송
            const SERVER_IP = "http://70.12.247.115:8080/insert_burst";

            let data = {
              userid: userId,
              videoURL: downloadURL
            };

            axios
              .post(SERVER_IP, data)
              .then(response => {
                this.$router.push("/");
              })
              .catch(error => {
                console.log(error);
                // this.loading = false;
              });
          });
        }
      );
    }
  },
  created() {
    // fire base list에 있는 항목들을 불러옴

    //var temp=this;
    var storageRef = firebase.storage().ref();
    console.log("1234");
    console.log(storageRef);

    // storageRef.listAll.then(result => {
    //   result.items.forEach(name => {
    //     this.musicTitle.push(name);
    //   });
    // });

    storageRef
      .listAll()
      .then(result => {
        console.log("333");

        //this.musicUrl.push(result.items);
        result.items.forEach(musicRef => {
          let music = {};

          music.title = musicRef.name;
          //this.musicTitle.push(musicRef);
          console.log(musicRef);
          musicRef.getDownloadURL().then(url => {
            music.url = url;

            if (music.title[music.title.length - 1] == "g") {
              this.musicTitle.push(music);
            }
            console.log("1234");
            console.log(music);
          });
          //console.log(musicRef.getDownloadURL().i);
        });
      })
      .catch(function(error) {});

    storageRef
      .listAll()
      .then(result => {
        console.log(result);
        //this.musicUrl.push(result.items);
        result.items.forEach(urlRef => {
          let video = {};
          video.title = urlRef.name;

          urlRef
            .getDownloadURL()
            .then(url => {
              video.url = url;
              if (video.title[video.title.length - 1] == "4") {
                this.videoUrl.push(video);
              }
            })
            .catch(function(error) {});
        });
      })
      .catch(function(error) {});
  }
};
</script>
