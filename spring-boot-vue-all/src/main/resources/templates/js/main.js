require.config({
    paths: {
        // Vue组件
        Vue: 'libs/vue-2.5.2/vue',
        VueRouter: 'libs/vue-router-3.0.1/vue-router',
        text: 'libs/requirejs-2.3.5/text',
        // ElementUI
        element_css: 'libs/element-ui-2.11.0/theme-chalk/index.css',
        element_js: 'libs/element-ui-2.11.0/index',
        // axiosJS
        axios: 'libs/axios-0.18.1/axios.min',
        // jsCookie
        jsCookie: 'libs/js-cookie-2.2.0/js.cookie',
        // jquery
        jquery: 'libs/jquery-3.4.1/jquery-3.4.1.min.js',

        // 模块会引入组件
        // process
        backlogCard: 'js/work/backlog-card',
        backlogTable: 'js/work/backlog-table'
    }
});