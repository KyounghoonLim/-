<template>
  <div id="stt-container">
    <button :class="['rec-btn', state ? 'rec-active':'rec-inactive']" @click="changeState"
     :title="state ? '녹음종료':'녹음하기'" >
      <img class="rec-img" :src="state ? require('../../../assets/Editor/on-record.png'):require('../../../assets/Editor/off-record.png')">
      REC
    </button>
    <av-media v-if="state" :media="stream" type="frequ"
      :canv-width="30" :canv-height="30"
      :frequ-line-cap="true" :frequ-lnum="3"
      :line-width="6" :line-color="'#93D9CE'" />
    <vue-speech :lang="'ko-KR'" @onTranscriptionEnd="speech" v-if="state && stream" :style="{display: 'none'}" />
  </div>
</template>

<script>
import Vue from 'vue'
import AudioVisual from 'vue-audio-visual'
import VueSpeech from 'vue-speech'

Vue.use(AudioVisual)
Vue.use(VueSpeech)

export default {
  data: function(){
    return {
      stream: null,
      text: null,
      state: false
    }
  },
  props: {
    selected: Object
  },
  methods: {
    changeState: function(){
      this.state = !this.state
    },
    speech: function({ transcription }){
      this.text = transcription.join(' ')
      console.log(this.text)
    },
    streamVisualize: async function(){
      console.log('start to rec')
      const stream = await navigator.mediaDevices.getUserMedia({audio: true, video: false})
      console.dir(stream)
      this.stream = stream
    },
    stopStream: function(){
      this.stream.getAudioTracks()[0].stop()
      this.stream.getAudioTracks()[0].enabled = false
    }
  },
  watch: {
    state: function(){
      if (this.state){
        this.streamVisualize()
      }
      else {
        this.stopStream()
        this.stream == null
      }
    },
    text: function(){
      const text = this.selected.content + this.text
      this.$parent.$parent.$emit('value-change', {content: text})
    }
  }
}
</script>

<style lang="scss">
  #stt-container {
    position: absolute;
    bottom: -110px;
    background-color: white;
    width: 100px;
    height: 100px;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .rec-btn {
      display: flex;
      justify-content: space-evenly;
      align-items: center;
      width: 50px;
      height: 20px;
      padding: 0;
      margin: 0.25rem;
      border: none;
      border-radius: 10px;
      outline: none;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      font-weight: bold;
      background-color: white;
      cursor: pointer;

      &.rec-active {
        border: 2px rgb(255, 75, 75) solid;
        color: rgb(255, 75, 75);
      }

      &.rec-inactive {
        border: 2px #555555 solid;
        color: #555555;
      }

      .rec-img {
        width: 10px;
        aspect-ratio: 1/1;
      }
    }
  }
</style>