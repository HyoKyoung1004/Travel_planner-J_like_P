<template>
  <div>
    <div class="center">
      <b-container>
        <b-row class="text-center">
          <b-col></b-col>
          <b-col cols="10"> <h3 style="font-weight: 800">근처 여행지</h3></b-col>
          <b-col></b-col>
        </b-row>
        <b-row class="text-center">
          <b-col cols="12"> <hr class="boldhr" /></b-col>
        </b-row>
        <br />
        <b-row class="text-center">
          <b-col cols="7">
            <div id="map2"></div>
          </b-col>
          <b-col cols="5">
            <div style="overflow-y: scroll; height: 700px">
              <ul v-for="(item, idx) in detailNearAttraction" :key="idx">
                <li style="width: 95%" @click="makeMaker(item)">
                  <div class="flex-container">
                    <div style="flex-grow: 4">
                      <img
                        v-if="item.firstImage != ''"
                        :src="item.firstImage"
                        alt="My Image"
                        style="width: 180px; height: 120px"
                      />
                      <div v-else style="width: 180px; height: 120px" id="map2"></div>
                    </div>
                    <div style="flex-grow: 8">
                      <h6>{{ item.title }}</h6>
                      <br />
                      <i class="fa-solid fa-map"></i>{{ item.distance }}km<br />
                      <i class="fa-solid fa-star"></i>{{ item.rating }}/ 5
                      <i class="fa-solid fa-thumbs-up"></i>
                      {{ item.likeCheck }}개<br />
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </b-col>
        </b-row>
        <b-row>
          <p>
            <button @click="showMarkers">전체 목록 마커 보이기</button>
          </p>
        </b-row>
        <br />
      </b-container>
    </div>
    <br /><br /><br /><br />
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      nearAttraction: [],
      map: null,
      position: [],
      markers: [],
    };
  },

  computed: {
    ...mapState("AttractionStore", ["detailNearAttraction"]),
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },

  methods: {
    initMap() {
      console.log("initMap() > 여기까지 ");
      var container2 = document.getElementById("map2");
      var options2 = {
        center: new kakao.maps.LatLng(37.76390729, 128.9543806),
        level: 3,
      };
      var map2 = new kakao.maps.Map(container2, options2);
      this.map = map2;

      //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var marker = new kakao.maps.Marker({
        position: map2.getCenter(),
      });
      marker.setMap(map2);

      this.positions = [];
      this.detailNearAttraction.forEach((item) => {
        let obj = {};
        obj.title = item.title;
        obj.latlng = new kakao.maps.LatLng(item.latitude, item.longitude);
        obj.content = `<div style="padding:5px;">${item.title}</div>`;
        this.positions.push(obj);
      });
      this.loadMaker();
    },

    reMap(lat, lng, title) {
      var container2 = document.getElementById("map2");
      var options2 = {
        center: new kakao.maps.LatLng(lat, lng),
        level: 3,
      };
      var map2 = new kakao.maps.Map(container2, options2);

      var iwContent = `<div style="padding:5px;">${title}</div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(lat, lng), //인포윈도우 표시 위치입니다
        iwRemoveable = true;

      var infowindow = new kakao.maps.InfoWindow({
        map: map2, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });
      console.log(infowindow);

      //마커추가하려면 객체를 아래와 같이 하나 만든다.
      //   var marker = new kakao.maps.Marker({
      //     position: map2.getCenter(),
      //   });
      //   marker.setMap(map2);
    },

    addScript() {
      console.log("addScript() >여기까지");

      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=94b8d2908a19fbb8a3e35c6690f180ce";
      document.head.appendChild(script);
    },
    makeMaker(item) {
      console.log(item);
      this.reMap(item.latitude, item.longitude, item.title);
    },
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      console.log("1111");
      //this.deleteMarker();
      console.log("2222");
      // 마커 이미지를 생성합니다
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기 입니다
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성합니다
      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          //   image: markerImage, // 마커의 이미지
        });
        this.markers.push(marker);

        var infowindow = new kakao.maps.InfoWindow({
          content: position.content, // 인포윈도우에 표시할 내용
        });

        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow)
        );
        kakao.maps.event.addListener(marker, "mouseout", this.makeOutListener(infowindow));
      });
      console.log("마커수 ::: " + this.markers.length);

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },
    showMarkers() {
      this.initMap();
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
  },
};
</script>

<style scoped>
#map2 {
  width: 680px;
  height: 700px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

/* strong {
  width: 20%;
  float: left;
}
span {
  width: 80%;
  float: right;
} */

ul li {
  float: left;
  width: 50%;
  padding: 3px 0 6px 0;
  display: table;
  font-size: 15px;
  font-weight: 400;
  background: none !important;
}

ul li strong {
  float: none;
  margin-right: 0;
  position: relative;
  display: table-cell;
  width: 128px;
  padding: 0 0 0 12px;
  font-weight: 700;
  color: #333;
}

.user_expression {
  display: flex;
  width: 940px;
  margin: 0 auto;
  padding: 62px 0;
  justify-content: space-between;
  word-break: keep-all;
}

.user_expression strong {
  text-align: center;
  font-weight: 700;
  font-size: 22px;
  letter-spacing: -1px;
  line-height: 24px;
  color: #333;
}

/* .btn-1 {
  transition: all 0.3s ease;
}
.btn-1:hover {
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9,
    7px 7px 20px 0px #0002, 4px 4px 5px 0px #0001;
} */

button {
  background-color: transparent;
  border-radius: 20px;
  padding: 7px;
  border-color: #e2e2e2;
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9, 7px 7px 20px 0px #0002,
    4px 4px 5px 0px #0001;
}

.flex-container {
  display: flex;
  align-items: stretch;
  background-color: #f1f1f1;
}

.flex-container > div {
  margin: 3px;
  text-align: center;
}
</style>
