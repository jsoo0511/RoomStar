import Password from "./views/user/Password.vue";

import webCam from './webcam/webCam.vue'
import HomePage from "./views/general/HomePage.vue";
import Introduce from "./views/general/Introduce.vue";
import MyPage from "./views/general/MyPage.vue";
import WaitingRoom from "./views/general/WaitingRoom.vue";
import SharingPage from "./views/general/SharingPage.vue";

import GameRoom from "./views/general/GameRoom.vue";
import firstGameRoom from "./views/gamerooms/firstGameRoom.vue"
import secondGameRoom from "./views/gamerooms/secondGameRoom.vue"
import thirdGameRoom from "./views/gamerooms/thirdGameRoom.vue"
import fourthGameRoom from "./views/gamerooms/fourthGameRoom.vue"

import Components from './views/Components.vue'
import Vue from 'vue'
import VueRouter from 'vue-router'

//upload
import FileUploader from './views/FileUploader.vue'
import Downloader from './views/Downloader.vue'

// test(bumi)
import tempFeed from './views/general/tempFeed.vue'
import MixFeed from './views/general/MixFeed.vue'

Vue.use(VueRouter)

export default [
  {
    path: "/components",
    name: "Components",
    component: Components
  },
  {
    path: "/",
    name: "HomePage",
    component: HomePage
  },
  {
    path:"/introduce",
    name: "introduce",
    component: Introduce
  },
  {
    path:"/webCam",
    name:"webCam",
    component : webCam
  },
  {
    path:"/myPage",
    name:"myPage",
    component : MyPage
  },
  {
    path:"/gameRoom",
    name:"gameRoom",
    component : GameRoom
  },
  {
    path:"/firstGameRoom",
    name:"firstGameRoom",
    component : firstGameRoom
  },
  {
    path:"/secondGameRoom",
    name:"secondGameRoom",
    component : secondGameRoom
  },
  {
    path:"/thirdGameRoom",
    name:"thirdGameRoom",
    component : thirdGameRoom
  },
  {
    path:"/fourthGameRoom",
    name:"fourthGameRoom",
    component : fourthGameRoom
  },
  {
    path:"/waitingRoom",
    name:"waitingRoom",
    component : WaitingRoom
  },
  {
    path:"/SharingPage",
    name:"SharingPage",
    component : SharingPage
  },
  {
    path:"/user/password",
    name:"Password",
    component : Password
  },
  {
    path: '/file',
    name: 'file',
    component: FileUploader
  },
  {
    path: '/down',
    name: 'down',
    component: Downloader
  },

  //test
  {
    path:"/test",
    name:"test",
    component : tempFeed
  },{
    path:"/test2",
    name:"test2",
    component : MixFeed
  },
];
