<template>
  <div>
    <br />
    <br />
    <div>
      <h2>가서 뭐하지?</h2>
      <h5 style="color: #999">추천 여행로 나만의 여행을 계획해보세요!!</h5>
      <div class="center">
        <b-card-group deck class=".mx-auto">
          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/jeju.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem; max-hight=15rem;"
            class="mb-2"
            title="JEJU"
            @click="MakePlan(1)"
          >
            <b-card-text>
              <h6>제주특별자치시</h6>
            </b-card-text>
          </b-card>
          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/busan.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            title="BUSAN"
            @click="MakePlan(2)"
          >
            <b-card-text>
              <h6>부산광역시</h6>
            </b-card-text>
          </b-card>

          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/jeonju.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            title="JEONJU"
            @click="MakePlan(3)"
          >
            <b-card-text>
              <h6>전라북도 전주시</h6>
            </b-card-text>
          </b-card>
          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/gyeongju.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            title="GYEONGJU"
            @click="MakePlan(4)"
          >
            <b-card-text>
              <h6>경상북도 경주시</h6>
            </b-card-text>
          </b-card>
        </b-card-group>
      </div>
      <div class="center">
        <b-card-group deck class=".mx-auto">
          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/yeongwol.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            title="YEONGWOL"
            @click="MakePlan(5)"
          >
            <b-card-text>
              <h6>강원도 영월군</h6>
            </b-card-text>
          </b-card>
          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/gangneung.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            title="KANGNEUNG"
            @click="MakePlan(6)"
          >
            <b-card-text>
              <h6>강원도 강릉시</h6>
            </b-card-text>
          </b-card>

          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/daejeon.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            title="DAEJEON"
            @click="MakePlan(7)"
          >
            <b-card-text>
              <h6>대전광역시</h6>
            </b-card-text>
          </b-card>
          <b-card
            img-src="https://www.myro.co.kr/myro_image/city/yeosu.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            title="YEOSU"
            @click="MakePlan(8)"
          >
            <b-card-text>
              <h6>전라남도 여수시</h6>
            </b-card-text>
          </b-card>
        </b-card-group>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
const planStore = "planStore";
export default {
  components: {},
  computed: {
    ...mapState(planStore, ["selectedDayNum"]),
  },
  data() {
    return {
      lanlng: [
        { lat: 33.39273062, lng: 126.4948725 },
        { lat: 35.15912435, lng: 129.1603079 },
        { lat: 35.81622521, lng: 127.1493588 },
        { lat: 35.78927343, lng: 129.3318475 },
        { lat: 37.12568181, lng: 128.5701372 },
        { lat: 37.77876518, lng: 128.8776815 },
        { lat: 36.36863023, lng: 127.3862702 },
        { lat: 34.73059689, lng: 127.7415235 },
      ],
    };
  },
  created() {},
  methods: {
    ...mapMutations(planStore, ["clearMapState", "clearDayPlan"]),
    MakePlan(idx) {
      this.clearMapState();
      this.clearDayPlan();
      this.selectedDayNum = null;
      this.$router.push({
        name: "planTrip",
        params: {
          lat: this.lanlng[idx - 1].lat,
          lng: this.lanlng[idx - 1].lng,
        },
      });
    },
  },
};
</script>

<style scoped>
.card {
  margin: 0 auto; /* Added */
  float: none; /* Added */
  margin-bottom: 10px; /* Added */
}

.center {
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
.row {
  position: static;
}
</style>
