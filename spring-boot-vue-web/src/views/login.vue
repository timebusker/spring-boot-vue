<template>
  <el-container class="container">
    <el-header class="header">
      这是头部区
    </el-header>
    <el-aside class="aside">
      这是侧边栏
    </el-aside>
    <el-main class="main">
      这是主要内容展示区
    </el-main>
    <el-footer class="footer">
      这时底部
    </el-footer>
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
  .header
    width 100%
    height 15%
    background yellow
  .aside
    height auto
    background #25a4bb
    float left
  .main
    float right
    width 70%
    height auto
    background #eee
  .footer
    height 10%
    width 100%
    background #ccc
</style>
