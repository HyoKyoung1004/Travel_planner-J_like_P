<template>
  <div class="center">
    <b-nav class="child">
      <b-nav-item active> 관광지</b-nav-item>
      <b-nav-item active>문화시설</b-nav-item>
      <b-nav-item active>축제공연행사</b-nav-item>
      <b-nav-item active>여행코스</b-nav-item>
      <b-nav-item active>레포츠</b-nav-item>
      <b-nav-item active>숙박</b-nav-item>
    </b-nav>
    <br />
    <choose-sido @select-sido="selectSido"></choose-sido>
    <choose-gu-gun :sidoCode="sidoCode"></choose-gu-gun>
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
      sidoCode:null,
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
  }
  
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
