<template>
  <div>
    <br />
    <br />
    <div>
      <h2>인기 여행일정</h2>
      <h5 style="color: #999">
        다른 여행자들의 일정을 참고해 나만의 여행을 계획해보세요!!
      </h5>
      <div class="center">
        <b-card-group deck class=".mx-auto">
          <b-card
            img-src="http://tong.visitkorea.or.kr/cms/resource/78/2729578_image2_1.JPG"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              <div>
                <h6>여행 제목</h6>
                <i class="fa-regular fa-calendar-days"></i> 2022-05 17 12DAYS
                <br />
                <i class="fa-solid fa-location-dot"></i> 여행 경로 <br />
                <i class="fa-solid fa-user"></i> 사용자 닉네임 <br />
              </div>
            </b-card-text>
          </b-card>
          <b-card
            img-src="http://tong.visitkorea.or.kr/cms/resource/78/2729578_image2_1.JPG"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              <div>
                <h6>여행 제목</h6>
                <i class="fa-regular fa-calendar-days"></i> 2022-05 17 12DAYS
                <br />
                <i class="fa-solid fa-location-dot"></i> 강릉, 영월(여행주소)
                <br />
                <i class="fa-solid fa-user"></i> 사용자 닉네임 <br />
              </div>
            </b-card-text>
          </b-card>

          <b-card
            img-src="http://tong.visitkorea.or.kr/cms/resource/78/2729578_image2_1.JPG"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              <div>
                <h6>여행 제목</h6>
                <i class="fa-regular fa-calendar-days"></i> 2022-05 17 12DAYS
                <br />
                <i class="fa-solid fa-location-dot"></i> 강릉, 영월(여행주소)
                <br />
                <i class="fa-solid fa-user"></i> 사용자 닉네임 <br />
              </div>
            </b-card-text>
          </b-card>
          <b-card
            img-src="http://tong.visitkorea.or.kr/cms/resource/78/2729578_image2_1.JPG"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              <div>
                <h6>여행 제목</h6>
                <i class="fa-regular fa-calendar-days"></i> 2022-05 17 12DAYS
                <br />
                <i class="fa-solid fa-location-dot"></i> 강릉, 영월(여행주소)
                <br />
                <i class="fa-solid fa-user"></i> 사용자 닉네임 <br />
              </div>
            </b-card-text>
          </b-card>
        </b-card-group>
      </div>
      <b-button href="#" style="background-color: #6a24fe"
        >더 보러 가기</b-button
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "TopTripPlan",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
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
