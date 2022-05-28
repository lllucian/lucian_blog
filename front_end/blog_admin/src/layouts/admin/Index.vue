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
          <span class="iconify" data-icon="ant-design:menu-fold-outlined" style="font-size: 20px;" ></span>
        </div>
      </div>
      <div style="float:right;display: flex;justify-content: center;align-items: center;">
        <el-dropdown>
          <Icon icon="ep:setting"></Icon>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>View</el-dropdown-item>
              <el-dropdown-item>Add</el-dropdown-item>
              <el-dropdown-item>Delete</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <span>Tom</span>
        </div>
      </el-header>

      <el-main>
        <routerView/>
      </el-main>
    </el-container>
  </el-container>
  <el-backtop></el-backtop>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, watch} from "vue";
import {default as Icon} from "/@/components/basic/Icon.vue";
import {CustomMenu} from "./menu";

export default defineComponent({
  components: {
    CustomMenu,
    Icon
  },
  setup() {
    const pageHeight = ref(window.innerHeight + "px");

    const getPageRizeHeight = async () => {
      pageHeight.value = window.innerHeight + "px";
    };

    const collapseMenu = ref(false);

    const iconData = ref('ant-design:menu-fold-outlined');

    const menuWidth = ref('200px');

    onMounted(() => {
      window.addEventListener("resize", getPageRizeHeight);
    });

    watch(collapseMenu, (newValue, oldValue) => {
        iconData.value = newValue ? 'ant-design:menu-unfold-outlined' : 'ant-design:menu-fold-outlined';
        document.getElementsByClassName('icon-menu')[0].getElementsByTagName("svg")[0].setAttribute("data-icon", iconData.value);
        menuWidth.value = newValue ? '46px' : '200px';
    });

    const changeCollapse = () => collapseMenu.value = !collapseMenu.value;

    return {
      pageHeight,
      menuWidth,
      iconData,
      collapseMenu,
      changeCollapse,
    };
  },
});
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
  align-items:center;
}

.icon-menu{
  cursor: pointer;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
