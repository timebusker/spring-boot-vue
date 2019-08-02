define(function () {
    return {
        component: Vue.component("backlogCard", function (resolve) {
            console.log("resolve:", resolve)
            require(['text!./views/work/backlog-card.html'], function (tpl) {
                console.log("异步加载模板了！！！--------------------------a")
                resolve({
                    template: tpl,
                    props: [],
                    data: function () {
                        return {}
                    },
                    created: function () {

                    },
                    methods: {}
                })
            })
        })
    }
});