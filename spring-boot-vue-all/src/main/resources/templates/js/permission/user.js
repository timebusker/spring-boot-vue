const userView = Vue.component("userView", function (resolve) {
    require(['text!./views/permission/user.html'], function (tpl) {
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
                    user: {},
                    authDialogVisible: false,
                    authDialogTitle: '',
                    authTreeData: [],
                }
            },
            created: function () {
                this.queryUser();
            },
            mounted: function () {

            },
            methods: {
                saveAuth() {
                    let res = this.$refs.tree.getCheckedKeys()
                    let _this = this;
                    $.post({
                        url: "user/auth",
                        methods: "post",
                        contentType: 'application/json;charset=utf-8',
                        data: JSON.stringify({
                            roleIds: res,
                            userId: _this.user.id,
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
                cancelAuth() {
                    this.authDialogTitle = "";
                    this.authDialogVisible = false;
                },
                authUser(data) {
                    this.user = data;
                    this.authDialogTitle = data.nickName + "(" + data.loginName + ") 用户授权";
                    this.authDialogVisible = true;
                    this.queryRole();
                    let _this = this;
                    $.post({
                        url: "user/authorized",
                        methods: "post",
                        dataType: "json",
                        data: data,
                        success: function (res) {
                            _this.$refs.tree.setCheckedKeys(res.data,true)
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(textStatus, errorThrown);
                            this.$message.error('查询权限信息失败！');
                        }
                    })
                },
                queryRole() {
                    let _this = this;
                    $.post({
                        url: "role/all",
                        methods: "post",
                        dataType: "json",
                        data: {},
                        success: function (res) {
                            _this.authTreeData = res.data;
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(textStatus, errorThrown);
                            this.$message.error('查询权限信息失败！');
                        }
                    })
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
                        this.user = {};
                    })
                },
                addUser() {
                    this.user = {};
                    this.openDialog("新增用户信息");
                },
                editUser(data) {
                    this.user = data;
                    this.openDialog("更新用户信息");
                },
                deleteUser(data) {
                    let _this = this;
                    $.post({
                        url: "user/delete",
                        methods: "post",
                        dataType: "json",
                        data: data,
                        success: function (res) {
                            _this.queryUser();
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            this.$message.error('删除用户信息失败！');
                        }
                    })
                },
                saveUser() {
                    let _this = this;
                    $.post({
                        url: "user/save",
                        methods: "post",
                        dataType: "json",
                        data: _this.user,
                        success: function (res) {
                            _this.dialogVisible = false;
                            _this.dialogTitle = "";
                            _this.user = {};
                            _this.queryUser();
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            this.$message.error('查询用户信息失败！');
                        }
                    })
                },
                queryUser() {
                    let _this = this;
                    $.post({
                        url: "user/list",
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
                            this.$message.error('查询用户信息失败！');
                        }
                    })
                }
            }
        })
    })
});