import Vue from "vue";
import Vuex from "vuex";
import memberStore from "@/store/modules/memberStore";
import Attraction from "@/store/modules/Attrraction";
import itemStore from "@/store/modules/items/itemStore";
import AttractionStore from "./modules/Attrraction";
import createPersistedState from 'vuex-persistedstate';
import planStore from "@/store/modules/planStore";
import wishListStore from "@/store/modules/wishListStore";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [
    createPersistedState({
      paths: ['memberStore'],
    }),
  ],
  modules: {
    memberStore,
    Attraction,
    itemStore,
    AttractionStore,
    planStore,
    wishListStore
  },
  state: {
    bcards: {},
    startDate: "",
    endDate: "",
    dayDifference: 0,
  },
  // state: {
  //   bcards: {},
  //   startDate: "",
  //   endDate: "",
  //   dayDifference: 0,
  // },
  // mutations: {
  //   setBcards(state, bcards) {
  //     state.bcards = bcards;
  //   },
  //   setStartDate(state,date){
  //     state.startDate=date;

  //   },
  //   setEndDate(state,date){
  //     state.endDatee=date;
  //   },
  //   setDayDifference(state,date){
  //     state.dayDifference=date;
  //   }
  // },
  // actions: {
  //   async fetchBcards({ commit }) {
  //     try {
  //       await axios.get("http://localhost:9999/trip/wishList/3").then(({ data }) => {
  //         this.bcards = data;
  //         commit("setBcards", this.bcards);
  //       });
  //     } catch (error) {
  //       console.error("Error fetching B-cards:", error);
  //     }
  //   },
  // },
});
