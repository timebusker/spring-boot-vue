define(function () {
    return {
        component: Vue.component("backlogTable", function (resolve) {
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

