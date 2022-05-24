<template>
  <div class="menu-bar">
    <label :class="[{'clicked': menu == 'text'}, 'text']" title="텍스트" for="text"
     @click="menuClickHandler" />
    <input type="radio" id="text" value="text" v-model="menu">

    <label :class="[{'clicked': menu == 'mic'}, 'mic']" title="음성녹음:Beta" for="mic"
     @click="menuClickHandler">
      <speech-to-text v-if="menu == 'mic' && selected" :selected="selected" />
    </label>
    <input type="radio" id="mic" value="mic" v-model="menu">

    <label :class="[{'clicked': menu == 'image'}, 'image']" title="이미지" for="image"
     @click="menuClickHandler" />
    <input type="radio" id="image" value="image" v-model="menu">
    <image-uploader v-show="false" @input="fileUploadToS3" 
     :outputFormat="'file'" :id="'file-input'" :maxWidth="500" :maxHeight="500" :preview="false" />

    <label :class="[{'clicked': menu == 'sticker'}, 'sticker']" :title="menu == 'sticker' ? false:'스티커'" for="sticker"
     @click="menuClickHandler">
      <div v-if="menu == 'sticker' && isActive && !file" class="popup-container" @click="closePopUp" />
      <transition name="swipe" class="test">
        <sticker-loader v-if="menu == 'sticker' && isActive && !file" @select-sticker="selectSticker" />
      </transition>
    </label>
    <input type="radio" id="sticker" value="sticker" v-model="menu">
    
    <text-options v-if="menu == 'text'" :selected="selected" />

    <div class="do-box">
      <button :class="['undo', {'active':historyInfo[0] > 0}]" title="undo" 
       @click="historyChange('undo')" />
      <button :class="['redo', {'active':historyInfo[1] > historyInfo[0]}]" title="redo" 
       @click="historyChange('redo')" />
    </div>

    <button class="submit-btn" @click="submitHandler">
      작성 완료
    </button>
  </div>
</template>

<script>
import TextOptions from './TextOptions.vue'
import SpeechToText from './SpeechToText.vue'
import ImageUploader from 'vue-image-upload-resize'
import StickerLoader from './StickerLoader.vue'
import AWS from 'aws-sdk'
import Swal from 'sweetalert2'

export default {
  data: function(){
    return {
      menu: null,
      isActive: false,
      file: null
    }
  },
  props: {
    selected: Object,
    isCreated: Boolean,
    historyInfo: Array
  },
  components: {
    TextOptions,
    ImageUploader,
    StickerLoader,
    SpeechToText
  },
  computed: {
    user: function(){
      return this.$store.state.user
    }
  },
  methods: {
    menuClickHandler: function(e){
      if (this.menu == e.target.htmlFor){
        e.preventDefault();
        this.menu = null
        this.isActive = false
      }
      else {
        this.isActive = true
        if (e.target.htmlFor == 'image'){
          document.querySelector('#file-input').click()
        }
      }
    },
    selectSticker: function(sticker){
      this.file = sticker
    },
    closePopUp: function(e){
      if (e.target.className == 'popup-container'){
        this.menu = null
      }
    },
    historyChange: function(payload){
      if ((payload == 'undo' && this.historyInfo[0] <= 0) || (payload == 'redo' && this.historyInfo[1] <= this.historyInfo[0])){
        return
      }
      this.$emit('history-change', payload)
    },
    submitHandler: function(){
      this.$emit('submit')
    },
    fileUploadToS3: async function(file){
      const body = document.querySelector('body')
      body.style.cursor = 'wait'

      const credentials = JSON.parse(process.env.VUE_APP_AWS_S3_CREDENTIALS)
      const s3 = new AWS.S3(credentials)

      const params = {
        Bucket: process.env.VUE_APP_AWS_S3_BUCKET,
        Key: `${this.user.email}/${file.name}`,
        ACL: 'public-read',
        Body: file,
        ContentType: file.type,
      }

      await s3.upload(params, (err, data) => {
        if (err){
          console.dir('에러!', err)
        }
        else {
          this.file = data.Location
        }
      })

      body.style.cursor = 'auto'
    }
  },
  watch: {
    selected: function(){
      if (this.selected){
        this.menu = this.selected.type
        if (this.selected.type == 'image' || this.selected.type == 'sticker'){
          this.menu = null
        }
      }
      else {
        this.menu = null
      }
      this.isActive = false
    },
    menu: function(){
      if (this.menu != 'mic'){
        this.$emit('active-menu', {menu: this.menu, isActive: this.isActive})
      }
      else {
        if (!this.selected){
          Swal.fire({
            icon: 'info',
            text: '입력할 텍스트 박스를 선택해주세요'
          })
          .then(() => {
            this.menu = null
          })
        }
        else if (this.selected.type != 'text'){
          Swal.fire({
            icon: 'info',
            text: '텍스트 박스에만 사용 가능합니다'
          })
          .then(() => {
            this.menu = null
          })
        }
      }
    },
    file: function(){
      if (this.file){
        this.$nextTick(() => {
          this.$emit('image-upload', this.file)
        })
      }
      else {
        this.menu = null
      }
    },
    isCreated: function(){
      if (this.isCreated){
        this.isActive = false
        this.file = null
        if (this.file){
          this.file = null
        }
      }
    }
  }
}
</script>

