<template>
<div id="test">

 
  <div v-show="show" id="loading">
           <div class="cssload-box-loading">
              <img
                src="../../assets/images/roomstar.png"
                style="margin-top:10vh; margin-left:-6vw; width:230px; height:auto ;"
              />
           </div>
        </div>

  <!-- <div v-show="show" id="demo"></div> -->
 
  <div class="bg_feed" v-show="!show">
    <br />
    <br />
    <br />
    <!-- 업로드 하는 곳 따로 만들기 -->
    <!-- 제목, 내용, 게시자닉네임, 게시자아이디, 이미지 == null 이면 profile사진으로, (필수)동영상파일, 좋아요수-->
    <!-- crud -->
    <h1 style="color:white" id="showOff_text">자랑하기</h1>

    <v-dialog v-model="uploadDialog" id="dialog" max-width="350">
      <template v-slot:activator="{ on }">
        <v-btn class="download" text v-on="on"></v-btn>
      </template>
      <v-card id="modalUpload">

        <div>파일업로드하는곳</div>
        <input placeholder="제목" v-model="newTitle" />
        <br />
        <!-- <textarea placeholder="내용" v-model="newContent"></textarea> -->
        <v-text-field v-model="newContent" placeholder="내용" Solo height="100"></v-text-field>
        <br />
        <!-- 동영상 -->
        <span>동영상</span>
        <!-- change되면 무조건 업로드됨 -->
        <input type="file" accept="video/mp4" @change="detectFilesVideo($event.target.files)" />

        <span>사진</span>

        <input type="file" accept="image/jpeg" @change="detectFilesImage($event.target.files)" />
        <br />
        <v-progress-linear class="progress-bar" :height="20" :value="progressUpload" color="purple">
          <strong>{{ progressUpload }}% 완료</strong>
        </v-progress-linear>

        <v-btn text small color="black" dark @click="uploadDialog = false">취소</v-btn>
        <v-btn
          text
          small
          color="black"
          type="submit"
          @click="uploadContent({newTitle, newContent})"
          v-if="progressUpload !== 100"
          disabled
        >등록</v-btn>
        <v-btn
          text
          small
          color="black"
          type="submit"
          @click="uploadContent({newTitle, newContent})"
          v-if="progressUpload === 100"
        >등록</v-btn>
      </v-card>
    </v-dialog>

    <div class="wrapper">
      <div v-for="(item, i) in info[0]" :key="i">
        <v-flex xs12 sm6 md3 style="float:left">
          <div class="item">
            <v-dialog v-model="itemDialog[i]" max-width="500" max-height="350">
              <template v-slot:activator="{ on }">
                <!-- 이걸 클릭할때 해당것에 필요한 정보를 가져오게 한다. -->
                <div @click="checkLike(userId, item.id)" class="polaroid" v-on="on">
                  <img id="picture" :src="item.imgURL" />
                  <br />
                  <!-- title을 각 제목으로 수정 -->
                  <span class="caption" v-text="item.title"></span>
                </div>
              </template>
              <!-- item하나 선택하고 -> 그 아이템에 관한 id값으로 axios로 데이터 받고
              그 데이터 받은걸 v-card에 갱신-->
              <v-card id="modal">
                <div>
                  <p id="ptag">{{item.title}}</p>
                  <!-- 비디오 src 추가 max-width:640px; -->
                  <video style="width: 100%;height: auto" ref="video" controls>
                    <source :src="item.videoURL" type="video/mp4" />
                  </video>
                  <p class="resultOwner">게시자:{{item.nickname}}</p>
                  <p class="resultContent">내용: {{item.contents}}</p>
                  <!-- 좋아요 수: -->
                  <!-- <p id="newLike">{{ item.like_num }}</p> -->
                  <!-- {{item.videoURL}} -->
                  <!-- 열자마자 좋아요 누른사람인지 아닌지 판단해주는것 서버에 요청 -->
                  <!-- true면 -->
                  <v-icon
                    color="pink"
                    @click="like(item.id) & item.like_num - 1"
                    v-if="checkLikeStatus === 'false'"
                  >mdi-thumb-up</v-icon>
                  <!-- false면 -->
                  <v-icon
                    color="gray"
                    @click="like(item.id) & item.like_num + 1"
                    v-else-if="checkLikeStatus === 'true'"
                  >mdi-thumb-up</v-icon>
                  <br />
                  <v-btn text small color="black" type="submit" @click="dClose(i)">나가기</v-btn>
                  <v-btn
                    text
                    small
                    color="black"
                    type="submit"
                    v-if="userId == item.userid"
                    @click="deleteItem(item.id)"
                  >삭제</v-btn>
                  <br />
                </div>
              </v-card>
            </v-dialog>
          </div>
        </v-flex>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import { app } from "../../services/FirebaseServices";
