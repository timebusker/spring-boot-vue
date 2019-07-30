var approve = Vue.component("approve", function (resolve) {
    require(['text!./views/work/manage/approve.html'], function (tpl) {
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