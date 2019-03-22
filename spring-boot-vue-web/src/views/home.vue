<template>
  <el-container class="main">
    <el-aside class="aside" style="text-align: left;width: 230px">
      <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>导航一</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>导航二</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-header class="header" style="height: 80px">
    
    </el-header>
    <el-main class="main">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
  import ElContainer from "../../node_modules/element-ui/packages/container/src/main";
  import ElAside from "../../node_modules/element-ui/packages/aside/src/main";
  import ElMenu from "../../node_modules/element-ui/packages/menu/src/menu";
  import ElHeader from "../../node_modules/element-ui/packages/header/src/main";
  import ElMain from "../../node_modules/element-ui/packages/main/src/main";
  export default {
    components: {
      ElMain,
      ElHeader,
      ElMenu,
      ElAside,
      ElContainer
    },
    name: 'home',
    data: function () {
      return {}
    },
    created: function () {
      this.$router.push("/home/menu");
    },
    methods: {
      handleOpen: function (key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose: function (key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .main
    display flex
    flex-wrap wrap
    background red
    width 100%
    height 100%
    .aside
      background #545c64
      height 100%
    .header
      width calc(100vw - 230px)
      background #25a4bb
    .main
      width calc(100vw - 230px)
      background #ffffff
</style>
