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
        drawer: false
    },
    created: function () {
    },
    methods: {
        handleSelectMenu(key, keyPath) {
            console.log(key, keyPath);
        },
        handleCloseDrawer(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
        }
    }
});