import { chooseWishList } from "@/api/plan";
import { nearAttractXY } from "@/api/plan";
import Vue from 'vue';

const planStore = {
  namespaced: true,
  state: {
    bcards: {},
    nearAttraction: {},
    startDate: "",
    endDate: "",
    dayDifference: 0,
    stateVisible: "nearAttract",
    markersV: [],
    planItems: [], // 변경된 부분
    selectedDayNum: null,
    dayNum:[],
    planNameAdd:"",
    randomplan:[],
    sido:0,
    gugun:0,
  },
  mutations: {
    SET_RANDOM_CONTENT(state,data){
      state.sido=data;
  },
    SET_RANDOM_CONTENT_SUB(state,data){
      state.gugun=data;
      console.log(state.gugun);
  },
    SET_RANDOM_PALN(state,data){
        state.randomplan=data;
    },
    SET_DAYNUM(state,count){
        state.dayNum[count] +=1;
    },
    setSelectedDay(state, day) {
      state.selectedDayNum = day;
      console.log(state.selectedDayNum);
    },
    removePlannerItem(state, { key, index }) {
      if (state.planItems[key] && state.planItems[key][index]) {
        state.planItems[key].splice(index, 1);
      }
      console.log("asdasd", state.planItems);
    },
    setPlannerItem(state, { key, value }) {
      console.log("before",state.planItems[key]);
      if (!state.planItems[key]) {
        Vue.set(state.planItems, key, []);
      }
      if (state.planItems[key][0] && state.planItems[key][0].__ob__) {
        state.planItems[key].shift();
        }
  
      console.log("after",state.planItems);
      state.planItems[key].push(value);
      console.log("이게 진짜 내가 궁금한것", state.planItems);
    },
    clearMapState(state) {
      state.markersV = [];
      state.nearAttraction = {};
      state.bcards = {};
    },
    clearDayPlan(state) {
      state.planItems = [];
    },
    setMarkers(state, markers) {
      state.markersV.push(markers);
      console.log(state.markersV);
    },
    setWishList(state, bcards) {
      state.bcards = bcards;
    },
    setNearAttraction(state, nearAttraction) {
      state.nearAttraction = nearAttraction;
    },
    setStartDate(state, date) {
      state.startDate = date;
      console.log(state.startDate);
    },
    setEndDate(state, date) {
      state.endDate = date;
      console.log(state.endDate);
    },
    setDayDifference(state, date) {
      state.dayDifference = date;
    },
    SET_STATE_VISIBLE(state, value) {
      state.stateVisible = value;
    },
    setPlannerElement(state,{key,value}){
      if (state.planItems[key][0] && !state.planItems[key][0].__ob__) {
        console.log("삭제했당ㅇㅋㅋㅋㅋ");
        Vue.delete(state.planItems[key], 0);
      }
  
      console.log("after",state.planItems);
      state.planItems[key].push(value);
      console.log("이게 진짜 내가 궁금한것", state.planItems);
    }
  },
  actions: {
    chooseMyWish({ commit }) {
      chooseWishList(
        (data) => {
          console.log(data);
          commit("setWishList", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    nearAttract({ commit }, location) {
      nearAttractXY(
        location,
        (data) => {
          console.log(data);
          console.log("asdsd");
          commit("setNearAttraction", data.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    toggleMapStateVisibility({ commit, state }, newState) {
      const toggledState = state.stateVisible === newState ? "" : newState;
      commit("SET_STATE_VISIBLE", toggledState);
    },
    addMarker({ commit }, chooseMarker) {
      commit('setMarkers', chooseMarker);
    },
    setPlannerItemMachine({ commit }, temp) {
      console.log(temp);
      commit('setPlannerItem', { key: temp.day, value:temp.value });
      // console.log(day);
      // console.log(value);
    },
    setPlannerItemElement({ commit }, temp) {
      console.log(temp);
      commit('setPlannerElement', { key: temp.day, value:temp.value });
      // console.log(day);
      // console.log(value);
    },
    setRandomPlan({commit},data){
      console.log(data);
      commit('SET_RANDOM_PALN',data);
    },
    setSidoGugun({commit},data){
      console.log(data);
      commit('SET_RANDOM_CONTENT',data);
    },
    setSidoGugunSub({commit},data){
      console.log(data);
      commit('SET_RANDOM_CONTENT_SUB',data);
    }
  },
  getters: {
    getPlannerItemsByDay: (state) => (day) => {
      return state.planItems[day] || [];
    },
  },
}
export default planStore;
