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
          >
            <b-card-text>
              <h6>전라남도 여수시</h6>
            </b-card-text>
          </b-card>
        </b-card-group>
      </div>

      <b-button href="#" style="background-color: #6a24fe">여행 만들기</b-button>
    </div>
  </div>
</template>

<script>
export default {
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