import * as firebase from "firebase/app";
import "firebase/firestore";
import "firebase/storage";
import axios from "axios";
import router from "@/routes";

export default {
  
  data() {
    return {

      show: true,
      musicTitle: [],
      videoUrl: [],
      info: [],
      progressUpload: 0,
      file: File,
      itemDialog: [],
      checkLikeStatus: "false",
      uploadTaskVideo: "",
      uploadTaskImage: "",
      uploadDialog: false,
      userId: this.$session.get("userId"),
      profile: this.$session.get("profileImg"),
      userNickname: this.$session.get("userNickname"),
      videoDownloadUrl: null,
      imageDownloadUrl: null,
      newTitle: null,
      newContent: null
    };
  },

  methods: {
    dClose(idx) {
      this.$set(this.itemDialog, idx, false);
    },
    detectFilesVideo(fileList) {
      Array.from(Array(fileList.length).keys()).map(x => {
        this.uploadVideo(fileList[x]);
      });
    },
    detectFilesImage(fileList) {
      Array.from(Array(fileList.length).keys()).map(x => {
        this.uploadImage(fileList[x]);
      });
    },
    uploadVideo(file) {
      this.uploadTaskVideo = firebase
        .storage(app)
        .ref(file.name)
        .put(file);
    },
    uploadImage(file) {
      this.uploadTaskImage = firebase
        .storage(app)
        .ref(file.name)
        .put(file);
    },
    like(data) {
      //  아이디를 보내서 해당 유저가 좋아요를 누른 사람인지 아닌지를 확인해서 true false를 받아야한다.
      //  좋아요를 누른사람이라면 true와 현재 숫자를
      //  좋아요를 누르지않은사람이라면 false와 현재숫자를
      //  받은거에따라 return true or false 를 해서 보여주고 말고를 해야한다.
      // 보내서 확인
      let params = {
        userid: this.userId,
        id: data
      };
      console.log(params);
      // userid videoURL

      axios
        .put(process.env.VUE_APP_SERVER_IP + "/update_like_video", params)
        .then(response => {
          console.log(
            "response for update_like_video",
            response,
            response.data.data,
            response.data,
            response.data.num
          );

          // 이게 첫번째만 가능 그래서 코치님한테 물어보기
          // document.getElementById("newLike").innerText = response.data.num;
          this.checkLikeStatus = response.data.data;
          return response.data.data;
        })
        .catch(error => {
          console.log(error);
          // this.loading = false;
        });
    },
    checkLike(userid, id) {
      // checkLikeStatus
      console.log("data", userid, id);
      let params = {
        userid,
        id
      };
      console.log(params);
      axios
        .post(process.env.VUE_APP_SERVER_IP + "/check", params)
        .then(response => {
          this.checkLikeStatus = response.data.data;
          console.log(this.checkLikeStatus);
        })
        .catch(error => {
          console.log(error);
        });
      axios
        .get(process.env.VUE_APP_SERVER_IP + "/get_burst/" + id)
        .then(response => {
          return response.data.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    deleteItem(id) {
      axios
        .delete(process.env.VUE_APP_SERVER_IP + "/delete_burst/" + id)
        .then(response => {
          console.log("response for delete_burst", response);
        })
        .catch(error => {
          console.log(error);
        });
    },

    uploadContent(data) {
      console.log(data["newTitle"], data["newContent"]);
      console.log(this.userNickname, this.userId, this.profile);
      console.log(this.videoDownloadUrl, this.imageDownloadUrl);
      if (this.imageDownloadUrl === null) {
        this.imageDownloadUrl = this.profile;
      }

      if (this.imageDownloadUrl === undefined) {
        this.imageDownloadUrl =
          "https://firebasestorage.googleapis.com/v0/b/test-ff9ab.appspot.com/o/%EC%95%84%EC%9D%B4%EC%9C%A0-%EA%B8%88%EC%9A%94%EC%9D%BC%EC%97%90%20%EB%A7%8C%EB%82%98%EC%9A%94.jpg?alt=media&token=11a481ff-501b-48af-a37a-31ce5e9b93b0";
      }
      let params = {
        videoURL: this.videoDownloadUrl,
        like_num: 0,
        userid: String(this.userId),
        imgURL: this.imageDownloadUrl,
        nickname: this.userNickname,
        title: data["newTitle"],
        contents: data["newContent"]
      };
      console.log(params);
      // 보내서 확인
      console.log("sdds");
      axios
        .post(process.env.VUE_APP_SERVER_IP + "/insert_burst", params)
        .then(response => {
          console.log("response for insert_burst", response);
          // 등록이 되면 바로 동적으로 씌워져야하는데, update를 하게 해야한다.
          // 이런경우 watch를 사용하는게 맞겠지?
          this.uploadDialog = false;
        })
        .catch(error => {
          console.log(error);
        });
    }
  },

  watch: {
    uploadTaskVideo: function() {
      console.log("-----------------");
      this.uploadTaskVideo.on(
        "state_changed",
        sp => {
          this.progressUpload = Math.floor(
            (sp.bytesTransferred / sp.totalBytes) * 100
          );
        },
        null,
        () => {
          this.uploadTaskVideo.snapshot.ref
            .getDownloadURL()
            .then(downloadURL => {
              this.$emit("url", downloadURL);
              console.log("여기가 ");
              //videoUrl 저장, 게시자이름도 함께
              console.log(downloadURL);
              this.videoDownloadUrl = downloadURL;
              console.log(this.videoDownloadUrl);
              // *********************생성된 url 정보를 데이터에서 갱신하고 버튼이 눌리면 그때 같이 보내도록 하자
            });
        }
      );
    },
    uploadTaskImage: function() {
      this.uploadTaskImage.on(
        "state_changed",
        sp => {
          this.progressUpload = Math.floor(
            (sp.bytesTransferred / sp.totalBytes) * 100
          );
        },
        null,
        () => {
          this.uploadTaskImage.snapshot.ref
            .getDownloadURL()
            .then(downloadURL => {
              this.$emit("url", downloadURL);
              console.log("여기가 ");
              //imageUrl 저장, 게시자이름도 함께
              console.log(downloadURL);
              this.imageDownloadUrl = downloadURL;
              // *********************생성된 url 정보를 데이터에서 갱신하고 버튼이 눌리면 그때 같이 보내도록 하자
              // DB에 사용자, 이미지의 URL 전송
            });
        }
      );
    }
  },
  created() {
    this.show=true;

    
    // 모른 정보를 DB에서 불러온다.
    // 다른 api로 변경될 것
    axios
      .get(process.env.VUE_APP_SERVER_IP + "/get_burst")
      .then(response => {
        console.log("get_burst", response);
        console.log("--------------------", response);
        // db 정보를 나열하기
        console.log(response.data.data.length);
        for (let i = 0; i < response.data.data.length; i++) {
          this.itemDialog.push(false);
          console.log(this.itemDialog[i]);
          console.log("=================");
        }
        console.log(this.itemDialog);
        this.info.push(response.data.data);
        console.log("clean", this.info[0]);
      })
      .catch(error => {
        console.log(error);
      });
    var storageRef = firebase.storage().ref();
    storageRef
      .listAll()
      .then(result => {
        result.items.forEach(musicRef => {
          let music = {};

          music.title = musicRef.name;
          console.log(musicRef);
          musicRef.getDownloadURL().then(url => {
            music.url = url;
            if (music.title[music.title.length - 1] == "g") {
              this.musicTitle.push(music);
            }
          });
        });
      })
      .catch(function(error) {});

    storageRef
      .listAll()
      .then(result => {
        console.log(result);
        result.items.forEach(urlRef => {
          let video = {};
          video.title = urlRef.name;
          urlRef
            .getDownloadURL()
            .then(url => {
              video.url = url;
              if (video.title[video.title.length - 1] == "4"||video.title[video.title.length - 1] == "V") {
                this.videoUrl.push(video);
              }
            })
            .catch(function(error) {});
        });
      })
      .catch(function(error) {});
      
      console.log(this.show);
      setTimeout(() => {
        this.show=false;
}, 1000);
      console.log(this.show);
  }
};
</script>

<style scoped>
body
{
  margin:0;
  padding:0
}

#loading {
    background: #DA22FF;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #9733EE, #DA22FF);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #9733EE, #DA22FF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

    height: 100%;
    width: 100%;
    position: fixed;
    z-index: 1;
    margin-top: 0px;
    top: 0px;
    z-index: 999999;
    display: block;
}


/*-------------------------
    33.Preloader CSS
---------------------------*/
.loadding-page {
  width: 100%;
  height: 100%;
  background: #fdd245;
  overflow: hidden;
  position: fixed;
  top: 0;
  z-index: 9999;
}
.cssload-box-loading {
  width: 49px;
  height: 49px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}
.cssload-box-loading:before {
  content: '';
  width: 49px;
  height: 5px;
  background: #000000;
  opacity: 0.1;
  position: absolute;
  top: 58px;
  left: 0;
  border-radius: 50%;
  animation: shadow 0.58s linear infinite;
  -o-animation: shadow 0.58s linear infinite;
  -ms-animation: shadow 0.58s linear infinite;
  -webkit-animation: shadow 0.58s linear infinite;
  -moz-animation: shadow 0.58s linear infinite;
}
.cssload-box-loading:after {
  content: '';
  width: 49px;
  height: 49px;
  background: #fdd245;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 3px;
  animation: cssload-animate 0.58s linear infinite;
  -o-animation: cssload-animate 0.58s linear infinite;
  -ms-animation: cssload-animate 0.58s linear infinite;
  -webkit-animation: cssload-animate 0.58s linear infinite;
  -moz-animation: cssload-animate 0.58s linear infinite;
}
@keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    transform: translateY(0) rotate(90deg);
  }
}
@-o-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -o-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -o-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -o-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -o-transform: translateY(0) rotate(90deg);
  }
}
@-ms-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -ms-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -ms-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -ms-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -ms-transform: translateY(0) rotate(90deg);
  }
}
@-webkit-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -webkit-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -webkit-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -webkit-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(90deg);
  }
}
@-moz-keyframes cssload-animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    -moz-transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    -moz-transform: translateY(18px) scale(1, 0.9) rotate(45deg);
    border-bottom-right-radius: 39px;
  }
  75% {
    -moz-transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    -moz-transform: translateY(0) rotate(90deg);
  }
}
@keyframes shadow {
  0%,
  100% {
    transform: scale(1, 1);
  }
  50% {
    transform: scale(1.2, 1);
  }
}
@-o-keyframes shadow {
  0%,
  100% {
    -o-transform: scale(1, 1);
  }
  50% {
    -o-transform: scale(1.2, 1);
  }
}
@-ms-keyframes shadow {
  0%,
  100% {
    -ms-transform: scale(1, 1);
  }
  50% {
    -ms-transform: scale(1.2, 1);
  }
}
@-webkit-keyframes shadow {
  0%,
  100% {
    -webkit-transform: scale(1, 1);
  }
  50% {
    -webkit-transform: scale(1.2, 1);
  }
}
@-moz-keyframes shadow {
  0%,
  100% {
    -moz-transform: scale(1, 1);
  }
  50% {
    -moz-transform: scale(1.2, 1);
  }
}
#modalUpload {
  padding: 30px;
  max-width: 350px;
  width: 100% !important;
  margin: 0 auto;
  border-radius: 2px;
  overflow: hidden;
}
#modal {
  margin: 0 auto;
  border-radius: 2px;
  overflow: hidden;
}
#ptag {
  font-size: 30px;
  margin-left: 1em;
}
#dialog {
  max-width: 350px;
}

