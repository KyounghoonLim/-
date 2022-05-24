<template>
  <div :class="[{'clicked': active}, 'font-size-container']"
   :title="active? '':'글자크기'">
    <input class="curr-font-size" type="number" v-model.number="currSize" min="1" max="200"
     @click="() => activate(true)" @keypress.enter="keypress" @input="input">
    <div class="font-size-controller" v-if="active"
     title="글자크기는 1~270까지 가능합니다.">
      <hr class="controller-rail">
      <span class="controller-btn" :style="{left: `${10 + currSize}px`}" 
       @mousedown="drag" @mouseup="drop" />
    </div>

    <div class="popup-container" v-if="active" @click="() => activate(false)" @mouseup="drop" />
  </div>
</template>

<script>
export default {
  data: function(){
    return {
      currSize: 10,
      active: false,
      dragged: false,
    }
  },
  props: {
    size: Number
  },
  methods: {
    activate: function(payload){
      this.active = payload
    },
    drag: function(){
      this.dragged = true
      const editor = document.querySelector('#editor')
      editor.addEventListener('mousemove', this.move)
      editor.addEventListener('mouseup', this.drop)
    },
    move: function(e){
      if (this.dragged){
        const temp = e.movementX
        if (temp + this.currSize > 0 && temp + this.currSize <= 270){
          this.currSize += temp
        }
      }
    },
    drop: function(){
      this.dragged = false
      const editor = document.querySelector('#editor')
      editor.addEventListener('mousemove', this.move)
      editor.addEventListener('mouseup', this.drop)
    },
    keypress: function(){
      this.active = false
    },
    input: function(){
      if (this.currSize > 270){
        this.currSize = 270
      }
      else if (this.currSize < 1){
        this.currSize = 1
      }
    },
    /// 선택된 요소의 값을 현재 값에 부여 ///
    getter: function(){
      if (this.size){
        this.currSize = this.size
      }
    },
    /// 변경된 현재 값을 선택된 요소에 적용 ///
    setter: function(){
      /// to Editor.vue ///
      this.$parent.$parent.$parent.$emit('value-change', {size: this.currSize})
    }
  },
  watch: {
    size: function(){
      this.getter()
    },
    currSize: function(){
      if (this.currSize !== this.size){
        this.setter()
      }
    }
  },
  mounted: function(){
    this.getter()
  }
}
</script>

<style lang="scss">
  .font-size-container {
    width: 50px;
    position: relative;
    z-index: 1;

    &.clicked {
      z-index: 9999;
    }

    .curr-font-size {
      width: 100%;
      padding: 0.25rem;
      font-size: 1rem;
      border: none;
      cursor: pointer;

      &::-webkit-outer-spin-button, &::-webkit-inner-spin-button {
        appearance: none;
      }

      &:focus {
        border-radius: 10px;
        outline: 1px #cccccc solid;
      }
    }

    .font-size-controller {
      position: absolute;
      bottom: -30px;
      left: 0;
      width: 300px;
      height: 20px;
      display: flex;
      align-items: center;
      background-color: rgba(0, 0, 0, 0.5);
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      border-radius: 10px;
      z-index: 2;

      .controller-rail {
        width: 280px;
        border: 1px rgba(141, 141, 141, 0.5) solid;
        border-radius: 10px;
      }

      .controller-btn {
        width: 12px;
        height: 12px;
        background-color: #d9d9d9;
        background-image: linear-gradient(315deg, #d9d9d9 0%, #f6f2f2 74%);
        // background-color: white;
        box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
        border-radius: 50%;
        position: absolute;
        cursor: pointer;
      }
    }

    .popup-container {
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
      z-index: 1;
    }
  }
</style>