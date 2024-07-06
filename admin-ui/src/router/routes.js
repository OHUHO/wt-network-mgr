// import store from "@/state/store";
import { isLogin } from "../utils/user";

// 下面的 store.getters["auth/loggedIn"] 替换成为了 isLogin

export default [
  {
    path: "/login",
    name: "login",
    component: () => import("../views/account/login"),
    meta: {
      beforeResolve(routeTo, routeFrom, next) {
        // 如果用户已经登录
        if (isLogin()) {
          // 重定向到 home
          next({ name: "home" });
        } else {
          // 进入登录页面
          next();
        }
      },
    },
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/account/register"),
    meta: {
      beforeResolve(routeTo, routeFrom, next) {
        if (isLogin()) {
          next({ name: "home" });
        } else {
          next();
        }
      },
    },
  },
  {
    path: "/forgot-password",
    name: "Forgot-password",
    component: () => import("../views/account/forgot-password"),
    meta: {
      beforeResolve(routeTo, routeFrom, next) {
        if (isLogin()) {
          next({ name: "home" });
        } else {
          next();
        }
      },
    },
  },
  {
    path: "/logout",
    name: "logout",
    meta: {
      authRequired: true,
      beforeResolve(routeTo, routeFrom, next) {
        // store.dispatch("authfack/logout");
        window.localStorage.removeItem("token");
        const authRequiredOnPreviousRoute = routeFrom.matched.some((route) =>
          route.push("/login")
        );
        // 回到上一页或home页
        next(authRequiredOnPreviousRoute ? { name: "home" } : { ...routeFrom });
      },
    },
  },
  {
    path: "/",
    name: "home",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/dashboard/index"),
  },
  {
    path: "/network/schedule",
    name: "Schedule",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/network/schedule/index"),
  },
  {
    path: "/network/log",
    name: "Log",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/network/log/index"),
  },
  {
    path: "/network/log/:logId",
    name: "LogDetails",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/network/log/details"),
  },
  {
    path: "/contact",
    name: "Contact",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/contact/index"),
  },
  {
    path: "/message",
    name: "Message",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/message/index"),
    redirect: "/message/conversations",
    children: [
      {
        path: "conversations",
        component: () => import("../views/message/components/Conversations"),
        children: [
          {
            path: "privatechat/:id",
            component: () => import("../views/message/components/PrivateChat"),
          },
        ],
      },
      {
        path: "contacts",
        component: () => import("../views/message/components/Contacts"),
      },
    ],
  },
  {
    path: "/experience",
    name: "Experience",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/experience/index"),
  },
  {
    path: "/notice",
    name: "Notice",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/notice/index"),
  },
  {
    path: "/user",
    name: "User",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/user/index"),
  },
  {
    path: "/detail/:experienceId",
    name: "content",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/experience/detail/index"),
  },
  {
    path: "/sys",
    name: "sys",
    meta: {
      authRequired: true,
    },
    component: () => import("../views/sys/index"),
  },
];
