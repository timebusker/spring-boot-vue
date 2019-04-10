<template>
  <div style="background: #b4bccc;text-align: left">
    <div class="user">
      <el-radio-group v-model="collapse" style="display: inline-block;margin: 5px 5px 15px" size="mini" @change="handleCollapse">
        <el-radio-button :label="false" v-show="collapse"><span class="iconfont icon-zhankai2">&nbsp;</span></el-radio-button>
        <el-radio-button :label="true" v-show="!collapse"><span class="iconfont icon-shouqi4"></span></el-radio-button>
      </el-radio-group>
      <!-- 设置 line-height:30px; 可让文字自动水平居中-->
      <el-breadcrumb separator="/" style="display: inline-block;margin: 5px 5px 15px;line-height:30px;">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{breadcrumb}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="tab-view">
      <div style="display:inline-block;float:right;padding-right: 10px;line-height: 20px">
        {{time}}
      </div>
    </div>
  </div>
</template>

<script>
  export default{
    name: "adminHeader",
    props: ['isCollapse'],
    data: function () {
      return {
        collapse: true
      }
    },
    computed: {
      time(){
        var value = new Date();
        var year = value.getFullYear();
        var month = (value.getMonth() + 1);
        var day = (value.getDate());
        var hour = (value.getHours());
        var minutes = (value.getMinutes());
        var seconds = (value.getSeconds());
        return year + '年' + month + '月' + day + '日 ' + hour + '时' + minutes + '分';
      },
      breadcrumb(){
        return this.$store.state.currentMenuName;
      }
    },
    created: function () {
      this.collapse = this.isCollapse;
    },
    methods: {
      handleCollapse: function (value) {
        this.$emit('collapse', value)
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .user
    display flex
</style>
