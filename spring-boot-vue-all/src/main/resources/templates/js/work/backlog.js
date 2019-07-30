var backlog = Vue.component("backlog", function (resolve) {
    require(['text!./views/work/backlog.html'], function (tpl) {
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
});