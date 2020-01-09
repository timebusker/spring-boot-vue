const menuView = Vue.component("menuView", function (resolve) {
    require(['text!./views/system/menu.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {
                    systemData: [],
                    tableData: [],
                    treeData: [],
                    // 别名适配
                    treeProps: {
                        children: 'children',
                        label: 'name'
                    },
                    systemId: '',
                    parentId: '',
                    // 对话框显示控制
                    dialogVisible: false,
                    dialogTitle: "",
                    menu: {},

                }
            },
            created: function () {
                this.querySystemList();
            },
            mounted: function () {
            },
            methods: {
                addChildMenu(data, title) {
                    this.menu.parentId = data.id;
                    this.menu.systemId = this.systemId;
                    this.openDialog(title);
                },
                addMenu(title) {
                    this.menu.parentId = this.parentId;
                    this.menu.systemId = this.systemId;
                    this.openDialog(title);
                },
                editMenu(data, title) {
                    data.children = [];
                    this.menu = data;
                    this.openDialog(title);
                },
                saveMenu() {
                    let _this = this;
                    $.post({
                        url: "menu/save",
                        methods: "post",
                        dataType: "json",
                        data: _this.menu,
                        success: function (res) {
                            _this.dialogVisible = false;
                            _this.parentId = "";
                            _this.menu = {};
                            _this.queryMenuTree();
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            // 出现任何错误都会跳到error函数里
                            // 1、dataType错误：后台返回的dataType类型和前台写的不一致会跳入error（本处已去除dataType:json配置）
                            // 2、async请求同步异步问题：async默认是true(异步请求),如果想一个Ajax执行完后再执行另一个Ajax, 需要把async=false
                            // 3、data不能不写：data为空也一定要传"{}"；不然返回的是xml格式的。并提示parsererror. data:"{}"
                            // 4、传递的参数必须是ajax支持的编码格式
                            // 5、URL路径问题： 路径不能有中文
                            // 异常捕获主要打印：textStatus（错误原因）
                            console.log(XMLHttpRequest, textStatus, errorThrown);
                            _this.$message.error('保存菜单失败！');
                        }
                    })
                },
                openDialog(title) {
                    this.dialogVisible = true;
                    this.dialogTitle = title;
                },
                closeDialog() {
                    this.$confirm('确认关闭？').then(_ => {
                        this.dialogVisible = false;
                        this.menu = {};
                    })
                },
                updateTable(data) {
                    this.tableData = [];
                    this.getChildNode(data, this.treeData)
                    this.parentId = data.id;
                },
                getChildNode(node, list) {
                    list.forEach((menu, index) => {
                        if (menu.id === node.id) {
                            this.tableData = menu.children;
                        } else if (menu.children) {
                            this.getChildNode(node, menu.children)
                        }
                    })
                },
                queryMenuTree() {
                    let _this = this;
                    $.post({
                        url: "menu/list",
                        methods: "post",
                        dataType: "json",
                        data: {
                            systemId: _this.systemId,
                            parentId: _this.parentId,
                        },
                        success: function (res) {
                            _this.treeData = res.menus;
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            // 出现任何错误都会跳到error函数里
                            // 1、dataType错误：后台返回的dataType类型和前台写的不一致会跳入error（本处已去除dataType:json配置）
                            // 2、async请求同步异步问题：async默认是true(异步请求),如果想一个Ajax执行完后再执行另一个Ajax, 需要把async=false
                            // 3、data不能不写：data为空也一定要传"{}"；不然返回的是xml格式的。并提示parsererror. data:"{}"
                            // 4、传递的参数必须是ajax支持的编码格式
                            // 5、URL路径问题： 路径不能有中文
                            // 异常捕获主要打印：textStatus（错误原因）
                            console.log(XMLHttpRequest, textStatus, errorThrown);
                            _this.$message.error('查询树形菜单失败！');
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
                            _this.systemId = res.data[0].id;
                            _this.queryMenuTree();
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            // 出现任何错误都会跳到error函数里
                            // 1、dataType错误：后台返回的dataType类型和前台写的不一致会跳入error（本处已去除dataType:json配置）
                            // 2、async请求同步异步问题：async默认是true(异步请求),如果想一个Ajax执行完后再执行另一个Ajax, 需要把async=false
                            // 3、data不能不写：data为空也一定要传"{}"；不然返回的是xml格式的。并提示parsererror. data:"{}"
                            // 4、传递的参数必须是ajax支持的编码格式
                            // 5、URL路径问题： 路径不能有中文
                            // 异常捕获主要打印：textStatus（错误原因）
                            console.log(XMLHttpRequest, textStatus, errorThrown);
                            _this.$message.error('查询系统信息失败！');
                        }
                    });
                }
            }
        })
    })
});