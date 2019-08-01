const process = Vue.component("process", function (resolve) {
    require(['text!./views/process/process.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {}
            },
            created: function () {
                console.log("------>", this.$router)
            },
            methods: {
                selectMenu: function (index) {
                    console.log("--------->", index, "菜单被选中！！！")
                    if (index.indexOf("1") >= 0) {
                        this.$router.push("/process/table")
                    } else {
                        this.$router.push("/map")
                    }
                }
            }
        })
    })
});