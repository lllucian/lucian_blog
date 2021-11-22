import { router } from "..";
import { DEFAULT_LAYOUT } from "../constant";
import { AppRouteModule, AppRouteRecordRaw } from "/@/router/types";

export const LoginRoute: AppRouteRecordRaw = {
  name: "Login",
  path: "/login",
  component: () => import("/@/views/login/Login.vue"),
  meta: { title: "Lucian Blog后台管理系统登陆界面" },
};

export const AdminRoutes: AppRouteModule[] = [
  {
    name: "AdminPost",
    path: "/",
    component: DEFAULT_LAYOUT,
    redirect: '/posts',
    meta: {
      title: "文章",
    },
    children: [
      {
        name: "AdminPostIndex",
        path: "posts",
        component: () => import("/@/views/admin/post/Post.vue"),
        meta: {
          title: "文章一览",
        },
      },
    ],
  },
];
export const basicRoutes = [LoginRoute, ...AdminRoutes];

const routeNames:Array<string> = [];

const getRouteNames = (routes:Array<AppRouteModule>) => {
    return routes.forEach((route) => {
        routeNames.push(route.name);
        if (route.children !== undefined && route.children.length !== 0){
            getRouteNames(route.children)
        }
    })
}

getRouteNames(AdminRoutes);

export {routeNames};
