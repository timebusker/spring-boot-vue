let $MENUS = [], $ROUTER = []
$MENUS = getMenus();
$ROUTER = formatRoutes($MENUS);

console.log($MENUS)

let router = new VueRouter({
    routes: $ROUTER
})

window.$root = new Vue({
    el: '#app',
    router: router,
    data: {
        menus: $MENUS,
        activeIndex: $MENUS[0].url
    },
    created: function () {

    },
    methods: {
        handleSelectMenu(key, keyPath) {
            console.log(key, keyPath);
        }
    }
});