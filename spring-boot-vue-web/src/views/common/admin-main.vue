<template>
  <el-container>
    <!-- 使用 keep-alive 进行组件状态保持-->
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </el-container>
</template>

<script>
  import ElContainer from "../../../node_modules/element-ui/packages/container/src/main";
  export default{
    components: {ElContainer},
    name: "adminMain"
  }
</script>

<style lang="stylus" scoped>

</style>