<style lang="scss">
  .menu-bar {
    width: 100%;
    height: 70px;
    background-color: white;
    border: 1px #cccccc solid;
    display: flex;
    align-items: center;

    label {
      width: 40px;
      height: 40px;
      background-size: 30px 30px;
      background-repeat: no-repeat;
      background-color: transparent;
      background-position: center;
      border: none;
      margin: 1rem;
      border-radius: 10px;
      position: relative;
      cursor: pointer;

      &:active {
        background-color: #777777;
      }
      
      &.clicked {
        background-color: #cccccc;
        z-index: 9;
      }
    }

    input[type="radio"], input[type="checkbox"], input[type="file"]{
      display: none;
    }

    .side-options {
      height: 100%;
      display: flex;
      align-items: center;
      border-left: 1px #cccccc solid;
    }

    .text {
      background-image: url('../../../assets/Editor/text.png');
    }

    .mic {
      background-image: url('../../../assets/Editor/mic.png');
    }

    .image {
      background-image: url('../../../assets/Editor/image.png');

    }

    .sticker {
      background-image: url('../../../assets/Editor/sticker.png');

      & > * {
        cursor: auto
      }

      @keyframes swipe {
        from {
          opacity: 0;
          transform: scaleY(0);
          transform-origin: top;
        }
        to {
          opacity: 1;
          transform: scaleY(1);
          transform-origin: top;
        }
      }

      .swipe-enter-active {
        animation: swipe 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards;
        z-index: 987654321;
      }
      
      .swipe-leave-active {
        animation: swipe 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards reverse;
        z-index: 987654321;
      }

      .popup-container {
        position: fixed;
        width: 100vw;
        height: 100vh;
        top: 0;
        left: 0;
        z-index: 1;
      }
    }

    .do-box {
      display: flex;
      margin-left: auto;
      align-items: center;

      .undo {
        border: none;
        background-color: transparent;
        width: 20px;
        height: 20px;
        margin: 0 0.75rem;
        background-image: url('../../../assets/Editor/undo-inactive.png');
        background-size: cover;

        &.active {
          cursor: pointer;
          background-image: url('../../../assets/Editor/undo-active.png');
        }
      }

      .redo {
        border: none;
        background-color: transparent;
        width: 20px;
        height: 20px;
        margin: 0 0.75rem;
        background-image: url('../../../assets/Editor/redo-inactive.png');
        background-size: cover;

        &.active {
          cursor: pointer;
          background-image: url('../../../assets/Editor/redo-active.png');
        }
      }
    }

    .submit-btn {
      font-size: 1rem;
      font-weight: bold;
      border-radius: 10px;
      border: 1px #93D9CE solid;
      color: #93D9CE;
      background-color: white;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      margin: 1.5rem;
      padding: 0.5rem 0.75rem;
      cursor: pointer;

      &:hover, &:focus {
        outline: none;
        background-color: #93D9CE;
        color: white;
      }
    }
  }
</style>