import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import store from './vuex/store'
import vuetify from './plugins/vuetify';
import VideoBg from 'vue-videobg'
import io from 'socket.io-client'
//import VueMaterial from 'vue-material' 

const socket = io("http://70.12.246.73:3001");
Vue.prototype.$socket = socket;

Vue.config.productionTip = false

Vue.use(Router)

const router = new Router({
    routes,
});

Vue.component('video-bg', VideoBg)

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app');


