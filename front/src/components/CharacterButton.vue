<template>
  <div id="character-container" v-if="itemList">
    <div @click="openPopup()">
      <img src="@/assets/character/head.png">
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

    <div class="character-popup"
			:class="{ active : popupView }"
			>
      <character-pop-up @close-pop-up="openPopup()"	@change-item="getCharItem()" />
    </div>
  </div>
</template>
<script>
import CharacterPopUp from '@/components/CharacterPopUp.vue'

export default {
	name: 'CharacterButton',
	data: () =>{
		return {
			popupView : false,
		}
	},
	components:{
		CharacterPopUp,
	},
	computed:{
		itemList(){
			return this.$store.state.wearItem
		}
	},
	methods:{
		openPopup(){
			this.popupView = (this.popupView) ? false : true;
		}
	},
}
</script>
<style lang="scss">
	@media only screen and (min-width:800px){
    #character-container{
      position: fixed;
      bottom: 5%;
      right: 2%;
			z-index: 987654321;
			cursor: pointer;

			& > div{
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
			}

      & > .character-popup{
        & {
          opacity: 0;
          display: none;
          visibility: hidden;
        }

        &.active {
          opacity: 1;
          display: block;
          visibility: visible;
        }
      }
    }
	}
	@media only screen and (max-width:799px){
		#character-container{
      position: fixed;
      bottom: 0;
      right: -30px;

			& > div{
				& > img{
					width: 80%;
					height: 80%;
				}
				& > .parts{
					position: absolute;
					// width: 80%; height: 80%;
					left: 0px;
					top: 0px;
					z-index: 2;

					& > img {
						& {
							width: 80%; height: 80%;
						}
					}
				}
			}

      & > .character-popup{
        & {
          opacity: 0;
          display: none;
          visibility: hidden;
        }

        &.active {
          opacity: 1;
          display: block;
          visibility: visible;
        }
      }
    }
	}
</style>
