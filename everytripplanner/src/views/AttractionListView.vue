<template .center>
  <div class=".center">
    <div>
      <choose-attraction @childData="Change"></choose-attraction>
      <hr />
    </div>
    <div>
      <b-container class="bv-example-row">
        <b-row style="margine: 1px">
          <b-col cols="2" class="total_check"
            ><strong
              >총<span id="totalCnt">{{ totalCount }}</span
              >건</strong
            ></b-col
          >
          <b-col cols="7"></b-col>
          <b-col cols="3">
            <b-row>
              <b-col
                :class="{ clickedOrder: latest }"
                @click="makeOrder('latest')"
                >최신순</b-col
              >
              |
              <b-col :class="{ clickedOrder: like }" @click="makeOrder('like')"
                >인기순</b-col
              >
              |
              <b-col
                id="title"
                :class="{ clickedOrder: title }"
                @click="makeOrder('title')"
                >이름순</b-col
              >
            </b-row>
          </b-col>
        </b-row>
        <hr />
      </b-container>
    </div>

    <b-container class="bv-example-row">
      <template v-for="(attraction, idx) in attractionList">
        <b-row :key="idx">
          <b-col cols="12" class="center">
            <b-card
              no-body
              class="overflow-hidden"
              style="width: 1000px; height: 200px"
            >
              <b-row no-gutters>
                <b-col md="4">
                  <b-card-img
                    v-if="attraction.firstImage != ''"
                    :src="attraction.firstImage"
                    alt="Image"
                    class="rounded-0"
                  ></b-card-img>
                </b-col>
                <b-col md="8" @click="goDetail(attraction.contentId)">
                  <b-card-body :title="attraction.title">
                    <b-card-text>
                      <b-row>
                        <b-col>
                          <div style="float: left">
                            <i class="fa-solid fa-location-dot"></i>
                            {{ attraction.addr }}
                          </div>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col>
                          <div style="float: left">
                            <template v-if="attraction.rating >= 1">
                              <span
                                class="num"
                                id="좋아요수"
                                v-for="index in attraction.rating"
                                :key="index"
                                >⭐</span
                              ></template
                            >
                            <template v-else>⭐</template>
                            ( {{ attraction.rating }} ) 👍 (
                            {{ attraction.likeCheck }} )
                          </div>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col>
                          <div style="float: left">
                            <template v-if="attraction.tel != ''">
                              📞
                              {{ attraction.tel }}</template
                            >
                            <template v-else> 📞 051-607-6395</template>
                          </div>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col>
                          <div style="float: left">
                            <button type="button" class="btn active">
                              <span>{{ typeString(attraction.type) }}</span>
                            </button>
                          </div>
                        </b-col>
                        <div style="float: right">
                          <button
                            ref="myWish"
                            type="button"
                            @click.capture.stop="
                              setWish(attraction.contentId, $event)
                            "
                            style="border-color: #ff0044"
                          >
                            <i class="fa-solid fa-heart"></i>
                          </button>
                        </div>
                      </b-row>
                    </b-card-text>
                  </b-card-body>
                </b-col>
              </b-row>
            </b-card>
          </b-col>
        </b-row>
        <br :key="attraction.contentId" />
      </template>
      <div class="center">
        <b-pagination v-model="page" :total-rows="rows"></b-pagination>
      </div>
    </b-container>
  </div>
</template>

<script>
import ChooseAttraction from "@/components/Attraction/ChooseAttraction.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";
import {
  attractdionList_addr_searchData_type,
  attractionList_type,
  attractdionList_addr,
  attractdionList_addr_type,
} from "@/api/attraction";

import { setWishList } from "@/api/wishList";

