import Vue from 'vue'
import Vuex from 'vuex'
import http from "./http-common.js";
import router from "@/router/index.js";



Vue.use(Vuex);

let store = new Vuex.Store({
  state: {
    user: {
      id: { type: String },
      pw: { type: String },
      user_name: { type: String },
      age: { type: Number },
      email: { type: String },
      role: { type: String },
    },
    login: {
      id: { type: String },
      pw: { type: String },
      user_name: { type: String },
      age: { type: Number },
      email: { type: String },
      role: { type: String },
    },
   
  },
  getters: {
  
  },
  mutations: {
    REGISTER_USER(state, data) {
      state.user = data;
    },

    LOGIN_USER(state, data) {
      state.login = data;
      store._vm.$session.set("user", data);
      console.log(store._vm.$session.get("user"));
    },

   
  },
  actions: {
 
    register({ commit }, data) {
      http
        .post("/account/signup", data)
        .then(({ data }) => {
          if (data === "success") {
            alert("회원가입 성공!");
            router.push({ name: "index" });
          }
        })

        .catch((error) => {
          alert("회원가입 실패!");
          console.dir(error);
        });
      commit("REGISTER_USER", data);
    },

      user_login({ commit }, data) {
      
      http
        .get("/login", {
          params: {
            email: data.email,
            pw: data.pw,
          },
        })
        .then((response) => {
          if (response.data.email === data.email) {
            console.log(response.data.role);
            alert("로그인 성공!");
            commit("LOGIN_USER", response.data);
              router.push({ name: "Login" });
          } else {
              alert("로그인 실패!");
              router.push("/error/error");
              
          }
           
        })
        .catch((error) => {
          console.dir(error);
            alert("로그인 실패!!");
            router.push("/error/error");
           
        });
    },

   
   
  },
});
export default store;
