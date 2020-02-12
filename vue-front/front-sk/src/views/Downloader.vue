<template>
  <div>
    <div id="testt">
      <button @click="downloadImg">download</button>
      <button @click="test">download</button>
      <img src="" id="imgtag1">
      <video autoplay loop muted src="" id="imgtag"></video>
    </div>
  </div>
</template>
<script>
import { app } from "../services/FirebaseServices";

import * as firebase from 'firebase/app';
//import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";

export default {
  methods: {
    downloadImg() {
      var storage = firebase.storage();
      var storageRef = firebase.storage().ref();
      var gsReference = storage.refFromURL(
        "gs://test-ff9ab.appspot.com/IU_SKD.mp4"
      );
      storageRef
        .child("IU_SKD.mp4")
        .getDownloadURL()
        .then(function(url) {
          var img = document.getElementById("imgtag");
          img.src = url;
        });
    },
    test() {  // list에 있는 항목들을 불러옴
    
      var storageRef = firebase.storage().ref();
      storageRef
        .listAll()
        .then(function(result) {
          console.log(result.items[0].location.path_);
          result.items.forEach(function(imageRef) {
            displayImage(imageRef);
            
          });
        })
        .catch(function(error) {});

      function displayImage(imageRef) {
        imageRef
          .getDownloadURL()
          .then(function(url) {
            
            var img = document.getElementById("imgtag1");
            img.src = url;
            
          })
          .catch(function(error) {});
      }
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
