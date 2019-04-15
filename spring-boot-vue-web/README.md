### spring-boot-vue项目实战总结

> 本项目技术栈基于 ES2015+、vue、vuex、vue-router 、axios 和 element-ui

#### 第一天（项目初始化）

```
# 项目初始化
# 安装node.js、vue-cli
# 初始化项目
vue init webpack spring-boot-vue-web

# 配置任意IP可以访问
# 修改index.js--> localhost==0.0.0.0
host: '0.0.0.0', // can be overwritten by process.env.HOST
port: 8080, // can be overwritten by process.env.PORT, if port is in use, a free one will be determined

# 目录结构
├── build                      // 构建相关  
├── config                     // 配置相关
├── src                        // 源代码
│   ├── api                    // 所有请求
│   ├── assets                 // 主题 字体等静态资源
│   ├── components             // 全局公用组件
│   ├── directive              // 全局指令
│   ├── filtres                // 全局 filter
│   ├── icons                  // 项目所有 svg icons
│   ├── lang                   // 国际化 language
│   ├── mock                   // 项目mock 模拟数据
│   ├── router                 // 路由
│   ├── store                  // 全局 store管理
│   ├── styles                 // 全局样式
│   ├── utils                  // 全局公用方法
│   ├── vendor                 // 公用vendor
│   ├── views                   // view
│   ├── App.vue                // 入口页面
│   ├── main.js                // 入口 加载组件 初始化等
│   └── permission.js          // 权限管理
├── static                     // 第三方不打包资源
│   └── Tinymce                // 富文本
├── .babelrc                   // babel-loader 配置
├── eslintrc.js                // eslint 配置项
├── .gitignore                 // git 忽略项
├── favicon.ico                // favicon图标
├── index.html                 // html模板
└── package.json               // package.json

# idea ESLint 关闭代码检查
config/index.js --> useEslint: false,


# Stylus是一个CSS预处理器
# CSS 预处理器是一种语言用来为 CSS 增加一些编程的的特性，无需考虑浏览器的兼容性问题，例如你可以在 CSS 中使用变量、简单的程序逻辑、函数等等在编程语言中的一些基本技巧，可以让你的 CSS 更见简洁，适应性更强，代码更直观等诸多好处。
npm install stylus --save 
npm install stylus-loader 

# 常用样式引用
# reset.css: 重置浏览器的默认样式(各大厂之间各有不同)
npm install reset-css --save

# 移动端1像素（1px）解决方案：border.css
# 面对多端屏幕分辨率不同问题（设计师要的1px指的是物理像素，而我们写的是逻辑像素：css像素=物理像素/dpr=1/2=0.5px ）
# 原理是把原先元素的 border 去掉，然后利用 :before 或者 :after 重做 border ，设置其高为一个像素，然后设置上边框也为一个像素，最后通过 CSS3 的 transform 属性把伪元素缩放为原来的一半大小。原先的元素相对定位，新做的 border 绝对定位

# 新增favicon.ico路径映射
webpack.dev.conf.js-->HtmlWebpackPlugin-->favicon: 'favicon.ico'(新增)
webpack.prod.conf.js-->HtmlWebpackPlugin-->favicon: 'favicon.ico'(新增)

# 引入element-ui
npm install --save element-ui

import elementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(elementUi)

# axios 是一个基于Promise 用于浏览器和 nodejs 的 HTTP 客户端
# 从浏览器中创建 XMLHttpRequest
# 从 node.js 发出 http 请求
# 支持 Promise API
# 拦截请求和响应
# 转换请求和响应数据
# 取消请求
# 自动转换JSON数据
# 客户端支持防止 CSRF/XSRF
npm install axios --save
```

#### 第二天（前后端数据交互）

