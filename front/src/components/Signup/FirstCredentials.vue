<template>
  <form id="first-form" @submit.prevent="nextStep()">
    <label for="email">
      <h5>이메일</h5>
      <span>
        <input type="email" id="email" placeholder="이메일을 입력하세요" v-model="credentials.email">
        <button :disabled="isValid.email" @click.prevent="validator('email')">중복확인</button>
      </span>
      <div class="help-message-box" v-show="isActive.email">
        <p class="help-message corr" v-if="isValid.email">
          사용가능한 이메일입니다.
        </p>
        <p class="help-message warn" v-else>
          사용할 수 없는 이메일입니다.
        </p>
      </div>
    </label>

    <label for="nickname">
      <h5>닉네임</h5>
      <span>
        <input type="text" id="nickname" placeholder="닉네임은 2글자 이상, 8글자 이하입니다 " v-model="credentials.nickName">
        <button :disabled="isValid.nickName" @click.prevent="validator('nickname')">중복확인</button>
      </span>
      <div class="help-message-box" v-show="isActive.nickName">
        <p class="help-message corr" v-if="isValid.nickName">
          사용가능한 닉네임입니다.
        </p>
        <p class="help-message warn" v-else>
          사용할 수 없는 닉네임입니다.
        </p>
      </div>
    </label>

    <label for="password">
      <h5>비밀번호</h5>
      <span>
        <input type="password" id="password" placeholder="비밀번호는 8 ~ 16자, 숫자, 문자, 특수문자 중 두 가지를 포함합니다" v-model="credentials.password"
         @change="validator('password')">
      </span>
      <div class="help-message-box" v-show="isActive.password">
        <p class="help-message corr" v-if="isValid.password">
          사용가능한 비밀번호입니다.
        </p>
        <p class="help-message warn" v-else>
          사용할 수 없는 비밀번호입니다.
        </p>
      </div>
    </label>

    <label for="password-conf">
      <h5>비밀번호 확인</h5>
      <span>
        <input type="password" id="password-conf" placeholder="비밀번호를 다시 입력하세요" v-model="credentials.passwordConf"
         @change="validator('passwordConf')">
      </span>
      <div class="help-message-box" v-show="isActive.passwordConf">
        <p class="help-message corr" v-if="isValid.passwordConf">
          비밀번호가 확인되었습니다.
        </p>
        <p class="help-message warn" v-else>
          비밀번호가 맞지 않습니다.
        </p>
      </div>
    </label>

    <button>다음</button>

    <router-link to="/login">
      <p>로그인</p>으로 이동
    </router-link>
  </form>
</template>

<script>
import customAxios from '../../customAxios.js'
import Swal from 'sweetalert2'

export default {
  data: function(){
    return {
      credentials: {...this.firstCredentials},
      isValid: {...this.validData},
      isActive: {
        email: false,
        nickName: false,
        password: false,
        passwordConf: false
      }
    }
  },
  props: {
    firstCredentials: Object,
    validData: Object
  },
  methods: {
    nextStep: function(){
      this.$emit('next-step', 2)
    },
    validator: function(payload){
      if (payload == 'email'){
        const emailValidator = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/
        if (!this.credentials.email){
          return Swal.fire({
            icon: 'warning',
            text: '이메일을 입력해주세요!'
          })
        }
        if (emailValidator.exec(this.credentials.email)){
          return this.emailCheck()
        }
        this.isActive.email = true
      }
      else if (payload == 'nickname'){
        if (!this.credentials.nickName){
          return Swal.fire({
            icon: 'warning',
            text: '닉네임을 입력해주세요!'
          })
        }
        if (this.credentials.nickName.length > 1 && this.credentials.nickName.length < 9){
          return this.nicknameCheck()
        }
        this.isActive.nickName = true
      }
      else if (payload == 'password'){
        this.isActive.password = true
        const passValidator = /^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{8,16}$/
        if (passValidator.exec(this.credentials.password)){
          this.isValid.password = true
        }
        else {
          this.isValid.password = false
        }
        if (this.credentials.passwordConf){
          this.validator('passwordConf')
        }
      }
      else if (payload == 'passwordConf'){
        this.isActive.passwordConf = true
        if (this.credentials.password == this.credentials.passwordConf){
          this.isValid.passwordConf = true
        }
        else {
          this.isValid.passwordConf = false
        }
      }
    },
    emailCheck: function(){
      customAxios({
        method: 'get',
        url: `/api/auth/emailCheck/${this.credentials.email}`
      })
      .then(() => {
        Swal.fire({
          icon: 'success',
          title: '사용가능한 이메일입니다',
          text: '다음 단계로 넘어가볼까요?'
        })
      })
      .then(() => this.isValid.email = true)
      .catch(() => {
        Swal.fire({
          icon: 'error',
          title: '사용중인 이메일이에요',
          text: '다른 이메일을 입력해주세요'
        })
      })
      .finally(() => this.isActive.email = true)
    },
    nicknameCheck: function(){
      customAxios({
        method: 'get',
        url: `/api/auth/nickNameCheck/${this.credentials.nickName}`
      })
      .then(() => {
        Swal.fire({
          icon: 'success',
          title: '사용가능한 닉네임입니다',
          text: '다음 단계로 넘어가볼까요?'
        })
      })
      .then(() => this.isValid.nickName = true)
      .catch(() => {
        Swal.fire({
          icon: 'error',
          title: '사용중인 닉네임이에요',
          text: '다른 닉네임을 입력해주세요'
        })
      })
      .finally(() => this.isActive.nickName = true)
    }
  },
  watch: {
    credentials: {deep: true, handler(){
      Object.keys(this.credentials).forEach(key => {
        if (this.credentials[key] !== this.firstCredentials[key]){
          if (this.isValid[key]){
            this.isValid[key] = false
          }
          this.isActive[key] = false
        }
      })
    }},
    isValid: {deep:true, handler(){
      console.log('valid')
      this.$emit('get-valid-data', this.isValid)
      this.$emit('get-credentials', this.credentials)
    }}
  },
  created: function(){
    for (const [key, value] of Object.entries(this.isValid)){
      this.isActive[key] = value
    }
  }
}
</script>

<style lang="scss">
  ::placeholder {
    font-size: 1rem !important;
    letter-spacing: 0;
  }

  .help-message-box {
    position: absolute;
    bottom: -0.25rem;
    left: 29%;

    .help-message {
      margin: 0;

      &.warn {
        color: rgb(250, 75, 75);
      }

      &.corr {
        color: #39bda9;
      }
    }
  }
</style>