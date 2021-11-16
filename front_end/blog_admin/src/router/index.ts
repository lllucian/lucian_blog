import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
    {path: "/", component: () => import("/@/views/login/Login.vue"), name: 'login'}
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})