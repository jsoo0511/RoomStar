<template >
  <div>
  
  <div id="testt">
    <button @click="downloadImg">download</button>
    <video autoplay loop muted src="" id="imgtag"></video>
    
  </div>
  </div>
</template>
<script>

import { app } from '../services/FirebaseServices'

import firebase from 'firebase/app';
import 'firebase/firestore';
import 'firebase/storage';

export default {
  
  methods: {
    
    downloadImg(){
        var storage = firebase.storage();
        var storageRef = firebase.storage().ref();
        var gsReference = storage.refFromURL('gs://test-ff9ab.appspot.com/IU_SKD.mp4')
        storageRef.child('IU_SKD.mp4').getDownloadURL().then(function(url) {
        var img = document.getElementById('imgtag');
        img.src = url;
      });
    }
  }
};
</script>
<style lang="scss">
</style>
<style scoped>
#testt{
  margin-top:70px !important;
    padding-top:50px !important;
}
</style>
