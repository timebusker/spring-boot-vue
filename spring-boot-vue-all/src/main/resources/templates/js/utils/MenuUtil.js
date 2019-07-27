/**
 * 菜单-路由预处理工具类
 */

function root() {
    let url = window.location.href;
    let path = url.substring(0, url.lastIndexOf("#") + 1);
    return path;
}

// 动态路由设置时，要保证所有路由组件不能为空

function formatRoutes(menus) {
    let fmtRoutes = [];
    menus.forEach(menu => {
        let {
            menuId,
            menuPid,
            menuName,
            menuUrl,
            template,
            isFrame,
            isEnable,
            children
        } = menu;
        if (isFrame != true) {
            menuUrl = root() + menuUrl
        }
        let fmtRouter = {
            menuId: menuId,
            menuPid: menuPid,
            icon: "",
            menuName: menuName,
            isFrame: isFrame,
            disabled: !isEnable,
            template: template,
            path: menuUrl,
        };
        // 处理空节点
        if (children instanceof Array && children.length > 0) {
            children = formatRoutes(children);
            fmtRouter.children = children;
        }
        fmtRoutes.push(fmtRouter);
    })
    return fmtRoutes;
}

/**
 * JQuery默认开启异步请求
 * @returns {Array}
 */
function getMenus() {
    let menus = [];
    $.post({
        url: "/menus",
        dataType: "json",
        async: false,
        success: function (r) {
            menus = r.menus
        },
        error: function (r) {
            console.log(r)
        }
    });
    return menus;
}
