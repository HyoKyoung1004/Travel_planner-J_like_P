<template>
  <div>
    <div>
      <b-navbar toggleable="lg" variant="faded" type="light">
        <!-- 로고자리 -->
        <b-navbar-brand href="#" style="color: #6a24fe">
          <router-link :to="{ name: 'main' }">EveryTrip</router-link>
        </b-navbar-brand>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto"></b-navbar-nav>
          <!-- Right aligned nav items -->

          <b-navbar-nav class="ml-auto" right>
            <b-nav-item @click="plantripview">여행만들기</b-nav-item>
<<<<<<< HEAD
=======
            <b-nav-item @click="plantripview">내 여행</b-nav-item>
>>>>>>> 6b0b4821ff6a44cd0ed3d8e9636c77d8a20e58c2
            <b-nav-item>위시리스트</b-nav-item>
            <b-nav-item @click="sigin">로그인</b-nav-item>
            <b-nav-item @click="signup">회원가입</b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav>
            <!-- <b-navbar-nav>
              <b-nav-item href="#">여행만들기</b-nav-item>
              <b-nav-item href="#">위시리스트</b-nav-item>
              <b-nav-item href="#">sign In</b-nav-item>
              <b-nav-item href="#">회원가입</b-nav-item>
            </b-navbar-nav> -->

            <b-navbar-nav right>
              <b-nav-item-dropdown>
                <template #button-content>
                  <b-avatar></b-avatar>
                </template>
                <b-dropdown-item href="#">마이페이지</b-dropdown-item>
                <b-dropdown-item href="#">Sign Out</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheHeaderNavbar",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    sigin() {
      this.$router.push({ path: "signin" });
    },
    signup() {
      this.$router.push({ path: "signup" });
    },
    plantripview() {
      this.$router.push({ path: "planTrip" });
    },
  },
};
</script>

<style scoped>
nav a.router-link-exact-active {
  color: #6a24fe;
}
</style>
