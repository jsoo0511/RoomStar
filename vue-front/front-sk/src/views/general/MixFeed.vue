<template>
  <div id="bg_feed">
    <!-- 업로드 하는 곳 따로 만들기 -->
    <!-- 제목, 내용, 게시자닉네임, 게시자아이디, 이미지 == null 이면 profile사진으로, (필수)동영상파일, 좋아요수-->
    <!-- crud -->

    <v-dialog v-model="uploadDialog" id="dialog" max-width="350">
      <template v-slot:activator="{ on }">
        <v-btn class="download" v-on="on">등록하기</v-btn>
      </template>
      <v-card id="modalUpload">
        <div>파일업로드하는곳</div>-----
        <input placeholder="제목" v-model="newTitle" />
        <br />
        <!-- <textarea placeholder="내용" v-model="newContent"></textarea> -->
        <v-text-field v-model="newContent" placeholder="내용" Solo max="500"></v-text-field>
        <br />
        <!-- 동영상 -->
        <span>동영상</span>
        <!-- change되면 무조건 업로드됨 -->
        <v-file-input
          type="file"
          accept="video/mp4"
          @change="detectFilesVideo($event.target.files)"
          label="동영상 업로드"
        ></v-file-input>
        <!-- <input type="file" accept="video/mp4" @change="detectFilesVideo($event.target.files)" /> -->
        <v-progress-linear
          class="progress-bar"
          :active="active"
          :background-opacity="opacity"
          :height="20"
          :query="query"
          :rounded="rounded"
          :striped="striped"
          :value="progressUpload"
          color="purple"
        >
          <strong>{{ progressUpload }}% 완료</strong>
        </v-progress-linear>
        <!-- 사진 -->
        <span>사진</span>
        <v-file-input
          type="file"
          accept="image/jpeg"
          @change="detectFilesImage($event.target.files)"
          label="사진 업로드"
        ></v-file-input>
        <!-- <input type="file" accept="image/jpeg" @change="detectFilesImage($event.target.files)" /> -->
        <br />

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
      <!-- 파이어베이스로 바꿔서 가져온애들을 나열하는 걸로 수정해야한다. -->
      <div v-for="(music, i) in musicTitle" :key="i">
        <div v-for="(video, ii) in videoUrl" :key="ii">
          <v-flex xs12 sm6 md3 style="float:left">
            <div
              class="item"
              v-if="
                video.title.substring(0, video.title.length - 4) ===
                  music.title.substring(0, music.title.length - 4)
              "
            >
              <v-dialog max-width="500">
                <template v-slot:activator="{ on }">
                  <!-- 이걸 클릭할때 해당것에 필요한 정보를 가져오게 한다. -->
                  <!-- methods 필요 -->
                  <div class="polaroid" v-on="on">
                    <img :src="music.url" />
                    <br />
                    <!-- title을 각 제목으로 수정 -->
                    <span class="caption" v-text="music.title.substring(0, music.title.length - 4)"></span>
                  </div>
                </template>
                <v-card id="modal">
                  <p>{{video.title}}</p>
                  <p>{{music.title}}</p>

                  <div>
                    <p>제목</p>
                    <!-- 비디오 src 추가 -->
                    <video width="500" ref="video" controls :style="videoStyles">
                      <source :src="video.url" type="video/mp4" />
                    </video>
                    <p>게시자</p>
                    <p>내용</p>

                    <span>좋아요 수</span>
                    <br />
                    <!-- true면 -->
                    <v-icon color="pink" v-if="!like()">mdi-thumb-up</v-icon>
                    <!-- false면 -->
                    <v-icon color="gray" v-if="!like()">mdi-thumb-down</v-icon>
                    <br />
                    <!-- axios 좋아요 연결 -->
                    <!-- 이미 사용자가 좋아요를 한 번 누른 영상이라면 더이상 누룰 수 없게 조절해야한다. -->
                    <v-btn @click="like">동영상 좋아요</v-btn>

                    <v-btn>나가기</v-btn>
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
      musicTitle: [],
      videoUrl: [],
      progressUpload: 0,
      file: File,
      uploadTaskVideo: "",
      uploadTaskImage: "",
      uploadDialog: false,
      userId: this.$session.get("userId"),
      profile: this.$session.get("profileImg"),
      userNickname: this.$session.get("userNickname"),
      videoDownloadUrl: null,
      imageDownloadUrl: null
    };
  },

  methods: {
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
    like() {
      //  아이디를 보내서 해당 유저가 좋아요를 누른 사람인지 아닌지를 확인해서 true false를 받아야한다.
      //  좋아요를 누른사람이라면 true와 현재 숫자를
      //  좋아요를 누르지않은사람이라면 false와 현재숫자를
      //  받은거에따라 return true or false 를 해서 보여주고 말고를 해야한다.

      // 보내서 확인
      axios
        .get(process.env.VUE_APP_SERVER_IP+"/count_like_video/")
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log(error);
          // this.loading = false;
        });

    },
    uploadContent(data) {
      // if imageDownloadUrl === null  => profile default image 전달
      console.log(data["newTitle"], data["newContent"]);
      console.log(this.userNickname, this.userId, this.profile);
      console.log(this.videoDownloadUrl, this.imageDownloadUrl);
      // 안들어오면 user profile로 대신 넣어준다.
      if (this.imageDownloadUrl === null) {
        this.imageDownloadUrl = this.profile;
      }

      data = {
        title: data["newTitle"],
        content: data["newContent"],
        usernickname: this.userNickname,
        userid: this.userId,
        videoUrl: this.videoDownloadUrl,
        imageUrl: this.imageDownloadUrl,
        like: 0
      };
      console.log(data);
      // 보내서 확인
      axios
        .get(process.env.VUE_APP_SERVER_IP+"/count_like_video/")
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log(error);
          // this.loading = false;
        });
    }
  },

  watch: {
    uploadTaskVideo: function() {
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
    // 모른 정보를 DB에서 불러온다.

    // 다른 api로 변경될 것
    // const SERVER_IP = "http://70.12.247.115:8080/insert_burst";
    axios
      .post()
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
        // this.loading = false;
      });

    // 이후 삭제 앞으로 DB에서 불러올 것
    // firebase list에 있는 항목들을 불러옴

    var storageRef = firebase.storage().ref();
    console.log("1234");
    console.log(storageRef);

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

            if (music.title[music.title.length - 1] == "g") {
              this.musicTitle.push(music);
            }
            console.log("1234");
            console.log(music);
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

<style scoped>
#modalUpload {
  padding: 30px;
  max-width: 350px;
  width: 100% !important;
  margin: 0 auto;
  border-radius: 2px;
  overflow: hidden;
}

#dialog {
  max-width: 350px;
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
img {
  max-width: 100%;
  height: auto;
}
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

</style>