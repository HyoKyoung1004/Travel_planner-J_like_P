<template>
  <div class="my-page">
    <div class="container">
      <div class="row">
        <div class="col-md-6 offset-md-3">
          <div class="card">
            <div class="card-body">
              <!-- 프로필 이미지 영역 -->
              <div class="user-profile">
                <!-- 기존 코드 -->
                <!-- ... -->
                
                <!-- 이미지 업로드 버튼 -->
                <div class="upload-button">
                  <input type="file" @change="handleImageUpload" accept="image/*" />
                  <span>프로필 이미지 업로드</span>
                </div>
              </div>
              
              <!-- 기존 코드 -->
              <!-- ... -->
              
              <!-- 회원정보 변경 폼 -->
              <div class="mt-4">
                <div class="form-group">
                  <label for="nickname">닉네임</label>
                  <input type="text" class="form-control" id="nickname" v-model="form.nickName" required>
                </div>
                <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="text" class="form-control" id="email" v-model="form.userEmail" required>
                </div>
                <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" v-model="form.userPassword" required>
                </div>
                
                <button type="button" class="btn btn-primary" @click="updateUserInfo">
                  회원정보 변경 저장
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";

const memberStore = "memberStore";

export default {
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    userAvatar() {
      return "default-avatar.png";
    },
  },
  data() {
    return {
      form: {
        nickName: "",
        userEmail: "",
        userPassword: "",
      },
    };
  },
  methods: {
    ...mapActions(memberStore, ["logout"]),
    handleImageUpload(event) {
      const file = event.target.files[0];
      console.log(file);
      // 이미지 업로드 처리 로직을 추가해주세요.
    },
    async updateUserInfo() {
      try {
        const { nickName, userEmail, userPassword } = this.form;
        console.log(nickName, userEmail, userPassword);
        const response = await axios.put(
          `http://localhost:9999/trip/user/modify/${this.userInfo.userId}`,
          {
            nickName,
            userEmail,
            userPassword,
          }
        );
        console.log("회원정보 변경 완료");
        console.log(response.data); // 변경된 회원정보 응답 확인

        // 변경 성공 시 알림 메시지 또는 리다이렉션 등 추가 처리 가능
        alert("다시 로그인 해주시길 바랍니다");
        this.logout();
        this.$router.push({ name: "main" });
      } catch (error) {
        console.error("회원정보 변경 실패", error);
        // 변경 실패 시 알림 메시지 또는 에러 처리 등 추가 처리 가능
      }
    },
  },
};
</script>

<style scoped>
/* 기존 코드와 동일 */
</style>
