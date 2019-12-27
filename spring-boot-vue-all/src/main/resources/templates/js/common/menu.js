const menuView = Vue.component("menuView", function (resolve) {
    require(['text!./views/common/menu.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {
                    systemData: [],
                    selectSystem: '',
                    tableData: [],
                    treeData: [],
                    defaultProps: {
                        children: 'children',
                        label: 'label'
                    }
                }
            },
            created: function () {
                this.querySystemList();
            },
            mounted: function () {
                console.log(this.systemData[0]);
                this.selectSystem = this.systemData[0].name;
            },
            methods: {
                queryMenuTree() {
                    let _this = this;
                    $.post({
                        url: "",
                        methods: "post",
                        dataType: "json",
                        data: {},
                        success: function (res) {

                        },
                        error: function (res) {
                            this.$message.error('查询菜单树失败！');
                        }
                    })
                },
                querySystemList() {
                    let _this = this;
                    $.post({
                        url: "system/list",
                        methods: "post",
                        dataType: "json",
                        success: function (res) {
                            _this.systemData = res.data;
                        },
                        error: function (res) {
                            this.$message.error('查询系统信息列表失败！');
                        }
                    });
                }
            }
        })
    })
});