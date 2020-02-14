<template>
    <div>
        <div id="muisicList">
            <button @click="showList">list 보기</button>
        </div>
    </div>
</template>

<script>
import {app} from "../../services/FirebaseServices";

import * as firebase from 'firebase/app';
//import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";
import "../../assets/css/style.scss";
import "../../assets/css/user.scss";
import router from "@/routes";
import axios from "axios";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import swal from "sweetalert";
import UserApi from "../../apis/UserApi";


export default {
    components:{},
    showList(){
        
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
    } ,
  data: () => {
    return {
      dialog: false,
      email: "",
    
      error: {
        email: false,
      },
      isSubmit: false,
      component: this
    };
  }   
}
</script>
<style lang="scss"></style>
<style scoped>
#testt {
  margin-top: 70px !important;
  padding-top: 50px !important;
}
</style>