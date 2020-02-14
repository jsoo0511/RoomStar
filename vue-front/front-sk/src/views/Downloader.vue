<template>
  <div>
    <div id="testt">
      <!-- <button @click="downloadImg">download</button> -->
      <!-- <video autoplay loop muted src="" id="imgtag"></video> -->
      <button @click="test">download</button>

      <!-- <audio src="" id="music" type="audio/mp3"></audio> -->

      <table class="lectures">
        <tr v-for="(music, i) in musicTitle,musicUrl" :key="i">
          <td>
            {{ musicTitle[i].name }}
            
          </td>
          <td>
             <a href="">{{ music }} </a>
          </td>
        </tr>

        <!-- <tr v-for="(music, i) in musicUrl" :key="i">
          <td>
            {{ music }}
          </td>
        </tr> -->
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

export default {
  data() {
    return {
      musicUrl: [],
      musicTitle: [],
      musicTest:[]
    };
  },
  methods: {
    test() {
      // list에 있는 항목들을 불러옴

      //var temp=this;
      var storageRef = firebase.storage().ref();

      // storageRef.listAll.then(result => {
      //   result.items.forEach(name => {
      //     this.musicTitle.push(name);
      //   });
      // });

       storageRef
        .listAll()
        .then(result => {
          console.log(result);
          //this.musicUrl.push(result.items);
     
        
          result.items.forEach(musicRef => {
            this.musicTitle.push(musicRef);
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
