import { attractionDetail } from "@/api/attraction.js";
import { commentDetailFunction } from "@/api/comment.js";

const AttractionStore = {
  namespaced: true,
  state: {
    contentId: 0,
    attractionDtailData: [{ test: "test" }],
    detailNearAttraction: [{ test: "test" }],
    attractionComment: [],
    commentAttractionDetail: []
  },
  mutations: {
    SET_ATTRACTION_DETAIL(state, data) {
      console.log(data);
      state.attractionDtailData = data.attraction;
      state.detailNearAttraction = data.nearAttraction;
      state.attractionComment = data.comment;
      state.contentId = state.attractionDtailData.contentId;
      console.log("받아온데이터");
      console.log(data.comment);
      //console.log(state.attraction);
      //console.log(state.detailNearAttraction);
      console.log(state.attractionComment);
    },
    SET_COMMENT_DETAIL(state, data) {
      state.commentAttractionDetail = data;
      console.log("받아온 댓글 데이터")
      console.log(state.commentAttractionDetail);
    }
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
    commentDetail: ({ commit }, contentId_1) => {
      console.log("store까지 오니?")
      console.log(contentId_1)
      commentDetailFunction(
        contentId_1,
        ({ data }) => {
          commit("SET_COMMENT_DETAIL", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default AttractionStore;
