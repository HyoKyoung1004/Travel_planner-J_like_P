<template>
  <div>
    <br />
    <br />

    <div>
      <i class="fa-duotone fa-comments"></i>
      <i class="fa-duotone fa-comments"></i>
      <i class="fa-duotone fa-comments"></i>
      <font-awesome-icon :icon="['fad', 'comments']" />
      <h2>인기여행지</h2>
      <h5>많은 좋아요를 받은 인기여행지를 확인해보세요!</h5>
      <div class="center">
        <b-card-group deck class=".mx-auto">
          <b-card
            img-src="https://picsum.photos/600/500/?image=25"
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
              </div>

              <div style="display: flex">
                <div class="rating">
                  <div class="mixture">
                    <div>
                      <span class="text">4.8 /5</span>
                    </div>
                  </div>
                </div>

                <div class="review">
                  <span class="reviewtext"> <i class="fa-light fa-comment"></i>리뷰 4896개</span>
                </div>
              </div>
            </b-card-text>
          </b-card>
          <b-card
            title="Card Title"
            img-src="https://picsum.photos/600/300/?image=25"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            align="center"
          >
            <b-card-text>
              Some quick example text to build on the card title and make up the bulk of the card's
              content.
            </b-card-text>
          </b-card>

          <b-card
            title="Card Title"
            img-src="https://picsum.photos/600/300/?image=25"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            align="center"
          >
            <b-card-text>
              Some quick example text to build on the card title and make up the bulk of the card's
              content.
            </b-card-text>
          </b-card>

          <b-card
            title="Card Title"
            img-src="https://picsum.photos/600/300/?image=25"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            align="center"
          >
            <b-card-text>
              Some quick example text to build on the card title and make up the bulk of the card's
              content.
            </b-card-text>
          </b-card>
        </b-card-group>
      </div>
      <b-button href="#" variant="primary">Go somewhere</b-button>
    </div>
  </div>
</template>


<script>
export default {
    components: {},
    data() {
        return {
            message: '',
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
