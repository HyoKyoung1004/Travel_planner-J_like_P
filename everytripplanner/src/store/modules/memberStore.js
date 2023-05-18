import { login} from "@/api/member";

const memberStore = {
    namespaced: true,
    state: {
        token: null,
        isLoggedIn: false
    },
    mutations: {
        SET_TOKEN(state, token) {
            state.token = token;
            state.isLoggedIn = true;
        },
        CLEART_TOKEN(state) {
            state.token = null;
            state.isLoggedIn = false;
        },
        SET_IS_LOGIN: (state, isLoggedIn) => {
            state.isLoggedIn = isLoggedIn;
        }
    },
    actions: {
        async login({ commit }, user) {
            await login(
                user,
                ({ data }) => {
                    if (data != "x") {
                        console.log(data);
                        this.token = data;
                        commit("SET_IS_LOGIN", true);
                        commit("SET_TOKEN", this.token);
                    }
                    console.log(data);
                }
            )
            // API 호출 등 로그인 처리
            // 성공시에는 commit('setToken', 발급된 토큰);
            // 실패시에는 오류 처리 등 적절한 처리를 한다.
        },
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