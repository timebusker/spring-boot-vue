const processTable = Vue.component("processTable", function (resolve) {
    require(['text!./views/process/process-table.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {
                    a: "aaaaaaaaaaaaaaaaaaa"
                }
            },
            created: function () {
            },
            methods: {}
        })
    })
})
