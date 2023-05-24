import { login } from "@/api/member";
import jwt_decode from "jwt-decode";
import { findById } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            console.log(token);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => { },
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      console.log(decode_token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    logout({ commit }) {
      commit('SET_IS_LOGIN', false);
      commit('SET_IS_LOGIN_ERROR', false);
      commit('SET_USER_INFO', null);
      sessionStorage.removeItem('access-token');
      sessionStorage.removeItem('refresh-token');
      // 필요에 따라 추가적인 로그아웃 동작을 수행할 수 있습니다.
    }

    // logout({ commit }) {
    //     // 로그아웃 처리
    //     // 서버로 로그아웃 요청을 보낸다.
    //     // 로그아웃 처리가 완료되면 commit('clearToken')을 호출하여 토큰을 지운다.
    // },
  },
  getters: {
    isLoggedIn(state) {
      return state.isLoggedIn;
    },
    getToken(state) {
      return state.token;
    },
  },
}
export default memberStore;
// // store.js
// import Vue from 'vue';
// import Vuex from 'vuex';

// Vue.use(Vuex);

// export default new Vuex.Store({
//   state: {
//     token: null,
//     isLoggedIn: false,
//   },
//   mutations: {
//     setToken(state, token) {
//       state.token = token;
//       state.isLoggedIn = true;
//     },
//     clearToken(state) {
//       state.token = null;
//       state.isLoggedIn = false;
//     },
//   },
//   actions: {
//     login({ commit }, { username, password }) {
//       // API 호출 등 로그인 처리
//       // 성공시에는 commit('setToken', 발급된 토큰);
//       // 실패시에는 오류 처리 등 적절한 처리를 한다.
//     },
//     logout({ commit }) {
//       // 로그아웃 처리
//       // 서버로 로그아웃 요청을 보낸다.
//       // 로그아웃 처리가 완료되면 commit('clearToken')을 호출하여 토큰을 지운다.
//     },
//   },
//   getters: {
//     isLoggedIn(state) {
//       return state.isLoggedIn;
//     },
//     getToken(state) {
//       return state.token;
//     },
//   },
// });