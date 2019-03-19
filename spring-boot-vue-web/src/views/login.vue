<template>
  <el-container class="container">
    <div class="aside">
      这是侧边栏
    </div>
    <div class="context">
      <div class="header">
        这是头部区
      </div>
      <div class="main">
        这是主要内容展示区
      </div>
      <div class="footer">
        这时底部
      </div>
    </div>
  </el-container>
</template>

<script>
  export default {
    name: 'login'
  }
</script>

<style lang="stylus" scoped>
  .container
    display flex
    flex-wrap wrap
    height 100%
    width 100%
  .aside
    width 15%
    height 100%
    background #25a4bb
    float left
  .context
    width 85%
    float right
  .header
    width 100%
    height 60px
    background yellow
  .main
    width 100%
    height 0
    padding-bottom: 53%;
    background #eee
  .footer
    height 30px
    width 100%
    background #ccc
</style>