export default {
  components: {
    ChooseAttraction,
  },
  data() {
    return {
      attractionList: [],
      sido: undefined,
      gugun: undefined,
      type: undefined,
      searchData: undefined,
      page: 1,
      orderType: 0,
      latest: true,
      like: false,
      title: false,
      length: 0,
      startPage: 0,
      endPage: 0,
      pre: 1,
      next: 11,
      start: 0,
      totalCount: 0,
      rows: 0,
    };
  },
  created() {
    var sido = this.$route.query.sido;
    var gugun = this.$route.query.gugun;
    var type = this.$route.query.type;
    var searchData = this.$route.query.searchData;
    var page = this.$route.query.page;
    var orderType = this.$route.query.orderType;
    this.diviceSearch(sido, gugun, type, searchData, page, orderType);
  },
  mounted() {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    getTypeString(type) {
      return this.typeString(type);
    },
    // getTotolPage() {
    //   console.log("페이징 개수 변경됨");
    //   console.log("this.totalCount", this.totalCount);
    //  this.rows = Math.floor(this.totalCount / 10) + 1;
    //   console.log("페이징 수 ", this.totalCount / 10 + 1);
    //   return this.totalCount / 10 + 1;
    // },
  },
  watch: {
    gugun() {
      console.log("구 변경됨");
      console.log(this.sido);
      console.log(this.page);
      this.diviceSearch(
        this.sido,
        this.gugun,
        this.type,
        this.searchData,
        this.page,
        this.orderType
      );
    },
    page() {
      console.log(this.page);
      this.diviceSearch(
        this.sido,
        this.gugun,
        this.type,
        this.searchData,
        this.page,
        this.orderType
      );
    },
    totalCount() {
      console.log("페이지 변경 완료?");
      this.rows = Math.floor(this.totalCount / 10) + 1;
      console.log(this.rows);
      this.rows = Math.floor(this.totalCount / 10) + 1;
    },
  },
  methods: {
    Change(childData) {
      console.log(childData);
      console.log(this.type);
      console.log("Change()");

      this.sido = childData.sido;
      this.gugun = childData.gugun;

      if (childData.sido != undefined && childData.type != undefined) {
        console.log("시도, type");
        this.type = childData.type;

        this.search_addr_type(this.sido, this.gugun, this.type);
      } else if (childData.sido != undefined) {
        console.log("시도");

        this.search_addr(this.sido, this.gugun);
      } else if (childData.type != undefined) {
        console.log("타입만");

        this.type = childData.type;
        this.search_type(this.type, 1, this.orderType);
      }
    },
    goDetail(contentId, e) {
      //상세페이지 이동
      console.log(e);
      this.$router.push({
        name: "attractionDetail",
        params: { contentId: contentId },
      });
    },
    typeString(type) {
      if (type == 12) return "관광지";
      else if (type == 14) return "문화시설";
      else if (type == 15) return "축제공연행사";
      else if (type == 25) return "여행코스";
      else if (type == 28) return "레포츠";
      else if (type == 32) return "숙박";
      else if (type == 38) return "쇼핑";
      else if (type == 39) return "음식점";
    },
    diviceSearch(sido, gugun, type, searchData, page, orderType) {
      if (sido != undefined && gugun != undefined) {
        this.sido = sido;
        this.gugun = gugun;
      }
      if (type != undefined) {
        this.type = type;
      }
      if (searchData != undefined) {
        this.searchData = searchData;
      }
      if (page === undefined) {
        page = 1;
        this.page = page;
      }
      if (orderType === undefined) {
        orderType = "latest";
        this.orderType = orderType;
      }
      console.log("갈라짐");
      console.log(sido, gugun, type, searchData, page, orderType);

      if (
        sido !== undefined &&
        gugun !== undefined &&
        searchData !== undefined &&
        type !== undefined
      ) {
        console.log("설마 여기로감?");
        this.search_addr_title_type(
          sido,
          gugun,
          searchData,
          type,
          page,
          orderType
        );
      } else if (sido != undefined && gugun != undefined && type != undefined) {
        console.log("시도_타압");
        this.search_addr_type(sido, gugun, type, page, orderType);
      } else if (sido != undefined && gugun != undefined) {
        console.log("시도");
        console.log(this.page, this.orderType);
        this.search_addr(sido, gugun, this.page, this.orderType);
      } else if (type != undefined) {
        console.log("타압");
        this.search_type(type, page, orderType);
      }
    },
    search_addr_title_type(sido, gugun, searchData, type, page, orderType) {
      attractdionList_addr_searchData_type(
        sido,
        gugun,
        searchData,
        type,
        page,
        orderType,
        ({ data }) => {
          console.log(data);
          this.attractionList = data.list;
          this.length = this.attractionList.length;
          this.page = page;
          this.totalCount = data.totalCount;
          this.endPage = data.endPage;
          this.next = data.next;
          this.pre = data.pre;
          this.start = data.start;
          this.startPage = data.startPage;
          this.attractionList = data.list;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    search_addr(sido, gugun, page, orderType) {
      console.log(sido, gugun, page, orderType);
      if (page == undefined) page = 1;
      if (orderType == undefined) orderType = "latest";
      attractdionList_addr(
        sido,
        gugun,
        page,
        orderType,
        ({ data }) => {
          console.log(data);
          this.page = page;
          this.totalCount = data.totalCount;
          this.endPage = data.endPage;
          this.next = data.next;
          this.pre = data.pre;
          this.start = data.start;
          this.startPage = data.startPage;
          this.attractionList = data.list;
          console.log(this.attractionList);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    search_addr_type(sido, gugun, type, page, orderType) {
      console.log("시도_타입");
      console.log(sido, gugun, type, page, orderType);
      attractdionList_addr_type(
        sido,
        gugun,
        type,
        page,
        orderType,
        ({ data }) => {
          console.log(data);
          this.page = page;
          this.totalCount = data.totalCount;
          this.endPage = data.endPage;
          this.next = data.next;
          this.pre = data.pre;
          this.start = data.start;
          this.startPage = data.startPage;
          this.attractionList = data.list;
          console.log(this.attractionList);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    search_type(type, page, orderType) {
      attractionList_type(
        type,
        page,
        orderType,
        ({ data }) => {
          console.log("타입만 선택");
          console.log(data);
          this.page = page;
          this.totalCount = data.totalCount;
          this.endPage = data.endPage;
          this.next = data.next;
          this.pre = data.pre;
          this.start = data.start;
          this.startPage = data.startPage;
          this.attractionList = data.list;
          console.log(this.attractionList);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    makeOrder(tmp) {
      console.log("최신순을 누름");
      this.diviceSearch(
        this.sido,
        this.gugun,
        this.type,
        this.searchData,
        this.page,
        tmp
      );
      this.like = false;
      this.latest = false;

      this.title = false;
      if (tmp == "latest") this.latest = true;
      if (tmp == "like") this.like = true;
      if (tmp == "title") this.title = true;
    },

    setWish(contentId, event) {
      //로그인 하지 않은 사용자라면,,
      if (this.userInfo == null) {
        alert("로그인 후 이용 가능합니다.");
      } else {
        // const buttonElement = this.$refs.myWish;

        console.log(this.$refs.myWish);
        console.log(this.$refs.myWish.innerHTML);

        this.$nextTick(() => {
          console.log(this.$refs.myWish);
          console.log(this.$refs.myWish.innerHTML);
        });

        const button = event.target;
        console.log(button);
        console.log(button.innerHTML);

        var userId = this.userInfo.userId;
        //로그인 한 사용자라면,,,,
        setWishList(
          contentId,
          userId,
          ({ data }) => {
            console.log(data);

            if (data == "insert") {
              alert("위시리스트에 담겼습니다.");
              button.innerHTML = "❤";
            } else if (data == "delete") {
              alert("위시리스트에서 삭제되었습니다.");
              button.innerHTML = '<i class="fa-solid fa-heart"></i>';
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
.center {
  margin: 0 auto;
  display: flex;
  justify-content: center;
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
</style>
