import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import store from './vuex/store'
import axios from 'axios';

// axios 객체 생성
export default axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-type': 'application/json',
  },
});


Vue.config.productionTip = false



new Vue({
    router,
    store,
    render: (h) => h(App),
    
    
  }).$mount("#app");
