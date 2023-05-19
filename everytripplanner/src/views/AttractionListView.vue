<template>
  <div>
    <div>
      <choose-attraction></choose-attraction>
      <hr />
    </div>
  </div>
</template>

<script>
import ChooseAttraction from "@/components/Attraction/ChooseAttraction.vue";

export default {
  components: {
    ChooseAttraction,
  },
  data() {
    return {
      sido: 0,
      gugun: 0,
      type: 0,
      searchData: 0,
      page: 1,
      orderType: 0,
    };
  },
  created() {
    // const sido = this.$route.query.contant;
    // const gugun = this.$route.query.gugun;
    // const type = this.$route.query.type;
    // const searchData = this.$route.query.searchData;
    // const page = this.$route.query.page;
    // const orderType = this.$route.query.orderType;
    // if (page == null) page = 1;
    // if (orderType == null) orderType;
  },
  methods: {},
};
</script>

<style scoped></style>
