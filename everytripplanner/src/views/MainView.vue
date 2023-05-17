<template>
  <div>
    <choose-attrcation></choose-attrcation>
    <br />
    <br />
    <top-plan-addr></top-plan-addr>
    <br />
    <br />
    <top-trip-plan></top-trip-plan>
    <br />
    <br />
    <top-attraction></top-attraction>
  </div>
</template>

<script>
import ChooseAttrcation from "@/components/Attraction/ChooseAttrcation.vue";
import TopAttraction from "@/components/Attraction/TopAttraction.vue";
import TopPlanAddr from "@/components/Plan/TopPlanAddr.vue";
import TopTripPlan from "@/components/Plan/TopTripPlan.vue";
export default {
  name: "MainView",
  components: {
    ChooseAttrcation,
    TopAttraction,
    TopPlanAddr,
    TopTripPlan,
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
