<template>
  <template v-for="menu in menuData" :key="menu.path">
    <el-sub-menu
      :index="menu.path"
      v-if="menu.children && !menu.meta.hiddenMenu"
      :popper-offset="6"
    >
      <template #title>
        <span class="iconify" :data-icon="menu.meta.icon" v-if="menu.meta.icon"></span>
        <span :style="{paddingLeft: '4px'}">{{ menu.meta.title }}</span>
      </template>
      <MenuTree :menuData="menu.children"></MenuTree>
    </el-sub-menu>
    <el-menu-item v-else-if="!menu.meta.hiddenMenu" :index="menu.path">
      <span class="iconify" :data-icon="menu.meta.icon" v-if="menu.meta.icon"></span>
      <span :style="{paddingLeft: '4px'}">{{ menu.meta.title }}</span>
    </el-menu-item>
  </template>
</template>
<script lang="ts">
import {
  defineComponent,
  PropType,
  toRef,
} from "vue";
import { AppRouteModule } from "/@/router/types";

export default defineComponent({
  props: {
    menuData: {
      type: Array as PropType<Array<AppRouteModule>>,
      required: true,
    },
  },
  setup(props) {
    const menuData = toRef(props, "menuData");

    return { menuData };
  },
});
</script>
