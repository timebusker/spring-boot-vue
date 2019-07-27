let router = new VueRouter();

window.$root = new Vue({
    el: '#app',
    router,
    data: {
        menus: []
    },
    created: function () {
        this.menus = getMenus();
        console.log(this.menus)
        let fmtRoutes = formatRoutes(this.menus);
        router.addRoutes(fmtRoutes);
    },
    methods: {}
});