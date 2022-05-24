<template>
  <div :class="['grid-row', `row-${num}`]">
    <button v-if="num == 0" class="create-btn">+</button>
    <card v-for="item of items" :key="item.title + item.diaryNo" :item="item"/>
    <transition name="markers-show">
      <div class="markers" v-show="showMarkers">
        <div v-for="marker in markers" :key="marker"
        :class="['marker', `marker-${marker}`]"
        :style="{ backgroundColor: colors[marker.slice(5, 7)] }" >
          <span class="marker-arrow" :style="{ borderRight: `10px ${colors[marker.slice(5, 7)]} solid` }" />
          {{ marker }}
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import store from '../../store/index'
import Card from './GridCard.vue'

export default {
  data: function(){
    return {
      offset: 0
    }
  },
  props: {
    num: Number,
    items: Array,
    point: Array,
  },
  components: {
    Card
  },
  computed: {
    scroll: function(){
      return store.state.scroll
    },
    markers: function(){
      const markers = []
      this.point.forEach(ele => {
        if (this.num == 0 && ele[0] < 3){
          markers.push(ele[1])
        }
        else if ((ele[0] >= this.num * 4 - 1) && (ele[0] < this.num * 4 + 3)){
          markers.push(ele[1])
        }
      })
      return markers
    },
    showMarkers: function(){
      const h = window.innerHeight
      const p = this.offset - this.scroll

      return (p >= h * 0.25 && p <= h * 0.75) ? true:false
    },
    colors: function(){
      const colors = {
        '01': 'dodgerblue', '02': 'darkturquoise', '03': 'lightsalmon', '04': 'lightcoral',
        '05': 'darkOrange', '06': 'yellowGreen', '07': 'limeGreen', '08': 'forestGreen',
        '09': 'olive', '10': 'peru', '11': 'rosybrown', '12': 'slateblue'
      }
      return colors
    }
  },
  methods: {
    getOffset: function(){
      const tar = document.querySelectorAll(`.row-${this.num}`)[0]
      this.offset = tar.parentElement.offsetTop + tar.offsetTop
    }
  },
  mounted: function(){
    this.getOffset()
  },
}
</script>

<style lang="scss">
  .grid-row {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 2rem;
    position: relative;

    .create-btn {
      width: 30%;
      min-width: 50px;
      min-height: 50px;
      aspect-ratio: 1/1;
      justify-self: center;
      align-self: center;
      border-radius: 50%;
      border: none;
      color: white;
      font-size: 3rem;
      font-weight: bold;
      background-color: #93D9CE;
      padding: 0;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);

      &:hover {
        background-color: #48857b;
      }
    }

    .markers {
      position: absolute;
      top: 0;
      right: -10%;

      .marker {
        color: white;
        font-weight: bold;
        padding: 0.5rem 1rem;
        border-radius: 10px;
        box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
        margin: 0.5rem;
        position: relative;

        .marker-arrow {
          position: absolute;
          border-left: 0;
          border-top: 5px transparent solid;
          border-bottom: 5px transparent solid;
          top: 40%;
          left: -10%;
        }
      }
    }

    @keyframes smooth-show {
      from {
        opacity: 0;
        transform: translateX(-1);
      }
      to {
        opacity: 1;
        transform: translateX(0);
      }
    }

    .markers-show-enter-active {
      animation: smooth-show 0.5s ease-in forwards;
    }

    .markers-show-leave-active {
      animation: smooth-show reverse 0.5s ease-in forwards;
    }
  }
</style>