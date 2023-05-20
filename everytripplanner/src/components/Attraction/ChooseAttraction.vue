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
    <!-- <div class="center">
      <choose-sido @select-sido="selectSido"></choose-sido>
      <choose-gu-gun :sidoCode="sidoCode"></choose-gu-gun>
    </div> -->

    <div>
      <b-container class="bv-example-row">
        <b-row>
          <b-col><choose-sido @select-sido="selectSido"></choose-sido></b-col>
          <b-col><choose-gu-gun :sidoCode="sidoCode"></choose-gu-gun></b-col>
        </b-row>
      </b-container>
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
      if (this.$route.path !== "/attractionList") {
        this.$router.push({ name: "attractionList", query: { type: idx } });
      } else {
        console.log("왜 안됨>?");
        //데이터만 보내야함,,,,
        var childData = {
          sidoCode: this.sidoCode,
          gugunCode: this.gugunCode,
          type: idx,
        };
        this.$emit("childData", childData);
        console.log("보냈음", childData);
      }
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
