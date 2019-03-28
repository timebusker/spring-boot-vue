import Vue from 'vue'
import App from './App'
import router from './router'
import elementUi from 'element-ui'
import {getRequest} from '@/utils/AxiosUtil'
import {postRequest} from '@/utils/AxiosUtil'
import {deleteRequest} from '@/utils/AxiosUtil'
import {putRequest} from '@/utils/AxiosUtil'
import {encode} from "@/utils/Base64Util"
import {decode} from "@/utils/Base64Util"

import * as filters from './filter' // global filters

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
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.filter('decodeFilter', function(value) {
  console.log("------------------>",value)
  return decode(value);
})

Vue.filter('encodeFilter', function(value) {
  console.log("------------------>",value)
  return encode(value);
})

new Vue({
  el: '#app',
  router,

  // 等价
  // components: { App },
  // template: '<App/>'
  render: h => h(App)
})
