import axios from 'axios'
import Cookies from 'js-cookie'
import {Message, Loading} from 'element-ui'
import store from './../store'

let cookie_token = 'token', base = '', loading;

// Promise 是异步编程的一种解决方案，比传统的解决方案——回调函数和事件——更合理和更强大。它由社区最早提出和实现，ES6 将其写进了语言标准，统一了用法，原生提供了Promise对象。
// resolve函数的作用是，将Promise对象的状态从“未完成”变为“成功”（即从 pending 变为 resolved），在异步操作成功时调用，并将异步操作的结果，作为参数传递出去；
// reject函数的作用是，将Promise对象的状态从“未完成”变为“失败”（即从 pending 变为 rejected），在异步操作失败时调用，并将异步操作报出的错误，作为参数传递出去。

// 创建axios实例
const service = axios.create({
  timeout: 900000 // 请求超时时间
})

service.interceptors.request.use(config => {
  // element ui Loading方法:为所有请求添加上loading
  loading = Loading.service({fullscreen: true});
  if (Cookies.get(cookie_token)) {
    // 让每个请求携带自定义token
    config.headers['Authorization'] = 'Bearer ' + Cookies.get(cookie_token)
  } else {
    if (window.location.href.indexOf("login") == -1) {
      window.location.assign("/");
    }
  }
  return config;
}, error => {
  loading.close()
  Message.error({message: '请求超时!'});
  return Promise.resolve(error);
})

service.interceptors.response.use(data => {
  loading.close()
  if (data.status && data.status == 200 && data.data.status == 'error') {
    Message.error({message: data.data.msg});
    return;
  }
  return data;
}, error => {
  loading.close()
  if (error.response.status == 504 || error.response.status == 404) {
    Message.error({message: '服务器被吃了⊙﹏⊙∥'});
  } else if (error.response.status == 403) {
    Message.error({message: '权限不足,请联系管理员!'});
  } else {
    Message.error({message: '未知错误!'});
  }
  return Promise.resolve(error);
})

export const postRequest = (url, params) => {
  return service({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    // 可修改参数组装传递格式
    // transformRequest: [function (data) {
    //   let ret = ''
    //   for (let it in data) {
    //     ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
    //   }
    //   return ret
    // }],
    headers: {
      // 'Content-Type': 'application/x-www-form-urlencoded',
      'Content-Type': 'application/json'
    }
  });
}
export const uploadFileRequest = (url, params) => {
  return service({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}
export const putRequest = (url, params) => {
  return service({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    // Unsupported Media Type 不做出来，采用JSON传输数据
    // transformRequest: [function (data) {
    //   let ret = ''
    //   for (let it in data) {
    //     ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
    //   }
    //   return ret
    // }],
    headers: {
      // Unsupported Media Type 不做出来，采用JSON传输数据
      // 'Content-Type': 'application/x-www-form-urlencoded'
      'Content-Type': 'application/json'
    }
  });
}
export const deleteRequest = (url) => {
  return service({
    method: 'delete',
    url: `${base}${url}`
  });
}
export const getRequest = (url) => {
  return service({
    method: 'get',
    url: `${base}${url}`
  });
}
