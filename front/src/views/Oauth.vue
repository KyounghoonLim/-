<template>
  <div id="oauth-container">
    <img class="loading" src="../assets/loading-daki.gif">
    <p class="loading-message">{{ target }} 로그인이 진행중입니다..</p>
  </div>
</template>

<script>
import axios from 'axios'
// eslint-disable-next-line no-unused-vars
import customAxios from '../customAxios'

export default {
  data: function(){
    return {
      type: null,
      token: null
    }
  },
  computed: {
    target: function(){
      if (this.type == 'google'){
        return '구글'
      }
      else if (this.type == 'kakao'){
        return '카카오'
      }
      return ''
    }
  },
  methods: {
    getGoogleUser: function(){
      this.type = 'google'
      const hash = this.$route.hash.replace('#', '').split('&')
      for (const ele of hash){
        const t = ele.split('=')
        if (t[0] == 'access_token'){
          this.token = t[1]
          return
        }
      }
    },
    getKakaoUser: function(code){
      axios({
        method: 'post',
        url: 'https://kauth.kakao.com/oauth/token',
        headers: {
          'Content-type': 'application/x-www-form-urlencoded;charset=utf-8'
        },
        params: {
          'grant_type': 'authorization_code',
          'client_id': process.env.VUE_APP_KAKAO_CLIENT_ID,
          'redirect_uri': process.env.VUE_APP_REDIRECT_URI,
          'code': code
        }
      })
      .then(res => {
        this.token = res.data['access_token']
      })
      .catch(err => console.log(err))
    },
    sendTokenToSever: function(){
      console.log(this.token)
      customAxios({
        method: 'get',
        url: '/api/oauth/login',
        headers: {
          'token': this.token,
          'type': this.type
        }
      })
      .then(() => this.$router.push({name: 'Main', query: {tab: 'calendar'}}))
    }
  },
  watch: {
    token: function(){
      this.sendTokenToSever()
    }
  },
  created: function(){
    if (this.$route.hash){
      this.getGoogleUser()
    }
    else if (this.$route.query){
      this.type = 'kakao'
      const code = this.$route.query.code
      this.getKakaoUser(code)
    }
  }
}
</script>

<style lang="scss">
  #oauth-container {
    width: 100vw;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .loading-message {
      font-size: 1.5rem;
      color: #cccccc;
    }
  }
</style>