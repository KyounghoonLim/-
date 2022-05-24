<template>
  <section id="signup-container">
    <account-header />
    <article class="signup-body">
      <account-progress :step="step" :direction="direction" :firstValidData="firstValidData" :secondValidData="secondValidData" :oauth="oauth"
       @change-step="tar => changeStep(Number(tar))" />

      <first-credentials v-if="step == 1" :firstCredentials="firstCredentials" :validData="firstValidData"
       @next-step="p => changeStep(p)" @get-credentials="credentials => getFirst(credentials)"
       @get-valid-data="validData => getValidData(validData)" />

      <second-credentials v-if="step == 2" :secondCredentials="secondCredentials" :validData="secondValidData"
       @next-step="p => changeStep(p)" @get-credentials="credentials => getSecond(credentials)"
       @get-valid-data="validData => getValidData(validData)" />

      <account-complete v-if="step == 3" />
    </article>
  </section>
</template>

<script>
import AccountHeader from '@/components/AccountHeader.vue'
import AccountProgress from '@/components/Signup/AccountProgress.vue'
import FirstCredentials from '@/components/Signup/FirstCredentials.vue'
import SecondCredentials from '@/components/Signup/SecondCredentials.vue'
import AccountComplete from '@/components/Signup/AccountComplete.vue'
import customAxios from '../customAxios'
import Swal from 'sweetalert2'

export default {
  data: () => {
    return {
      step: 1,
      direction: true,
      firstCredentials: {
        email: null,
        nickName: null,
        password: null,
        passwordConf: null
      },
      secondCredentials: {
        birth: null,
        gender: null,
        dollType: null
      },
      validData: {
        email: false,
        nickName: false,
        password: false,
        passwordConf: false,
        birth: false,
        gender: false,
        dollType: false
      }
    }
  },
  props: {
    oauth: Boolean
  },
  components: {
    AccountHeader,
    AccountProgress,
    FirstCredentials,
    SecondCredentials,
    AccountComplete
  },
  computed: {
    firstValidData: function(){
      const {email, nickName, password, passwordConf} = this.validData
      return  {email, nickName, password, passwordConf}
    },
    secondValidData: function(){
      const {birth, gender, dollType} = this.validData
      return {birth, gender, dollType}
    }
  },
  methods: {
    changeStep: function(tar){
      let validData
      if (this.oauth){
        validData = {birth: this.validData.birth, gender: this.validData.gender, dollType: this.validData.dollType}
      }
      else {
        validData = this.validData
      }
      if (tar == 3){
        for (const value of Object.values(validData)){
          if (!value){
            return Swal.fire({
              icon: 'warning',
              text: '회원가입에 필요한 정보를 모두 입력해주세요'
            })
          }
        }
        return this.signUp()
      }
      if (tar - this.step > 0){
        this.direction = true
      }
      else {
        this.direction = false
      }
      this.step = tar
    },
    signUp: function(){
      customAxios({
        method: 'post',
        url: '/api/auth/signup',
        data: {...this.firstCredentials, ...this.secondCredentials}
      })
      .then(() => {
        this.step = 3
      })
      .catch(() => {
        Swal.fire({
          icon: 'error',
          title: '회원가입에 실패했습니다..',
          text: '계속 문제가 발생할 시 개발진에게 연락주세요'
        })
      })
    },
    oAuthSignUp: function(){
      customAxios({
        method: 'put',
        url: '/api/auth/signup'
      })
    },
    getFirst: function(credentials){
      this.firstCredentials = {...credentials}
    },
    getSecond: function(credentials){
      this.secondCredentials = {...credentials}
    },
    getValidData: function(validData){
      for (const [key, value] of Object.entries(validData)){
        this.validData[key] = value
      }
    }
  },
  created: function(){
    if (this.oauth){
      this.step = 2
    }
  }
}
</script>

<style lang="scss">
@media only screen and (min-width: 800px){
  #signup-container {
    display: flex;
    flex-direction: column;
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;

    .signup-body {
      min-height: 65vh;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;

      form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 70%;
        margin: 5rem 0 0;

        label {
          width: 80%;
          display: flex;
          justify-content: space-between;
          align-items: center;
          padding: 1.5rem 0;
          position: relative;

          h5 {
            width: 30%;
            font-size: 1.5rem;
            font-weight: bold;
            margin: 0;
          }

          span {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 80%;
            padding: 0.25rem 0.5rem;
            margin: 0 1rem;
            border-bottom: 1px #cccccc solid;

            input {
              border: none;
              margin: 0;
              padding: 0;
              width: 100%;
            }

            button {
              box-sizing: content-box;
              min-width: 64px;
              margin: 0;
              padding: 0.25rem 0.5rem;
              font-size: 1rem;
            }
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

        &:hover, &:disabled {
          background-color: #93D9CE;
          color: white;
        }

        &:disabled {
          cursor: auto;
        }

        &:focus {
          outline: none;
        }
      }

      a {
        margin: 2rem 0;
        font-size: 1.25rem;
        font-weight: bold;
        text-decoration: none;
        color: #555555;
        display: flex;

        p {
          font-size: 1.25rem;
          color: #93D9CE;
          margin: 0
        }
      }
    }
  }
}
@media only screen and (max-width:799px){
  #signup-container {
    display: flex;
    flex-direction: column;
    width: 100%;
    min-height: 100vh;

    .signup-body {
      min-height: 65vh;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;

      form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 70%;
        margin: 5rem 0 0;

        label {
          width: 90%;
          display: flex;
          justify-content: space-between;
          align-items: center;
          padding: 1.5rem 0;
          position: relative;

          h5 {
            width: 30%;
            font-size: 1.5rem;
            font-weight: bold;
            margin: 0;
          }

          span {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 80%;
            padding: 0.25rem 0.5rem;
            margin: 0 1rem;
            border-bottom: 1px #cccccc solid;

            input {
              border: none;
              margin: 0;
              padding: 0;
              width: 100%;
            }

            button {
              box-sizing: content-box;
              min-width: 64px;
              margin: 0;
              padding: 0.25rem 0.5rem;
              font-size: 1rem;
            }
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

        &:hover, &:disabled {
          background-color: #93D9CE;
          color: white;
        }

        &:disabled {
          cursor: auto;
        }

        &:focus {
          outline: none;
        }
      }

      a {
        margin: 2rem 0;
        font-size: 1.25rem;
        font-weight: bold;
        text-decoration: none;
        color: #555555;
        display: flex;

        p {
          font-size: 1.25rem;
          color: #93D9CE;
          margin: 0
        }
      }
    }
  }
}
</style>