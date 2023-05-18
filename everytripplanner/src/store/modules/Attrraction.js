import { houseList } from "@/api/attraction.js";

const houseStore = {
  namespaced: true,
  state: {
    houses: [],
    house: null,
  },
  getters: {},
  mutations: {
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
  },
  actions: {
    getHouseList: ({ commit }, gugunCode) => {
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202304",
      };
      houseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
