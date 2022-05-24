<template>
  <div :class="['card-container', `date-${item.diaryDate.slice(0, 7)}`]" 
   @click="goToDetail">
    <div class="card-img" :style="{backgroundImage: `url(${thumbnail})`}" />
    <h2 class="card-title">{{ item.title }}</h2>
    <p class="card-content">작성일: {{ item.diaryDate.slice(0, 10) }}</p>
    <span class="opacity" />
  </div>
</template>

<script>
import defalutImage from '../../assets/default.png'

export default {
  props: {
    item: Object
  },
  computed: {
    thumbnail: function(){
      if (!this.item.thumbnail){
        return defalutImage
      }
      return this.item.thumbnail
    }
  },
  methods: {
    goToDetail: function(){
      this.$router.push(`/diary/${this.item.diaryNo}`)
    }
  }
}
</script>

<style lang="scss">
  .card-container {
    display: flex;
    flex-direction: column;
    padding: 1rem;
    width: 100%;
    aspect-ratio: 1/1.25;
    border-radius: 10px;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
    overflow: hidden;
    position: relative;

    & * {
      margin: 0.25rem 0;
      width: 100%;
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
    }

    &:hover {
      .opacity {
        visibility: visible;
      }
    }

    .opacity {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      margin: 0;
      visibility: hidden;
      background-color: rgba(0, 0, 0, 0.35);
    }

    .card-img {
      aspect-ratio: 1/1;
      border-radius: 10px;
      background-size: cover;
    }

    .card-title {
      font-size: 1.5rem;
    }

    .card-content {
      color: #777777;
    }
  }
</style>