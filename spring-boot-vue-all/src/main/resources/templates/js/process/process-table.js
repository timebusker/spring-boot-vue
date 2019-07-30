let processTable = Vue.component("processTable", function (resolve) {
    console.log("11111111111111111111111111")
    require(['text!./views/process/process-table.html'], function (tpl) {
        console.log("---------------->",tpl)
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {
                    d: 'ddddddd'
                }
            },
            created: function () {
            },
            methods: {}
        })
    })
});
console.log("222222222222222222222222222222222")

console.log(processTable.$data)
