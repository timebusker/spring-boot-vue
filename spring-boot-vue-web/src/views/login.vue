<template>
  <el-container>
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
  .header
    width 100%
    background yellow
  .aside
    width 20%
    background #25a4bb
  .main
    background #333333
  .footer
    background black
</style>
