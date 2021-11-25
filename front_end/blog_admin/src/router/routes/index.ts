import { router } from "..";
// import { DEFAULT_LAYOUT } from "../constant";
import DEFAULT_LAYOUT from '/@/layouts/default/index.vue'
import { AppRouteModule, AppRouteRecordRaw } from "/@/router/types";

export const LoginRoute: AppRouteRecordRaw = {
  name: "Login",
  path: "/login",
  component: () => import("/@/views/login/Login.vue"),
  meta: { title: "Lucian Blog后台管理系统登陆界面", hiddenMenu: true },
};

export const AdminRoutes: AppRouteModule[] = [
  {
    name: "AdminPost",
    path: "/",
    component: DEFAULT_LAYOUT,
    redirect: '/posts',
    meta: {
      title: "文章",
      hiddenMenu: false,
      icon: 'pixelarticons:article-multiple'
    },
    children: [
      {
        name: "AdminPostIndex",
        path: "/posts",
        component: () => import("/@/views/admin/post/Post.vue"),
        meta: {
          title: "文章一览",
          hiddenMenu: false,
          icon: 'pixelarticons:article'
        },
      },
    ],
  },
];
export const basicRoutes = [LoginRoute, ...AdminRoutes];
