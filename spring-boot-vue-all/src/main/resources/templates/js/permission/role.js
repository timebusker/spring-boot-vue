const roleView = Vue.component("roleView", function (resolve) {
    require(['text!./views/permission/role.html'], function (tpl) {
        resolve({
            template: tpl,
            props: [],
            data: function () {
                return {
                    tableHeight: (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 200,
                    tableData: [],
                    pageSize: 0,
                    totalSize: 0,
                    currentPage: 0,
                    dialogVisible: false,
                    dialogTitle: "",
                    role: {},
                    authDialogVisible: false,
                    authDialogTitle: '',
                    authTreeData: [],
                    authSystemData: [],
                    authSystemId: "",
                }
            },
            created: function () {
                this.queryRole();
            },
            mounted: function () {

            },
            methods: {
                saveAuth() {
                    // 包含父节点
                    let res = this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys())
                    // 不包含父节点
                    res = this.$refs.tree.getCheckedKeys()
                    let _this = this;
                    $.post({
                        url: "role/auth",
                        methods: "post",
                        contentType: 'application/json;charset=utf-8',
                        data: JSON.stringify({
                            menuIds: res,
                            roleId: _this.role.id,
                        }),
                        success: function (res) {
                            _this.authDialogVisible = false;
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest, textStatus, errorThrown);
                            _this.$message.error('角色授权失败！');
                        }
                    })
                },
                authRole(data) {
                    this.role = data;
                    this.authDialogVisible = true;
                    this.authDialogTitle = data.name + '（' + data.symbol + ") 角色授权";
                    this.querySystem();
                    let _this = this;
                    $.post({
                        url: "role/authorized",
                        methods: "post",
                        dataType: 'json',
                        data: data,
                        success: function (res) {
                            _this.$refs.tree.setCheckedKeys(res.data, true);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest, textStatus, errorThrown);
                            _this.$message.error('角色授权失败！');
                        }
                    })
                },
                cancelAuth() {
                    this.authDialogVisible = false;
                },
                queryMenu() {
                    let _this = this;
                    $.post({
                        url: "menu/list",
                        methods: "post",
                        dataType: "json",
                        data: {
                            systemId: _this.authSystemId
                        },
                        success: function (res) {
                            _this.authTreeData = res.menus;
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest, textStatus, errorThrown);
                            _this.$message.error('查询树形菜单失败！');
                        }
                    })
                },
                querySystem() {
                    let _this = this;
                    $.post({
                        url: "system/list",
                        methods: "post",
                        dataType: "json",
                        success: function (res) {
                            _this.authSystemData = res.data;
                            _this.authSystemId = res.data[0].id;
                            _this.queryMenu();
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest, textStatus, errorThrown);
                            _this.$message.error('查询系统信息失败！');
                        }
                    });
                },
                handleCurrentChange(value) {
                    this.currentPage = value;
                    this.queryUser();
                },
                handleSizeChange(value) {
                    this.pageSize = value;
                    this.queryUser();
                },
                openDialog(title) {
                    this.dialogVisible = true;
                    this.dialogTitle = title;
                },
                closeDialog() {
                    this.$confirm('确认关闭？').then(_ => {
                        this.dialogVisible = false;
                        this.dialogTitle = "";
                        this.role = {};
                    })
                },
                addRole() {
                    this.role = {};
                    this.openDialog("新增角色信息");
                },
                editRole(data) {
                    this.role = data;
                    this.openDialog("更新角色信息");
                },
                deleteRole(data) {
                    let _this = this;
                    this.$confirm('确认删除？').then(_ => {
                        $.post({
                            url: "role/delete",
                            methods: "post",
                            dataType: "json",
                            data: data,
                            success: function (res) {
                                _this.queryRole();
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
                                _this.$message.error('删除角色失败！');
                            }
                        })
                    })
                },
                saveRole() {
                    let _this = this;
                    $.post({
                        url: "role/save",
                        methods: "post",
                        dataType: "json",
                        data: _this.role,
                        success: function (res) {
                            _this.dialogVisible = false;
                            _this.dialogTitle = "";
                            _this.role = {};
                            _this.queryRole();
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
                            _this.$message.error('保存角色失败！');
                        }
                    })
                },
                queryRole() {
                    let _this = this;
                    $.post({
                        url: "role/list",
                        methods: "post",
                        dataType: "json",
                        data: {
                            pageSize: _this.pageSize,
                            currentPage: _this.currentPage,
                        },
                        success: function (res) {
                            _this.tableData = res.data;
                            _this.pageSize = res.pageSize;
                            _this.totalSize = res.totalSize;
                            _this.totalSize = res.totalSize;
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
                            _this.$message.error('查询角色失败！');
                        }
                    })
                }
            }
        })
    })
});