<template>
  <section id="main-container">
    <navigation />
    <article class="tab-box">
      <span @click="changeTab('calendar')" :class="{'tab':true, 'active': tab == 'calendar'}">달력으로 보기</span>
      <span @click="changeTab('grid')" :class="{'tab':true, 'active': tab == 'grid'}">그리드로 보기</span>
    </article>
    <diary-pop-up v-if="target" :target="target" @close-pop-up="closePopUp" @change-diaries="changeDiaries" />
    <article class="main-body">
      <calendar v-if="tab == 'calendar'" :changes="changes" @show-date-diary="showDateDiary" @change-diaries="changeDiaries" />
      <grid v-if="tab == 'grid'" />
    </article>
  </section>
</template>

<script>
import Navigation from '../components/Navigation.vue'
import Calendar from '../components/Main/Calendar.vue'
import Grid from '../components/Main/Grid.vue'
import DiaryPopUp from '../components/Main/DiaryPopUp.vue'
// import { mapState } from 'vuex'

export default {
  data: () => {
    return {
      target: null,
      dollNo: null,
      changes: false
    }
  },
  components: {
    Navigation,
    Calendar,
    Grid,
    DiaryPopUp
  },
  computed: {
    tab: function(){
      return this.$route.query.tab
    },
		// ...mapState([
    //   'user',
    // ])
  },
  methods: {
    changeTab: function(tab){
      this.$router.push({ name: 'Main', query: { tab: tab }})
    },
    showDateDiary: function(t){
      this.target = t
    },
    closePopUp: function(){
      this.target = null
    },
    changeDiaries: function(tar){
      this.changes = tar
    }
  },
  created(){
    this.dollNo = this.$store.state.user.doll_no
    console.log(this.dollNo)
    this.$store.dispatch('userItemList',this.dollNo)
  }
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  #main-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;
    padding-top: 100px;

    .tab-box {
      width: 100%;
      height: 100px;
      display: flex;
      justify-content: center;
      align-items: flex-end;
      border-bottom: 1px #cccccc solid;
      overflow: hidden;

      .tab {
        width: 25%;
        height: 60px;
        background-color: #cccccc;
        color: #777777;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 1.5rem;
        font-weight: bold;
        margin: 0 3rem;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        cursor: pointer;
        box-shadow: 2px 0 4px rgba(0, 0, 0, 0.5);
      }

      @keyframes scale-up {
        from {
          height: 60px;
        }
        to {
          height: 75px;
        }
      }

      .active {
        animation: scale-up 0.1s ease-in forwards;
        background-color: #93D9CE;
        color: white;
      }
    }

    .main-body {
      display: flex;
      justify-content: center;
    }
  }
}
@media only screen and (max-width:799px){
  #main-container {

    width: 100%;
    min-height: 100vh;
    padding-top: 100px;

    .tab-box {
      width: 100%;
      height: 100px;
      display: flex;
      justify-content: center;
      align-items: flex-end;
      border-bottom: 1px #cccccc solid;
      overflow: hidden;

      .tab {
        width: 25%;
        height: 60px;
        background-color: #cccccc;
        color: #777777;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 1.5rem;
        font-weight: bold;
        margin: 0 3rem;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        cursor: pointer;
        box-shadow: 2px 0 4px rgba(0, 0, 0, 0.5);
      }

      @keyframes scale-up {
        from {
          height: 60px;
        }
        to {
          height: 75px;
        }
      }

      .active {
        animation: scale-up 0.1s ease-in forwards;
        background-color: #93D9CE;
        color: white;
      }
    }

    .main-body {
      display: flex;
      justify-content: center;
    }
  }
}
</style>