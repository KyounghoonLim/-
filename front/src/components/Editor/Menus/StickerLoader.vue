<template>
  <div class="sticker-container">
    <div class="small-wrapper">
      <h3 class="sticker-message">
        {{ stickers ? '스티커를 선택해주세요':'패키지를 선택해주세요' }}
      </h3>
    </div>
    
    <div v-if="!stickers" class="wrapper">
      <span v-for="pack in packageList" :key="pack.packageId"
      class="package-item" :style="{backgroundImage: `url(${pack.packageImg})`}" :title="pack.packageName"
      @click="getStickers(pack.packageId)" />
    </div>

    <div v-else class="wrapper">
      <span v-for="sticker in stickers" :key="sticker.stickerId"
      class="sticker-item" :style="{backgroundImage: `url(${sticker.stickerImg})`}"
      @click="select(sticker.stickerImg)" />
    </div>

    <div class="small-wrapper" v-if="stickers" @click="backToPackage">
      <button class="backward"> &lt; 패키지 다시 고르기</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data: function(){
    return {
      packageList: null,
      stickers: null
    }
  },
  methods: {
    select: function(sticker){
      this.$emit('select-sticker', sticker)
    },
    backToPackage: function(){
      this.stickers = null
    },
    getPackageList: function(){
      const packageList = window.sessionStorage.getItem('packageList')
      if (packageList){
        this.packageList = JSON.parse(packageList)
      }
      else {
        axios({
          method: 'get',
          url: 'https://messenger.stipop.io/v1/package',
          headers: {
            apikey: process.env.VUE_APP_STIPOP_API_KEY
          },
          params: {
            userId: 'test',
            countryCode: 'KO',
            premium: 'N'
          }
        })
        .then(res => {
          const packageList = res.data.body.packageList
          this.packageList = packageList

          window.sessionStorage.setItem('packageList', JSON.stringify(packageList))
        })
        .catch(err => console.log(err))
      }
    },
    getStickers: function(id){
      axios({
        method: 'get',
        url: `https://messenger.stipop.io/v1/package/${id}`,
        headers: {
          apikey: process.env.VUE_APP_STIPOP_API_KEY
        },
        params: {
          userId: 'test'
        }
      })
      .then(res => {
        this.stickers = res.data.body.package.stickers
      })
      .catch(err => console.log(err))
    }
  },
  created: function(){
    this.getPackageList()
  }
}
</script>

<style lang="scss">
  .sticker-container {
    width: 600px;
    height: 600px;
    position: absolute;
    bottom: -600px;
    left: 0;
    background-color: white;
    border-radius: 10px;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
    z-index: 2;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;


    .small-wrapper {
      height: 10%;
      margin: 0 2rem;
      display: flex;
      align-items: center;

      .sticker-message {
        margin: 0;
        font-size: 1.5rem;
      }

      .backward {
        background-color: transparent;
        border: none;
        color: #777777;
        cursor: pointer;

        &:hover {
          color: #93D9CE;
        }
      }
    }

    .wrapper {
      display: flex;
      justify-content: space-evenly;
      align-items: flex-start;
      flex-wrap: wrap;
      width: 100%;
      height: 80%;
      overflow-y: scroll;

      &::-webkit-scrollbar {
        width: 10px;
        position: absolute;
      }

      &::-webkit-scrollbar-thumb {
        background-color: #D8D8D8;
        border-radius: 10px;
        background-clip: padding-box;
        border: 1px solid transparent;
      }

      .package-item, .sticker-item {
        width: 150px;
        height: 150px;
        background-size: cover;
        background-position: center;
        margin: 1rem;
        cursor: pointer;
      }
    }
  }
</style>