```$xslt
# 使用axios发送后台交互请求：axios 是一个基于Promise 用于浏览器和 nodejs 的 HTTP 客户端
# 从浏览器中创建 XMLHttpRequest
# 从 node.js 发出 http 请求
# 支持 Promise API
# 拦截请求和响应
# 转换请求和响应数据
# 取消请求
# 自动转换JSON数据
# 客户端支持防止 CSRF/XSRF
https://www.kancloud.cn/yunye/axios/234845

npm install axios --save


# 配置端口转发到后端服务程序进行数据交互
# 编辑config/index.js配置代理转发
# 将请求转发到80端口服务器上：/api/login-->http://localhost:80/api/login
proxyTable: {
  '/api': {
    target: 'http://localhost:80'
  }
}

# (重写的新路径):/api/login-->http://localhost:80/static/mock/login
# changeOrigin: true 开启跨域请求
proxyTable: {
  '/api': {
    target: 'http://localhost:8080'
    changeOrigin: true, 
	  pathRewrite:{
	    '^api/':'/static/mock' 
	  }
  }
}

# /api/admin/login --> http://localhost:8033/admin/login
proxyTable: {
  '/api': {
    target: 'http://localhost:8033/',
    changeOrigin: true,
    pathRewrite: {
      '^/api/': '/'
    }
  }
},

# 如果前后端需要合并部署，不建议重新URL地址
proxyTable: {
  '/': {
    target: 'http://localhost:8082',
    changeOrigin: true,
    pathRewrite: {
      '^/': ''
    }
  },
  '/ws/*': {
    target: 'ws://127.0.0.1:8082',
    ws: true
  }
},


# v-model 与 ：model 之间的区别
:model是v-bind:model的缩写,<child :model="msg"></child>这种只是将父组件的数据传递到了子组件，并没有实现子组件和父组件数据的双向绑定。
v-model通常用于input的双向数据绑定 <input v-model="parentMsg">，也可以实现子组件到父组件数据的双向数据绑定
:model != v-model---> v-show/v-if/v-else 等指令不能缩写，其主要完成数据直接的双向绑定


# axios 发post请求，后端接收不到参数的解决方案
axios会帮我们 转换请求数据和响应数据 以及 自动转换 JSON 数据-->传输对象：源码中会触发生成json串
Object --> JSON.stringify(Object)
对于参数封装为对象传输的，后端服务可直接使用对象引用，spring mvc底层会自动帮助完成对象转换--> 服务端参数使用@RequestBody注解
对于未封装为对象传输的参数，可采用axios 原生AIP封装


// 使用监听器监听数据变化，完成异步数据更新;
watch: {
  dialogVisible: function (newVisible, oldVisible) {
    if (false === newVisible) {
      this.menu = {};
    }
  }
},
```

#### 第三天（使用vue-router开发菜单栏）

```
# router/index.js文件下设置路由表规则（url-->组件）
routes: [
  {
    path: '/',
    name: '登录',
    component: login
  },
  {
    path: '/home',
    name: '主页',
    component: home
  }
]
# 在需要跳转时，设置当前路由地址，实现路由跳转
// _this.$router.replace({path: path == '/' || path == undefined ? '/home' : path});（该方式不会向 history 添加新记录）
_this.$router.push({path: path == '/' || path == undefined ? '/home' : path});
# this.$router 和 router 使用起来完全一样。我们使用 this.$router 的原因是我们并不想在每个独立需要封装路由的组件中都导入路由

# 申明式
<router-link :to="..." replace>

# 编程式
router.replace(location, onComplete?, onAbort?)
# 路由出口：路由匹配到的组件将渲染在这里(指定位置渲染)
<router-view></router-view>

# 路由后退：类似 window.history.go(n)
router.go(n)


# 指定对应element-ui版本开发


# vue拦截器
// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.filter('decodeFilter', function(value) {
  console.log("------------------>",value)
  return decode(value);
})


# 动态路由 --> 动态组件加载 --> 侧边栏功能
# 动态路由设置时，要保证所有路由组件不能为空

之前偷懒，父级菜单设置了组件信息为空，想在前端简单控制一下
# -- 动态加载组件，虽然侧边栏菜单能加载成功，但是动态路由一直设置失败，导致所有菜单不能跳转
component(resolve){
  if (component.length > 0) {
    // 异步加载
    require(['@/views' + component + '.vue'], resolve)
  } else {
    import('@/views/home')
  }
}
```


#### VueX 状态管理模式 -- 数据共享，避免N多个组件件传值共享

采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。

```
# state : 单一状态树，用一个对象就包含了全部的应用层级状态。至此它便作为一个“唯一数据源 (SSOT)”而存在。从 store 实例中读取状态最简单的方法就是在计算属性中返回某个状态。

# Getter ： 

# Mutation ： 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation。Vuex 中的 mutation 非常类似于事件：每个 mutation 都有一个字符串的 事件类型 (type) 和 一个 回调函数 (handler)。

# 
```
