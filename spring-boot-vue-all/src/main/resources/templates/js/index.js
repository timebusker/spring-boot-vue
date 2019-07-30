let $MENUS = [], $ROUTER = []

$MENUS = getMenus();
$ROUTER = formatRoutes($MENUS);
// console.log("========>>>", $ROUTER)

const router = new VueRouter({
    routes: $ROUTER
})
router.beforeEach((to, from, next) => {
    // console.log(from.path)
    // console.log(to.path)
    next()
})

window.$root = new Vue({
    el: '#app',
    router: router,
    data: {
        menus: $MENUS,
        activeIndex: "0_1"
    },
    created: function () {
        // console.log("----->", this.$router)
        // console.log("----->", this.menus)
        // // 获取当前组件的父组件和子组件实例信息
        // console.log("----->", this.$parent)
        // console.log("----->", this.$children)
    },
    methods: {}
});