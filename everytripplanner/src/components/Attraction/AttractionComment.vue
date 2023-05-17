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
          <button v-b-modal.modal-prevent-closing style="border-color: #ffc314">
            <h5><i class="fa-regular fa-comments"></i> 리뷰 작성</h5>
          </button>

          <b-modal id="modal-prevent-closing" ref="modal" @ok="handleOk">
            <template #modal-title>
              {{ attraction.title }}은 어떠셨나요?
            </template>

            <form ref="form" @submit.stop.prevent="handleSubmit">
              <label for="rating-10">별점 남기기</label>
              <b-form-rating
                id="rating-10"
                v-model="value"
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
                  id="name-input"
                  v-model="name"
                  required
                ></b-form-textarea>
                <br />
                <label for="rating-10">사진 첨부(선택)</label>
                <b-form-file accept="image/*"></b-form-file>
              </b-form-group>
            </form>
          </b-modal>
        </b-col>
      </b-row>
      <b-row class="text-center">
        <b-col cols="9"> <hr class="boldhr" /></b-col>
      </b-row>
      <br />
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      attraction: [],
      nearAttraction: [],
      comment: [],
      name: "",
      nameState: null,
      submittedNames: [],
    };
  },
  created() {
    axios
      .get("http://localhost:9999/trip/attract/view/2729266")
      .then((resp) => {
        console.log(resp);
        this.nearAttraction = resp.data.nearAttraction;
        this.attraction = resp.data.attraction;
        this.comment = resp.data.comment;
        console.log(this.nearAttraction);
        console.log(
          this.nearAttraction[0].latitude,
          this.nearAttraction[0].longitude
        );
      });
  },
  mounted() {},

  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.nameState = valid;
      return valid;
    },
    resetModal() {
      this.name = "";
      this.nameState = null;
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      this.handleSubmit();
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return;
      }
      // Push the name to submitted names
      this.submittedNames.push(this.name);
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide("modal-prevent-closing");
      });
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
</style>