<template>
  <div>
    <attraction-detail :contentId="contentId"></attraction-detail>
    <near-attraction :contentId="contentId"></near-attraction>
    <attraction-comment :contentId="contentId"></attraction-comment>
  </div>
</template>

<script>
//import { attractionDetail } from "@/api/attraction";
const AttractionStore = "AttractionStore";
import { mapActions } from "vuex";
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
      contentId: 0,
    };
  },
  created() {
    let contentId = this.$route.params.contentId;
    this.contentId = contentId;
    console.log(contentId);
    this.loadAttractionDetail(contentId);
    // attractionDetail(
    //   contentId,
    //   ({ data }) => {
    //     this.nearAttraction = data.nearAttraction;
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
  },
  methods: {
    ...mapActions(AttractionStore, ["attractionDetail"]),
    loadAttractionDetail(contentId) {
      if (contentId) {
        console.log("loadAttractionDetail", contentId);
        this.attractionDetail(contentId);
        console.log("loadAttractionDetail", contentId);
      }
    },
  },
};
</script>

<style scoped></style>
