<template>
    <div>
        안녕
        <showMapViewVue></showMapViewVue>
        <router-view></router-view>
    </div>
</template>

<script>
import showMapViewVue from "@/components/Plan/showMapView.vue";
export default {
    components: {
        showMapViewVue
    },
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
};
</script>

<style scoped></style>