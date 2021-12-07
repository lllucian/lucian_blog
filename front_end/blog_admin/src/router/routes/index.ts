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
          icon: 'pixelarticons:article',
          canRedirect: true
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
          canRedirect: true
        },
      },
      {
        name: 'AdminPostEdit',
        path: '/post/:id',
        component: () => import("/@/views/admin/posts/edit/Edit.vue"),
        meta: {
          title: '编辑文章',
          hiddenMenu: true,
          canRedirect: false
        }
      }
    ],
  },
  {
    name: "AdminCategory",
    path: "/categories",
    component: DEFAULT_LAYOUT,
    meta: {
      title: "分类",
      hiddenMenu: false,
      icon: 'carbon:categories',
    },
    children: [
      {
        name: "AdminCategoryIndex",
        path: "/categories",
        component: () => import("/@/views/admin/categories/index/Index.vue"),
        meta: {
          title: "分类列表",
          hiddenMenu: false,
          icon: 'carbon:categories',
          canRedirect: true,
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
          canRedirect: true,
        },
      },
      {
        name: "AdminCategoryEdit",
        path: "/category/:id",
        component: () => import("/@/views/admin/categories/edit/Edit.vue"),
        meta: {
          title: "编辑分类",
          hiddenMenu: true,
          icon: 'jam:write',
          alias: '/categories',
          canRedirect: false,
        },
      },
    ],
  },
  {
    name: "AdminTag",
    path: "/tags",
    component: DEFAULT_LAYOUT,
    meta: {
      title: "标签",
      hiddenMenu: false,
      icon: 'gis:tags',
    },
    children: [
      {
        name: "AdminTagIndex",
        path: "/tags",
        component: () => import("/@/views/admin/tags/index/Index.vue"),
        meta: {
          title: "标签列表",
          hiddenMenu: false,
          icon: 'gis:tags',
          canRedirect: true
        },
      },
      {
        name: "AdminTagNew",
        path: "/tag/new",
        component: () => import("/@/views/admin/tags/new/New.vue"),
        meta: {
          title: "新建标签",
          hiddenMenu: false,
          icon: 'carbon:tag-edit',
          canRedirect: true,
        },
      },
      {
        name: "AdminTagEdit",
        path: "/tag/:id",
        component: () => import("/@/views/admin/tags/edit/Edit.vue"),
        meta: {
          title: "编辑标签",
          hiddenMenu: true,
          icon: 'carbon:tag-edit',
          alias: '/tags',
          canRedirect: false
        },
      },
    ],
  },
];
export const basicRoutes = [LoginRoute, ...AdminRoutes];
