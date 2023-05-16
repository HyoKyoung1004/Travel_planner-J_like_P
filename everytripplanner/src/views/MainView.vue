<template>
    <div>
    여기는 메인
    <choose-attrcation></choose-attrcation>
    </div>
</template>

<script>
import ChooseAttrcation from "@/components/Attraction/ChooseAttrcation.vue";
export default {
    name: 'MainView',
    components: {
        ChooseAttrcation,
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