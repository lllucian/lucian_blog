<template>
  <el-container :style="{ height: pageHeight, border: '1px solid #eee' }">
    <el-aside style="background-color: rgb(238, 241, 246)" :width="menuWidth">
      <CustomMenu :pageHeight="pageHeight" v-model="collapseMenu"></CustomMenu>
    </el-aside>

    <el-container>
      <el-header class="header-content"
                 style="font-size: 12px; border-bottom: 1px solid #eee"
      >
        <div class="header-left">
          <div class="icon-menu" @click="changeCollapse">
            <span class="iconify" data-icon="ant-design:menu-fold-outlined" style="font-size: 20px;"></span>
          </div>
        </div>
        <div style="float:right;display: flex;justify-content: center;align-items: center;">
          <el-dropdown>
            <Icon icon="ep:setting"></Icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="logout">注销</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span>{{ username }}</span>
        </div>
      </el-header>

      <el-main>
        <routerView/>
      </el-main>
    </el-container>
  </el-container>
  <el-backtop></el-backtop>
</template>

<script lang="ts" setup>
import {defineComponent, nextTick, onMounted, ref, watch} from "vue";
import {default as Icon} from "/@/components/common/basic/Icon.vue";
import {CustomMenu} from "./menu";
import {stroage} from "/@/stroage";
import {Base64} from "js-base64";
import {useRoute, useRouter} from "vue-router";


const pageHeight = ref(window.innerHeight + "px");

const getPageRizeHeight = async () => {
  pageHeight.value = window.innerHeight + "px";
};

const collapseMenu = ref(false);

const iconData = ref('ant-design:menu-fold-outlined');

const menuWidth = ref('200px');

const username = ref<String>();

const setUserName = (() => {
  const token = stroage.getters.getToken;
  const tokenArr = token!.split(".");
  const payload = tokenArr[1];
  const userInfo = Base64.decode(payload);
  const userInfoObject = JSON.parse(userInfo);
  username.value = userInfoObject.sub;
});
const router = useRouter();

const logout = (() => {
  stroage.commit({ type: "clearToken" });

  nextTick()
  router.push({path: router.currentRoute.value.path, query: {date: new Date().getDate()}});
});
onMounted(() => {
  window.addEventListener("resize", getPageRizeHeight);
  setUserName()
});

watch(collapseMenu, (newValue, oldValue) => {
  iconData.value = newValue ? 'ant-design:menu-unfold-outlined' : 'ant-design:menu-fold-outlined';
  document.getElementsByClassName('icon-menu')[0].getElementsByTagName("svg")[0].setAttribute("data-icon", iconData.value);
  menuWidth.value = newValue ? '64px' : '200px';
});

const changeCollapse = () => collapseMenu.value = !collapseMenu.value;

</script>

<style>
.el-header {
  background-color: var(--el-color-white);
  color: var(--el-text-color-primary);
  line-height: 60px;
}

.el-aside {
  color: var(--el-text-color-primary);
}

body {
  margin: 0;
}

.card-header {
  background-color: unset !important;
}
</style>
<style scoped>
.header-left {
  display: flex;
  height: 100%;
  align-items: center;
}

.icon-menu {
  cursor: pointer;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
