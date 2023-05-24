<template>
  <div>
    <b-container class="bv-example-row">
      <b-row>
        <h3>여행 일정 -</h3>
        &nbsp; &nbsp;
        <h3 style="color: #0a97cd" v-if="plan != null">{{ plan.plan_name }}</h3>
      </b-row>
      <hr style="margin-top: 0px" />
    </b-container>

    <b-container class="bv-example-row">
      <b-row>
        <div id="map" style="width: 100%; height: 400px"></div>
      </b-row>
      <hr style="margin-top: 0px" />
    </b-container>

    <b-container v-for="idx in date" :key="idx">
      <div style="padding: 0 0 0 0; margin: 0 0 0 0">
        <b-row>
          <div class="day_info_box">
            <div class="day_txt">DAY{{ idx }}</div>
            <div class="day_info">
              <div class="day_info_left">
                <div class="day_title">{{ planDate[idx - 1] }}</div>
              </div>
            </div>
          </div>
        </b-row>
        <br />
        <b-row v-for="(detailPlan, i) in planDetailArr[idx - 1]" :key="i">
          <b-container class="bv-example-row">
            <template>
              <b-row>
                <b-col cols="12" class="center">
                  <b-card
                    no-body
                    class="overflow-hidden"
                    style="width: 100%; height: 130px"
                  >
                    <b-row no-gutters>
                      <b-col md="2" style="align-items: center">
                        <b-card-body style="align-items: center">
                          <b-card-text style="align-items: center"
                            ><div class="sch_num">{{ i + 1 }}</div>
                          </b-card-text>
                        </b-card-body>
                      </b-col>
                      <b-col md="2">
                        <b-card-img
                          :src="detailPlan.first_image"
                          alt="Image"
                          class="rounded-0"
                          style="height: 100%"
                        ></b-card-img>
                      </b-col>
                      <b-col md="8">
                        <b-card-body>
                          <b-row>
                            <div
                              class="travel-title"
                              style="float: left; margin-left: 15px"
                            >
                              {{ detailPlan.title }}
                            </div>
                          </b-row>
                          <br />
                          <b-row>
                            <b-col cols="10">
                              <div style="float: left; margin-left: 15px">
                                <i class="fa-solid fa-location-dot"></i>
                                {{ detailPlan.addr1 }}
                                <br />
                              </div>
                            </b-col>
                            <b-col
                              cols="2"
                              @click="goAttractionDetail(detailPlan.content_id)"
                            >
                              <i
                                class="fa-solid fa-circle-info"
                                style="font-size: 30px"
                              ></i>
                            </b-col>
                          </b-row>
                        </b-card-body>
                      </b-col>
                    </b-row>
                  </b-card>
                </b-col>
              </b-row>
              <b-row v-if="planDetailArr[idx - 1].length - 1 != i">
                <b-col cols="12" class="center">
                  <div class="day_sch_distance">
                    <a
                      :href="`https://www.google.com/maps/dir/?api=1&amp;origin=${
                        latlng[idx - 1][i][0]
                      },${latlng[idx - 1][i][1]}&amp;destination=${
                        latlng[idx - 1][i + 1][0]
                      },${latlng[idx - 1][i + 1][1]}`"
                      onclick="window.open(this.href, '_blank', 'width=800, height=600'); return false;"
                      >-&gt; {{ disArr[idx - 1][i] }}km 추천경로
                    </a>
                  </div>
                </b-col>
              </b-row>
            </template>
          </b-container>
        </b-row>
      </div>
    </b-container>
    <br /><br /><br />
  </div>
</template>

<script>
import { planDetail } from "@/api/plan";

export default {
  components: {},
  data() {
    return {
      map: null,
      date: 0,
      disArr: null,
      planDetailArr: null,
      latlng: null,
      plan: null,
      planDate: null,
    };
  },
  created() {
    let planId = this.$route.query.planId;
    console.log("여긴 plan 상세", planId);
    var this_temp = this;
    planDetail(
      planId,
      ({ data }) => {
        console.log(data);
        this_temp.date = data.date;
        this_temp.disArr = data.disArr;
        this_temp.planDetailArr = data.planDetailArr;
        this_temp.latlng = data.latlng;
        this_temp.plan = data.plan;
        this_temp.planDate = data.planDate;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  mounted() {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initializeMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=94b8d2908a19fbb8a3e35c6690f180ce";
    document.head.appendChild(script);

    // this.initializeMap();
  },
  methods: {
    initializeMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(
          this.latlng[0][0][0],
          this.latlng[0][0][1]
        ), // 초기 지도 중심 좌표 설정
        level: 7, // 초기 지도 줌 레벨 설정
      };

      const map = new kakao.maps.Map(container, options);

      for (var i = 0; i < this.date; i++) {
        const latLngArray = [];
        for (var j = 0; j < this.latlng[i].length; j++) {
          latLngArray.push(
            new kakao.maps.LatLng(this.latlng[i][j][0], this.latlng[i][j][1])
          );
        }

        const color = ["#FF0000", "#00FF00", "#0000FF", "#555555", "#444400"];
        // 선 그리기
        const polyline = new kakao.maps.Polyline({
          path: latLngArray,
          strokeWeight: 5,
          strokeColor: `${color[i]}`,
          strokeOpacity: 3,
        });
        polyline.setMap(map);

        latLngArray.forEach((latLng, index) => {
          const marker = new kakao.maps.Marker({
            position: latLng,
            map: map,
          });
          const infowindow = new kakao.maps.InfoWindow({
            content: ` ${i + 1}일차 여행순서 : ${index + 1}`,
            removable: true,
          });
          kakao.maps.event.addListener(marker, "click", function () {
            infowindow.open(map, marker);
          });
        });
      }
    },
    goAttractionDetail(contentId) {
      console.log(contentId);
      this.$router.push({
        name: "attractionDetail",
        params: { contentId: contentId },
      });
    },
  },
};
</script>

<style scoped>
.cover_img {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100%;
  height: 350px;
  z-index: 1;
}
#map {
  height: 400px;
}

.day_info_box {
  margin-top: 20px;
  width: 100%;
  height: 60px;
}

.day_txt {
  float: left;
  background: #203341;
  height: 60px;
  line-height: 60px;
  color: white;
  font-weight: bold;
  font-size: 22px;
  width: 110px;
  text-align: center;
}

.day_info {
  float: left;
  width: 90%;
  background: white;
  border-top: 3px solid #e5e5e5;
  border-right: 3px solid #e5e5e5;
  border-bottom: 3px solid #e5e5e5;

  height: 60px;
}
.clear {
  clear: both;
}
.day_title {
  float: left;

  color: #363636;
  font-weight: bold;
  font-size: 22px;
  padding: 7px;
}

.center {
  margin: 0 auto;
  display: flex;
  justify-content: center;
}

.sch_num {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  color: white;
  background: #223b68;
  margin: 0 auto;
  font-size: 25px;
  font-weight: bold;
  line-height: 32px;
  text-align: center;
  margin-top: 20px;
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

.day_sch_distance {
  width: 100%;
  border-left: 1px solid #e5e5e5;
  border-right: 1px solid #e5e5e5;
  height: 28px;
  line-height: 30px;
  background-position: 57px center;
  padding-left: 117px;
  color: #555555;
  font-size: 12px;
  background: #d6e1f1;
  font-weight: 900;
}

a {
  color: #555555;
  font-size: 12px;
  font-weight: 900;
  background: #d6e1f1;
}

element.style {
  font-size: 12px;
  margin-top: 8px;
}
.travel-title {
  font-family: "Montserrat" !important;
  font-size: 1.6rem;
  font-weight: 400;
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

.dayList {
  height: 30px;
  text-align: center;
  background-color: rgb(255, 255, 255);
  font-size: 14px;
  font-weight: 700;
}
</style>
