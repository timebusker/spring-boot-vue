<template>
  <div class="home">
    <admin-aside class="aside" v-bind:isCollapse="isCollapse">
    </admin-aside>
    <div class="main-container">
      <admin-header class="header" v-bind:isCollapse="isCollapse" v-on:collapse="handleCollapse">
      </admin-header>
      <admin-main class="main">
      </admin-main>
    </div>
  </div>
</template>

<script>
  import adminAside from "./common/admin-aside";
  import adminHeader from "./common/admin-header";
  import adminMain from "./common/admin-main.vue";
  export default {
    components: {
      adminAside,
      adminHeader,
      adminMain
    },
    name: 'home',
    data: function () {
      return {
        isCollapse: false
      }
    },
    methods: {
      handleCollapse: function (value) {
        this.isCollapse = value;
      }
    }
  }
</script>

<!-- 严格控制页面布局 ：100vh/100vw:高度/宽度等分100份。类似100%，但可以和像素单位计算-->
<!-- flex 嵌套布局-->
<style lang="stylus" scoped>
  .home
    display flex
    background red
    max-width 100vw
    max-height 100vh
    padding 0px
    overflow hidden
    .aside
      background #545c64
      width 200px
      height 100vh
    .main-container
      width calc(100vw - 200px)
      height 100vh
      background white
      .header
        width 100%
        height 70px
        background #25a4bb
      .main
        background green
        width 100%
        height calc(100vh - 70px)
</style>
