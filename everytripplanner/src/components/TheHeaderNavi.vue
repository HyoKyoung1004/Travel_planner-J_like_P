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

          <b-navbar-nav class="ml-auto" right v-if="userInfo">
            <b-nav-item @click="planList">내 여행 확인</b-nav-item>
            <b-nav-item @click="randomTrip">랜덤여행</b-nav-item>
            <b-nav-item @click="plantripview">여행만들기</b-nav-item>
            <b-nav-item @click="wishList">위시리스트</b-nav-item>
            <b-nav-item @click.prevent="onClickLogout">로그아웃</b-nav-item>
            <b-nav-item @click="myPage">{{ userInfo.nickName }} 님, </b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto" right v-else>
            <b-nav-item @click="planList">내 여행 확인</b-nav-item>
            <b-nav-item @click="plantripview">여행만들기</b-nav-item>
            <b-nav-item @click="wishList">위시리스트</b-nav-item>
            <b-nav-item @click="sigin">로그인</b-nav-item>
            <b-nav-item @click="signup">회원가입</b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav>
            <b-navbar-nav right>
              <b-nav-item-dropdown>
                <template #button-content>
                  <!-- src="https://placekitten.com/300/300" -->
                  <b-avatar v-if="userInfo == null"></b-avatar>
                  <b-avatar v-else :src="img"></b-avatar>
                </template>
                <b-dropdown-item @click="myPage">마이페이지</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
  </div>
</template>

<script>
// import { faAnglesRight } from "@fortawesome/free-solid-svg-icons";
import { mapState, mapActions, mapMutations } from "vuex";
import { getUserImg } from "@/api/member";

const memberStore = "memberStore";
const planStore = "planStore";
export default {
  name: "TheHeaderNavbar",
  components: {},
  data() {
    return {
      message: "",
      uploadedfiles: null,
      fileId: 0,
      originalFileName: null,
      saveFileName: null,
      saveFolder: null,
      userId: 0,
    };
  },
  computed: {
    ...mapState(planStore, ["selectedDayNum"]),
    ...mapState(memberStore, ["userInfo", "userfile"]),
    img() {
      getUserImg(
        this.userInfo.userId,
        ({ data }) => {
          console.log(data);
          this.fileId = data.fileId;
          this.originalFileName = data.originalFileName;
          this.saveFileName = data.saveFileName;
          this.saveFolder = data.saveFolder;
          this.userId = data.userId;
        },
        (error) => {
          console.log(error);
        }
      );
      return `http://localhost:9999/trip/${this.saveFolder}/${this.saveFileName}`;
    },
  },
  watch: {
    ...mapActions(memberStore, ["getUserFile"]),

    saveFileName() {
      console.log("파일 변경됨");
    },
    userInfo() {
      this.getUserFile(this.userInfo.userId);
    },
  },
  created() {
    console.log("여기가 파일 가져오는뎅");
    console.log(this.userfile);
  },

  methods: {
    ...mapMutations(planStore, ["clearMapState", "clearDayPlan","setSelectedDay"]),
    ...mapActions(memberStore, ["logout"]),
    sigin() {
      this.$router.push({ path: "signin" });
    },
    signup() {
      this.$router.push({ path: "signup" });
    },
    myPage() {
      if (this.userInfo != null) {
        this.$router.push({ path: "myPage" });
      } else {
        alert("로그인점 ㅋㅋ");
      }
    },
    randomTrip() {
      this.$router.push({ name: "RandomTripDetailView" });
    },
    plantripview() {
      if (this.userInfo == null) alert("로그인을 후 이용 가능합니다.");
      else {
        this.clearMapState();
        this.clearDayPlan();
        this.setSelectedDay(null);
        this.$router.push({
          name: "planTrip",
        });
      }
    },
    planList() {
      // if (this.userInfo == null) alert("로그인을 후 이용 가능합니다.");
      // else this.$router.push({ path: "planList" });
      this.$router.push({ path: "planList" });
    },
    wishList() {
      console.log("위시리스트");
      if (this.userInfo == null) {
        alert("로그인을 후 이용 가능합니다.");
      } else {
        this.$router.push({
          name: "wishList",
          query: { userId: this.userInfo.userId },
        });
      }
    },
    onClickLogout() {
      //로그아웃
      // console.log(this.userInfo.userid);
      // //여기 로그아웃 기능 구현 안되어 있음,,,,
      // // this.logout(this.userInfo.userid);
      // sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      // sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      this.logout();
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
nav a.router-link-exact-active {
  color: #6a24fe;
}
</style>
