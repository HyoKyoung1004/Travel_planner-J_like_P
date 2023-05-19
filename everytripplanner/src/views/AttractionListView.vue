<template .center>
  <div class=".center">
    <div>
      <choose-attraction></choose-attraction>
      <hr />
    </div>
    <div>
      <b-container class="bv-example-row">
        <b-row style="margine: 1px">
          <b-col cols="2">총 3000건</b-col>
          <b-col cols="7"></b-col>
          <b-col cols="3">
            <b-row>
              <b-col :class="{ clickedOrder: latest }" @click="makeOrder('latest')">최신순</b-col> |
              <b-col class="{ clickedOrder: like }" @click="makeOrder('like')">인기순</b-col> |
              <b-col id="title" :class="{ clickedOrder: title }" @click="makeOrder('title')"
                >이름순</b-col
              >
            </b-row>
          </b-col>
        </b-row>
        <hr />
      </b-container>
    </div>
    <div>
      <b-container>
        <b-row>
          <b-col>
            <b-card
              :src="attractionList[0].firstImage"
              img-src="https://placekitten.com/300/300"
              img-alt="Card image"
              img-left
              class="mb-3"
              style="max-height: 150px; padding: 10px; margin: 10px"
            >
              <b-card-text>
                <div>
                  <div class="area_txt">
                    <b-row>
                      <b-col col="3">
                        <div class="tit" style="float: left">
                          <a
                            href="javascript:;"
                            onclick="goDetail('6cb1642d-b578-462f-82c6-b1c1ef468af7','6',null);"
                            >{{ attractionList[0].title }}</a
                          >
                        </div>
                      </b-col>
                    </b-row>
                    <b-col col="9"></b-col>
                    <b-row>
                      <b-col>
                        <div style="float: left">
                          <i class="fa-solid fa-location-dot"></i> {{ attractionList[0].addr }}
                        </div>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col>
                        <div style="float: left">
                          <template v-if="attractionList[0].rating >= 1">
                            <span
                              class="num"
                              id="좋아요수"
                              v-for="index in attractionList[0].rating"
                              :key="index"
                              >⭐</span
                            ></template
                          >
                          <template v-else>⭐</template>
                          [ {{ attractionList[0].rating }} ] | 👍 [
                          {{ attractionList[0].likeCheck }}]
                        </div>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col>
                        <div style="float: left">
                          <template v-if="attractionList[0].tel != ''">
                            📞
                            {{ attractionList[0].tel }}</template
                          >
                          <template v-else> 📞 051-607-6395</template>
                        </div>
                      </b-col>
                    </b-row>

                    <p>부산 남구</p>
                    <p>051-607-6395</p>
                    <div class="btn_foot_stamp"></div>
                  </div>
                </div>
              </b-card-text>
            </b-card>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import ChooseAttraction from "@/components/Attraction/ChooseAttraction.vue";
import { attractdionList_addr_searchData_type, attractionList_type } from "@/api/attraction";

export default {
  components: {
    ChooseAttraction,
  },
  data() {
    return {
      attractionList: [],
      sido: 0,
      gugun: 0,
      type: 0,
      searchData: 0,
      page: 1,
      orderType: 0,
      latest: true,
      like: false,
      title: false,
    };
  },
  created() {
    var sido = this.$route.query.contant;
    var gugun = this.$route.query.gugun;
    var type = this.$route.query.type;
    var searchData = this.$route.query.searchData;
    var page = this.$route.query.page;
    var orderType = this.$route.query.orderType;
    this.diviceSearch(sido, gugun, type, searchData, page, orderType);
  },
  methods: {
    diviceSearch(sido, gugun, type, searchData, page, orderType) {
      if (sido != undefined && gugun != undefined) {
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
      console.log(sido, gugun, type, searchData, page, orderType);

      if (sido != undefined && gugun != undefined && searchData != undefined && type != undefined) {
        this.search_addr_title_type(sido, gugun, searchData, type, page, orderType);
      }
      if (type != undefined) {
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
      this.diviceSearch(this.ido, this.gugun, this.type, this.searchData, this.page, tmp);
      this.latest = false;
      this.like = false;
      this.title = false;
      if (tmp == "latest") this.latest = true;
      if (tmp == "like") this.like = true;
      if (tmp == "title") this.title = true;
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
</style>
