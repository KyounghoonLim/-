<template>
  <article id="editor" ref="editor">
    <div class="title-box">
      <input type="text" class="title" placeholder="제목" v-model="title">
    </div>

    <menu-bar :selected="selected" :isCreated="isCreated" :historyInfo="historyInfo"
     @active-menu="params => activate(params)" @image-upload="file => fileSetter(file)" @history-change="payload => historyChange(payload)"
     @submit="submit('manual')" />
    
    <Canvas :type="type" :initialItems="initialItems" :isActive="isActive" :changes="changes" :file="file" :historyChangeFromMenu="historyChangeFromMenu"
     @deactivate="deactivate" @select="tar => select(tar)" @get-history-info="info => getHistoryInfo(info)" />
  </article>
</template>

<script>
import MenuBar from './Menus/MenuBar.vue'
import Canvas from './Canvas/Canvas.vue'
import customAxios from '@/customAxios'
import Swal from 'sweetalert2'

export default {
  data: function(){
    return {
      title: null,
      initialItems: [],
      type: null,
      isActive: false,
      selected: null,
      changes: null,
      file: null,
      historyInfo: [0, 0],
      historyChangeFromMenu: null
    }
  },
  props: {
    diaryId: Number
  },
  components: {
    MenuBar,
    Canvas
  },
  computed: {
    isCreated: function(){
      return !this.isActive
    },
    items: function(){
      return this.$children[1].items
    }
  },
  methods: {
    getInitialItems: function(){
      customAxios({
        method: 'get',
        url: `/api/diary/${this.diaryId}`
      })
      .then(res => {
        this.initialItems = JSON.parse(res.data.content)
        this.title = res.data.title != '제목 없음' ? res.data.title:null
      })
      .catch(err => console.log(err))
    },
    activate: function(params){
      this.type = params.menu
      this.isActive = params.isActive
    },
    deactivate: function(){
      this.isActive = false
      this.changes = null
      this.file = null
    },
    select: function(tar){
      this.selected = tar
    },
    changeSetter: function(payload){
      if (this.selected){
        this.changes = payload
      }
    },
    fileSetter: function(file){
      this.file = file
    },
    getHistoryInfo: function(info){
      this.historyInfo = info
      this.historyChangeFromMenu = null
    },
    historyChange: function(payload){
      this.historyChangeFromMenu = payload
    },
    submit: function(type){
      this.$emit('is-save', type)

      const data = {
        title: this.title ? this.title:'제목 없음',
        content: JSON.stringify(this.items),
        diaryNo: this.diaryId
      }

      if (type == 'manual'){
        let method;
        if (this.diaryId){
          method = 'put'
        }
        else {
          method = 'post'
        }
        customAxios({
          method: method,
          url: '/api/diary',
          data: data
        })
        .then(() => {
          Swal.fire({
            icon: 'success',
            text: '일기가 작성되었어요!'
          })
          .then(() => this.$router.push({name: 'Main', params: {forced: true}, query: {tab: 'calendar'}}))
        })
        .catch(() => {
          Swal.fire({
            icon: 'warning',
            text: '일기를 작성하지 못했어요'
          })
        })
        .finally(() => this.$emit('is-save'))
      }
      else {
        customAxios({
          method: 'put',
          url: `/api/diary/${this.diaryId}`,
          data: data
        })
        .finally(() => this.$emit('is-save'))
      }
    },
    autoSave: function(){
      this.submit('auto')
    },
  },
  created: function(){
    if (this.diaryId){
      this.getInitialItems()
    }
  },
  mounted: function(){
    if (this.diaryId){
      window.setInterval(this.autoSave, 150000)
    }
    this.$on('value-change', payload => this.changeSetter(payload))
  },
  destroyed: function(){
    window.clearInterval(this.autoSave)
  }
}
</script>

<style lang="scss">
  #editor {
    width: 100%;
    min-height: 125vh;
    margin: 3rem;
    border: none;
    background-color: #cccccc;
    display: flex;
    flex-direction: column;

    .title-box {
      width: 100%;
      height: 75px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding-bottom: 2rem;
      background-color: white;

      .title {
        width: 100%;
        border: none;
        font-size: 2rem;
        padding: 0.5rem;
        border-bottom: 1px #cccccc solid;

        &:focus {
          outline: none;

          &::placeholder {
            color: transparent;
          }
        }

        &::placeholder {
          color: #cccccc;
        }
      }
    }
  }
</style>