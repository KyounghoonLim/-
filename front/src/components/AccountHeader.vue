<template>
  <header class="login-header">
    <div class="header-content">
      <img src="../assets/character.png" alt="">
      <div class="typed-box">
        <h1 class="typed"></h1>
      </div>
    </div>
    <note-top />
  </header>
</template>

<script>
import NoteTop from './NoteTop.vue'
import Typed from 'typed.js'

export default {
  components: {
    NoteTop
  },
  methods: {
    typing: function(){
      // eslint-disable-next-line no-unused-vars
      const typed = new Typed('.typed', {
        strings: ['다키에 오신 것을 환영합니다', '새로운 일기를 작성해보세요'],
        typeSpeed: 50,
        backSpeed: 30,
        backDelay: 1000,
        showCursor: false,
        loop: true,
        loopCount: 2
      })
    }
  },
  mounted: function(){
    this.typing()
  }
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  header {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    background-image: url('../assets/bg.png');
    background-size: cover;
    height: 35vh;

    .header-content {
      display: flex;
      align-items: flex-end;
      margin: 0 3rem;

      img {
        width: 150px;
        height: 150px;
        aspect-ratio: 3/4;
      }

      .typed-box {
        display: flex;
        max-width: 50%;
        
        .typed {
          position: relative;
          margin: 0;
          font-size: 4rem;
          font-weight: bold;
          color: white;
          text-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          word-break: keep-all;
        }

        @keyframes blink {
          from {opacity: 0;}
          to {opacity: 1;}
        }

        .typed::after {
          content: '|';
          font-weight: normal;
          font-size: 4.2rem;
          animation: blink 0.7s infinite alternate;
        }
      }
    }
  }
}
@media only screen and (max-width:799px){
    header {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    background-image: url('../assets/bg.png');
    background-size: cover;
    height: 25vh;

    .header-content {
      display: flex;
      align-items: flex-end;
      margin: 0 3rem;

      img {
        // width: 150px;
        // aspect-ratio: 3/4;
        display: none;
      }

      .typed-box {
        display: flex;
        max-width: 50%;
        
        .typed {
          position: relative;
          margin: 0;
          font-size: 4rem;
          font-weight: bold;
          color: white;
          text-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          word-break: keep-all;
        }

        @keyframes blink {
          from {opacity: 0;}
          to {opacity: 1;}
        }

        .typed::after {
          content: '|';
          font-weight: normal;
          font-size: 4.2rem;
          animation: blink 0.7s infinite alternate;
        }
      }
    }
  }
}
</style>