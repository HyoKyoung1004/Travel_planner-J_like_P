<template>
  <div>
    <br />

    <section class="Easy-sgin-in-wrap">
      <h2>SNS 간편 로그인 및 회원가입</h2>
      <ul class="sign-button-list">
        <li>
          <a :href="uri_naver">
            <button>
              <i class="">
                <img
                  style="width: 48px; height: 48px"
                  src="../../assets/naver_btn.png"
                  alt="logo"
                /> </i
              ><span>네이버로 로그인하기</span>
            </button>
          </a>
        </li>
        <li>
          <a :href="uri_google">
            <button>
              <i class=""></i>
              <img
                style="width: 48px; height: 48px"
                src="../../assets/google_btn.png"
                alt="logo"
              />
              <span>구글로 로그인하기</span>
            </button></a
          >
        </li>
      </ul>
    </section>
  </div>
</template>

<script>
// import axios from "axios";
// import queryString from "query-string";

export default {
  data() {
    return {
      uri_naver: "http://localhost:9999/trip/oauth2/authorization/naver",
      uri_google: "http://localhost:9999/trip/oauth2/authorization/google",
    };
  },
  methods: {},
};
</script>

<style scope>
.logo {
  width: 200px;
  height: 70px;
}

.socialBtn-container {
  display: flex;
  /* flex-direction: column; */
  justify-content: center;
  align-items: center;
  width: 100%;
  margin: 16px 0;
}

.socialBtn {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 10%;
}

.socialBtn-container img {
  max-height: 48px !important;
}

:root {
  --body-background-color: #f5f6f7;
  --font-color: #4e4e4e;
  --border-gray-color: #dadada;
  --naver-green-color: #04c75a;
  --naver-green-border-color: #06b350;
}

* {
  margin: 0;
  padding: 0;
}

body {
  background: var(--body-background-color);
}

.main-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 21px;
}
.main-container .main-wrap {
  width: 768px;
}
.main-container .main-wrap .sel-lang-wrap .lang-select {
  width: 96px;
  height: 30px;
  color: var(--font-color);
  border: solid 1px var(--border-gray-color);
}
.main-container .main-wrap .logo-wrap {
  padding-top: 55px;
}
.main-container .main-wrap .logo-wrap img {
  width: 231px;
  height: 44px;
}

.main-container .main-wrap header .sel-lang-wrap {
  display: flex;
  justify-content: flex-end;
}

.main-container .main-wrap header .logo-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.login-input-section-wrap {
  padding-top: 60px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.login-input-wrap {
  width: 465px;
  height: 48px;
  border: solid 1px var(--border-gray-color);
  background: white;
}
.password-wrap {
  margin-top: 13px;
}
.login-input-wrap input {
  border: none;
  width: 430px;
  margin-top: 10px;
  font-size: 14px;
  margin-left: 10px;
  height: 30px;
}

.login-stay-sign-in i {
  font-size: 25px;
  color: #9ba1a3;
}
.login-stay-sign-in span {
  padding-left: 5px;
  line-height: 25px;
}

.Easy-sgin-in-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 40px;
}
.Easy-sgin-in-wrap h2 {
  font-size: 20px;
}
.Easy-sgin-in-wrap .sign-button-list {
  padding-top: 25px;
  list-style: none;
  width: 465px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.Easy-sgin-in-wrap .sign-button-list li {
  padding-bottom: 10px;
}
.Easy-sgin-in-wrap .sign-button-list li button {
  width: 465px;
  height: 56px;
  border: solid 1px var(--border-gray-color);
  text-align: left;
  background: white;
}
.Easy-sgin-in-wrap .sign-button-list li button i {
  padding-left: 15px;
  font-size: 20px;
}
.fa-qrcode {
  color: var(--naver-green-color);
}
.fa-line {
  color: var(--naver-green-color);
}
.fa-facebook-square {
  color: #4064ac;
}
.Easy-sgin-in-wrap .sign-button-list li button span {
  padding-left: 20px;
  font-size: 18px;
}
.Easy-sgin-in-wrap .forget-msg {
  color: var(--font-color);
  font-size: 14px;
  padding-top: 10px;
}
footer {
  padding-top: 95px;
  padding-bottom: 15px;

  display: flex;
  flex-direction: column;
  align-items: center;
  width: 768px;
}
.copyright-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 15px;
}
footer .copyright-wrap span img {
  width: 59px;

  height: 11px;
}
footer .copyright-wrap span {
  font-size: 13px;
  line-height: 15px;
}
</style>