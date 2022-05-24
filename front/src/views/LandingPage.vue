<template>
  <div id="landingpage-container">
    <header class="main">
      <div class="image">
        <img class="diary" src="@/assets/landing/diary.png"/>
      </div>
      <div class="text">
        <div>
          <span>다키에서</span>
          <br>
          <span>일기쓰기</span>
        </div>
        <button @click="goToLogin">로그인</button>
      </div>
      <div class="mobile-img">
        <img src="@/assets/logo.png" alt="">
      </div>
    </header>
    <section class="bgcolor content-padding">
      <div class="text">
        <span>매일 기록하는 하루일과</span>
        <br>
        <span>이제 더 쉽고 예쁘게</span>
      </div>
      <div class="image">
        <img src="@/assets/landing/editor.gif" alt="">
      </div>
      <img class="character" src="@/assets/character.png" alt="">
    </section>
    <section class="content-padding edit-bg">
      <article class="text-left">
				<img src="@/assets/landing/pencil.png" alt="">
        <span>캐릭터를 육성하며</span>
        <br>
        <span>매일매일 일기 습관 생성</span>
      </article>
      <article class="text-right">
				<img src="@/assets/landing/voice.png" alt="">
        <span>타이핑조차 귀찮을땐</span>
        <br>
        <span>음성인식을 통한 일기작성</span>
      </article>
      <article class="text-left">
				<img src="@/assets/landing/coin.png" alt="">
        <span>포인트를 모으고</span>
        <br>
        <span>캐릭터를 꾸며보아요</span>
      </article>
    </section>
    <section class="bgcolor content-padding">
      <div class="text"><span>다키와 함께 일기써요</span></div>
      <div><img class="logoimg" src="@/assets/landing/logo.png" alt=""></div>
      <div class="door_upper">
        <img src="@/assets/landing/door_upper.png" alt="">
      </div>
    </section>
    <section class="content-padding bottom-section">
      <div class="door_botton">
        <img src="@/assets/landing/door_botton.png" alt="">
      </div>
      <div>
        <button class="start"
          @click="goToLogin">
          시작하기
        </button>
      </div>
    </section>

    <footer>
        <span>contect us</span>
        <br>
        <span>부산 강서구 녹산산업중로 333</span>
        <span>Copyright ⓒ팀이름</span>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'LandingPage',
  methods:{
    goToLogin:function(){
      this.$router.push({ name: 'Login' })
    }
  }
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  #landingpage-container{    
    width:100%;
    .main{
      & {
        height:100vh;
        background-image: url("@/assets/landing/paper_bg.png");
        padding-left:15%;
        padding-right:15%;
        padding-top: 5%;
        display: flex;
        justify-content: space-between;
      }
      & .image{
        // float: left;

        .diary{
          width: 80%;
        }
      }
      .text{
        // float: right;
        font-family: kyobo;
        font-weight: 400;
        font-size: 64px;
      }
      & > .mobile-img{
        display: none;
      }
    }
    .content-padding{
      padding-left: 15%;
      padding-right: 15%;
      position: relative;
      text-align: center;

      .text-left{
        text-align: left;
        font-family: kyobo;
        font-weight: 400;
        font-size: 2.5rem;
        margin: 10% 0% 10% 0%;

        & > span{
          z-index: 2;

        }
        & > img{
          z-index: 1;
          position: absolute;
          width: 20%;
        }
      }
      .text-right{
        text-align: right;
        font-family: kyobo;
        font-weight: 400;
        font-size: 2.5rem;
        margin: 10% 0% 10% 0%;
        & > span{
          z-index: 2;

        }
        & > img{
          z-index: 1;
          position: absolute;
          width: 20%;
        }
      }
    }

    .bgcolor{
      background-color:#93D9CE;
      display: flex;
      justify-content: space-between;
      align-items: center;
      position: relative;
      font-size:0;
      z-index: 2;
      .text{
        font-size: 48px;
        font-weight: 400;
        color: #fff;
        text-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
        font-family: kyobo;
      }
      .image{

        img{
          width: 500px;
          margin: 50px;
        }
      }
      .character{
        width: 15%;
        position: absolute;
        transform: translate(800px,150px);
      }
    }

    .start{
      display: inline-block;
      margin-right: auto;
      margin-left: auto;
      margin-top: 5%;
      margin-bottom: 5%;
      margin: 0;
    }
    .bottom-section{
      padding-top: 300px;
      padding-bottom: 50px;
    }
    .logoimg{
      padding: 20%;
    }
    .door_upper{
      position: absolute;
      bottom: 0;
      & > img {
        width: 100px;
      }
    }
    .door_botton{
      position: absolute;
      top: 0%;
      & > img{
        width: 22px;
      }
    }
    button{
      font-size: 1.25rem;
      font-weight: bold;
      border-radius: 10px;
      border: 1px #93D9CE solid;
      color: #93D9CE;
      background-color: white;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      // margin: 3rem 0 0;
      margin: 0;
      padding: 1rem 5rem;
      cursor: pointer;

      &:hover {
        background-color: #93D9CE;
        color: white;
      }
    }
    footer{
      background-color: #d8d8d8;
    }
  }
}
@media only screen and (max-width:799px){

  @font-face{
  font-family : 'kyobo';
  src : url("@/assets/landing/Kyobo Handwriting 2019.ttf")
  }
  button{
    font-size: 1.25rem;
    font-weight: bold;
    border-radius: 10px;
    border: 1px #93D9CE solid;
    color: #93D9CE;
    background-color: white;
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
    // margin: 3rem 0 0;
    margin: 0;
    padding: 1rem 5rem;
    cursor: pointer;

    &:hover {
      background-color: #93D9CE;
      color: white;
    }
  }
  #landingpage-container{
    width: 100%;

    & > .main{
      background-color: #FED771;
      height: 100vh;
      top: 0;
      // position: relative;
      // height: 100%;
      & > .image{
        display: none;
      }
      & > .text{
        // float: right;
        font-family: kyobo;
        font-weight: 400;
        font-size: 64px;
        // margin:0% 20%;
        padding: 20%;
        color: #fff;
      }
      & > .mobile-img{
        bottom: 0;
        right: 0;
        position: absolute;
        text-align: right;
        & > img{
          width: 300px;
        }
      }
    }

    & > .bgcolor{
      background-image: url("@/assets/landing/paper_bg.png");
      font-family: kyobo;
      text-align: center;
      font-size: 32px;
      padding: 20px;

      & > .door_upper{
        display: none;
      }

      & > .image{
        & > img{
          width: 400px;
        }
      }
    }

    & > .bottom-section{
      & > .door_botton{
        display: none;
      }
    }

    & > .edit-bg{
      
      text-align: center;
      // margin-left: auto;
      & > article{
        padding: 10%;
        & > img{
          width: 100px;
          margin: auto;
          display: block;
          text-align: center;
        }
        & > span{
          font-size: 24px;
          font-family: kyobo;
        }
      }
    }
    & > .bottom-section{
      margin: 10%;
      text-align: center;
    }

    & > footer{
      text-align: center;
    }
  }
}

</style>