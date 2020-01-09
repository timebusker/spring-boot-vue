/**
 * 菜单-路由预处理工具类
 * @returns {string}
 */
function root() {
    let url = window.location.href;
    let path = url.substring(0, url.lastIndexOf("#") + 1);
    return path;
}

/**
 * 动态路由设置时，要保证所有路由组件不能为空
 * @param menus
 * @returns {Array}
 */

function formatRoutes(menus) {
    let fmtRoutes = [];
    menus.forEach(menu => {
        let {
            id,
            systemId,
            parentId,
            icon,
            name,
            url,
            template,
            type,
            target,
            disabled,
            sort,
            updateTime,
            updateUserId,
            children,
        } = menu;
        if (target === "view_frame") {
            if (template != '') {
                let fmtRouter = {
                    path: url,
                    // 根据组件实例名称获取组件实例
                    component: Vue.component(template)
                };
                fmtRoutes.push(fmtRouter);
            }
            // 处理空节点
            if (children instanceof Array && children.length > 0) {
                children = formatRoutes(children);
                fmtRoutes = fmtRoutes.concat(children)
            }
        }
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
        url: "home/menu",
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