.resultOwner {
  font-family: "Indie Flower";
}

.resultContent {
  font-family: "Indie Flower";
}
* {
  box-sizing: border-box;
}
body {
  font-family: "Indie Flower";
  line-height: 1.618em;
  background-color: #e4d4bb;
  background-image: repeating-radial-gradient(
    circle,
    #e4d4bb,
    #e7dac6 50%,
    #e7dac6 100%
  );
  background-size: 10px 10px;
}

  #picture{
  max-width: 100%;
  height: auto;
  }

.download {
  height: 30vh !important;
  width: 20vw !important;
  margin-top: -20vh;
  margin-left: 70vw;
  background-image: url("../../../src/assets/images/btntree.png");
}

.download:hover {
  height: 30vh !important;
  width: 20vw !important;
  margin-top: -20vh;
  margin-left: 70vw;
  background-image: url("../../../src/assets/images/btntree2.png");
}
/* background: url("https://cdn.pixabay.com/photo/2015/01/07/16/37/wood-591631_1280.jpg")
    no-repeat; */
.wrapper {
  width: 100%;
  padding: 0 2rem;
  text-align: center;
}
.polaroid {
  background: #fff;
  padding: 1rem;
  box-shadow: 0 0.25rem 1rem rgba(0, 0, 0, 0.2);
}
.caption {
  font-size: 1.125rem;
  text-align: center;
  line-height: 2em;
}
.item {
  display: inline-block;
  margin-top: 2rem;
  filter: grayscale(50%);
}
.item .polaroid:before {
  content: "";
  position: absolute;
  z-index: -1;
  transition: all 0.35s;
}
.item:nth-of-type(4n + 1) {
  transform: scale(0.8, 0.8) rotate(5deg);
  transition: all 0.35s;
}
.item:nth-of-type(4n + 1) .polaroid:before {
  transform: rotate(6deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  right: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.4);
}
.item:nth-of-type(4n + 2) {
  transform: scale(0.8, 0.8) rotate(-5deg);
  transition: all 0.35s;
}
.item:nth-of-type(4n + 2) .polaroid:before {
  transform: rotate(-6deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  left: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.4);
}
.item:nth-of-type(4n + 4) {
  transform: scale(0.8, 0.8) rotate(3deg);
  transition: all 0.35s;
}
.item:nth-of-type(4n + 4) .polaroid:before {
  transform: rotate(4deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  right: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.3);
}
.item:nth-of-type(4n + 3) {
  transform: scale(0.8, 0.8) rotate(-3deg);
  transition: all 0.35s;
}
.item:nth-of-type(4n + 3) .polaroid:before {
  transform: rotate(-4deg);
  height: 20%;
  width: 47%;
  bottom: 30px;
  left: 12px;
  box-shadow: 0 2.1rem 2rem rgba(0, 0, 0, 0.3);
}
.item:hover {
  filter: none;
  transform: scale(1, 1) rotate(0deg) !important;
  transition: all 0.35s;
}
.item:hover .polaroid:before {
  content: "";
  position: absolute;
  z-index: -1;
  transform: rotate(0deg);
  height: 90%;
  width: 90%;
  bottom: 0%;
  right: 5%;
  box-shadow: 0 1rem 3rem rgba(0, 0, 0, 0.2);
  transition: all 0.35s;
}

.bg_feed {
  background-image: url("../../assets/images/bg_showoff.png");
  background-repeat: repeat;
  width: 100vw;
  height: auto;
}

#showOff_text {
  text-align: center;
  font-size: 3rem;
}

#test{
    background-image: url("../../assets/images/bg_showoff.png");
  background-repeat: repeat;
  width: 100vw;
  height: auto;
}



</style>