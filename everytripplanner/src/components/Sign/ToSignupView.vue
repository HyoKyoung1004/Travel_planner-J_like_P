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
          <b-card class="login-form" style="max-width: 40rem" align="left">
            <b-form class="text-left">
              <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="userAccount"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호:" label-for="userpwd">
                <b-form-input
                  type="password"
                  v-model="userPassword"
                  id="userpwd"
                >
              </b-form-input>
          </b-form-group>
              <b-form-group label="이메일:" label-for="nickName">
                <b-form-input
                v-model="userEmail"
                  id="userEmail"
                >
              </b-form-input>
          </b-form-group>
              <b-form-group label="권한:" label-for="userRole">
                <b-form-input
                v-model="userRole"
                  id="userRole"
                >
              </b-form-input>
          </b-form-group>
              <b-form-group label="닉네임:" label-for="nickName">
                <b-form-input
                v-model="nickName"
                  id="nickName"
                >
              </b-form-input>
              </b-form-group>
              <b-button type="button" variant="primary" class="m-1" @click="confirm">로그인</b-button>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </template>
  
  <script>
  import axios from "axios";
  export default {
      components: {},
      data() {
          return {
              message: '',
              userAccount: "",
              userEmail: "",
              userRole: "",
              userPassword: "",
              nickName:""
          };
      },
      computed: {
      },
      created() {},
      methods: {
          confirm() {
              const userInfo = {
              userAccount :this.userAccount,
              userEmail: this.userEmail,
              userPassword: this.userPassword,
                  userRole: this.userRole,
                  nickName: this.nickName
              }   
              const headers = {
              'Content-type': 'application/json'
              }
              axios.defaults.headers.post = null
              console.log(userInfo.nickName);
              axios.post("http://localhost:9999/trip/user/join", userInfo,{headers}).then(({ data }) => {
                  this.data = data;
                  if (this.data == 0) {
                  alert("가입안됨")
                  }
                  else {
                      this.$router.push({ name:"main"});
                  }
              })
          }
      }
  }
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