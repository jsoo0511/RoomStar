<template>
  <div>
    <br />
    <br />
    <br />

    <v-dialog v-model="dialog" id="dialog" max-width="350">
      <template v-slot:activator="{ on }">
        <v-btn @click="musicList" dark v-on="on">노래고르는버튼</v-btn>
      </template>

      <v-card>
        <div>
          <v-radio-group v-model="radios" :mandatory="false">
            <ul v-for="(music, i) in musicTitle" :key="i">
              <li v-if="music.title != '창모- meteor.mp3'&music.title[music.title.length-1]==='3'">
                <v-radio color="orange" :value="music.title" :label="music.title"></v-radio>
                <!-- 여기서 checked된 값을 저장해서 music.url을 저장함 -->
                <!-- <video controls name="media">
                <source :src="music.url" type="audio/mp3" />
                </video>-->
              </li>
            </ul>
          </v-radio-group>
          <!-- 선택하면 선택한 것만 music.url을 보내기 -->
          <v-btn type="submit" @click="dialog = false">선택완료</v-btn>
        </div>
      </v-card>
    </v-dialog>
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
      musicUrl: [],
      musicTitle: [],
      musicSelected: [],
      dialog: false
    };
  },
  created() {
    // list에 있는 항목들을 불러옴

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

        result.items.forEach(musicRef => {
          let music = {};
          music.title = musicRef.name;
          console.log(musicRef);
          musicRef.getDownloadURL().then(url => {
            music.url = url;
            this.musicTitle.push(music);
          });
        });
      })
      .catch(function(error) {});
  },
  methods: {
    test() {
      // list에 있는 항목들을 불러옴

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
</style>