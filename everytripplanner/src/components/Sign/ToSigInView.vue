<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
            <b-form class="text-left">
              <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="user.userAccount"
                  required
                  placeholder="아이디 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호:" label-for="userpwd">
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="user.userPassword"
                  required
                  placeholder="비밀번호 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-button type="button" variant="primary" class="m-1" @click="confirm">로그인</b-button>
              <b-button type="button" variant="success" class="m-1" @click="movePage">회원가입</b-button>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
</template>

<script>
import { mapActions ,mapState} from "vuex";
const memberStore = "memberStore";
export default {
    components: {},
    data() {
        return {
            message: '',
            user: {
                userAccount: null,
                userPassword:null
            }
        };
    },
    computed: {
        ...mapState(memberStore,["isLogin", "isLoginError"])
    },
    created() {},
    methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log(token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "main" });
      }
    },
        movePage() {
            this.$router.push({ name: "signup" });
        }
  },
};
</script>

<style scoped>
*{
    padding: 0;
    margin: 0;
    border: none;
}
body{
    font-size: 14px;
    font-family: 'Roboto', sans-serif;
}
.login-wrapper{
    width: 400px;
    height: 350px;
    padding: 40px;
    box-sizing: border-box;
    margin: 0 auto;

    justify-content: center;
}

.login-wrapper > h2{
    font-size: 24px;
    color: #6A24FE;
    margin-bottom: 20px;
}
#login-form > input{
    width: 100%;
    height: 48px;
    padding: 0 10px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border-radius: 6px;
    background-color: #F8F8F8;
}
#login-form > input::placeholder{
    color: #D2D2D2;
}
#login-form > input[type="submit"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
    margin-top: 20px;
}
#login-form > input[type="checkbox"]{
    display: none;
}
#login-form > label{
    color: #999999;
}
#login-form input[type="checkbox"] + label{
    cursor: pointer;
    padding-left: 26px;
    /* background-image: url("checkbox.png"); */
    background-repeat: no-repeat;
    background-size: contain;
}
#login-form input[type="checkbox"]:checked + label{
    /* background-image: url("checkbox-active.png"); */
    background-repeat: no-repeat;
    background-size: contain;
}

</style>