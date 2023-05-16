<template>
    <div class="center">
        <b-nav class="child">
            <b-nav-item active> 관광지</b-nav-item>
            <b-nav-item active>문화시설</b-nav-item>
            <b-nav-item active>축제공연행사</b-nav-item>
            <b-nav-item active>여행코스</b-nav-item>
            <b-nav-item active>레포츠</b-nav-item>
            <b-nav-item active>숙박</b-nav-item>
        </b-nav>
        <br>
        <div class="row">
            <div>
                <b-button variant="danger" @click="openClick">어디로 가세요?</b-button>
                <PopUpView v-bind:openModal="openModal" v-if="openModal==true" />
            </div>
        </div>
    </div>
    
</template>

<script>
import PopUpView from '@/components/Attraction/PopUpView.vue'
export default {
    components: {
        PopUpView
    },
    data() {
        return {
            openModal:false,
        }
    },
    created: {
    },  
    methods: {
        openClick() {
            console.log(this.openModal)
            this.openModal=true;
        }
    }
};
</script>

<style scoped>
.center{
  margin: 0 auto;
  display: flex;
  justify-content: center;

}
.row{
    position: static;
}
</style>