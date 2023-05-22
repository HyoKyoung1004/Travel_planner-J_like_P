<template>
  <div>
    <b-container class="bv-example-row">
      <b-row>
        <h3>여행 일정 -</h3>
        &nbsp; &nbsp;
        <h3 style="color: #0a97cd">3 건</h3>
      </b-row>
      <hr style="margin-top: 0px" />
    </b-container>
    <br />
    <b-container class="bv-example-row">
      <template>
        <b-row>
          <b-col cols="12" class="center">
            <b-card
              no-body
              class="overflow-hidden"
              style="width: 100%; height: 200px"
            >
              <b-row no-gutters>
                <b-col md="3" @click="goPlanDetail">
                  <b-card-img
                    :src="mainImg"
                    alt="Image"
                    class="rounded-0"
                    style="height: 100%"
                  ></b-card-img>
                  <div class="d-day-circle">D-15</div>
                </b-col>
                <b-col md="9">
                  <b-card-body>
                    <b-card-text>
                      <b-row style="margin: 0px">
                        <b-col cols="4">
                          <div>
                            <div class="travel-title">NAMWON</div>
                            <div class="uk-text-meta">대한민국 남원</div>
                            <div
                              class="uk-text-meta"
                              style="font-size: 12px; margin-top: 8px"
                            >
                              plan 주인
                            </div>
                            <div
                              class="info-container"
                              style="margin-top: 40px"
                            ></div>
                          </div>
                        </b-col>
                        <b-col cols="8">
                          <div style="height: 50%">
                            <div
                              class="small-title"
                              style="float: left; width: 50%"
                            >
                              여행이름
                              <span
                                class="content-text"
                                style="line-height: 40px"
                              >
                                plan Name
                              </span>
                            </div>

                            <div
                              class="small-title"
                              style="float: right; width: 50%"
                            >
                              최종인원

                              <span
                                class="content-text"
                                style="line-height: 40px"
                              >
                                3인
                              </span>
                            </div>
                          </div>
                          <div style="height: 50%">
                            <div
                              class="small-title"
                              style="float: left; width: 50%"
                            >
                              여행 일자
                              <span
                                class="content-text"
                                style="line-height: 40px"
                              >
                                2023.5.21 ~ 2023.5.25
                              </span>
                            </div>

                            <div
                              class="small-title"
                              style="float: right; width: 50%"
                            >
                              선택장소

                              <span
                                class="content-text"
                                style="line-height: 40px"
                              >
                                12
                              </span>
                            </div>
                          </div>
                        </b-col>
                      </b-row>
                      <b-row style="margin: 0px">
                        <b-col>
                          <div>
                            <button class="plan-button" @click="goPlanDetail">
                              일정 상세
                            </button>
                            <button class="plan-button" @click="sharePlan">
                              일정 공유
                            </button>
                            <button class="plan-button" @click="deletePlan">
                              일정 삭제
                            </button>
                          </div>
                        </b-col>
                      </b-row>
                    </b-card-text>
                  </b-card-body>
                </b-col>
              </b-row>
            </b-card>
          </b-col>
        </b-row>
      </template>
    </b-container>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      mainImg:
        "http://tong.visitkorea.or.kr/cms/resource/25/2823725_image2_1.jpg",
    };
  },
  created() {},
  mounted() {},
  computed: {},
  watch: {},
  methods: {
    goPlanDetail() {
      this.$router.push({ path: "planDetail" });
    },
    sharePlan() {
      alert("일정 공유 구현");
    },
    deletePlan() {
      alert("여행 삭제 구현");
    },
  },
};
</script>

<style scoped>
.center {
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
h3 {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
  font-weight: 600;
  color: #333;
  text-transform: none;
}

.d-day-circle {
  font-family: "Montserrat";
  position: absolute;
  top: 8px;
  left: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #26dbe1;
  color: #fff;
  font-weight: 700;
  font-size: 0.8rem;
}

element.style {
  font-size: 12px;
  margin-top: 8px;
}
.travel-title {
  font-family: "Montserrat" !important;
  font-size: 1.6rem;
  font-weight: 700;
  color: #000;
}

.uk-text-meta {
  font-size: 0.875rem;
  line-height: 1.4;
  color: #999;
}
.small-title {
  font-size: 1rem;
  font-weight: 700;
  color: #5dc9dd;
}

.small-text {
  font-size: 12px;
  /* text-align: center; */
  color: #616161;
  letter-spacing: 1px;
  padding: 8px;
}

.content-text {
  font-size: 0.9rem;
  color: #000;
  font-family: "Montserrat";
}

#test {
  font-weight: normal;
  color: black;
  transition: all 0.3s;
  box-shadow: none;
}

/* .clickedOrder {
  font-weight: bold;
  color: blue;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
} */

b-col span {
  font-weight: bold;
  font-size: 30px;
}
.test {
  font-weight: bold;
  font-size: 30px;
  content: "";
  height: 5px;
  width: 100%;
  transition: 0.3s;
  position: absolute;
  bottom: 0;
  left: 0;
  background-color: rgb(211, 120, 120);
}

text {
  font-weight: bold;
  font-size: 30px;
}

.clickedOrder {
  font-weight: bold;
  display: flex;
  justify-content: center;
  text-decoration-line: underline;
  text-decoration-color: rgb(231, 125, 125);
  text-decoration-thickness: 3px;
}

.tit a {
  overflow: hidden;
  display: inline-block;
  width: 100%;
  font-weight: bold;
  font-size: 20px;
  color: #000;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.boader {
  width: 100px;
  height: 100px;
  background-color: #f5d682;
  border: 1px solid rgba(71, 68, 68, 0.048);
}

.total_check strong span {
  padding: 0 2px;
  color: #0a97cd;
}

.btn {
  font-size: 1em;
  font-family: NotoSansKR, NotoSansJP, "돋움", Dotum, AppleGothic, Sans-serif;
  padding: 3px 3px;
  height: auto;
  border-top-left-radius: 35px;
  border-top-right-radius: 35px;
  border-bottom-left-radius: 35px;
  border-bottom-right-radius: 35px;
  background-color: #aab6c9;
  color: #fff;
  font-size: 14px;
  letter-spacing: -0.6px;
}

button {
  background-color: transparent;
  border-radius: 20px;
  padding: 7px;
  border-color: #e2e2e2;
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9,
    7px 7px 20px 0px #0002, 4px 4px 5px 0px #0001;
}

.plan-button {
  margin: 0 10px;
  border: none;
  overflow: visible;
  font: inherit;
  color: inherit;
  text-transform: none;
  -webkit-appearance: none;
  border-radius: 0;
  display: inline-block;
  box-sizing: border-box;
  padding: 0 30px;
  vertical-align: middle;
  font-size: 0.875rem;
  line-height: 38px;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  transition: 0.1s ease-in-out;
  transition-property: color, background-color, border-color;
  line-height: 53px;
  font-size: 0.875rem;
  background: #fff;
  color: #666;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
}
</style>
