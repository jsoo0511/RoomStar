import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import store from './vuex/store'
import vuetify from './plugins/vuetify';
import VideoBg from 'vue-videobg'
Vue.config.productionTip = false

Vue.use(Router)
// Install BootstrapVue


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


