import { ElMessage } from "element-plus";
import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import { stroage } from "../stroage";
import { basicRoutes } from "./routes";
import { Base64 } from "js-base64";

export const router = createRouter({
  history: createWebHashHistory(),
  routes: basicRoutes as unknown as RouteRecordRaw[],
});

const defaultTitle = "Lucian Blog";
router.beforeEach((to, from, next) => {
  if (to.fullPath.match(/^(?!\/admin)/)){
    next()
    return
  }
  document.title = <string>to.meta.title || defaultTitle;
  stroage.commit({ type: "checkToken" });
  const token = stroage.getters.getToken;
  if (to.name !== "Login" && !token) {
    next({
      name: "Login",
      query: { redirect_to: to.fullPath as string },
      replace: true,
    });
    ElMessage.error({ message: "请先登录账号！" });
  } else if (to.name !== "Login") {
    const tokenArr = token!.split(".");
    if (tokenArr.length !== 3) {
      next({
        name: "Login",
        query: { redirect_to: to.fullPath as string },
        replace: true,
      });
      ElMessage.error({ message: "请先登录账号！" });
    } else {
      const payload = tokenArr[1];
      const userInfo = Base64.decode(payload);
      const userInfoObject = JSON.parse(userInfo);
      if (userInfoObject.exp < new Date().getTime() / 1000) {
        next({
          name: "Login",
          query: { redirect_to: to.path as string },
          replace: true,
        });
        ElMessage.error("登陆信息过期，请重新登陆！");
      } else {
        next();
      }
    }
  } else {
    next();
  }
});
