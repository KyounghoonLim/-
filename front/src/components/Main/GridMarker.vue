<template>
  <div :class="['marker', `${date}`]">
    <span class="marker-arrow" />
    {{ date }}
  </div>
</template>

<script>
export default {
  props: {
    date: String
  },
}
</script>

<style lang="scss">
  .marker {
      position: absolute;
      right: 0%;
      background-color: #93D9CE;
      color: white;
      padding: 0.5rem 1rem;
      box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
      border-radius: 10px;
      // opacity: 0;

      .marker-arrow {
        position: absolute;
        top: 30%;
        left: -15%;
        border-left: 0px;
        border-right: 20px #93D9CE solid;
        border-top: 10px transparent solid;
        border-bottom: 10px transparent solid;
      }
    }
</style>