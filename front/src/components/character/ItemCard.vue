<template>
  <div id="item-box-container" :class="{'equiped':item.wearFlag == 1}"
    @click="clickHandler">
    <div v-if="item.wearFlag == -1" class="not"/>
    <img :src="`${item.itemImage}`">
    {{ item.itemName }}
  </div>
</template>
<script>

export default {
  props:{
    item: Object
  },
  methods:{
    clickHandler: function(){
      if (this.item.wearFlag == 0){
        this.dressUp()
      }
    },
    dressUp(){
      this.$store.dispatch('changeItem', this.item)
    }
  }
}
</script>

<style lang="scss">
#item-box-container{
    box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.25);
    background: #fff;
    text-align: center;
    padding: 5px 20px;
    margin: 0.5rem;
    border-radius: 10px;
    position: relative;
    width: 30%;
    cursor: pointer;
    overflow: hidden;

    &.equiped {
      outline: 5px #93D9CE solid;
    }

    .not {
      background-color: rgba(0, 0, 0, 0.35);
      width: 100%;
      height: 100%;
      position: absolute;
      top: 0;
      left: 0;
    }

    img{
      width: 100%;
      z-index: -1;
    }

}
</style>