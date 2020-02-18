<template>
    <v-container class="gallery">
        <v-col v-for="(music, i) in musicTitle" :key="i" cols="4" width="auto">
        <v-row>
          <v-card :color="'purple'" dark>
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title class="headline" v-text="music.title.substring(0,music.title.length-4)"></v-card-title>

                <v-card-subtitle v-text="music.title.substring(0,music.title.length-4)"></v-card-subtitle>

                <div v-for="(video, ii) in videoUrl" :key="ii">
                    <!-- video에는 mp4 관련 정보 music에는 jpg관련 정보가 들어있음-->
                  <a
                    v-if="video.title.substring(0,video.title.length-4)===music.title.substring(0,music.title.length-4)"
                    v-bind:href="video.url"
                  >링크</a>
                </div>
              </div>

              <v-avatar class="ma-3" size="125" tile>
                <v-img :src="music.url"></v-img>
              </v-avatar>
            </div>
          </v-card>
          </v-row>
        </v-col>
    </v-container>
</template>


<script>
import { app } from "../../services/FirebaseServices";

import * as firebase from "firebase/app";
//import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";
import axios from "axios";

export default {
  data() {
    return {
      musicTitle: [],
      videoUrl: []
    };
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
<style lang="scss"></style>
<style scoped>
.container{
  width:100vw;
  height:auto;
}
.gallery{
  display:grid;
  grid-gap:10px;
}
</style>
