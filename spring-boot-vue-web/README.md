### spring-boot-vue项目实战总结

> 本项目技术栈基于 ES2015+、vue、vuex、vue-router 、axios 和 element-ui

#### 第一天

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
```

