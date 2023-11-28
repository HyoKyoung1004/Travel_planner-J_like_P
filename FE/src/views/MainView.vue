<template>
  <div>
    <choose-attraction></choose-attraction>
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
import ChooseAttraction from "@/components/Attraction/ChooseAttraction.vue";
import TopAttraction from "@/components/Attraction/TopAttraction.vue";
import TopPlanAddr from "@/components/Plan/TopPlanAddr.vue";
import TopTripPlan from "@/components/Plan/TopTripPlan.vue";

export default {
  name: "MainView",
  components: {
    ChooseAttraction,
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
