define(function () {
    Vue.component("backlogTable", function (resolve) {
        console.log("-------  resolve:", resolve)
        require(['text!./views/work/backlog-table.html'], function (tpl) {
            console.log("异步加载模板了！！！--------------------------b")
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
});

