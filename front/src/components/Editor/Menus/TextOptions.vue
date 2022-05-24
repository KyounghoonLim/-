<template>
  <div class="side-options">
    <option-color-picker :color="color" />

    <div class="option-box">
      <option-font-family :family="family" />
      <option-font-size :size="size" />
    </div>

    <label :class="[{'clicked': bold}, 'bold']" title="굵게" for="bold">
      <input type="checkbox" id="bold" v-model="bold">
     </label>

    <label :class="[{'clicked': underline}, 'underline']" title="밑줄" for="underline">
      <input type="checkbox" id="underline" v-model="underline">
     </label>

    <label :class="[{'clicked': align == 'left'}, 'align-left']" title="왼쪽정렬" for="align-left"
     @click="optionClickHandler">
      <input type="radio" id="align-left" value="left" v-model="align">
     </label>

    <label :class="[{'clicked': align == 'center'}, 'align-center']" title="중앙정렬" for="align-center"
     @click="optionClickHandler">
      <input type="radio" id="align-center" value="center" v-model="align">
     </label>

    <label :class="[{'clicked': align == 'right'}, 'align-right']" title="오른쪽정렬" for="align-right"
     @click="optionClickHandler">
      <input type="radio" id="align-right" value="right" v-model="align">
     </label>
  </div>
</template>

<script>
import OptionColorPicker from './Option-ColorPicker.vue'
import OptionFontFamily from './Option-FontFamily.vue'
import OptionFontSize from './Option-FontSize.vue'

export default {
  data: function(){
    return {
      align: 'left',
      bold: false,
      underline: false
    }
  },
  props: {
    selected: Object
  },
  components: {
    OptionColorPicker,
    OptionFontFamily,
    OptionFontSize
  },
  computed: {
    color: function(){
      if (this.selected){
        return this.selected.fontStyle.color
      }
      return null
    },
    family: function(){
      if (this.selected){
        return this.selected.fontStyle.family
      }
      return null
    },
    size: function(){
      if (this.selected){
        return this.selected.fontStyle.size
      }
      return null
    }
  },
  methods: {
    optionClickHandler: function(e){
      if (this.align == e.target.htmlFor){
        e.preventDefault();
      }
    },
    getter: function(){
      if (this.selected){
        this.align = this.selected.fontStyle.align
        this.bold = this.selected.fontStyle.weight == 'bold' ? true:false
        this.underline = this.selected.fontStyle.underline == 'underline' ? true:false
      }
    },
    setter: function(payload){
      /// to Editor ///
      const key = Object.keys(payload)[0]
      if (this.selected.fontStyle[key] != payload[key]){
        console.log('저용')
        this.$parent.$parent.$emit('value-change', payload)
      }
    }
  },
  watch: {
    selected: function(){
      this.getter()
    },
    align: function(){
      if (this.align !== this.selected.fontStyle.align){
        this.setter({align: this.align})
      }
    },
    bold: function(){
      this.setter({weight: this.bold ? 'bold':'normal'})
    },
    underline: function(){
      this.setter({underline: this.underline ? 'underline':'none'})
    }
  },
  mounted: function(){
    this.getter()
  }
}
</script>

<style lang="scss">
  .side-options {

    .option-box {
      height: 75%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }

    .bold {
      background-image: url('../../../assets/Editor/bold.png');
    }

    .underline {
      background-image: url('../../../assets/Editor/underline.png');
    }

    .align-left {
      background-image: url('../../../assets/Editor/align-left.png');
    }
  
    .align-center {
      background-image: url('../../../assets/Editor/align-center.png');
    }
  
    .align-right {
      background-image: url('../../../assets/Editor/align-right.png');
    }
  }
</style>