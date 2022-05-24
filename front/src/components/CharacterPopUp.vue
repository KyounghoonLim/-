<template>
  <div id="char-popup-container" v-if="user">
    <div @click="closePopUp()">

    </div>
    <div class="popup-box">
      <img src="@/assets/close.png" class="close"
      @click="closePopUp()">
      <section class="deco-box">
          <article class="char-box">
            <div class="char">
              <img src="@/assets/character/head.png">

              <!-- 캐릭터 파츠 -->

              <div class="parts background" v-if="this.itemList.itemBackground">
                <img :src="`${this.itemList.itemBackground}`">
              </div>
              <div class="parts head" v-if="this.itemList.itemHair">
                <img :src="`${this.itemList.itemHair}`">
              </div>
              <div class="parts cloth" v-if="this.itemList.itemCloth">
                <img :src="`${this.itemList.itemCloth}`">
              </div>
              <div class="parts deco" v-if="this.itemList.itemDeco">
                <img :src="`${this.itemList.itemDeco}`">
              </div>
              
            </div>
            <div class="char-info">
              <div class="nickname">{{ user.nickName }}</div>
              <div class="tooltip">
                {{ level }} LV
                <progress class="progress-tag" :value="user.doll_likable" max="200"></progress>
                <div class="tooltip-text">
                  <img src="@/assets/heart.png" alt="">
                  {{ user.doll_likable }}/200
                </div>
              </div>
              <div>
                <img src="@/assets/coin.png" alt=""><span>5000P</span><button>포인트 충전</button>
              </div>
              <div>
                <button class="save" @click="charSave()">저장</button>
              </div>
            </div>

          </article>
          <article class="inventory-box">
            <h3>인벤토리</h3>
            <div class="inventory">
              <div class="tab-box">
                <span @click="changeTab(0)" :class="{'tab':true, 'active': tab == 0}">옷</span>
                <span @click="changeTab(1)" :class="{'tab':true, 'active': tab == 1}">머리</span>
                <span @click="changeTab(2)" :class="{'tab':true, 'active': tab == 2}">배경</span>
                <span @click="changeTab(3)" :class="{'tab':true, 'active': tab == 3}">장식</span>
              </div>
              <inventory-box :tab="tab"/>
            </div>
          </article>
          <article>
            <div class="mobile-button">
              <button @click="charSave()">저장</button>
              <button @click="closePopUp()">나가기</button>
            </div>
          </article>
      </section>
    </div>

  </div>
</template>

<script>
import InventoryBox from '@/components/character/InventoryBox.vue'

export default {
	data: () => {
    return {
      tab: 0,
      target: null,
      level: 0,
    }
  },
  props:{
    popupVal: String,
  },
	components:{
    InventoryBox,
	},
  computed:{
    itemList(){
      return this.$store.state.wearItem
    },
    user(){
      return this.$store.state.user
    }
  },
  methods: {
    changeTab(index){
      this.tab = index;
    },
    closePopUp: function(){
      this.$emit('close-pop-up');
    },
    charSave:function(){
      this.$store.dispatch('userItemList')
    }
  },
  created: function(){
    const body = document.querySelector('body')
    body.style.overflow = 'hidden'
  },
  destroyed: function(){
    const body = document.querySelector('body')
    body.style.overflow = 'auto'
  }
}
</script>

