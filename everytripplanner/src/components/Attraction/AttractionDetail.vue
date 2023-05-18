<template>
  <div>
    여기는 상세정보 보여주는 화면임

    <div class="dbDetail" id="topCp">
      <h2 style="font-weight: 800">평창 태기산 캠핑장 펜션</h2>
      <span>강원도 평창군</span>
    </div>
    <br />
    <!-- 좋아요, 찜표시 아이콘으로 변경 예정 -->
    <div class="post_area">
      <button type="button" onclick="setWish();" style="border-color: #ff0044">
        <i class="fa-solid fa-heart"></i>
        <span class="num" id="위시리스트수">15 </span>
      </button>
      <button type="button" onclick="setLike();" style="border-color: #4b89dc">
        <i class="fa-solid fa-thumbs-up"></i>
        <span class="num" id="좋아요수">15 </span>
      </button>
    </div>

    <div>
      <b-container class="bv-example-row">
        <hr />
        <b-row>
          <b-col>사진보기</b-col> | <b-col>상세정보</b-col> | <b-col>근처 여행지보기</b-col> |
          <b-col>댓글 작성하기</b-col>
        </b-row>
        <hr />
      </b-container>
    </div>

    <div class="center">
      <b-container class="bv-example-row">
        <b-row class="text-center">
          <b-col></b-col>
          <b-col cols="10"
            ><div>
              <b-carousel
                id="carousel-fade"
                style="text-shadow: 0px 0px 2px #000"
                fade
                indicators
                img-width="600"
                img-height="480"
              >
                <!-- 여기는 for문으로 사진바꿔야함 -->
                <b-carousel-slide
                  caption="first_image"
                  img-src="http://tong.visitkorea.or.kr/cms/resource/21/2657021_image2_1.jpg"
                ></b-carousel-slide>
              </b-carousel></div
          ></b-col>
          <b-col></b-col>
        </b-row>
      </b-container>
    </div>
    <br /><br /><br />
    <div class="center">
      <b-container class="bv-example-row">
        <b-row class="text-center">
          <b-col></b-col>

          <b-col cols="10"> <h3 style="font-weight: 800">상세정보</h3></b-col>
          <b-col></b-col>
        </b-row>
        <b-row class="text-center">
          <b-col cols="12"> <hr class="boldhr" /></b-col>
        </b-row>
        <b-row class="text-center"> <br /></b-row>
        <b-row>
          <b-col></b-col>

          <b-col cols="12" style="float: left">
            <h5 style="float: left">
              솔향강릉오토캠핑장은 강릉의 랜드마크이자 핫 플레이스인 안목해변 커피 거리에서 도보
              5-7분 거리에 위치한 카라반 캠핑장이다. 도심에서 벗어나 야영을 하면서 커피 한잔의
              여유를 즐길 수 있는 매력적인 곳이다. 캠핑장은 강릉경찰수련원 뒤쪽 유휴부지에 조성되어
              있다. 경찰수련원 정문을 진입해서 바로 보이는 우측 건물 뒷편으로 들어가면 된다.
              이곳에는 잘 관리된 39대의 대형 카라반을 갖추고 있는데, 내부엔 간단한 조리시설과 TV,
              에어컨, 온풍기, 냉장고 등 이 구비되어 있고 샤워 가능한 화장실도 마련되어 있다.
              내부에는 수건, 헤어드라이어, 샤워용품 등의 물품들도 비치되어 있어 따로 챙겨 가지
              않아도 된다. 또한 야외에 따로 샤워장이 더 있고, 어린이들과 즐길 수 있는 오락실과 매점,
              캠핑의 낭만을 즐길 수 있는 데크와 그릴까지 장비들도 잘 구비되어 있다.
            </h5>
            <br /><br
          /></b-col>
          <b-col></b-col>
        </b-row>
        <b-row class="text-center">
          <b-col cols="1"></b-col>
          <b-col cols="10"> <div id="map"></div> </b-col>
          <b-col cols="1"></b-col>
        </b-row>
        <br /><br />
        <br /><br />
      </b-container>
      <br /><br />
      <div class="center">
        <b-container class="bv-example-row">
          <b-row align-v="stretch">
            <div class="inr">
              <ul style="width=50%; float=left">
                <li>
                  <strong>문의 및 안내</strong>
                  <span class="mo"><a href="#">044-251-0001</a></span>
                </li>
                <li>
                  <strong>홈페이지</strong
                  ><span
                    ><a
                      href="https://www.sejong.go.kr/prog/tursmCn/tour/sub01_17/view.do?cntno=23"
                      target="_blank"
                      title="국립세종수목원 홈페이지로 이동"
                      >https://www.sejong.go.kr/prog/tursmCn/tour/sub01_17/view.do?cntno=23</a
                    ></span
                  >
                </li>
                <li>
                  <strong>문의 및 안내</strong>
                  <span class="mo">044-251-0001</span>
                </li>
                <li>
                  <strong>이용시간</strong
                  ><span
                    >[3월~10월] 09:00~18:00<br />
                    [11월~[2반] 팀장 채널월] 09:00~17:00<br />
                    ※ 입장마감은 관람시간 마감 1시간전</span
                  >
                </li>
                <li>
                  <strong>휴일</strong
                  ><span
                    >매주 월요일, 1월 1일, 설추석 당일 (월요일이 공휴일인 경우 그 다음날 휴관)</span
                  >
                </li>
              </ul>
            </div>
          </b-row>
          <br /><br /><br />
          <b-row>
            <b-col cols="1"></b-col>
            <b-col cols="10">
              <div class="user_expression">
                <div class="txt">
                  <strong id="uriTitle">해당 여행지가 마음에 드시나요?</strong>
                  <p>
                    <i class="fa-solid fa-thumbs-up"></i>를 눌러주시면 추천 여행지에 순위가
                    반영됩니다. <br /><i class="fa-solid fa-heart"></i>를 눌러주시면 위시리스트에
                    담겨 여행 계획을 만들 때 쉽게 선택할 수 있습니다.
                  </p>
                </div>
                <div class="form">
                  <span class="good">
                    <button style="border-color: #4b89dc">
                      <h3><i class="fa-solid fa-thumbs-up"></i> 좋아요!</h3>
                    </button>
                    <button style="border-color: #ff0044">
                      <h3><i class="fa-solid fa-heart"></i> 찜!</h3>
                    </button>
                  </span>
                </div>
              </div>
            </b-col>
            <b-col cols="1"></b-col>
          </b-row>
        </b-container>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      infowindow: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=94b8d2908a19fbb8a3e35c6690f180ce";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map((position) => new kakao.maps.LatLng(...position));

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<style scoped>
#map {
  width: 900px;
  height: 400px;
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
</style>
