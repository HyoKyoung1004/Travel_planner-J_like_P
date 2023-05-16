<template>
  <div>
    여기는 메인
    <choose-attrcation></choose-attrcation>
    <top-plan-addr></top-plan-addr>
    <top-attraction></top-attraction>
  </div>
</template>

<script>
import ChooseAttrcation from "@/components/Attraction/ChooseAttrcation.vue";
import TopAttraction from "@/components/Attraction/TopAttraction.vue";
import TopPlanAddr from "@/components/Plan/TopPlanAddr.vue";
export default {
  name: "MainView",
  components: {
    ChooseAttrcation,
    TopAttraction,
    TopPlanAddr,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>