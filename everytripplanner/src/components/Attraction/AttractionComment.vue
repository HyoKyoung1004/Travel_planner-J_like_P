<template>
  <div>
    <b-container>
      <b-row class="text-center">
        <b-col></b-col>
        <b-col cols="10">
          <h3 style="font-weight: 800">댓글 작성하기</h3></b-col
        >
        <b-col></b-col>
      </b-row>
      <b-row class="text-center">
        <b-col cols="9"> </b-col>
        <b-col cols="3">
          <button
            v-b-modal.modal-prevent-closing
            style="border-color: #ffc314"
            @click="userCheck"
          >
            <h5><i class="fa-regular fa-comments"></i> 리뷰 작성</h5>
          </button>

          <b-modal
            v-if="userInfo"
            id="modal-prevent-closing"
            ref="modal"
            @ok="handleOk"
          >
            <template #modal-title
              >{{ attractionDtailData.title }} 은 어떠셨나요?
            </template>

            <form ref="form" @submit.stop.prevent="handleSubmit">
              <label for="rating-10">별점 남기기</label>
              <b-form-rating
                id="rating-10"
                v-model="commentForm.rating"
                variant="warning"
                class="mb-2"
              ></b-form-rating>
              <br />
              <b-form-group
                label="여행은 어떠셨나요?"
                label-for="name-input"
                invalid-feedback="Name is required"
              >
                <b-form-textarea
                  placeholder="위치나 교통, 편의시설 등 다른 여행자에게 추천하는 나만의 여행 팁을 알려주세요!"
                  v-model="commentForm.content"
                  id="name-input"
                  required
                ></b-form-textarea>
                <br />
                <label for="rating-10">사진 첨부(선택)</label>
                <b-form-file
                  v-model="commentForm.uploadedfiles"
                  accept="image/*"
                ></b-form-file>
              </b-form-group>
            </form>
          </b-modal>
        </b-col>
      </b-row>
      <b-row class="text-center">
        <b-col cols="9"> <hr class="boldhr" /></b-col>
      </b-row>
      <br />
      <template v-for="(comment, idx) in commentDetailDetail">
        <b-row
          class="text-center"
          style="max-height: 70px"
          v-bind:key="comment.commentId"
        >
          <b-col cols="2">
            <b-avatar variant="secondary"></b-avatar>
            <br />
            <span
              ><h6>{{ comment.userName }}</h6>
            </span>
          </b-col>
          <b-col cols="7">
            {{ comment.content }}<br />
            <span
              class="num"
              id="좋아요수"
              v-for="index in comment.rating"
              :key="index"
              >⭐</span
            >
            | {{ comment.regDate }}
          </b-col>
          <b-col cols="3">
            <img
              v-if="comment.fileInfo != null && comment.fileInfo[0] != null"
              :src="`http://localhost:9999/trip/${comment.fileInfo[0].saveFolder}/${comment.fileInfo[0].saveFileName}`"
              alt="My Image"
              style="width: 100px; height: 75px"
          /></b-col>
        </b-row>
        <b-row v-bind:key="idx"><hr /></b-row>
      </template>
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
  data() {
    return {
      commentDetailDetail: [],
      comment: [],
      userid: 1,
      commentForm: {
        rating: 0,
        content: "",
        contentId: 0,
        uploadedfiles: null,
      },
    };
  },

  props: {
    contentId: Number,
  },
  watch: {
    contentId() {
      this.commentDetail(this.contentId);
    },
    attractionComment() {
      console.log("변경됨");
      this.commentDetailDetail = this.attractionComment;
    },
  },
  created() {
    console.log("create()");
    console.log(this.contentId);
    this.commentDetailDetail = this.attractionComment;
  },
  mounted() {
    console.log("왜 댓글 안보여줘");
    console.log(this.attractionComment.fileInfo);
    // console.log(this.attractionComment.fileInfo[0].saveFolder);
  },
  computed: {
    ...mapState("AttractionStore", [
      "attractionDtailData",
      "attractionComment",
      "commentAttractionDetail",
    ]),
    ...mapActions("AttractionStore", ["commentDetail"]),
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    async handleOk() {
      console.log(this.commentForm.uploadedfiles);
      if (this.addComment()) {
        this.commentDetail(this.contentId);
      }
      console.log("addComment실행 후");
      console.log(this.attractionComment);
    },
    addComment() {
      this.commentForm.contentId = this.attractionDtailData.contentId;
      var comment = {
        content: this.commentForm.content,
        contentId: this.commentForm.contentId,
        rating: this.commentForm.rating,
      };

      console.log(comment);
      const formData = new FormData();
      formData.append("uploadedfiles", this.commentForm.uploadedfiles);
      const blob = new Blob([JSON.stringify(comment)], {
        type: "application/json",
      });
      formData.append("comment", blob);
      const config = {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      };
      console.log(formData);
      // var file = this.commentForm.uploadedfiles;
      axios
        .post(
          "http://localhost:9999/trip/comment/write/" + this.userInfo.userId,
          formData,
          config
        )
        .then((resp) => {
          console.log(resp);
          alert("성공");
          axios
            .get("http://localhost:9999/trip/comment/" + this.contentId)
            .then((resp) => {
              console.log(resp);
              console.log("이전");
              console.log(this.commentDetailDetail);
              this.commentDetailDetail = resp.data;
              console.log("이후");
              console.log(this.commentDetailDetail);
            });
        })
        .catch((resp) => {
          console.log(resp);
          alert("실패");
        });
    },
    userCheck() {
      console.log("로그인 체크");
      if (this.userInfo == null) {
        alert("로그인 후 이용 가능합니다");
        return false;
      } else {
        return true;
      }
    },
  },
};
</script>

<style scoped>
.comment-section {
  font-family: Arial, sans-serif;
  margin-bottom: 20px;
}

.comment-form {
  display: flex;
  margin-bottom: 10px;
}

.comment-input {
  flex: 1;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.file-input {
  margin-left: 10px;
}

.submit-button {
  margin-left: 10px;
  padding: 5px 10px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.comment-list {
  list-style-type: none;
  padding: 0;
}

.comment-item {
  margin-bottom: 10px;
  padding: 10px;
  background-color: #f2f2f2;
  border-radius: 3px;
}

.comment-item img {
  max-width: 100px;
  max-height: 100px;
  margin-bottom: 5px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

ul li {
  float: left;
  width: 50%;
  padding: 3px 0 6px 0;
  display: table;
  font-size: 15px;
  font-weight: 400;
  background: none !important;
}

ul li strong {
  float: none;
  margin-right: 0;
  position: relative;
  display: table-cell;
  width: 128px;
  padding: 0 0 0 12px;
  font-weight: 700;
  color: #333;
}

button {
  background-color: transparent;
  border-radius: 20px;
  padding: 7px;
  border-color: #e2e2e2;
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9,
    7px 7px 20px 0px #0002, 4px 4px 5px 0px #0001;
}

.flex-container {
  display: flex;
  align-items: stretch;
  background-color: #f1f1f1;
}

.flex-container > div {
  margin: 3px;
  text-align: center;
}
/* b-img {
  blank: true;
  blankcolor: "#777";
  width: 75;
  height: 75;
  class: "m1";
} */

.offer-review__list--content {
  margin: 24px 0;
}
</style>
