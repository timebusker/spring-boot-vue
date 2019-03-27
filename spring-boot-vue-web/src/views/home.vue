<template>
  <el-container class="main">
    <el-aside class="aside" style="text-align: left;width: 230px">
      <el-menu default-active="0-0" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" v-for="(item,index) in list" :key="item.id">
        <el-submenu :index="index + ''">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>{{item.name}}</span>
          </template>
          <el-menu-item @click="handleMenuClick(sub)" :index="index + '-' + subId" v-for="(sub,subId) in item.children" :key="sub.id">{{sub.name}}</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-header class="header" style="height: 80px">
    </el-header>
    <el-main style="padding:0">
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
      return {
        menu: {
          id: 0,
          pid: 0
        },
        list: {}
      }
    },
    created: function () {
      this.queryMenu();
    },
    methods: {
      handleOpen: function (key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose: function (key, keyPath) {
        console.log(key, keyPath);
      },
      queryMenu: function () {
        var _this = this;
        this.getRequest("/api/menu/list", _this.menu).then(response => {
          _this.list = response.data.list;
        }).catch(error => {
          console.log(error)
        });
      },
      handleMenuClick: function (item) {
        console.log(item);
        this.$router.push("/home/menu");
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .main
    flex-wrap wrap
    background red
    width 100%
    height 100%
    .aside
      background #545c64
      height 100%
      float left
    .header
      width calc(100vw - 230px)
      background #25a4bb
</style>
