import { attractionDetail } from "@/api/attraction.js";

const AttractionStore = {
  namespaced: true,
  state: {
    attractionDtailData: [{ test: "test" }],
    detailNearAttraction: [{ test: "test" }],
    comment: [],
  },
  mutations: {
    SET_ATTRACTION_DETAIL(state, data) {
      console.log(data);
      state.attractionDtailData = data.attraction;
      state.detailNearAttraction = data.nearAttraction;
      state.comment = data.comment;
      console.log("받아온데이터");
      console.log(state.attraction);
      console.log(state.detailNearAttraction);
      //   console.log(state.comment);
    },
  },
  actions: {
    attractionDetail: async ({ commit }, contentId) => {
      await attractionDetail(
        contentId,
        ({ data }) => {
          commit("SET_ATTRACTION_DETAIL", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default AttractionStore;
