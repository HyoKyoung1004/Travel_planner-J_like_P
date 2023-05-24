<template>
  <div>
    <to-sig-in-view></to-sig-in-view>
    <naver-login-button></naver-login-button>
  </div>
</template>

<script>
import ToSigInView from "@/components/Sign/ToSigInView.vue";
import NaverLoginButton from "@/components/Sign/NaverLoginButton.vue";
export default {
  components: {
    ToSigInView,
    NaverLoginButton,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>