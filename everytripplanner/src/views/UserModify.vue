<template>
  <div class="my-page">
    <b-container class="bv-example-row">
      <b-row>
        <h3>마이페이지 -</h3>
        &nbsp; &nbsp;
        <h3 style="color: #0a97cd">프로필 수정</h3>
      </b-row>
      <hr style="margin-top: 0px" />
    </b-container>
    <div class="container">
      <div class="row">
        <div class="col-md-6 offset-md-3">
          <div class="card">
            <div class="card-body">
              <!-- 프로필 이미지 영역 -->
              <div class="user-profile">
                <!-- 기존 코드 -->
                <!-- ... -->

                <div class="profile-img-container hide-on-tablet">
                  <img
                    v-if="fileId != null"
                    style="width: 200px; height: 200px"
                    :src="`http://localhost:9999/trip/${saveFolder}/${saveFileName}`"
                    alt="My Image"
                  />
                  <img
                    v-else
                    class="img profile-img"
                    width="98px"
                    src="../assets/user.png"
                    alt="User profile image"
                  />
                </div>

                <b-form-file v-model="uploadedfiles" accept="image/*" size="sm"></b-form-file>

                <!-- 회원정보 변경 폼 -->
                <div class="mt-4">
                  <div class="form-group">
                    <label for="nickname" class="inputName">닉네임</label>
                    <input
                      type="text"
                      class="form-control"
                      id="nickname"
                      v-model="form.nickName"
                      required
                    />
                  </div>
                  <div class="form-group">
                    <label for="email" class="inputName">이메일</label>
                    <input
                      type="text"
                      class="form-control"
                      id="email"
                      v-model="form.userEmail"
                      required
                    />
                  </div>
                  <div class="form-group">
                    <label for="password" class="inputName">비밀번호</label>
                    <input
                      type="password"
                      class="form-control"
                      id="password"
                      v-model="form.userPassword"
                      required
                    />
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
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";
import { getUserImg } from "@/api/member";
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
      uploadedfiles: null,
      fileId: 0,
      originalFileName: null,
      saveFileName: null,
      saveFolder: null,
      userId: 0,
    };
  },
  created() {
    this.form.nickName = this.userInfo.nickName;
    this.form.userEmail = this.userInfo.userEmail;
    this.form.userPassword = this.userInfo.userPassword;
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
  methods: {
    ...mapActions(memberStore, ["logout"]),
    handleImageUpload(event) {
      const file = event.target.files[0];
      console.log(file);
      // 이미지 업로드 처리 로직을 추가해주세요.
    },
    async updateUserInfo() {
      var user = {
        nickName: this.form.nickName,
        userEmail: this.form.userEmail,
        userPassword: this.form.userPassword,
      };
      console.log(user);
      console.log(this.uploadedfiles);

      const formData = new FormData();
      formData.append("uploadedfiles", this.uploadedfiles);

      const blob = new Blob([JSON.stringify(user)], {
        type: "application/json",
      });

      formData.append("user", blob);

      const config = {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      };
      console.log(formData);

      axios
        .post(`http://localhost:9999/trip/user/modify/${this.userInfo.userId}`, formData, config)
        .then((resp) => {
          console.log(resp);
          alert("회원정보 변경 완료");

          console.log("회원정보 변경 완료");
          console.log(resp.data); // 변경된 회원정보 응답 확인
          alert("다시 로그인 해주시길 바랍니다");
          this.logout();
          this.$router.push({ name: "main" });
        })
        .catch((resp) => {
          console.log(resp);
          alert("실패");
          console.error("회원정보 변경 실패", resp);
        });

      console.log("회원정보 변경 완료");

      // try {
      //   const { nickName, userEmail, userPassword } = this.form;
      //   console.log(nickName, userEmail, userPassword);
      //   const response = await axios.put(
      //     `http://localhost:9999/trip/user/modify/${this.userInfo.userId}`,
      //     {
      //       nickName,
      //       userEmail,
      //       userPassword,
      //     }
      //   );
      //   console.log("회원정보 변경 완료");
      //   console.log(response.data); // 변경된 회원정보 응답 확인
      //   alert("다시 로그인 해주시길 바랍니다");
      //   this.logout();
      //   this.$router.push({ name: "main" });

      //   // 변경 성공 시 알림 메시지 또는 리다이렉션 등 추가 처리 가능
      //   alert("다시 로그인 해주시길 바랍니다");
      //   this.logout();
      //   this.$router.push({ name: "main" });
      // } catch (error) {
      //   console.error("회원정보 변경 실패", error);
      //   // 변경 실패 시 알림 메시지 또는 에러 처리 등 추가 처리 가능
      // }
    },
  },
};
</script>

<style>
.inputName {
  font-weight: 600;
  float: left;
}
</style>
