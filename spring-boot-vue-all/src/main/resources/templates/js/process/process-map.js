var processMap = Vue.component("processMap", function (resolve) {
    console.log("333333333333333333333333333333333")
    require(['text!./views/process/process-map.html'], function (tpl) {
        console.log("---------------->", tpl)
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

console.log("4444444444444444444444444444444444444444")