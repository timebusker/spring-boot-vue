import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import elementUi from 'element-ui'
import {getRequest, postRequest, deleteRequest, putRequest} from './utils/AxiosUtil'
import {initMenu} from './utils/MenuUtil'

// import * as filters from './filter' // global filters

import 'styles/common/reset.css'
import 'styles/common/border.css'
// 已在index.html中引入
// import 'styles/icon/iconfont.css'
import 'element-ui/lib/theme-chalk/index.css'

// 设置全局组件属性，避免每个组件都需要引用
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;

Vue.config.productionTip = false;
// 注册组件
Vue.use(elementUi);

// register global utility filters.
// Object.keys(filters).forEach(key => {
//   Vue.filter(key, filters[key])
// })
//
// Vue.filter('decodeFilter', function (value) {
//   console.log("------------------>", value)
//   return decode(value);
// })
//
// Vue.filter('encodeFilter', function (value) {
//   console.log("------------------>", value)
//   return encode(value);
// })

// 全局前置导航守卫——>“导航”表示路由正在发生改变。
router.beforeEach((to, from, next) => {
    if (to.path.indexOf("/login") >= 0) {
      next();
      return;
    } else if (to.path === "/") {
      // next({path: "/login", query: {redirect: to.fullPath }});
      next("/login");
      return;
    }
    initMenu(router, store);
    next();
  }
)

// 全局后置钩子
router.afterEach((to, from) => {
  store.commit('setCurrentMenuName', to.name);
})

new Vue({
  el: '#app',
  router,
  // 把 store 对象提供给 “store” 选项，这可以把 store 的实例注入所有的子组件
  store,
  // 等价
  // components: { App },
  // template: '<App/>'
  render: h => h(App)
})
