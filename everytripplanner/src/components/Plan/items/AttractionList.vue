<template>
    <div id="choose" >
        <b-button variant="danger" @click="openClick2">주변여행지</b-button>
        <b-button variant="danger" @click="openClick">위시리스트</b-button>
    </div>

    
</template>

<script>
import { mapActions } from "vuex";
export default {
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() { },
    methods: {
        ...mapActions(['fetchBcards']),
        openClick() {
            this.fetchBcards();
            console.log("아아앙");
        }
    }
}
</script>

<style scoped>
#choose{
    border: 1px solid #000;
}
</style>