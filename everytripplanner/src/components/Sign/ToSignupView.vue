<template>
    <div>
        <div class="login-wrapper">
        <h2>Login</h2>
        <form method="post" id="login-form">
            <input type="text" name="userAccount" placeholder="아이디">
            <input type="password" name="userPassword" placeholder="패스워드">
            <input type="text" name="userEmail" placeholder="이메일">
            <input type="text" name="nickName" placeholder="닉네임">
            <input type="text" name="userRole" placeholder="역할">
            <label for="remember-check">
                <input type="checkbox" id="remember-check">아이디 저장하기
            </label>
            <input type="submit" value="Login">
        </form>
    </div>
    </div>
</template>

<script>
export default {
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
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