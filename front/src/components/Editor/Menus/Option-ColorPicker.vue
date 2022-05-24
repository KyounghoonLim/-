<template>
  <div class="color-picker-container">
    <label :class="[{'clicked': active}, 'text-color']" title="글자색" for="text-color">
      <input type="checkbox" id="text-color" :value="true" v-model="active">
      <span class="curr-color" :style="{backgroundColor: currColor}" :title="currColor" />
      <color-panel v-if="active" v-model="currColor" />
    </label>
    <div class="popup-container" v-if="active" @click="close" />
  </div>
</template>

<script>
import { ColorPanel } from 'one-colorpicker'

export default {
  data: function(){
    return {
      active: false,
      currColor: '#000000',
    }
  },
  props: {
    color: String
  },
  components: {
    ColorPanel
  },
  methods: {
    close: function(e){
      console.log(e.target)
      this.active = false
    },
    /// 선택된 요소의 값을 현재 값에 부여 ///
    getter: function(){
      if (this.color){
        this.currColor = this.color
      }
    },
    /// 변경된 현재 값을 선택된 요소에 적용 ///
    setter: function(){
      /// to Editor.vue ///
      this.$parent.$parent.$parent.$emit('value-change', {color: this.currColor})
    }
  },
  watch: {
    color: function(){
      this.getter()
    },
    currColor: function(){
      if (this.currColor !== this.color){
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
  .color-picker-container {
    display: flex;
    
    .text-color {
      background-image: url('../../../assets/Editor/text-color.png');

      .curr-color {
        position: absolute;
        bottom: 0;
        left: 10%;
        width: 80%;
        height: 10px;
        border-radius: 10px;
      }

      &.clicked {
        z-index: 9999;
      }
    }
    
    .popup-container {
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
    }
  }
</style>