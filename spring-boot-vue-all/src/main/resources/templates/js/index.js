let $MENUS = [], $ROUTER = []
$MENUS = getMenus();
$ROUTER = formatRoutes($MENUS);

let children = []
// 注意路由路径的定义
children.push({path: "/map", component: Vue.component("processMap")})
children.push({path: "table", component: processTable})
$ROUTER.forEach(v => {
    if (v.path === '/process') {
        v.children = children
    }
})

let router = new VueRouter({
    routes: $ROUTER
})

window.$root = new Vue({
    el: '#app',
    router: router,
    data: {
        menus: $MENUS,
        activeIndex: "0_1"
    },
    created: function () {
        console.log("========>", this.$router)
    },
    methods: {}
});