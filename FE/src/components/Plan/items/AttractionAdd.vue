<template>
  <div class="scroll-container">
      <ul style="list-style-type:none;">
        <li v-for="(value, key) in plannerItems" :key="key"  >
          <b-card
              v-if="key !== 0"
              :title="value.title"
              :img-src="value.img"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 10rem;"
              class="mb-2">
              <b-button variant="primary" @click="removeItem(key)">삭제하기</b-button>
          </b-card>
        </li>
      </ul>
  </div>
</template>

<script>
import { mapState,mapMutations} from "vuex";
const planStore = "planStore";

export default {
  props: {
    day: {
      type: Number, // 수정된 부분
      required: true
    }
  },
  computed: {
    ...mapState(planStore, ["planItems"]), // 수정된 부분
   ...mapMutations(planStore, ["removePlannerItem"]),
    plannerItems() {
      console.log("나는 아름 다운 나비 ",this.day);
      console.log("나는 아름 다운 나비ㅋㅋㅋㅋ ",this.day-1);
      console.log('sdassadsad',this.planItems[this.day-1]);
      return this.planItems[this.day-1] || []; // 수정된 부분
    }
  },
  methods:{
      removeItem(index) {
      this.removePlannerItem({
        key: this.day,
        index
      });
    }
  }
};
</script>

  <style scoped>
.scroll-container {
  max-height: 1000px; /* 원하는 높이로 설정 */
  overflow-y: scroll;
}
</style>
