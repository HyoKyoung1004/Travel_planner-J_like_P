<template>
  <div>
    <br />
    <br />
    <div>
      <h2>위시리스트</h2>
      <h5 style="color: #999">위시리스트에 담긴 여행지를 확인해주세요!</h5>
      <h5 style="color: #999">
        선택한 여행지로 간편하게 여행 일정을 만들 수 있습니다.
      </h5>
      <br />
      <!-- <div class="center" v-for="index in divide" :key="index">
        <h1>{{ index }}</h1>
        <div v-for="i in 4" :key="i">
          <h2 v-if="(index - 1) * 4 + (i - 1) < length">
            {{ (index - 1) * 4 + (i - 1) }}
          </h2>
        </div>
      </div> -->
      <div class="center" v-for="index in divide" :key="index">
        <b-card-group deck class=".mx-auto">
          <div v-for="j in 4" :key="j">
            <b-card
              v-if="(index - 1) * 4 + (j - 1) < length"
              :img-src="
                attraction[(index - 1) * 4 + (j - 1)].firstImage == ''
                  ? '../assets/noImage.png'
                  : attraction[(index - 1) * 4 + (j - 1)].firstImage
              "
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem"
              class="mb-2"
              @click="goDetail(attraction[(index - 1) * 4 + (j - 1)].contentId)"
            >
              <b-card-text>
                <div>
                  <h4>
                    {{ attraction[(index - 1) * 4 + (j - 1)].title }}
                  </h4>
                  <i class="fa-solid fa-location-dot"></i>
                  {{ attraction[(index - 1) * 4 + (j - 1)].addr }}, <br />
                  <i class="fa-solid fa-star"></i
                  >{{ attraction[(index - 1) * 4 + (j - 1)].rating }}/ 5 |
                  <i class="fa-solid fa-thumbs-up"></i
                  >{{ attraction[(index - 1) * 4 + (j - 1)].likeCheck }}개<br />
                </div>
                <div style="float: right">
                  <button
                    ref="myWish"
                    type="button"
                    @click.capture.stop="
                      setWish(
                        attraction[(index - 1) * 4 + (j - 1)].contentId,
                        $event
                      )
                    "
                    style="border-color: #ff0044"
                  >
                    <i class="fa-solid fa-heart"></i>
                  </button>
                </div>
              </b-card-text>
            </b-card>
          </div>
        </b-card-group>
      </div>

      <b-button href="#" style="background-color: #6a24fe">여행만들기</b-button>
    </div>
  </div>
</template>

<script>
import { setWishList, getUserWishList } from "@/api/wishList";
export default {
  components: {},
  data() {
    return {
      attraction: [],
      length: 0,
      divide: 0,
      userId: 1,
    };
  },
  created() {
    var userId = this.$route.query.userId;
    this.userId = userId;

    getUserWishList(
      userId,
      ({ data }) => {
        console.log(data);
        this.attraction = data;
        this.length = this.attraction.length;
        this.divide = Math.floor(this.length / 4) + 1;

        console.log(this.attraction);
        console.log(this.length);
        console.log(this.divide);
      },
      (error) => {
        console.log(error);
      }
    );
  },

  methods: {
    goDetail(contentId) {
      this.$router.push({
        name: "attractionDetail",
        params: { contentId: contentId },
      });
    },
    setWish(contentId) {
      //로그인 하지 않은 사용자라면,,

      //로그인 한 사용자라면,,,,
      setWishList(
        contentId,
        1,
        ({ data }) => {
          console.log(data);

          if (data == "insert") {
            alert("위시리스트에 담겼습니다.");
          } else if (data == "delete") {
            alert("위시리스트에서 삭제되었습니다.");
            this.$router.go();
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
nav a.router-link-exact-active {
  color: #6a24fe;
}

button {
  background-color: transparent;
  border-radius: 20px;
  padding: 7px;
  border-color: #e2e2e2;
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9,
    7px 7px 20px 0px #0002, 4px 4px 5px 0px #0001;
}

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

.rating {
  pointer-events: auto;
  display: flex;
  flex-direction: row;
  position: relative;
  left: 0px;
  top: 0px;
  background: rgb(107, 36, 254);
  width: 60px;
  height: 30px;
  border-width: 0px;
  border-style: solid;
  transform: scale(1);
  border-color: rgba(255, 255, 255, 0);
  border-radius: 0px;
  overflow: hidden;
  align-self: flex-start;
  padding: 0px 2.24267px;
  justify-content: center;
  align-items: center;
}

.mixture {
  pointer-events: auto;
  display: flex;
  flex-direction: row;
  position: relative;
  left: 0px;
  top: 0px;
  align-items: baseline;
}

.text {
  -webkit-line-clamp: 1;
  line-height: 1.22;
  white-space: pre-wrap;
  display: -webkit-box;
  flex-direction: column;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  color: rgb(255, 255, 255);
  font-size: 16px;
  font-weight: bold;
  text-align: center;
}

.review {
  pointer-events: auto;
  display: flex;
  flex-direction: row;
  position: relative;
  left: 0px;
  top: 0px;
  background: rgba(255, 255, 255, 0);
  border-radius: 0px;
  overflow: hidden;
  border-width: 0px;
  border-style: solid;
  transform: scale(1);
  border-color: rgba(255, 255, 255, 0);
  justify-content: flex-start;
  align-items: center;
  padding: 0px;
}

.reviewtest {
  -webkit-line-clamp: 1;
  line-height: normal;
  white-space: nowrap;
  display: -webkit-box;
  flex-direction: column;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  color: rgb(102, 102, 102);
  font-size: 14px;
  font-weight: normal;
  text-align: left;
}

img {
  width: 300px;
  height: 150px;
  object-fit: cover;
}
</style>
