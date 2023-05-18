<template>
  <div>
    <attraction-detail></attraction-detail>
    <near-attraction></near-attraction>
    <attraction-comment></attraction-comment>
  </div>
</template>

<script>
import AttractionDetail from "@/components/Attraction/AttractionDetail.vue";
import NearAttraction from "@/components/Attraction/NearAttraction.vue";
import AttractionComment from "@/components/Attraction/AttractionComment.vue";
export default {
  components: {
    AttractionDetail,
    NearAttraction,
    AttractionComment,
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
