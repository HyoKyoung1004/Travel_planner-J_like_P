<template>
  <div>
    <div class="dbDetail" id="topCp">
      <h2 style="font-weight: 800">{{ attractionDtailData.title }}</h2>
      <span
        >{{ attractionDtailData.sidoName }}
        {{ attractionDtailData.gugunName }}</span
      >
    </div>
    <br />
    <!-- 좋아요, 찜표시 아이콘으로 변경 예정 -->
    <div class="post_area">
      <button
        type="button"
        @click="setWish(attractionDtailData.contentId)"
        style="border-color: #ff0044"
      >
        <i class="fa-solid fa-heart"></i>
        <span class="num" id="위시리스트수">{{ wishCount }}</span>
      </button>
      <button
        type="button"
        @click="setLike(attractionDtailData.contentId)"
        style="border-color: #4b89dc"
      >
        <i class="fa-solid fa-thumbs-up"></i>
        <span class="num" id="좋아요수"
          >{{ attractionDtailData.likeCheck }}
        </span>
      </button>
    </div>

    <div>
      <b-container class="bv-example-row">
        <hr />
        <b-row>
          <b-col>사진보기</b-col> | <b-col>상세정보</b-col> |
          <b-col>근처 여행지보기</b-col> |
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
                  :img-src="attractionDtailData.firstImage"
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
            <h6 style="float: left">
              {{ attractionDtailData.overview }}
            </h6>
            <br /><br
          /></b-col>
          <b-col></b-col>
        </b-row>
        <br /><br />

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
                  <span class="mo"
                    ><a v-if="attractionDtailData.tel != ''">{{
                      attractionDtailData.tel
                    }}</a>
                    <a v-else>033-738-3000</a>
                  </span>
                </li>
                <li>
                  <strong>홈페이지</strong
                  ><span
                    ><a
                      :href="attractionDtailData.homepage"
                      target="_blank"
                      title="홈페이지로 이동"
                      >{{ attractionDtailData.title }} 홈페이지로 이동</a
                    ></span
                  >
                </li>
                <li>
                  <strong>상세 주소</strong>
                  <span class="mo">{{ attractionDtailData.addr }}</span>
                </li>
                <li>
                  <strong>이용시간</strong><span>[3월~10월] 09:00~18:00</span>
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
                    <i class="fa-solid fa-thumbs-up"></i>를 눌러주시면 추천
                    여행지에 순위가 반영됩니다. <br /><i
                      class="fa-solid fa-heart"
                    ></i
                    >를 눌러주시면 위시리스트에 담겨 여행 계획을 만들 때 쉽게
                    선택할 수 있습니다.
                  </p>
                </div>
                <div class="form">
                  <span class="good">
                    <button
                      style="border-color: #4b89dc"
                      @click="setLike(attractionDtailData.contentId)"
                    >
                      <h3><i class="fa-solid fa-thumbs-up"></i> 좋아요!</h3>
                    </button>
                    <button
                      style="border-color: #ff0044"
                      @click="setWish(attractionDtailData.contentId)"
                    >
                      <h3>
                        <i class="fa-solid fa-heart" @click="setWish"></i> 찜!
                      </h3>
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
import { mapState } from "vuex";
import { setWishList, getAttractionWishCount } from "@/api/wishList";
import { setLikeAttraction } from "@/api/like";

const AttractionStore = "AttractionStore";
const memberStore = "memberStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      attraction: [],
      map: null,
      wishCount: 0,
    };
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(AttractionStore, ["attractionDtailData"]),
  },
  watch: {
    map() {
      this.map.relayout();
    },
    attractionDtailData() {
      console.log("watch에서 바라보고 있음");
      this.initMap();

      getAttractionWishCount(
        this.attractionDtailData.contentId,
        ({ data }) => {
          console.log(data);
          this.wishCount = data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  mounted() {
    //this.initMap();
    // if (window.kakao && window.kakao.maps) {
    //   this.initMap();
    // } else {
    //   const script = document.createElement("script");
    //   /* global kakao */
    //   script.onload = () => kakao.maps.load(this.initMap);
    //   script.src =
    //     "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=94b8d2908a19fbb8a3e35c6690f180ce";
    //   document.head.appendChild(script);
    // }

    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=94b8d2908a19fbb8a3e35c6690f180ce";
    document.head.appendChild(script);
  },
  methods: {
    initMap() {
      var lat = this.attractionDtailData.latitude;
      var lng = this.attractionDtailData.longitude;
      console.log("여기서 변경된 확인");
      console.log(this.attractionDtailData.title, "여기임");
      console.log("지도의 좌표", lat, lng);
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(lat, lng),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      this.map.relayout();
      console.log("정말");
      this.map.relayout();
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },

    setLike(contentId) {
      if (this.userInfo == null) {
        alert("로그인 후 이용 가능합니다.");
      } else {
        // const buttonElement = this.$refs.myWish;

        var userId = this.userInfo.userId;
        //로그인 한 사용자라면,,,,
        setLikeAttraction(
          contentId,
          userId,
          ({ data }) => {
            console.log("좋아요");
            console.log(data);

            if (data == "insert") {
              alert("좋아요를 추가");
              this.attractionDtailData.likeCheck =
                this.attractionDtailData.likeCheck + 1;
            } else if (data == "delete") {
              alert("좋아요 취소");
              this.attractionDtailData.likeCheck =
                this.attractionDtailData.likeCheck - 1;
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    setWish(contentId) {
      if (this.userInfo == null) {
        alert("로그인 후 이용 가능합니다.");
      } else {
        // const buttonElement = this.$refs.myWish;

        var userId = this.userInfo.userId;
        //로그인 한 사용자라면,,,,
        setWishList(
          contentId,
          userId,
          ({ data }) => {
            console.log(data);

            if (data == "insert") {
              alert("위시리스트에 담겼습니다.");
              this.wishCount = this.wishCount + 1;
            } else if (data == "delete") {
              alert("위시리스트에서 삭제되었습니다.");
              this.wishCount = this.wishCount - 1;
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
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
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9,
    7px 7px 20px 0px #0002, 4px 4px 5px 0px #0001;
}
</style>
