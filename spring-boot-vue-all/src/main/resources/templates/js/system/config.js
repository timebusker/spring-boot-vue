const configView = Vue.component("configView", function (resolve) {
    require(['text!./views/system/config.html'], function (tpl) {
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
                    configType: '',
                    configKey: '',
                    dialogVisible: false,
                    dialogTitle: "",
                    config: {},
                }
            },
            created: function () {
                this.queryConfig();
            },
            mounted: function () {

            },
            methods: {
                deleteConfig(config) {
                    let _this = this;
                    this.$confirm('确认关闭？').then(_ => {
                        $.post({
                            url: "config/delete",
                            methods: "post",
                            dataType: "json",
                            data: config,
                            success: function (res) {
                                _this.queryConfig();
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
                                _this.$message.error('删除系统配置失败！');
                            }
                        })
                    })
                },
                editConfig(config) {
                    this.config = config;
                    this.openDialog('修改系统配置');
                },
                saveConfig() {
                    let _this = this;
                    $.post({
                        url: "config/save",
                        methods: "post",
                        dataType: "json",
                        data: _this.config,
                        success: function (res) {
                            _this.queryConfig();
                            _this.dialogVisible = false;
                            _this.config = {};
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
                            _this.$message.error('保存系统配置失败！');
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
                        this.dialogTitle = "";
                        this.config = {};
                    })
                },
                handleCurrentChange(value) {
                    this.currentPage=value;
                    this.queryConfig();
                },
                handleSizeChange(value) {
                    this.pageSize=value;
                    this.queryConfig();
                },
                queryConfig() {
                    let _this = this;
                    $.post({
                        url: "config/list",
                        methods: "post",
                        dataType: "json",
                        data: {
                            configType: _this.configType,
                            configKey: _this.configKey,
                            pageSize: _this.pageSize,
                            currentPage: _this.currentPage,
                        },
                        success: function (res) {
                            console.log(res);
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
                            _this.$message.error('查询系统配置信息失败！');
                        }
                    })
                }
            }
        })
    })
});