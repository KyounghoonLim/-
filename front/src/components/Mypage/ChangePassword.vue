<template>
  <div id="change-password-container">
		<div @click="closePopUp">

    </div>
    <div class="popup-box">
      <img src="../../assets/close.png" class="close"
      @click="closePopUp">

			<div class="content">
				<section>
					<h1>비밀번호 변경</h1>
					<title>비밀번호 변경</title>
				</section>
				<section>
					<div>
						<p>기존 비밀번호</p>
						<input type="text">
					</div>
					<div>
						<p>비밀번호</p>
						<input type="text">
					</div>
					<div>
						<p>비밀번호 확인</p>
						<input type="text">
					</div>
				</section>
				<section>
					<button>수정</button>
				</section>
			</div>
    </div>
  </div>
</template>

<script>


export default {
  props: {
  },
  computed: {

  },
  methods: {
    closePopUp: function(){
        this.$emit('close-pop-up', false);
    }
  }
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  #change-password-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.35);
    z-index: 3;

    & > div:nth-child(1){
      position:absolute;
      width: 100%; height: 100%;
      z-index: 0;
      left: 0; top: 0;
      cursor: pointer;
      background-color: rgba(0,0,0,0.35);
    }
    .popup-box {
      background-color: white;
      width: 40%;
      height: 50vh;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      padding: 3rem;
      border-radius: 10px;
      position: relative;

      .close {
        width: 20px;
        aspect-ratio: 1/1;
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
      }

      .popup-header {
        align-self: flex-start;
      }

			& > .content{
				width: 100%;

				& > section{
					text-align: center;

					& > div{
						display: flex;
						justify-content: space-between;
						font-weight: bold;

						& > input{
            width: 70%;
            border: none;
            border-bottom: 1px #cccccc solid;
            font-size: 1.5rem;
            margin: 0 1rem;
            padding: 0.25rem 0.5rem;
						}
					}

					& > h1{
						text-align: left;
					}
				}
			}
    }
  }
}
@media only screen and (max-width:799px){
  #change-password-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.35);
    z-index: 3;

    & > div:nth-child(1){
      position:absolute;
      width: 100%; height: 100%;
      z-index: 0;
      left: 0; top: 0;
      cursor: pointer;
      background-color: rgba(0,0,0,0.35);
    }
    .popup-box {
      background-color: white;
      height: 50vh;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
      padding: 3rem;
      border-radius: 10px;
      position: relative;

      .close {
        width: 20px;
        aspect-ratio: 1/1;
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
      }

      .popup-header {
        align-self: flex-start;
      }

			& > .content{
				width: 100%;

				& > section{
					text-align: center;

					& > div{
						display: flex;
						justify-content: space-between;
						font-weight: bold;

						& > input{
            width: 70%;
            border: none;
            border-bottom: 1px #cccccc solid;
            font-size: 1.5rem;
            margin: 0 1rem;
            padding: 0.25rem 0.5rem;
						}
					}

					& > h1{
						text-align: left;
					}
				}
			}
    }
  }
}

</style>