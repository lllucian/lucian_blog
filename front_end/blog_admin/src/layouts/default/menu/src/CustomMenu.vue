<template>
  <el-menu
    :style="{minHeight: pageHeight}"
    @open="handleOpen"
    @close="handleClose"
    :router="true"
  >
    <MenuTree :menuData="basicRoutes"></MenuTree>
  </el-menu>
</template>

<script lang="ts">
import { defineComponent, ref, toRef } from "vue";
import {MenuTree} from '../../menu'
import {basicRoutes} from '/@/router/routes'

import {
  Location,
  Document,
  Menu as IconMenu,
  Setting,
} from "@element-plus/icons";

export default defineComponent({
  components: {
    Location,
    Document,
    Setting,
    IconMenu,
    MenuTree
  },
  props: {
    pageHeight: {type: String, required: false}
  },
  setup(props) {
    const pageHeight = toRef(props, 'pageHeight')
    const handleOpen = (key: any, keyPath: any) => {
      console.log(key, keyPath);
    };
    const handleClose = (key: any, keyPath: any) => {
      console.log(key, keyPath);
    };
    return {
      handleOpen,
      handleClose,
      pageHeight,
      basicRoutes,
    };
  },
});
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
}
</style>
