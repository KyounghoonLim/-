<template>
  <div id="complete-box">
    <span class="complete-messages">
      <h3>가입이 완료되었습니다!</h3>
      <h5>다키에서 첫 일기를 작성해보세요</h5>
    </span>
    <button @click="goToMain">완료</button>
  </div>
</template>

<script>
export default {
  methods: {
    goToMain: function(){
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="scss">
  #complete-box {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    h3 {
      font-size: 2.5rem;
    }
    h5 {
      font-size: 1.75rem;
    }
  }
</style>