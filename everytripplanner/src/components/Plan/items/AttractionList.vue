<template>
    <div id="choose" >
        <b-button variant="danger" @click="showNearAttract">주변여행지</b-button>
        <b-button variant="danger" @click="showWishList">위시리스트</b-button>
    </div>
</template>

<script>
import { mapActions,mapState } from "vuex";
const planStore="planStore"
export default {
    components: {},
    data() {
        return {
            message: '',
            
        };
    },
    computed: { 
        ...mapState(planStore, ["bcards","nearAttraction"])
    },
    props:{
        latitude:Number,
        longitude:Number
    },
    methods: {
        ...mapActions(planStore,['choose','nearAttract','toggleMapStateVisibility']),
        showWishList() {
            this.choose();
            console.log("아아앙");
        },
        showNearAttract() {
            const location={
                lat:this.latitude,
                lng:this.longitude

            }
            console.log(this.latitude,this.longitude);
            this.toggleMapStateVisibility();
            this.nearAttract(location);
        },
        
    }
}
</script>

<style scoped>
#choose{
    border: 1px solid #000;
}
</style>