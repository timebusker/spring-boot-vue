import Vue from 'vue'
import Router from 'vue-router'
import login from '@/views/login'
import home from '@/views/home'
import market from '@/views/shop/market/index'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/shop/market",
      name: "移动商城",
      component: market
    }, {
      path: '/login',
      name: '登录',
      component: login
    }, {
      path: '/home',
      name: '主页',
      component: home
    }
  ]
})
