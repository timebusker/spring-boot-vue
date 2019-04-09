<template>
  <div style="text-align: left;">
    <div style="height: 60px;width: 100%;text-align: center">
      <img src="./../../assets/header.png" style="width: 60px;border-radius: 30px"/>
    </div>
    <el-menu :collapse="isCollapse" unique-opened router style="border: none;"
             background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" v-for="(item,index) in routes" :key="item.id">
      <el-submenu :index="index+''">
        <template slot="title">
          <span class="iconfont" :class="item.icon"></span>
          <span>{{item.name}}</span>
        </template>
        <el-menu-item :index="sub.path" :key="sub.id" v-for="(sub) in item.children">
          <span class="iconfont" :class="sub.icon"></span>
          <span>&nbsp;{{sub.name}}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
  import ElContainer from "../../../node_modules/element-ui/packages/container/src/main";
  
  export default{
    components: {ElContainer},
    name: "adminAside",
    props: ['isCollapse'],
    data: function () {
      return {}
    },
    computed: {
      routes () {
        return this.$store.state.routes
      }
    },
    created: function () {
    },
    methods: {}
  }
</script>

<style lang="stylus" scoped>

</style>
