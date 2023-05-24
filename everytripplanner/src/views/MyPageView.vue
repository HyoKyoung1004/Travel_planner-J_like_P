<template>
  <div class="my-page">
    <b-container class="bv-example-row">
      <b-row>
        <h3>마이페이지 -</h3>
        &nbsp; &nbsp;
        <h3 style="color: #0a97cd">프로필</h3>
      </b-row>
      <hr style="margin-top: 0px" />
    </b-container>
    <div class="container">
      <div class="row">
        <div class="col-md-6 offset-md-3">
          <div class="card">
            <div class="card-body">
              <div class="user-profile">
                <div class="profile-image">
                  <img
                    v-if="fileId != null"
                    :src="`http://localhost:9999/trip/${saveFolder}/${saveFileName}`"
                    alt="My Image"
                  />
                  <img v-else src="../assets/user.png" alt="User Avatar" />

                  <div class="upload-button">
                    <input type="file" @change="handleImageUpload" accept="image/*" />
                    <span>프로필 이미지 업로드</span>
                  </div>
                </div>
                <div class="profile-details">
                  <h3 class="profile-name">{{ userInfo.name }}</h3>
                  <p class="profile-email">{{ userInfo.email }}</p>
                </div>
              </div>
              <div class="user-info">
                <div class="info-item">
                  <h5 class="info-label">이름</h5>
                  <p class="info-value">{{ userInfo.userAccount }}</p>
                </div>
                <div class="info-item">
                  <h5 class="info-label">이메일</h5>
                  <p class="info-value">{{ userInfo.userEmail }}</p>
                </div>
                <div class="info-item">
                  <h5 class="info-label">닉네임</h5>
                  <p class="info-value">{{ userInfo.nickName }}</p>
                </div>
              </div>
              <div class="user-metrics">
                <button class="btn btn-primary" @click="changeMyInfo">회원정보 변경하기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
import { getUserImg } from "@/api/member";
export default {
  data() {
    return {
      fileId: 0,
      originalFileName: null,
      saveFileName: null,
      saveFolder: null,
      userId: 0,
    };
  },
  created() {
    console.log(this.userInfo);
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
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    // userAvatar() {
    //   if (this.userInfo.userimg == null) {
    //     console.log(
    //       "null이면 넣을 이미지 넣고, 널이 아니면 실제 이미지 보여주는 걸로 바꿔야함,,,,,"
    //     );
    //   }
    //   return this.userInfo.userimg || "default-avatar.png";
    // },
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0];
      console.log(file);
      // 이미지 업로드 처리 로직을 추가해주세요.
    },
    changeMyInfo() {
      this.$router.push("UserModify");
    },
  },
};
</script>

<style scoped>
.my-page {
  margin-top: 50px;
  max-width: 1000px;
  margin: 0 auto;
}

img {
  width: 200px;
  height: 200px;
}

.card {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.profile-image {
  position: relative;
  overflow: hidden;
  text-align: center;
}

.profile-image img {
  width: 200px;
  height: 200px;
  object-fit: cover;
}

.upload-button {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 40px;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.3s ease-in-out;
}

.upload-button:hover {
  opacity: 1;
}

.profile-details {
  margin-top: 20px;
  text-align: center;
}

.profile-name {
  font-size: 24px;
  margin-bottom: 10px;
}

.profile-email {
  font-size: 16px;
  color: #888;
  margin-bottom: 20px;
}

.user-info,
.user-metrics {
  margin-top: 30px;
}

.info-item,
.metric-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 15px;
}

.info-label,
.metric-label {
  font-weight: bold;
}

.info-value,
.metric-value {
  color: #888;
}

.btn-primary {
  margin-top: 20px;
}
</style>
