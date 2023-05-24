<template>
  <div>여기로 옴</div>
</template>

<script>
import axios from "axios";
import { mapActions, mapMutations, mapState } from "vuex";
const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    ...mapMutations(memberStore, [
      "SET_IS_LOGIN",
      "SET_IS_LOGIN_ERROR",
      "SET_USER_INFO",
    ]),
  },
  async created() {
    console.log("여기로 되돌아옴");
    axios
      .get("http://localhost:9999/trip/user/login/oauth2/test", {
        withCredentials: true,
      })
      .then((resp) => {
        console.log("요청이 오니?");
        console.log(resp.data);
        let token1 = resp.data["access-token"];
        console.log(token1);

        if (resp.data.message === "success") {
          console.log("여기까지오니?");
          let token = resp.data["access-token"];
          console.log(token);
          this.SET_IS_LOGIN(true);
          this.SET_IS_LOGIN_ERROR(false),
            sessionStorage.setItem("access-token", token);
        } else {
          this.SET_IS_LOGIN(false);
          this.SET_IS_LOGIN_ERROR(true);
        }

        sessionStorage.setItem("access-token", token1);
        this.getUserInfo(token1);
        this.$router.push({ name: "main" });
      });
  },
  data() {
    return {};
  },
  mounted() {},
};
</script>