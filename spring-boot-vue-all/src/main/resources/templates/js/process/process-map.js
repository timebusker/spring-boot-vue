const processMap = Vue.component("processMap", function (resolve) {
    require(['text!./views/process/process-map.html'], function (tpl) {
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
