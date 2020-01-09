let $MENUS = [], $ROUTER = []
$MENUS = getMenus();
$ROUTER = formatRoutes($MENUS);

let router = new VueRouter({
    routes: $ROUTER
})

window.$root = new Vue({
    el: '#app',
    router: router,
    data: {
        menus: $MENUS,
        activeIndex: $MENUS[0].url,
    },
    created: function () {
        console.log("---------->",router)
    },
    methods: {
        selectMenu(key, keyPath) {
            console.log(key, keyPath);
        },
        logout() {
            window.location.href = "/auth/logout"
        }
    }
});