<template>
  <div>
    <div id="testt">
      <button @click="test">download</button>

      <table class="lectures">
        <tr v-for="(video, i) in videoList" :key="i">
          <td v-if="video.title != '창모- meteor.mp3'&video.title[video.title.length-1]==='4'">
          <!-- <td v-else-if="music.title[music.title.length-1]==='3'"> -->
            {{ video.title }}
          </td>

            <td v-if="video.title != '창모- meteor.mp3'&video.title[video.title.length-1]==='4'">
          <!-- <td v-else-if="music.title[music.title.length-1]==='3'"> -->
            <video controls name="media">
              <source :src="video.url" type="audio/mp3" />
            </video>
          </td>
        </tr>
      </table>
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

export default {
  data() {
    return {
      videoList: [],
    };
  },
  methods: {
    test() { // fire base list에 있는 항목들을 불러옴

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
          result.items.forEach(videoRef => {
            let video = {};
            video.title = videoRef.name;
            //this.musicTitle.push(musicRef);
            console.log(videoRef);
            videoRef.getDownloadURL().then(url => {
              video.url = url;
              this.videoTitle.push(video);
            });
            //console.log(musicRef.getDownloadURL().i);
          });
        })
        .catch(function(error) {});
    }
  }
};
</script>
<style lang="scss"></style>
<style scoped>
#testt {
  margin-top: 70px !important;
  padding-top: 50px !important;
}
</style>
