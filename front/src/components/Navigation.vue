<template>
  <nav v-if="user">
    <div class="nav-mobile">
      <div class="mobile-bar">
        <router-link to="/main">
          <img class="logo-img" src="../assets/logo.png" alt="">
        </router-link>
        
        <!-- <button
          @click="menuClick()">
          메뉴버튼
        </button> -->
        <div>
          <a class="menu-trigger" href="#" @click="menuClick()">
            <span></span>
            <span></span>
            <span></span>
          </a>
        </div>

      </div>
    </div>
    <div id="nav-container" :class="{ active : onClick }">
      <div @click="menuClick()"></div>
      <router-link to="/main?tab=calendar" class="logo" />
      <ul class="menu-box">
        <li :class="['menu', {'now': path == '/diary'}]">
          <router-link to="/diary">
            일기쓰기
          </router-link>
        </li>
        <li :class="['menu', {'now': path.includes('/diary/')}]">
          <router-link to="">
            일기장
          </router-link>
        </li>
        <li :class="['menu', {'now': path == '/mypage'}]">
          <router-link :to="{name:'Mypage',params:{ nickName: user.nickName}}">
            마이페이지
          </router-link>
        </li>
        <li class="menu">
          <a @click="logOut">
            로그아웃
          </a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import Swal from 'sweetalert2'
import { mapState } from 'vuex'

export default {
  data: function(){
    return {
      isTop: true,
      flag: false,

      onClick: false,
    }
  },
  methods:{
    menuClick(){
      if(this.onClick == false){
        this.onClick = true
      }else{
        this.onClick = false
      }
    },
    logOut: function(){
      Swal.fire({
        icon: 'question',
        text: '로그아웃 하시겠습니까?',
        showCancelButton: true
      }).then(res => {
        if (res.isConfirmed){
          window.sessionStorage.clear()
          this.$store.dispatch('clearUser')
          this.$router.push('/login')
        }
      })
    }
  },
  computed: {
    path: function(){
      return this.$route.path
    },
    ...mapState([
      'user'
    ])
  },
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  .nav-mobile{
    display: none;
    button{
      display: none;
    }
    img{
      display: none;
    }
  }
  #nav-container {
    width: 100vw;
    height: 100px;
    position: fixed;
    top: 0;
    left: 0;
    padding: 3rem;
    border-bottom: 1px #cccccc solid;
    background-color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    z-index: 98765432;
    
    & > div{
      display: none;
    }

    .logo {
      width: 75px;
      aspect-ratio: 1/1;
      background-image: url('../assets/logo.png');
      background-size: cover;
      cursor: pointer;
    }

    .menu-box {
      width: 30vw;
      padding: 0;
      display: flex;
      justify-content: space-between;
      align-items: center;
      list-style: none;
      

      .menu > a {
        font-size: 1.5rem;
        color: #777777;
        text-decoration: none;
        cursor: pointer;
      }

      .now > a {
        font-weight: bold;
        color: #93D9CE;
      }

    }
  }
}
@media only screen and (max-width:799px){
  .menu-trigger{
    margin-right:50px;
    margin-bottom: 30px;
  }
  .menu-trigger,
  .menu-trigger span {
    display: inline-block;
    transition: all .4s;
    box-sizing: border-box;
  }
  .menu-trigger{
    position: relative;
    width: 30px;
    height: 30px;
  }
  .menu-trigger span{
    position: absolute;
    // left: 0;
    width: 100%;
    height: 4px;
    background-color: #fff;
    border-radius: 4px;
  }
  .menu-trigger span:nth-of-type(1){
    top: 0;
  }
  .menu-trigger span:nth-of-type(2){
    top: 13px;
  }
  .menu-trigger span:nth-of-type(3){
    bottom: 0;
  }
  .menu-trigget.active-1 span:nth-of-type(1){
    -webkit-transform: translateY(20px) rotate(-450deg);
    transform: translateY(20px) rotate(-450def);
  }
  .menu-trigger.active-1 span:nth-of-type(2){
    opacity: 0;
  }
  .menu-trigger.active-1 span:nth-of-type(3){
    -webkit-transform:translateY(-20px) rotate(45deg);
    transform: translateY(-20px) rotate(45deg)
  }
  .nav-mobile{
    width: 100vw;
    height: 50px;
    position: fixed;
    top: 0;
    left: 0;
    background-color: #FED771;
    text-align: right;
    z-index: 10;

    & > .mobile-bar{
      display: flex;
      justify-content: space-between;
      height: 100%;
      position: relative;

      & > div{
        padding-top: 15px;
      }
      & img {
        width: 50px;
        margin-left: 5px;
      }
    }

  }

  #nav-container{
    & {
      opacity: 0;
      display: none;
      visibility: hidden;
    }
    &.active{
      &{
        opacity: 1;
        display: block;
        visibility: visible;
        height: 100vh;
        float: right;
        top: 30px;
        right: 0px;
        position: fixed;
        z-index: 3;
        margin: 0px;

        & > div:nth-child(1){
          position: fixed;
          width: 100vw;
          height: 100vh;
          z-index: 0;
          left: 0;top:0;
          cursor: pointer;
          background-color: rgba(0, 0, 0, 0.35);
        }

        @keyframes fadeinRight{
          0%{
            opacity: 0;
            transform: translate3d(100%,0,0);
          }
          to{
            opacity: 1;
            transform: translateZ(0);
          }
        }
        & > .menu-box{
          background: #FED771;
          height: 100vw;
          height: 100%;
          top: 0px;
          padding-top: 30px;
          list-style: none;
          text-align: center;
          animation: fadeinRight 1s;
          vertical-align: middle;
          position: relative;
          z-index: 10;

          & a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
            font-size: 16px;
          }
          & > .menu{
            width: 200px;
            height: 50px;
            text-align: center;
            vertical-align: middle;
          }
        }
        & a {
          text-decoration: none;
        }
      }
    }
  }

}

</style>