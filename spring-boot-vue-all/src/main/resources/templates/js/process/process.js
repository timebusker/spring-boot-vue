var process = Vue.component("process", function (resolve) {
    require(['text!./views/process/process.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {
                    d: 'ddddddd'
                }
            },
            created: function () {
                // console.log("----->", this.$router)
                // console.log("----->", this.menus)
                // // 获取当前组件的父组件和子组件实例信息
                // console.log("----->", this.$parent)
                // console.log("----->", this.$children)
                let _this = this;
                require(['processMap', 'processTable'], function (processMap, processTable) {
                    // _this.$router.push('/process/map',processMap)
                });
            },
            methods: {
                selectMenu: function () {
                    console.log("------------------------")
                }
            }
        })
    })
});

console.log(process)