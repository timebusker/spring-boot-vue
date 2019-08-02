/**
 * 此实例为全局注册，必须在根Vue实例之前完成注册
 */
const backlog = Vue.component("backlog", function (resolve) {
    console.log("resolve:", resolve)
    require(['text!./views/work/backlog.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {}
            },
            created: function () {
                let children = loadComponent()
                console.log("children:", children)
                console.log("------>", this.$router)
                $ROUTER.forEach(v => {
                    if (v.path === '/work/backlog') {
                        v.children = children
                    }
                })
                this.$router.addRoutes($ROUTER);
                console.log("++++++>", this.$router)
            },
            methods: {}
        })
    })
});

function loadComponent() {
    let children = []
    require(['backlogCard', 'backlogTable'], function (backlogCard, backlogTable) {
        children.push({path: "/card", component: backlogCard.component})
        children.push({path: "table", component: backlogTable.component()})
    });
    return children;
}