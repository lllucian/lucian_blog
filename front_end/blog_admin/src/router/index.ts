import { nextTick } from 'process';
import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
    {path: "/", component: () => import("/@/views/login/Login.vue"), name: 'login', meta: {title: '管理系统登陆页面'}}
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

const defaultTitle = 'Lucian Blog'
router.beforeEach((to, from, next) => {
    document.title = <string>to.meta.title || defaultTitle;
    next();
})