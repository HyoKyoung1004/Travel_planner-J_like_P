<template>
  <div>
    <br />
    <br />
    <div>
      <h2>여행자들의 일정보기</h2>
      <h5 style="color: #999">
        다른 여행자들의 일정을 참고해 나만의 여행을 계획해보세요!!
      </h5>
      <div class="center">
        <b-card-group deck class=".mx-auto">
          <template>
            <b-card
              :img-src="plan.first_image"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem"
              class="mb-2 cardTest"
              v-for="plan in PlanList"
              :key="plan.plan_id"
              @click="goPlanDetail(plan.plan_id)"
            >
              <b-card-text>
                <div>
                  <h6>{{ plan.plan_name }}</h6>
                  <i class="fa-regular fa-calendar-days"></i>
                  {{ plan.plan_start }} {{ plan.dday }}
                  <br />
                  <i class="fa-solid fa-location-dot"></i> {{ plan.sidoName }},
                  {{ plan.gugunName }} <br />
                  <i class="fa-solid fa-user"></i> 사용자 닉네임 <br />
                </div>
              </b-card-text>
            </b-card>
          </template>
        </b-card-group>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "TopTripPlan",
  components: {},
  data() {
    return {
      message: "",
      PlanList: null,
    };
  },
  created() {
    var this_temp = this;
    axios
      .get("http://localhost:9999/trip/plan/getplanRandom")
      .then(({ data }) => {
        console.log(data);
        this_temp.PlanList = data.PlanList;
        console.log(this_temp.PlanList);
      })
      .catch(({ error }) => {
        console.log(error);
      });
  },

  methods: {
    goPlanDetail(planId) {
      // alert(planId);
      this.$router.push({ name: "planDetail", query: { planId: planId } });
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

.cardTest img {
  max-height: 240px;
}
</style>
