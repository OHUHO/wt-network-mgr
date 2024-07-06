/* eslint-disable no-unused-vars */
import axios from "axios";
import router from "@/router";

// 创建 axios 实例
const http = axios.create({
  baseURL: "http://localhost:8888",
  timeout: 10000,
  // 跨域请求是否需要携带 cookie
  withCredentials: false,
});

// 请求拦截器
http.interceptors.request.use(
  (config) => {
    if (window.localStorage.getItem("token")) {
      var token = JSON.parse(window.localStorage.getItem("token")).access_token;
      // config.headers["Authorization"] = token;
      config.headers.Authorization = "Bearer " + token;
      // console.log(token);
    }
    return config;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);

// 响应拦截器
http.interceptors.response.use(
  (response) => {
    console.log("response: ", response);
    const { code, message } = response.data;
    if (code == 200) {
      // console.log(message);
      return response.data;
    } else if (code == 401) {
      jumpLogin(); // 跳转到登录
    } else {
      // return Promise.reject(response.data);
    }
    return response.data;
  },
  (error) => {
    console.log("error-response:", error.response);
    console.log("error-config:", error.config);
    console.log("error-request:", error.request);
    if (error.response) {
      if (error.response.data.code === 401) {
        jumpLogin(); // 跳转到登录
      }
    }
    return Promise.reject(error);
  }
);

const jumpLogin = () => {
  window.localStorage.removeItem("token");
  router.push("/login");
};

export default http;
