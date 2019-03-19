import Vue from 'vue'
import App from './App'
import router from './router'
import elementUi from 'element-ui'

import 'styles/common/reset.css'
import 'styles/common/border.css'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false;
// 注册组件
Vue.use(elementUi);
new Vue({
  el: '#app',
  router,

  // 等价
  // components: { App },
  // template: '<App/>'
  render: h => h(App)
})
