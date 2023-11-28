const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  devServer: {
    proxy: {
      '/user/login/oauth2/test': {
        target: 'https://localhost:9999/trip/user/login/oauth2/test', // 실제 서버 주소
        changeOrigin: true,
        withCredentials: true // 요청에 쿠키를 포함시키기 위해 설정
      }
    }
  }
};
