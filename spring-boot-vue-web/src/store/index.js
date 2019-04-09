/**
 * Created by Administrator on 2019/4/8.
 * VueX实例使用
 */
import Vue from 'vue'
import VueX from 'vuex'

Vue.use(VueX)

const store = new VueX.Store({
  state: {
    user: {},
    routes: [],
    currentMenuName: '主页'
  },
  mutations: {
    initMenu(state, routes){
      state.routes = routes;
    },
    setCurrentMenuName(state, menuName){
      state.currentMenuName = menuName;
    },
    login(state, user){
      state.user = user;
      window.localStorage.setItem('user', JSON.stringify(user));
    },
    logout(state){
      window.localStorage.removeItem('user');
      state.routes = [];
    }
  },
  actions: {}
});

export default store;
