import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import VueSession from 'vue-session'

Vue.use(VueSession)
Vue.use(Vuex)

const state = {
  isUser: false,
  token: null,
  userId: "",
  userNickname: '',
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions
})