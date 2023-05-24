<template> 
    <div id="choose" >
        <b-button variant="danger" @click="showNearAttract"   >주변여행지</b-button>
        <b-button variant="danger" @click="showWishList " >위시리스트</b-button>
    </div>
</template>

<script>
import { mapActions,mapState } from "vuex";
const planStore="planStore"
const wishListStore ="wishListStore"
const memberStore="memberStore"
export default {
    components: {},
    data() {
        return {
            message: '',
            
        };
    },
    computed: { 
        ...mapState(memberStore, ["userInfo"]),
        ...mapState(planStore, ["nearAttraction"]),
        ...mapState(memberStore, ["userInfo"]),
        ...mapState(memberStore,["userInfo"]),
        ...mapState(planStore, {
            nearAttraction: (state) => state.nearAttraction,
            stateVisible: (state) => state.stateVisible,
            }),
        ...mapState(wishListStore, {
        myWishList: (state) => state.myWishList,
        }),
        isNearAttractVisible() {
        return this.stateVisible === "nearAttract";
        },
        isWishListVisible() {
        return this.stateVisible === "wishList";
        },
    },
    props:{
        latitude:Number,
        longitude:Number
    },
    methods: {
        ...mapActions(planStore,['chooseMyWish','nearAttract','toggleMapStateVisibility']),
        ...mapActions(wishListStore,['getMyWishList']),
        showWishList() {
            if (this.isWishListVisible) {
                // 이미 위시리스트가 선택된 상태이므로 refresh
                this.getMyWishList(this.userInfo.userId);
            } else {
                // 위시리스트로 상태 변경 및 데이터 fetch
                this.toggleMapStateVisibility("wishList");
                this.getMyWishList(this.userInfo.userId);
            }
        },
    showNearAttract() {
      if (this.isNearAttractVisible) {
        // 이미 주변여행지가 선택된 상태이므로 refresh
        const location = {
          lat: this.latitude,
          lng: this.longitude,
        };
        this.nearAttract(location);
      } else {
        // 주변여행지로 상태 변경 및 데이터 fetch
        const location = {
          lat: this.latitude,
          lng: this.longitude,
        };
        this.toggleMapStateVisibility("nearAttract");
        this.nearAttract(location);
      }
    },
    }
}
</script>

<style scoped>
#choose{
    border: 1px solid #000;
}
</style>