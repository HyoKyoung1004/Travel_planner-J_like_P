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
    <choose-sido></choose-sido>
  </div>
</template>
.

<script>
import ChooseSido from "@/components/Attraction/Sido/ChooseSido.vue";
export default {
  components: {
    ChooseSido,
  },
  data() {
    return {
      openModal: false,
    };
  },
  created: {},
  methods: {
    openClick() {
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
</style>
