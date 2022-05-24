<template>
  <section id="diary-create-container" :key="$route.path">
    <navigation />

    <editor ref="editor" :diaryId="diaryId" @is-save="type => save(type)" />

    <div v-if="isSave" class="save-box">
      <div class="auto-save">
        <img class="auto-img" src="../assets/loading-daki.gif">
        <p class="auto-msg">
          {{ saveMessage }}
        </p>
      </div>
    </div>
  </section>
</template>

<script>
import Navigation from '../components/Navigation.vue'
import Editor from '../components/Editor/Editor.vue'

export default {
  data: function(){
    return {
      title: null,
      isSave: false
    }
  },
  props: {
    diaryId: Number
  },
  components: {
    Navigation,
    Editor
  },
  computed: {
    saveMessage: function(){
      if (this.isSave == 'manual'){
        return '저장중입니다..'
      }
      else if (this.isSave == 'auto'){
        return '자동 저장중입니다..'
      }
      return ''
    }
  },
  methods: {
    save: function(type){
      this.isSave = type
    }
  }
}
</script>

<style lang="scss">
  #diary-create-container {
    border-left: 1px #cccccc solid;
    border-right: 1px #cccccc solid;
    width: 70%;
    min-height: 100vh;
    padding-top: 100px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;

    .save-box {
      position: fixed;
      top: 0;
      left: 0;
      width: 100vw;
      height: 100vh;
      background-color: rgba(0, 0, 0, 0.35);
      color: white;
      z-index: 987654321;
      display: flex;
      justify-content: center;
      align-items: center;

      .auto-save {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

        .auto-img {
          width: 100px;
          aspect-ratio: 1/1;
        }

        .auto-msg {
          font-size: 1.25rem;
          color: white;
          margin: 1rem;
        }
      }
    }
  }
</style>