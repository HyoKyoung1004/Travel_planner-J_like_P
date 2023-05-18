<template>
  <div>
    <br />
    <br />
    <div>
      <h2>인기여행지</h2>
      <h5 style="color: #999">많은 좋아요를 받은 인기여행지를 확인해보세요!</h5>
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
                <h4>솔향강릉오토캠핑장 <title></title></h4>
                <i class="fa-solid fa-location-dot"></i> 강원도, 강릉시 <br />
                <i class="fa-solid fa-star"></i>4.7/ 5 <br />
                <i class="fa-regular fa-comments"></i> 20개<br />
                <i class="fa-solid fa-thumbs-up"></i> 20개<br />
              </div>
            </b-card-text>
          </b-card>
          <b-card
            img-src="http://tong.visitkorea.or.kr/cms/resource/57/1581157_image2_1.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              <div>
                <small> 레포츠 </small>
                <h4>남향진해변 <title></title></h4>
                <i class="fa-solid fa-location-dot"></i> 강원도, 강릉시 <br />
                <i class="fa-solid fa-star"></i>4.7/ 5 <br />
                <i class="fa-regular fa-comments"></i> 20개<br />
                <i class="fa-solid fa-thumbs-up"></i> 20개<br />
              </div>
            </b-card-text>
          </b-card>

          <b-card
            img-src="http://tong.visitkorea.or.kr/cms/resource/67/2612467_image2_1.jpg"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
          >
            <b-card-text>
              <div>
                <small> 레포츠 </small>
                <h4>해운데 해수욕장 <title></title></h4>
                <i class="fa-solid fa-location-dot"></i> 강원도, 강릉시 <br />
                <i class="fa-solid fa-star"></i>4.7/ 5 <br />
                <i class="fa-regular fa-comments"></i> 20개<br />
                <i class="fa-solid fa-thumbs-up"></i> 20개<br />
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
                <small> 레포츠 </small>
                <h4>솔향강릉오토캠핑장 <title></title></h4>
                <i class="fa-solid fa-location-dot"></i> 강원도, 강릉시 <br />
                <i class="fa-solid fa-star"></i>4.7/ 5 <br />
                <i class="fa-regular fa-comments"></i> 20개<br />
                <i class="fa-solid fa-thumbs-up"></i> 20개<br />
              </div>
            </b-card-text>
          </b-card>
        </b-card-group>
      </div>
      <b-button href="#" style="background-color: #6a24fe">더 보러 가기</b-button>
    </div>
  </div>
</template>

<script>
import { likeTop4 } from "@/api/attraction";

export default {
  components: {},
  data() {
    return {
      attraction: [],
    };
  },
  created() {
    likeTop4(
      ({ data }) => {
        console.log(data);
        this.attraction = data.attractionArr;
        console.log(this.attraction);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {},
};
</script>

<style scoped>
nav a.router-link-exact-active {
  color: #6a24fe;
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
</style>
