<template>
  <v-flex
    xs12
    :class="classes"
  >
    <base-card
      :height="value.prominent ? 450 : 350"
      color="grey lighten-1"
      dark
      href="#!"
    >
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
    </base-card>
  </v-flex>
</template>

<script>
import { app } from "../../services/FirebaseServices";

import * as firebase from "firebase/app";
//import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";

  export default {
    data(){       //fire base list에 있는 항목을 불러옴
      return{
        videoList:[],
      };
    },
    created(){
      var storageRef = firebase.storage().ref();

      storageRef
      .listAll()
      .then(result =>{

        result.items.forEach(videoRef =>{
          let video={};
          video.title=videoRef.name;

          videoRef.getDownloadURL().then(url=>{
            video.url=url;
            this.videoList.push(video);
          });
        });
        
      })
      .catch(function(error){});


    },
    props: {
    key:{
      type:Number,
      required:true
    },
    music:{
      type:Object,
      default:  () => ({})
    }
    },
    computed: {
      classes () {
        return {
          'md6': this.size === 2,
          'md4': this.size === 3
        }
      }
    }
  }
</script>

<style scoped>
.container{
  width:100vw;
}
.v-image__image {
  transition: .3s linear;
}
#testt {
  margin-top: 70px !important;
  padding-top: 50px !important;
}
</style>