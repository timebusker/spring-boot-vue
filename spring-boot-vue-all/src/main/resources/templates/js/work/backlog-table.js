define(function () {
    let v = require(['libs/vue-2.5.2/vue.js'])
    console.log(v)
    return {
        component: Vue.component("backlogTable", function (resolve) {
            console.log("resolve:", resolve)
            require(['text!./views/work/backlog-table.html'], function (tpl) {
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

