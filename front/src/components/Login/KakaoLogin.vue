<template>
  <button class='social-login kakao-login' @click="kakaoLogin">
    <img src="../../assets/Login/kakao_logo.png"/>
    <p>
      Login with Kakao
    </p>
  </button>

</template>

<script>
export default {
  methods: {
    kakaoLogin: function(){
      let url = 'https://kauth.kakao.com/oauth/authorize?'
      const params = {
        'client_id': process.env.VUE_APP_KAKAO_CLIENT_ID,
        'redirect_uri': process.env.VUE_APP_REDIRECT_URI,
        'response_type': 'code'
      }
      for (const key in params){
        url += `${key}=${params[key]}&`
      }
      window.location.href = url
    }
  }
}
</script>

<style lang="scss">
  .kakao-login {
    background-color: #FEE500;
    box-shadow: 0.5px 1px 3px rgba(0, 0, 0, 0.25);

    &:hover {
      background-color: #e9d629;
    }
  }
</style>