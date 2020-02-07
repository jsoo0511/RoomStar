import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import webCam from './webcam/webCam.vue'
import HomePage from "./views/general/HomePage.vue";
import MyPage from "./views/general/MyPage.vue";
import GameRoom from "./views/general/GameRoom.vue";
import WaitingRoom from "./views/general/WaitingRoom.vue";
import SharingPage from "./views/general/SharingPage.vue";
import Password from "./views/user/Password.vue";

import Components from './views/Components.vue'
import Vue from 'vue'
import VueRouter from 'vue-router'

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
];
