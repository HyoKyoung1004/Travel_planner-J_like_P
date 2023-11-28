<template>
    <div>
        <to-signup-view-vue></to-signup-view-vue>
    </div>
</template>

<script>
import ToSignupViewVue from "@/components/Sign/ToSignupView.vue";
export default {
    
    components: {
        ToSignupViewVue
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