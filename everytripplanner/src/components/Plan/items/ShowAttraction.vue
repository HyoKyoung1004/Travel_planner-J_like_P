<template>
    <div>
    <div v-if="stateVisible === 'nearAttract'"  class="scroll-container">
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
              <b-button v-on:click="move(card)" variant="primary">추가하기</b-button>
          </b-card>
        </li>
      </ul>
    </div>
    <div v-if="stateVisible === 'wishList'"  class="scroll-container">
      <ul style="list-style-type:none;">
        <li v-for="card in wishList" :key="card.id">
          <b-card
              :title="card.title"
              :img-src="card.firstImage"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 10rem;"
              class="mb-2">
              <b-button v-on:click="move(card)" variant="primary">추가하기</b-button>
          </b-card>
        </li>
      </ul>
    </div>
    </div>
  </template>
  <script>
  import {mapActions, mapState } from 'vuex';
  const planStore ="planStore"
  const wishListStore ="wishListStore"
  export default {
    computed: {
      ...mapState(planStore, ["nearAttraction","stateVisible","markersV","selectedDayNum","dayNum"]),
      ...mapState(wishListStore,["wishList"])
    },
    methods: {
      ...mapActions(planStore,["addMarker","setPlannerItemMachine","setPlannerItemElement"]),
      move(card){
        if(this.selectedDayNum==null){
          alert("날짜일수를 먼저 선택해주세요 !!");
        }
        else{
          console.log(card);
          const marker={
                latitude:card.latitude,
                longitude:card.longitude
              }
          console.log("maker: ",marker);        
          this.addMarker(marker);
          console.log("this.markersV: ",this.markersV);
          const day = this.selectedDayNum-1;
          console.log("day",day);
          console.log("왜 자꾸? : ",card.contentId);
          const plannerItem = {
                // 선택된 카드 정보를 plannerItem에 저장
                title: card.title,
                addr1: card.addr1,
                img:card.firstImage,
                content_id:card.contentId,
                num:this.dayNum[day]
              };
          this.dayNum[day]= this.dayNum[day]+1;
          console.log("여기 한번봐보자",plannerItem);
          
        this.setPlannerItemElement({ day, value: plannerItem });
        }
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