const menuView = Vue.component("menuView", function (resolve) {
    require(['text!./views/common/menu.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {
                    systemData: [],
                    tableData: [],
                    treeData: [],
                    treeProps: {
                        children: 'children',
                        label: 'name',
                    },
                    systemId: '',
                    parentId: '',
                }
            },
            created: function () {
                this.querySystemList();
            },
            mounted: function () {
                console.log(this.systemData[0]);
            },
            methods: {
                queryMenuTree() {
                    let _this = this;
                    $.post({
                        url: "menu/list",
                        methods: "post",
                        dataType: "json",
                        // contentType: "application/json;charset=utf-8",
                        data: {
                            systemId: _this.systemId,
                            parentId: _this.parentId,
                        },
                        success: function (res) {
                            console.log(res);
                            _this.treeData = res.menus;
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