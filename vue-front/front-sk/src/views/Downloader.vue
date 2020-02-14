<template>
  <div>
    <div id="testt">
      <button @click="test">download</button>

      <table class="lectures">
        <tr v-for="(music, i) in musicTitle" :key="i">
          <td v-if="music.title != '창모- meteor.mp3'&music.title[music.title.length-1]==='3'">
          <!-- <td v-else-if="music.title[music.title.length-1]==='3'"> -->
            {{ music.title }}
          </td>

            <td v-if="music.title != '창모- meteor.mp3'&music.title[music.title.length-1]==='3'">
          <!-- <td v-else-if="music.title[music.title.length-1]==='3'"> -->
            <video controls name="media">
              <source :src="music.url" type="audio/mp3" />
            </video>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import { app } from "../services/FirebaseServices";

import * as firebase from "firebase/app";
//import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";
import axios from "axios";

export default {
  data() {
    return {
      musicTitle: [],
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
          result.items.forEach(musicRef => {
            let music = {};
            music.title = musicRef.name;
            //this.musicTitle.push(musicRef);
            console.log(musicRef);
            musicRef.getDownloadURL().then(url => {
              music.url = url;
              this.musicTitle.push(music);
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
            urlRef
              .getDownloadURL()
              .then(url => {
                this.musicUrl.push(url);
              })
              .catch(function(error) {});
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
