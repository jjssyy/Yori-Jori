import Vue from 'vue'
import Vuex from 'vuex'
import http from "./http-common.js";
import router from "@/router/index.js";



Vue.use(Vuex);

let store = new Vuex.Store({
  state: {
    user: {
      uid: { type: String },
      pw: { type: String },
      email: { type: String },
      nickName: {type:String}
    },
    login: {
      uid: { type: String },
      pw: { type: String },
      email: { type: String },
      nickName: {type:String},
   
    },
   
  },
  getters: {
  
  },
  mutations: {
    JOIN_USER(state, data) {
      state.user = data;
    },

    LOGIN_USER(state, data) {
      state.login = data;
      
    },
    CHANGE_USER(state, data) {
      state.login = data;
      
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
        .get("/account/login", {
          params: {
            email: data.email,
            password: data.password,
          },
        })
        .then((response) => {
          if (response.data.object === data.email) {
            
            alert("로그인 성공!");
            commit("LOGIN_USER", response.data);
              router.push({ name: "Login" });
          } else {
              alert("아이디나 비번이 틀렸습니다!");
              router.push("/error/error");
              
          }
           
        })
        .catch((error) => {
          console.dir(error);
            alert("로그인 실패!!");
            router.push("/error/error");
           
        });

    },
      
    user_join({ commit }, data) {
      http
        .post("/account/signup", data
        )
        .then((response) => { 
          if (response.data.data == "success") {
            
            alert("회원가입 성공!");
            commit("nickName", response.data);
            router.push("/user/joinsuccess");
          } else {
              alert("아이디중복 되었습니다.");
              router.push("/error/error");
              
          }
           
        })
        .catch((error) => {
          console.dir(error);
            alert("회원가입에 문제가 생겼습니다.");
            router.push("/error/error");
           
        });

    },

    user_changepassword({ commit }, data) {
      http
        .put("/account/changepassword", data
        )
        .then((response) => { 
          if (response.data.data == "success") {
            
            alert("변경 성공!");
            commit("CHANGE_USER", response.data);
            router.push("/");
          } else {
              alert("변경에 실패하였습니다.");
              router.push("/error/error");
              
          }
           
        })
        .catch((error) => {
          console.dir(error);
            alert("변경하는데 오류가 발생했습니다..");
            router.push("/error/error");
           
        });

    },

   
   
  },
});
export default store;
