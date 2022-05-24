<template>
  <div id="grid-container">
    <grid-row v-for="(t, num) in nums" :key="'row-' + num" :num="num"
     :items="slicedItems(num)" :point="point" />
    <img v-if="loading" class="loading" src="../../assets/loading.gif">
  </div>
</template>

<script>
import store from '../../store/index.js'
import GridRow from './GridRow.vue'
import customAxios from '@/customAxios'

export default {
  data: function(){
    return {
      items: null,
      page: 1,
      loading: false,
      flag: false
    }
  },
  components: {
    GridRow
  },
  computed: {
    isEnd: function(){
      return store.state.isEnd
    },
    nums: function(){
      if (this.items){
        return Math.ceil(this.items.length / 4)
      }
      return 0
    },
    point: function(){
      const point = [[0, this.items[0].diaryDate.slice(0, 7)]]

      this.items.forEach((ele, idx) => {
        const tar = point[point.length-1][1]
        if (tar != ele.diaryDate.slice(0, 7)){
          point.push([idx, ele.diaryDate.slice(0, 7)])
        }
      })

      return point
    }
  },
  methods: {
    slicedItems: function(n){
      if (n == 0){
        return this.items.slice(0, 3)
      }
      return this.items.slice(n * 4 - 1, n * 4 + 3)
    },
    getItem: function(){
      customAxios({
        method: 'get',
        url: `/api/diary/findPage/${this.page}`
      })
      .then(res => {
        this.items = res.data
        this.page += 1
      })
      .catch(err => console.log(err))
    }
  },
  watch: {
    isEnd: function(){
      if (!this.loading && this.isEnd && !this.flag){
        this.loading = true
        this.getItem()
      }
    },
    page: function(){
      if (this.page > this.nums / 2){
        this.flag = true
      }
    }
  },
  created: function(){
    this.getItem()
  }
}
</script>

<style lang="scss">
  #grid-container {
    width: 100%;
    padding: 5rem;
    display: grid;
    grid-template-columns: 1fr;
    gap: 2rem;
    position: relative;

    & * {
      cursor: pointer;
    }

    .loading {
      width: 100px;
      aspect-ratio: 1/1;
      margin: 3rem auto;
      cursor: auto;
    }
  }
</style>