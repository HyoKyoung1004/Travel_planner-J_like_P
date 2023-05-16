<template>
    <div>안녕하세요
        <to-sig-in-view></to-sig-in-view>
    </div>
</template>

<script>
import ToSigInView from "@/components/Sign/ToSigInView.vue";
export default {
    components: {
        ToSigInView
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