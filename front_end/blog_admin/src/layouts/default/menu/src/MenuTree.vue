<template>
  <template v-for="(menu, index) in menuData" :key="index">
    <el-sub-menu
      :index="menu.path"
      v-if="menu.children && !menu.meta.hiddenMenu"
    >
      <template #title>
        <span class="iconify" data-icon="gg:loadbar-doc"></span>
        <span>{{ menu.meta.title }}</span>
      </template>
      <MenuTree :menuData="menu.children"></MenuTree>
    </el-sub-menu>
    <el-menu-item v-else-if="!menu.meta.hiddenMenu" :index="menu.path">
      <span class="iconify" data-icon="gg:loadbar-doc"></span>
      <span>{{ menu.meta.title }}</span>
    </el-menu-item>
  </template>
</template>
<script lang="ts">
import {
  defineComponent,
  PropType,
  toRef,
  defineAsyncComponent,
  computed,
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

    // const dynamicImportIcon = computed((collection: string, icon: string) => {
    //   return defineAsyncComponent(() => import(`~icon/${collection}/${icon}`))
    // })
    return { menuData };
  },
});
</script>
