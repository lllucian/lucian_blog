import { router } from "..";
import { DEFAULT_LAYOUT } from "../constant";
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
        component: () => import("/@/views/admin/posts/index/Index.vue"),
        meta: {
          title: "文章列表",
          hiddenMenu: false,
          icon: 'pixelarticons:article'
        },
      },
      {
        name: "AdminPostNew",
        path: "/post/new",
        component: () => import("/@/views/admin/posts/new/New.vue"),
        meta: {
          title: "新建文章",
          hiddenMenu: false,
          icon: 'jam:write',
        },
      },
    ],
  },
  {
    name: "AdminCategory",
    path: "/categories",
    component: DEFAULT_LAYOUT,
    meta: {
      title: "分类",
      hiddenMenu: false,
      icon: 'carbon:categories'
    },
    children: [
      {
        name: "AdminCategoryIndex",
        path: "/categories",
        component: () => import("/@/views/admin/categories/index/Index.vue"),
        meta: {
          title: "分类列表",
          hiddenMenu: false,
          icon: 'carbon:categories'
        },
      },
      {
        name: "AdminCategoryNew",
        path: "/category/new",
        component: () => import("/@/views/admin/categories/new/New.vue"),
        meta: {
          title: "新建分类",
          hiddenMenu: false,
          icon: 'ic:outline-category',
        },
      },
    ],
  },
];
export const basicRoutes = [LoginRoute, ...AdminRoutes];
