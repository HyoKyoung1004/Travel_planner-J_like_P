<template>
  <div class="center">
    <div class="search-container">
      <input type="text" id="search-input-1" placeholder="첫 번째 검색어 입력" />
      <input type="text" id="search-input-2" placeholder="두 번째 검색어 입력" />
      <button id="search-button">검색</button>
    </div>

    <div>
      <b-nav class="child">
        <b-nav-item active> 관광지</b-nav-item>
        <b-nav-item active>문화시설</b-nav-item>
        <b-nav-item active>축제공연행사</b-nav-item>
        <b-nav-item active>여행코스</b-nav-item>
        <b-nav-item active>레포츠</b-nav-item>
        <b-nav-item active>숙박</b-nav-item>
      </b-nav>
      <br />
    </div>

    <div>
      <div>
        <b-button variant="danger" @click="openClick">어디로 가세요?</b-button>
        <PopUpView v-bind:openModal="openModal" v-if="openModal == true" />
      </div>
    </div>
  </div>
</template>

<script>
//import PopUpView from "@/components/Attraction/PopUpView.vue";
export default {
  components: {
    //PopUpView,
  },
  data() {
    return {
      openModal: false,
    };
  },
  created: {},
  methods: {
    openClick() {
      console.log(this.openModal);
      this.openModal = true;
    },
  },
};
</script>

<style scoped>
.center {
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
.row {
  position: static;
}

.search-container {
  display: flex;
  align-items: center;
  justify-content: center;
}

.search-container input {
  padding: 10px;
  height: 74px;
  margin-right: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

.search-container button {
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
}

.search-container button:hover {
  background-color: #45a049;
}
</style>
