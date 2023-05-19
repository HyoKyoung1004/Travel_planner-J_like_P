<template>
  <div>
    <div class="center">
      <b-nav class="child">
        <b-nav-item active @click="viewAttractionList(12)"> 관광지</b-nav-item>
        <b-nav-item active @click="viewAttractionList(14)">문화시설</b-nav-item>
        <b-nav-item active @click="viewAttractionList(15)"
          >축제공연행사</b-nav-item
        >
        <b-nav-item active @click="viewAttractionList(25)">여행코스</b-nav-item>
        <b-nav-item active @click="viewAttractionList(28)"> 레포츠</b-nav-item>
        <b-nav-item active @click="viewAttractionList(32)">숙박</b-nav-item>
        <b-nav-item active @click="viewAttractionList(38)">쇼핑</b-nav-item>
        <b-nav-item active @click="viewAttractionList(39)">음식점</b-nav-item>
      </b-nav>
    </div>
    <br />
    <div class="center">
      <choose-sido @select-sido="selectSido"></choose-sido>
      <choose-gu-gun :sidoCode="sidoCode"></choose-gu-gun>
    </div>
  </div>
</template>

<script>
import ChooseSido from "@/components/Attraction/Sido/ChooseSido.vue";
import ChooseGuGun from "@/components/Attraction/Sido/ChooseGuGun.vue";
import { mapActions, mapMutations } from "vuex";
const itemStore = "itemStore";
export default {
  components: {
    ChooseSido,
    ChooseGuGun,
  },
  data() {
    return {
      openModal: false,
      sidoCode: null,
    };
  },
  // created: {},
  methods: {
    ...mapActions(itemStore, ["getGugun"]),
    ...mapMutations(itemStore, ["CLEAR_GUGUN_LIST"]),

    selectSido(sidoCode) {
      console.log(sidoCode);
      this.sidoCode = sidoCode;
      console.log(this.sidoCode);
    },
    viewAttractionList(idx) {
      this.$router.push({ name: "attractionList", query: { type: idx } });
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
</style>
