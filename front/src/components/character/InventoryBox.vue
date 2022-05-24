<template>
  <div id="inventory-container">
    <item-card
      v-for="item in items" :key="item.itemNo" :tab="tab" :item="item"/>
  </div>
</template>

<script>
import ItemCard from '@/components/character/ItemCard.vue'

export default{
  props:{
    tab: Number,
  },
  components:{
    ItemCard,
  },
  computed: {
    items(){
      return this.$store.state.userItemList.filter(ele => ele.itemCategories == this.tab)
    }
  },
}
</script>
<style lang="scss">
#inventory-container{
    display: flex;
    flex-wrap: wrap;
    padding: 0.5rem;

    & > .item{
        // box-shadow: inset 1px 2px 4px rgba(0, 0, 0, 0.25);
        // background: #fff;
        // text-align: center;
        // padding: 5px 30px;
        // margin: 10px;
        // border-radius: 10px;
        // position: relative;


        & > img{
            width: 50%;
        }
        & > .unused{
            position: absolute;
            background: rgba(0, 0, 0, 0.25);
            width: 100%;
            height: 100%;
            border-radius: 10px;
            left: 0px;
            top: 0px;
            color: #fff;
            font-weight: bold;
            display: table;

            & > div{
                display: table-cell;
                vertical-align: middle;
            }

        }      
    }

}
</style>