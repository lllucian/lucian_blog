import { nextTick } from 'process';
import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import { basicRoutes } from './routes';


export const router = createRouter({
    history: createWebHashHistory(),
    routes: basicRoutes as unknown as RouteRecordRaw[]
})


const defaultTitle = 'Lucian Blog'
router.beforeEach((to, from, next) => {
    document.title = <string>to.meta.title || defaultTitle;
    next();
})