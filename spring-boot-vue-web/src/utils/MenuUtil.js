/**
 * Created by Administrator on 2019/4/8.
 * 侧边栏菜单工具类
 */

import {getRequest} from '@/utils/AxiosUtil'

// 动态路由设置时，要保证所有路由组件不能为空

export const formatRoutes = (routes) => {
  let fmtRoutes = [];
  routes.forEach(router => {
    let {
      id,
      isFrame,
      name,
      component,
      url,
      icon,
      sort,
      pid,
      children
    } = router;
    if (children && children instanceof Array) {
      children = formatRoutes(children);
    }
    let fmtRouter = {
      id: id,
      isFrame: isFrame,
      path: url,
      // component : menu,
      // component: () => {
      //   if(component.length > 0){
      //     import('@/views' + component)
      //   }else {
      //     import('@/views/home')
      //   }
      // },
      component(resolve){
        if (component.length > 0) {
          // 异步加载
          require(['@/views' + component + '.vue'], resolve)
        } else {
          import('@/views/home')
        }
      },
      name: name,
      icon: icon,
      sort: sort,
      pid: pid,
      children: children
    };
    fmtRoutes.push(fmtRouter);
  })
  return fmtRoutes;
}

export const initMenu = (router, store) => {
  if (store.state.routes.length > 0) {
    return;
  }
  getRequest("/api/menu/list").then(response => {
    let fmtRoutes = formatRoutes(response.data.list);
    router.addRoutes(fmtRoutes);
    store.commit('initMenu', fmtRoutes);
  }).catch(error => {
    console.log(error)
  });
}
