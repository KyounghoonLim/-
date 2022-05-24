<template>
  <div id="canvas-container" ref="canvas" @click="createNewOne" @contextmenu.prevent>
    <canvas-item v-for="(item, idx) in items" :key="item.content ? item.content + idx + pointer: item.imgUrl + idx + pointer" :item="item" :idx="idx" 
    :selected="selected == idx ? true:false" :canvasSize="{w: canvasWidth, h: canvasHeight}" :maxIdx="items.length - 1"
     @select="idx => select(idx)" @remove-item="removeItem" @move-index="index => moveIndex(index)"
     @value-change="payload => valueChange(payload)"
     ref="canvas-item"/>

    <img v-if="file && isActive" :src="file" class="image-preview" ref="preview" @load="loadPreview"
     :style="{top: `${mouseY}%`, left: `${mouseX}%`}" >
  </div>
</template>

<script>
// import {items} from './Dummy.js'
import CanvasItem from './CanvasItem.vue'
import AWS from 'aws-sdk'

export default {
  data: function(){
    return {
      cursorType: null,
      items: null,
      selected: null,
      canvasWidth: null,
      canvasHeight: null,
      mouseX: null,
      mouseY: null,
      history: Array(1000),
      historyChange: false,
      pointer: -1,
      historyHead: 0,
      ctrl: false,
      shift: false
    }
  },
  props: {
    initialItems: Array,
    type: String,
    isActive: Boolean,
    changes: Object,
    file: String,
    historyChangeFromMenu: String
  },
  components: {
    CanvasItem
  },
  methods: {
    init: function(){
      this.items = this.initialItems
    },
    getCanvasSize: function(){
      this.canvasWidth = this.$refs.canvas.clientWidth
      this.canvasHeight = this.$refs.canvas.clientHeight
    },
    createNewOne: function(e){
      if (e.target.id == 'canvas-container' && !this.isActive){
        this.selected = null
      }
      if (this.isActive){
        const canvas = this.$refs.canvas
        const preview = this.$refs.preview

        let temp = {
          type: this.cursorType,
          top: e.offsetY / canvas.clientHeight * 100,
          left: e.offsetX / canvas.clientWidth * 100,
          width: 20,
          height: 10,
          imgUrl: null,
          content: '',
          fontStyle: {
            size: 16,
            family: 'HallymMjo-Regular',
            weight: 'normal',
            align: 'left'
          }
        }

        if (this.cursorType == 'image' || this.cursorType == 'sticker'){
          temp.width = (preview.width / this.canvasWidth) * 100
          temp.height =( preview.height / this.canvasHeight) * 100
          temp.top = this.mouseY
          temp.left = this.mouseX
          temp.imgUrl = this.file
        }

        this.items.push(temp)
        this.selected = this.items.length - 1
      }
      this.$emit('deactivate')
      this.$refs.canvas.style.cursor = 'auto'
      this.mouseX = null; this.mouseY = null
    },
    keyAction: function(e){
      if (e.key == 'Control'){
        this.ctrl = true
      }
      if (e.key == 'Shift'){
        this.shift = true
      }
      if (this.selected){
        const item = this.items[this.selected]
        /// remove
        if (e.key == 'Backspace' || e.key == 'Delete'){
          this.removeItem()
        }
        /// item move
        if (e.key == 'ArrowUp'){
          this.valueChange({top: item.top - 1})
        }
        else if (e.key == 'ArrowDown'){
          this.valueChange({top: item.top + 1})
        }
        else if (e.key == 'ArrowLeft'){
          this.valueChange({left: item.left - 1})
        }
        else if (e.key == 'ArrowRight'){
          this.valueChange({left: item.left + 1})
        }
        /// send to back
        if (e.key == '['){
          if (this.selected == 0){
            return
          }

          if (this.ctrl){
            this.moveIndex(3)
          }
          else {
            this.moveIndex(2)
          }
        }
        /// bring to front
        else if (e.key == ']'){  
          if (this.selected == this.items.length - 1){
            return
          }

          if (this.ctrl){
            this.moveIndex(0)
          }
          else {
           this.moveIndex(1)
          }
        }
      }
      /// undo & redo
      if (e.key == 'z'){
        let tar
        if (this.ctrl){
          if (this.shift){
            tar = this.pointer + 1
          }
          else {
            tar = this.pointer - 1
          }

          if (this.history[tar]){
            this.pointer = tar
            this.historyChange = true /// history trigger
          }
        }
      }
    },
    /// 요소 순서 변경 ///
    moveIndex: function(index){
      const temp = [...this.items]
      const idx = this.selected
      console.log(index, temp, idx)
      /// 맨 앞으로 ///
      if (index == 0){
        const tar = temp.splice(idx, 1)[0]
        temp.push(tar)
        this.selected = this.items.length - 1
      }
      /// 앞으로 ///
      else if (index == 1){
        [temp[idx], temp[idx+1]] = [temp[idx+1], temp[idx]]
        this.selected = idx + 1
      }
      /// 뒤로 ///
      else if (index == 2){
        [temp[idx], temp[idx-1]] = [temp[idx-1], temp[idx]]
        this.selected = idx - 1
      }
      /// 맨 뒤로 ///
      else if (index == 3){
        const tar = temp.splice(idx, 1)[0]
        temp.shift(tar)
        this.selected = 0
      }
      this.items = temp
    },
    removeCtrl: function(){
      this.ctrl = false
    },
    removeShift: function(){
      this.shift = false
    },
    select: function(idx){
      if (this.selected == idx){
        return
      }
      this.selected = idx
    },
    /// S3 파일 삭제 ///
    fileRemoveFromS3: function(url){
      const credentials = JSON.parse(process.env.VUE_APP_AWS_S3_CREDENTIALS)
      const s3 = new AWS.S3(credentials)
      const fileName = url.replace('https://diarypj.s3.ap-northeast-2.amazonaws.com/', '')
      
      s3.deleteObject({
        Bucket: process.env.VUE_APP_AWS_S3_BUCKET,
        Key: fileName.replace('%40', '@')
      })

      return new Promise(resolve => resolve())
    },
    removeItem: async function(){
      const selectedItem = this.items[this.selected]
      if (selectedItem.type == 'image'){
        await this.fileRemoveFromS3(selectedItem.imgUrl)
      }
      this.items.splice(this.selected, 1)
      this.$emit('select', null)
    },
    valueChange: function(payload){
      const tar = {...this.items[this.selected], fontStyle: {...this.items[this.selected].fontStyle}}
      let flag = false
      for (const key of Object.keys(payload)){
        if (tar.fontStyle[key] && tar.fontStyle[key] != payload[key]){
          tar.fontStyle[key] = payload[key]
          flag = true
        }
        else if (tar[key] != payload[key]) {
          tar[key] = payload[key]
          flag = true
        }
      }
      if (flag){
        this.$set(this.items, this.selected, tar)
      }
    },
    /// add mouse-move event when preview was loaded ///
    loadPreview: function(){
      if (this.file){
        const canvas = this.$refs.canvas
        canvas.addEventListener('mousemove', this.mouseMove)
      }
    },
    /// new image, sticker assistant ///
    mouseMove: function(e){
      if (this.mouseX === null){
        this.mouseX = ((e.offsetX - this.$refs.preview.width / 2) / this.canvasWidth) * 100
      }
      if (this.mouseY === null){
        this.mouseY = ((e.offsetY - this.$refs.preview.height / 2) / this.canvasHeight) * 100
      }
      this.mouseX += (e.movementX / this.canvasWidth) * 100
      this.mouseY += (e.movementY / this.canvasHeight) * 100
    }
  },
  watch: {
    initialItems: function(){
      this.init()
    },
    isActive: function(){
      if (this.isActive){
        this.selected = null
      }
    },
    selected: function(){
      this.$emit('select', this.items[this.selected])
    },
    type: function(){
      this.cursorType = this.type
    },
    cursorType: function(){
      if (this.cursorType == 'text'){
        this.$refs.canvas.style.cursor = 'text'
      }
      else if (this.cursorType == 'image' || this.cursorType == 'sticker'){
        this.$refs.canvas.style.cursor = 'grabbing'
      }
      else {
        this.$refs.canvas.style.cursor = 'auto'
      }
    },
    changes: function(){
      if (this.changes){
        this.valueChange(this.changes)
      }
    },
    file: function(){
      const canvas = this.$refs.canvas
      if (!this.file){
        canvas.removeEventListener('mousemove', this.mouseMove)
      }
    },
    items: {deep: true, handler(){
      if (!this.historyChange){
        this.pointer += 1
        this.historyHead = this.pointer
        this.history[this.pointer] = [...this.items]
      }
    }},
    historyChange: function(){
      if (this.historyChange){
        this.items = [...this.history[this.pointer]]
        this.$nextTick(() => {
          this.historyChange = false
        })
      }
    },
    pointer: function(){
      this.$emit('get-history-info', [this.pointer, this.historyHead])
    },
    historyChangeFromMenu: function(){
      if (this.historyChangeFromMenu == 'undo'){
        this.pointer -= 1
      }
      else if (this.historyChangeFromMenu == 'redo'){
        this.pointer += 1
      }
      this.historyChange = true
    }
  },
  mounted: function(){
    this.init()
    this.getCanvasSize()
    window.addEventListener('resize', this.getCanvasSize)
    document.addEventListener('keydown', this.keyAction)
    document.addEventListener('keyup', this.removeCtrl)
  },
  destroyed: function(){
    window.removeEventListener('resize', this.getCanvasSize)
    document.removeEventListener('keydown', this.keyAction)
    document.removeEventListener('keyup', this.removeCtrl)
  }
}
</script>

<style lang="scss">
  #canvas-container {
    // display: flex;
    position: relative;
    height: 100%;
    margin: 3rem;
    background-color: white;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
    overflow: hidden;

    .image-preview {
      max-width: 500px;
      max-height: 500px;
      position: absolute;
      z-index: 987654321;
    }
  }
</style>