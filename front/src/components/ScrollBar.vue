<template>
  <div id="scroll-container"
   :class="[activate ? 'scroll-active':'scroll-inactive', {'clicked': clicked}]"
   v-if="isShow" @mouseover="scrollActivate(true)" @mouseleave="scrollActivate(false)" 
   @click="moveScroll">
    <span :class="['scroll-bar', {'dragged': dragged}]" :style="{ height: s, top: position }"
     @mousedown="e => dragDrop(e, true)" @mouseup="e => dragDrop(e, false)" />
    <div class="drag-box" v-if="dragged" @mouseup="e => dragDrop(e, false)" @mousemove="move" />
  </div>
</template>

<script>
import store from '../store/index.js'

export default {
  data: function(){
    return {
      activate: true,
      clicked: false,
      dragged: false,
      firstClicked: null,
      flag: false,
      flag2: false,
    }
  },
  props: {
    height: Number
  },
  computed: {
    scroll: function(){
      return store.state.scroll
    },
    isEnd: function(){
      return store.state.isEnd
    },
    isShow: function(){
      if (this.height > this.v){
        return true
      }
      return false
    },
    v: function(){
      return window.innerHeight
    },
    s: function(){
      return `${this.v / this.height * 100}%`
    },
    position: function(){
      if (this.clickPosition){
       return `${this.clickPosition / this.height * 100}%`
      }
      return `${this.scroll / this.height * 100}%`
    }
  },
  watch: {
    scroll: function(){
      clearTimeout(this.foo)
      this.activate = true
      this.foo = setTimeout(() => {
        this.activate = false
      }, 2000)
    }
  },
  methods: {
    scrollActivate: function(payload){
      if (this.clicked){
        return
      }
      this.activate = payload
    },
    moveScroll: function(e){
      if (e.target.id !== 'scroll-container'){
        return
      }
      const p = this.height * e.clientY / this.v
      let scrollTo
      if (p > this.scroll){
        const _p = this.height * (e.clientY / this.v - this.v / this.height)
        scrollTo = {top: _p, behavior: 'smooth'}
      }
      else {
        scrollTo = {top: p, behavior: 'smooth'}
      }
      console.log(scrollTo)
      this.clicked = true
      window.scrollTo(scrollTo)
      setTimeout(() => {
        this.clicked = false
      }, 500)
    },
    dragDrop: function(e, payload){
      if (payload){
        this.firstClicked = e.offsetY
      }
      this.dragged = payload
      this.activate = payload
    },
    move: function(e){
      if (!this.dragged){
        return
      }
      const p = this.height * (e.clientY - this.firstClicked) / this.v
      window.scrollTo({top: p, behavior: 'smooth'})
    },
    scrollHandler: async function(){
      store.dispatch('scrollHandler', window.scrollY)

      if (!this.flag2){
        this.flag2 = true
        if (this.scroll + this.v == this.height){
          await this.$store.dispatch('arrivedEnd', true)
          this.$store.dispatch('arrivedEnd', false)
        }
        this.flag2 = false
      }
    }
  },
  created: function(){
    const _this = this
    window.addEventListener('scroll', () => {
      if (!_this.flag){
        _this.flag = true
        window.requestAnimationFrame(this.scrollHandler)
        _this.flag = false
      }
    })
  },
  mounted: function(){
    setTimeout(() => {
      this.activate = false
    }, 1000)
  }
}
</script>

<style lang="scss">
  #scroll-container {
    position: fixed;
    top: 0;
    right: 0;
    width: 20px;
    height: 100vh;
    opacity: 0;
    z-index: 9;

    @keyframes fade-in {
      from { opacity: 0; }
      to { opacity: 1; }
    }
  
    @keyframes fade-out {
      from { opacity: 1; }
      to { opacity: 0; }
    }

    &.scroll-active {
      animation: fade-in 0.2s ease-in forwards;
    }

    &.scroll-inactive {
      animation: fade-out 0.5s ease-in forwards;
    }

    &.clicked {
      background-color: rgba(147, 158, 253, 0.5);
    }

    .scroll-bar {
      position: absolute;
      width: 8px;
      right: 6px;
      border-radius: 10px;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      background-color: rgba(110, 110, 110, 0.5);

      &.dragged {
        background-color: rgba(110, 110, 110, 0.85);;
      }
    }

    .drag-box {
      position: absolute;
      top: 0;
      right: 0;
      width: 100vw;
      height: 100vh;
      z-index: 2;
    }
  }
</style>