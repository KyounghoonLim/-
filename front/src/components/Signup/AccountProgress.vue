<template>
  <div :class="['progress-box', {'oauth': oauth}]">
    <hr class="progress-bar" ref="progress-bar">
    <div :class="['progress', {'done': step > 0, 'warn': step > 0 && !firstValid, 'disabled': step == 3}]" v-if="!oauth"
     :title="firstValid ? '':`${firstHelpMessage}를 확인해주세요`" id="1" @click="e => changeStep(e)">
      <p class="progress-text">기본 정보</p>
    </div>
    <div :class="['progress', {'done': step > 1, 'warn': step > 1 && !secondValid, 'disabled': step == 3}]" 
     :title="secondValid ? '':`${secondHelpMessage}를 확인해주세요`" id="2" @click="e => changeStep(e)">
      <p class="progress-text">개인 정보</p>
    </div>
    <div :class="['progress', {'done': step > 2, 'disabled': step == 3}]" id="3" @click="e => changeStep(e)">
      <p class="progress-text">가입 완료</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    step: Number,
    direction: Boolean,
    oauth: Boolean,
    firstValidData: Object,
    secondValidData: Object
  },
  computed: {
    firstValid: function(){
      return Object.values(this.firstValidData).every(ele => ele)
    },
    firstHelpMessage: function(){
      const temp = {email: '이메일', nickname: '닉네임', password: '비밀번호', passwordConf: '비밀번호확인'}
      const msg = []
      Object.keys(this.firstValidData).forEach(key => {
        if (!this.firstValidData[key]){
          msg.push(temp[key])
        }
      })
      return msg.join(', ')
    },
    secondValid: function(){
      return Object.values(this.secondValidData).every(ele => ele)
    },
    secondHelpMessage: function(){
      const temp = {birth: '생년월일', gender: '성별', dollType: '캐릭터'}
      const msg = []
      Object.keys(this.secondValidData).forEach(key => {
        if (!this.secondValidData[key]){
          msg.push(temp[key])
        }
      })
      return msg.join(', ')
    },
  },
  methods: {
    changeStep: function(e){
      let tar
      if (e.target.id){
        tar = e.target.id
      }
      else {
        tar = e.target.parentElement.id
      }
      if (this.step !== 3){
        this.$emit('change-step', tar)
      }
    }
  },
  watch: {
    step: function(){
      const bar = this.$refs['progress-bar']

      if (this.step == 1 && !this.direction){
        bar.style.animation = 'decrease2 0.5s ease-in both'
      }
      else if (this.step == 2){
        if (this.oauth){
          if (!this.direction){
            bar.style.animation = 'decrease3 0.5s ease-in both'
          }
        }
        else {
          if (this.direction){
            bar.style.animation = 'increase1 0.5s ease-in both'
          }
          else {
            bar.style.animation = 'decrease1 0.5s ease-in both'
          }
        }
      }
      else if (this.step == 3){
        if (this.oauth){
          bar.style.animation = 'increase3 0.5s ease-in both'
        }
        bar.style.animation = 'increase2 0.5s ease-in both'
      }
    }
  },
  mounted: function(){
    const dots = document.querySelectorAll('.progress')

    if (this.oauth){
      dots.forEach((ele, idx) => {
        ele.style.left = `${100 * idx}%`
      })
    }
    else {
      dots.forEach((ele, idx) => {
        ele.style.left = `${50 * idx}%`
      })
    }
  }
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  .progress-box {
    display: flex;
    width: 50%;
    min-width: 500px;
    justify-content: space-between;
    align-items: center;
    margin-top: 5%;
    border-top: 1px #777777 solid;
    position: relative;

    &.oauth {
      width: 10%;
    }

    @keyframes increase1 {
      from {width: 0%;}
      to {width: 50%;}
    }

    @keyframes increase2 {
      from {width: 50%;}
      to {width: 100%;}
    }

    @keyframes increase3 {
      from {width: 0%;}
      to {width: 100%;}
    }

    @keyframes decrease1 {
      from {width: 100%;}
      to {width: 50%;}
    }

    @keyframes decrease2 {
      from {width: 50%;}
      to {width: 0%;}
    }

    @keyframes decrease3 {
      from {width: 100%;}
      to {width: 0%;}
    }

    .progress-bar {
      position: absolute;
      border: 4px #93D9CE solid;
      border-radius: 5px;
      top: -4px;
      margin: 0;
      z-index: 1;
    }

    .progress {
      box-sizing: border-box;
      width: 20px;
      aspect-ratio: 1/1;
      outline: 1px #777777 solid;
      background-color: white;
      border-radius: 50%;
      z-index: 2;
      position: absolute;
      cursor: pointer;

      .progress-text {
        min-width: 100px;
        color: #777777;
        font-size: 1.25rem;
        font-weight: bold;
        position: absolute;
        word-break: keep-all;
        top: 10px;
        left: -32px;
      }

      &.done {
        outline: 1px #93D9CE solid;
        border: 5px white solid;
        background-color: #93D9CE;

        .progress-text {
          font-size: 1.35rem;
          left: -40px;
          color: #93D9CE;
        }
      }

      &.warn {
        outline: 1px rgb(252, 112, 112) solid;
        border: 5px white solid;
        background-color: rgb(250, 112, 112);

        .progress-text {
          color: rgb(252, 112, 112);
        }
      }

      &.disabled {
        cursor: auto;
      }
    }
  }
}
@media only screen and (max-width:799px){
    .progress-box {
    display: flex;
    width: 80%;
    // min-width: 500px;
    justify-content: space-between;
    align-items: center;
    margin-top: 5%;
    border-top: 1px #777777 solid;
    position: relative;

    &.oauth {
      width: 10%;
    }

    @keyframes increase1 {
      from {width: 0%;}
      to {width: 50%;}
    }

    @keyframes increase2 {
      from {width: 50%;}
      to {width: 100%;}
    }

    @keyframes increase3 {
      from {width: 0%;}
      to {width: 100%;}
    }

    @keyframes decrease1 {
      from {width: 100%;}
      to {width: 50%;}
    }

    @keyframes decrease2 {
      from {width: 50%;}
      to {width: 0%;}
    }

    @keyframes decrease3 {
      from {width: 100%;}
      to {width: 0%;}
    }

    .progress-bar {
      position: absolute;
      border: 4px #93D9CE solid;
      border-radius: 5px;
      top: -4px;
      margin: 0;
      z-index: 1;
    }

    .progress {
      box-sizing: border-box;
      width: 20px;
      aspect-ratio: 1/1;
      outline: 1px #777777 solid;
      background-color: white;
      border-radius: 50%;
      z-index: 2;
      position: absolute;
      cursor: pointer;

      .progress-text {
        min-width: 100px;
        color: #777777;
        font-size: 1.25rem;
        font-weight: bold;
        position: absolute;
        word-break: keep-all;
        top: 10px;
        left: -32px;
      }

      &.done {
        outline: 1px #93D9CE solid;
        border: 5px white solid;
        background-color: #93D9CE;

        .progress-text {
          font-size: 1.35rem;
          left: -40px;
          color: #93D9CE;
        }
      }

      &.warn {
        outline: 1px rgb(252, 112, 112) solid;
        border: 5px white solid;
        background-color: rgb(250, 112, 112);

        .progress-text {
          color: rgb(252, 112, 112);
        }
      }

      &.disabled {
        cursor: auto;
      }
    }
  }
}
</style>