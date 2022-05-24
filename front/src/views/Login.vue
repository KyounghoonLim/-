<template>
  <section id="login-container">
    <account-header title="다이어리 키우기" />
    <article class="login-body">
      <form @submit.prevent="login">
        <label for="email">
          <span>Email</span>
          <input 
            type="email" 
            id="email" 
            placeholder="이메일을 입력하세요"
            v-model="credentials.email">
        </label>
        <label for="password">
          <span>Password</span>
          <input
            type="password"
            id="password" 
            placeholder="비밀번호를 입력하세요"
            v-model="credentials.password">
        </label>
        <button>로그인</button>
        <router-link to="/signup">
          회원가입
        </router-link>
      </form>
      <div class="social-login-box">
        <kakao-login/>
        <google-login/>
      </div>
    </article>
  </section>
</template>

<script>
import AccountHeader from '../components/AccountHeader'
import KakaoLogin from '../components/Login/KakaoLogin'
import GoogleLogin from '../components/Login/GoogleLogin'
import customAxios from '../customAxios.js'
import Swal from 'sweetalert2'

export default {
  data:function(){
    return{
      credentials:{
        email: null,
        password: null,
      }
    }
  },
  components: {
    AccountHeader,
    KakaoLogin,
    GoogleLogin
  },
  methods:{
    login(){
      customAxios({
        method: 'post',
        url: '/api/auth/login',
        data: this.credentials
      })
      .then(() => {
        Swal.fire({
          icon: 'success',
          title: '로그인 되었습니다',
          text: '오늘도 다키에서 즐거운 시간 보내세요😎'
        }).then(() => this.$router.push('/main?tab=calendar'))
      })
      .catch(err => {
        console.log(err)
        Swal.fire({
          icon: 'error',
          title: '로그인에 실패했습니다',
          text: '입력정보를 확인해주세요'
        })
      })
    },
  },
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  #login-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;

    & > * {
      width: 100%;
    }

    .login-body {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 65%;

      form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 70%;
        margin: 4rem 0 0;

        label {
          width: 70%;
          display: flex;
          justify-content: space-between;
          margin: 1.5rem;

          span {
            width: 30%;
            font-size: 1.5rem;
            font-weight: bold;
          }

          input {
            width: 70%;
            border: none;
            border-bottom: 1px #cccccc solid;
            font-size: 1.5rem;
            margin: 0 1rem;
            padding: 0.25rem 0.5rem;

            &:focus {
              outline: none;

               &::-webkit-input-placeholder{
                color: transparent;
              }
            }

            &[type='password'] {
              font-family: Verdana;
              letter-spacing: 0.125rem;
            }

            &::placeholder {
              font-size: 1.25rem;
              color: #cccccc;
            }
          }
        }

        button {
          font-size: 1.25rem;
          font-weight: bold;
          border-radius: 10px;
          border: 1px #93D9CE solid;
          color: #93D9CE;
          background-color: white;
          box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          margin: 3rem 0 0;
          padding: 1rem 5rem;
          cursor: pointer;

          &:hover {
            background-color: #93D9CE;
            color: white;
          }
        }
      }

      a {
        margin: 2rem;
        font-size: 1.25rem;
        font-weight: bold;
        text-decoration: none;
        color: #555555;
      }

      .social-login-box {
        display: flex;
        margin: 1rem;
        width: 50%;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
      }

      .social-login {
        margin: 0.5rem;
        width: 220px;
        height: 50px;
        border-radius: 12px;
        display: flex;
        justify-content: center;
        align-items: center;
        border: none;
        cursor: pointer;

        img {
          width: 18px;
          aspect-ratio: 1/1;
          margin: 0.5rem;
        }

        p {
          font-size: 0.9rem;
          letter-spacing: 0;
          color: rgba(0, 0, 0, 0.85);
          padding: 10px;
          margin: 0;
          line-height: 0.9rem;
        }
      }
    }
  }
}
@media only screen and (max-width:799px){
  #login-container {
    width: 100%;
    min-height: 100vh;

    & > * {
      width: 100%;
    }

    .login-body {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 65%;

      form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 70%;
        margin: 4rem 0 0;

        label {
          width: 70%;
          display: flex;
          justify-content: space-between;
          margin: 1.5rem;

          span {
            width: 30%;
            font-size: 1.5rem;
            font-weight: bold;
          }

          input {
            width: 70%;
            border: none;
            border-bottom: 1px #cccccc solid;
            font-size: 1.5rem;
            margin: 0 1rem;
            padding: 0.25rem 0.5rem;

            &:focus {
              outline: none;

               &::-webkit-input-placeholder{
                color: transparent;
                font-size: 16px;
              }
            }

            &[type='password'] {
              font-family: Verdana;
              letter-spacing: 0.125rem;
            }

            &::placeholder {
              font-size: 1.25rem;
              color: #cccccc;
            }
          }
        }

        button {
          font-size: 1.25rem;
          font-weight: bold;
          border-radius: 10px;
          border: 1px #93D9CE solid;
          color: #93D9CE;
          background-color: white;
          box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          margin: 3rem 0 0;
          padding: 1rem 5rem;
          cursor: pointer;

          &:hover {
            background-color: #93D9CE;
            color: white;
          }
        }
      }

      a {
        margin: 2rem;
        font-size: 1.25rem;
        font-weight: bold;
        text-decoration: none;
        color: #555555;
      }

      .social-login-box {
        display: flex;
        margin: 1rem;
        width: 50%;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
      }

      .social-login {
        margin: 0.5rem;
        width: 220px;
        height: 50px;
        border-radius: 12px;
        display: flex;
        justify-content: center;
        align-items: center;
        border: none;
        cursor: pointer;

        img {
          width: 18px;
          aspect-ratio: 1/1;
          margin: 0.5rem;
          // display: none;
        }

        p {
          font-size: 0.9rem;
          letter-spacing: 0;
          color: rgba(0, 0, 0, 0.85);
          padding: 10px;
          margin: 0;
          line-height: 0.9rem;
        }
      }
    }
  }
}
</style>