<style lang="scss">
@media only screen and (min-width:800px){
  #char-popup-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    //background-color: rgba(0, 0, 0, 0.35);
    z-index: 987654321;

    & > div:nth-child(1){
      position:absolute;
      width: 100%; height: 100%;
      z-index: 0;
      left: 0; top: 0;
      cursor: pointer;
      background-color: rgba(0,0,0,0.35);
    }

    .popup-box {
      position: absolute;
      left: 50%; top: 50%;
      background-color: white;
      width: 50%;
      height: auto;
      align-items: center;
      padding: 3rem;
      border-radius: 10px;
      -webkit-transform: translate(-50%, -50%);
      -moz-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
      -o-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
      z-index: 2;

      .close {
        width: 20px;
        aspect-ratio: 1/1;
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
      }

      & .deco-box{
          display: flex;
          justify-content: space-around;
          height: 100%;


					& > .char-box{
            & > .char-info{
              & > .tooltip{
                position: relative;
                display: block;

                & > .tooltip-text{
                  visibility: hidden;
                  background-color: #eee;
                  padding: 10px 0px;
                  text-align: center;
                  border-radius: 10px;
                  width: 120px;
                  border-radius: 10px;
                  position: absolute;
                  z-index: 1;
                  font-size: 16px;
                }
              }

              & > .tooltip:hover{
                & > .tooltip-text{
                  visibility: visible;
                }
              }
              & > div{

                font-size: 1.5rem;
                // padding: 5px 0px;
                & > .progerr-tag{
                  color: yellow;
                  background-color: yellow;
                }
                ::-webkit-progress-value{
                  background-color: yellow;
                }
                & > progress{
                  color: yellow;

                  & ::-webkit-progress-value{
                    background-color: yellow;
                  }
                }
                & > button{
                  font-size: 1rem;
                  font-weight: 100;
                  margin: 10px;
                  padding: 2px 5px;

                  display: table-cell;
                  vertical-align: middle;
                }
                & > .save{
                  padding: 10px 35%;
                }
              }

              & > .nickname{
                font-size: 1.5rem;
                font-weight: bold;
              }
            }

						& > .char{
							width: 240px;
              height: 240px;
              border: 10px solid black;
							border-radius: 10px;
							text-align: center;
							display: table-cell;
							vertical-align: middle;
              position: relative;

              &:after {
                & {
                  content: " ";
                  display: block;
                  width: 100%;
                  padding-bottom: 100%;
                }
              }

              & > div{
                position: relative;
              }
							& > img{
								width: 100%;
                height: 100%;
                left: 0; top: 0;
								object-fit: contain;
                position: absolute;
                z-index: 1;
							}

              & > .parts{
                position: absolute;
                width: 100%; height: 100%;
                left: 0px;
                top: 0px;
                z-index: 2;

                & > img {
                  & {
                    width: 100%; height: 100%;
                  }
                }
              }

              & > .background{
                //position: absolute;
                //width: 100%; height: 100%;
                //left: 0; top: 0;
                position: absolute;
                z-index: 0;
              }

						}
					}
          & > .inventory-box{
            width: 100%;
          }
          & > article{

            margin: 10px;
            & > .inventory{
              box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
              border-radius: 10px;
              height: 400px;
              overflow-y: scroll;

              & > .tab-box{
                display: flex;
                justify-content: space-around;

                & > .tab{
                  box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
                  width: 100%;
                  text-align: center;
                  padding: 10px;
                  cursor: pointer;

                }
                & > .active {
                  // animation: scale-up 0.1s ease-in forwards;
                  background-color: #93D9CE;
                  color: white;
                  box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
                  border-radius: 10px 10px 0px 0px;
                }
              }
            }
            & > .mobile-button{
              display: none;
            }
          }

      }
    }
  }
}
@media only screen and (max-width:799px){
  #char-popup-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    padding-top: 80px;
    //background-color: rgba(0, 0, 0, 0.35);
    z-index: 20;


    .popup-box {
      position: absolute;
      left: 50%; top: 50%;
      background-color: white;
      width: 100%;
      height: 100%;
      align-items: center;
      padding: 50px 16px 0px 16px;
      border-radius: 10px;
      -webkit-transform: translate(-50%, -50%);
      -moz-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
      -o-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
      z-index: 15;

      .close {
        width: 20px;
        aspect-ratio: 1/1;
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
      }

      & .deco-box{
          // display: flex;
          // justify-content: space-around;
          // height: 100%;
          
					& > .char-box{

						& > .tooltip{
              position: relative;
              display: block;

              & > .tooltip-text{
                visibility: hidden;
                background-color: #eee;
                padding: 10px 0px;
                text-align: center;
                border-radius: 10px;
                width: 120px;
                border-radius: 10px;
                position: absolute;
                z-index: 10;
                font-size: 16px;
              }
            }

            & > .tooltip:hover{
              & > .tooltip-text{
                visibility: visible;
              }
            }
            & > div{

              font-size: 1.5rem;
              // padding: 5px 0px;
              & > .progerr-tag{
                color: yellow;
                background-color: yellow;
              }
              ::-webkit-progress-value{
                background-color: yellow;
              }
              & > progress{
                color: yellow;

                & ::-webkit-progress-value{
                  background-color: yellow;
                }
              }
              & > div{
                & > button{
                  display: none;
                }
              }
            }

            & > .nickname{
              font-size: 1.5rem;
              font-weight: bold;
            }
						& > .char{
							//width: 300px;
							//height: 300px;
							width: 180px;
              height: 180px;
              border: 5px solid black;
							border-radius: 10px;
							text-align: center;
							display: table-cell;
							vertical-align: middle;
              position: relative;

              &:after {
                & {
                  content: " ";
                  display: block;
                  width: 100%;
                  padding-bottom: 100%;
                }
              }

              & > div{
                position: relative;

              }
							& > img{
								width: 100%;
                height: 100%;
                left: 0; top: 0;
								object-fit: contain;
                position: absolute;
                z-index: 1;
							}

              & > .parts{
                position: absolute;
                width: 100%; height: 100%;
                left: 0px;
                top: 0px;
                z-index: 2;

                & > img {
                  & {
                    width: 100%; height: 100%;
                  }
                }
              }

              & > .background{
                //position: absolute;
                //width: 100%; height: 100%;
                //left: 0; top: 0;
                position: absolute;
                z-index: 0;
              }

						}
					}
          & > .inventory-box{
            width: 100%;
          }
          & > article{

            margin: 10px;
            & > .inventory{
              box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
              border-radius: 10px;
              height: 90%;

              & > .tab-box{
                display: flex;
                justify-content: space-around;

                & > .tab{
                  box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
                  width: 100%;
                  text-align: center;
                  padding: 10px;
                  cursor: pointer;

                }
                & > .active {
                  // animation: scale-up 0.1s ease-in forwards;
                  background-color: #93D9CE;
                  color: white;
                  box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
                  border-radius: 10px 10px 0px 0px;
                }
              }
            }

            & > div{
              
              text-align: center;
              & > button{
                width: 45%;
                padding: 20px 0px;
              }
            }
          }

      }
    }
  }
}
</style>