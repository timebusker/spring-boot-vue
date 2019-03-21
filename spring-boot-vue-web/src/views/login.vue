<template>
  <div class="container">
    <!-- 使用 :rules 绑定表单验证 -->
    <form id="login-form" ref="loginForm" :model="loginForm" :rules="loginRules">
      <div class="form-header">
        请输入密码登录
      </div>
      <div class="form-div">
        <label class="form-label">账户：</label>
        <input class="form-input" type="text" v-model="loginForm.userName">
      </div>
      <div class="form-div">
        <label class="form-label">密码：</label>
        <!-- 动态控制密码显、隐操作 -->
        <input class="form-input" :type="isShowPassword ? 'text' : 'password' " v-model="loginForm.password" >
        <!--<buttom @click="handleShowPassword">-->
          <!--{{isShowPassword ? '隐藏' : '显示'}}-->
        <!--</buttom>-->
      </div>
      <div class="form-div">
        <label class="form-label">验证码：</label>
        <input class="form-input" type="text" v-model="loginForm.checkNum" >
      </div>
      <div class="form-div">
        <a style="display: inline-block;float: right;color:#409EFF;vertical-align:middle;font-weight: 200" href="#">看不清，点击更换</a>
        <img src="../assets/yzm.png" width="100px" style="float: right;"/>
        <!--清除元素浮动:由于元素浮动后脱离了文档流，所以父元素是无法根据元素来自适应的。-->
        <div style="clear: both;height:0px;"></div>
      </div>
      <div class="form-div">
        <!-- 需要手动指定 字体属性从父元素继承 -->
        <button style="width: 20%;margin: 5px 20px;height:50px;border-radius: 10px;background: #67C23A;font: inherit;" type="button" @click="handleLogin">登录</button>
        <button style="width: 20%;margin: 5px 20px;height:50px;border-radius: 10px;background: #E6A23C;font: inherit" type="reset">重置</button>
      </div>
    </form>
  </div>
</template>

<script>
  // 按需引入自定义组件
  import {isValidUserName} from '@/utils/Validate'
  import axios from 'axios'
  
  export default {
    name: 'login',
    data: function () {
      // 定义属性变量
      const validateUserName = (rule, value, callback) => {
        if (!isValidUserName(value)) {
          callback(new Error('请输入正确的用户名'))
        } else {
          callback()
        }
      }
      const validatePass = (rule, value, callback) => {
        if (value.length < 5) {
          callback(new Error('密码不能小于5位'))
        } else {
          callback()
        }
      }
      return {
        loginForm: {
          userName: "",
          password: "",
          checkNum: ""
        },
        loginRules: {
          // 是否必填、触发条件、校验函数
          userName: [{required: true, trigger: 'blur', validator: validateUserName}],
          password: [{required: true, trigger: 'blur', validator: validatePass}]
        },
        isShowPassword: false
      }
    },
    methods: {
      handleShowPassword: function () {
        this.isShowPassword = !this.isShowPassword;
      },
      handleLogin: function () {
        axios.post('/api/admin/login', {
            userName: this.loginForm.userName,
            password: this.loginForm.password,
            checkNum: this.loginForm.checkNum
          }
        ).then(function (response) {
          // 捕获异常
          console.log(response.data)
        }).catch(function (error) {
          // 捕获异常
          console.log(error)
        })
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .container
    display flex
    flex-wrap wrap
    color red
    font-size 18px
    font-weight bold
    height 100%
    width 100%
    background url("../assets/login-bg.jpg")
    // 关闭背景拉伸平铺方式
    // background-repeat no-repeat
    #login-form
      width 50%
      height 500px
      background #ccc
      margin-top 8%
      margin-left 25%
      // 设置圆角
      border-radius 50px
      // 设置透明度
      opacity .8;
      .form-header
        margin-top 10px
        font-size 24px
        padding 5px
        margin-bottom 30px
      .form-div
        line-height 50px
        width 50%
        margin-left 25%
        margin-top 10px
        padding 5px
      .form-label
        display inline-block
        width 25%
      .form-input
        display inline-block
        width 60%
        height 40px
        outline 0
        padding 0px 5px
        border-radius 5px
        border 1px solid #cacaca
        background-color #fff
</style>
