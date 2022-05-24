<template>
  <button class='social-login google-login' @click="googleLogin">
    <img src="../../assets/Login/google_logo.png"/>
    <p>
      Sign in with Google
    </p>
  </button>

</template>

<script>

export default {
  methods: {
    googleLogin: async function(){
      let url = 'https://accounts.google.com/o/oauth2/v2/auth?'
      const params = {
        client_id: process.env.VUE_APP_GOOGLE_CLIENT_ID,
        redirect_uri: process.env.VUE_APP_REDIRECT_URI,
        response_type: 'token',
        scope: 'https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile'
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
  .google-login {
    background-color: white;
    box-shadow: 0.5px 1px 3px rgba(0, 0, 0, 0.25);

    &:hover {
      background-color: rgb(238, 237, 237);
    }
  }
</style>