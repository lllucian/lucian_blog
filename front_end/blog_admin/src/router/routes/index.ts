import { AppRouteRecordRaw } from "/@/router/types";

export const LoginRoute: AppRouteRecordRaw = {
    name: 'Login',
    path: '/',
    component: () => import('/@/views/login/Login.vue'),
    meta: {title: 'Lucian Blog后台管理系统登陆界面'}
}

export const basicRoutes = [
    LoginRoute,
]
