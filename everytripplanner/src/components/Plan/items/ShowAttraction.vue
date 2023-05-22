<template>
    <div v-if="stateVisible"  class="scroll-container">
      <h2>B-cardtss</h2>
      <ul style="list-style-type:none;">
        <li v-for="card in nearAttraction" :key="card.id">
          <b-card
              :title="card.title"
              :img-src="card.firstImage"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 10rem;"
              class="mb-2">
              <b-card-text>
                {{ card.addr1 }} 
              </b-card-text>
              <b-button v-on:click="move(card)" variant="primary">추가하기</b-button>
          </b-card>
        </li>
      </ul>
    </div>
  </template>
  <script>
  import {mapActions, mapState } from 'vuex';
  const planStore ="planStore"
  export default {
    computed: {
      ...mapState(planStore, ["nearAttraction","stateVisible","markersV","selectedDayNum"]),
    },
    methods: {
      ...mapActions(planStore,["addMarker","setPlannerItemMachine"]),
      async move(card){
        console.log(card);
        const marker={
              latitude:card.latitude,
              longitude:card.longitude
            }
        console.log("maker: ",marker);        
        this.addMarker(marker);
        console.log("this.markersV: ",this.markersV);
        const day = this.selectedDayNum;
        console.log("day",day);
        const plannerItem = {
              // 선택된 카드 정보를 plannerItem에 저장
              title: card.title,
              addr1: card.addr1,
              img:card.firstImage,
              content_id:card.contentId
            };
            console.log("contentid",card.contentId);
    console.log("여기 한번봐보자",plannerItem);
       await this.setPlannerItemMachine({ day, value: plannerItem });
      }
    }
  };
  </script>
  <style scoped>
.scroll-container {
  max-height: 1000px; /* 원하는 높이로 설정 */
  overflow-y: scroll;
}
</style>