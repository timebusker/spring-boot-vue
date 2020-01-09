let vm = new Vue({
    el: '#app',
    data: {
        loginForm: {
            loginName:'timebusker',
            password: '123',
        },
    },
    created: function () {
        if (sessionStorage.getItem("isLogin")) {
            this.isLogin = eval(sessionStorage.getItem("isLogin").toLowerCase())
        }
    },
    methods: {
        login() {
            let _this = this;
            $.post({
                url: "/auth/process",
                methods: "post",
                data: {
                    loginName: _this.loginForm.loginName,
                    password: _this.loginForm.password,
                    code: _this.loginForm.code,
                    rememberMe: _this.loginForm.rememberMe,
                },
                success: function (res) {
                    sessionStorage.setItem("isLogin", "true");
                    window.location.href = "/index";
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
                    _this.$message.error('登录失败！');
                }
            })
        },
    }
});