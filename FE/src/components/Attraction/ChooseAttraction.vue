<template>
  <div>
    <div class="center">
      <b-nav class="child">
        <b-nav-item class="tmp" active @click="viewAttractionList(12)"> 관광지</b-nav-item>
        <b-nav-item class="tmp" active @click="viewAttractionList(14)">문화시설</b-nav-item>
        <b-nav-item class="tmp" active @click="viewAttractionList(15)">축제공연행사</b-nav-item>
        <b-nav-item class="tmp" active @click="viewAttractionList(28)"> 레포츠</b-nav-item>
        <b-nav-item class="tmp" active @click="viewAttractionList(32)">숙박</b-nav-item>
        <b-nav-item class="tmp" active @click="viewAttractionList(38)">쇼핑</b-nav-item>
        <b-nav-item class="tmp" active @click="viewAttractionList(39)">음식점</b-nav-item>
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
          <b-col
            ><choose-gu-gun :sidoCode="sidoCode" @select-gugun="selectGugun"></choose-gu-gun
          ></b-col>
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
      type: null,
      gugunCode: null,
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

    selectGugun(addr) {
      this.sidoCode = addr.sidoCode;
      this.gugunCode = addr.gugunCode;
      if (this.$route.path !== "/attractionList") {
        this.$router.push({
          name: "attractionList",
          query: { sido: this.sidoCode, gugun: this.gugunCode },
        });
      } else {
        console.log("데이터 변경됨,,,,,");
        console.log(this.sidoCode);
        console.log(this.gugunCode);
        this.$emit("childData", { sido: this.sidoCode, gugun: this.gugunCode });
        // this.sidoCode = undefined;
        // this.gugunCode = undefined;
      }
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
        this.type = idx;
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

a {
  float: left;
  line-height: 56px;
  height: 57px;
  color: #383d41;
  position: relative;
  font-weight: 800;
  padding: 35px;
  display: block;
  font-size: 20px;
  letter-spacing: -1px;
  /* color: #333; */
  position: relative;
  text-align: center;
}
</style>