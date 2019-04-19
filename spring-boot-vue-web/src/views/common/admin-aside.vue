<template>
  <!-- overflow-y: auto;overflow-x: hidden; 纵向滚动条可以展示-->
  <div style="text-align: left;overflow-y: auto;overflow-x: hidden;">
    <div style="height: 60px;width: 100%;text-align: center">
      <img src="./../../assets/header.png" style="width: 60px;border-radius: 30px"/>
    </div>
    <el-menu :collapse="isCollapse" unique-opened router style="border: none;" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" v-for="(item,index) in routes" :key="item.id">
      <el-submenu :index="index+''">
        <template slot="title">
          <span class="iconfont" :class="item.icon"></span>
          <span>{{item.name}}</span>
        </template>
        <template v-for="(sub) in item.children">
          <el-menu-item :index="sub.path" :key="sub.id" v-if="sub.isFrame === 0">
            <span class="iconfont" :class="sub.icon"></span>
            <span>&nbsp;{{sub.name}}</span>
          </el-menu-item>
          <!--控制外部跳转连接-->
          <a v-else :href="sub.path" target="_blank" rel="noopener norefferrer">
            <!--Item采用空值，保证样式统一-->
            <el-menu-item>
              <span class="iconfont" :class="sub.icon"></span>
              <span>&nbsp;{{sub.name}}</span>
            </el-menu-item>
          </a>
        </template>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
  import ElContainer from "../../../node_modules/element-ui/packages/container/src/main";

  export default {
    components: {ElContainer},
    name: "adminAside",
    props: ['isCollapse'],
    computed: {
      routes() {
        return this.$store.state.routes
      }
    }
  }
</script>

<style lang="stylus" scoped>

</style>
