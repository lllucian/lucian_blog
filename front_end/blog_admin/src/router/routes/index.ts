import { AppRouteModule, AppRouteRecordRaw } from "/@/router/types";

export const LoginRoute: AppRouteRecordRaw = {
    name: 'Login',
    path: '/login',
    component: () => import('/@/views/login/Login.vue'),
    meta: {title: 'Lucian Blog后台管理系统登陆界面'},
}

export const AdminRoutes: AppRouteModule[] = [
    {
        name: 'AdminPost',
        path: '/',
        component: () => import('/@/views/admin/post/Post.vue'),
        meta: {title: '文章一览'},
    }
]
export const basicRoutes = [
    LoginRoute,
    ...AdminRoutes,
]

export const routeNames = basicRoutes.map(route => route.name)