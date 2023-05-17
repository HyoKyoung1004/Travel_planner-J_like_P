import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);
export default new Vuex.Store({
  state: {
    bcards: [],
  },
  mutations: {
    setBcards(state, bcards) {
      state.bcards = bcards;
    },
  },
  actions: {
    async fetchBcards({ commit}) {
      try {
        await axios.get("http://localhost:9999/trip/wishList/3").then(({ data }) => {
          
          this.bcards = data;
          commit('setBcards', this.bcards);
        })
        
      } catch (error) {
        console.error('Error fetching B-cards:', error);
      }
    },
  },
});