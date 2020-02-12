import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import VueSession from 'vue-session'

Vue.use(VueSession)
Vue.use(Vuex)

const state = {
  articles: require('@/data/articles.json'),
  isUser: false,
  token: null,
  userId: "",
  userNickname: '',
  profileImg:'',
  // 0은 아무것도 아닌 상태, 1은 singer인 상태, 2는 watcher인 상태
  singerOrWatcherStatus:0,
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions
})