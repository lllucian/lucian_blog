<template>
  <el-menu
    :style="{minHeight: pageHeight}"
    @open="handleOpen"
    @close="handleClose"
  >
    <MenuTree :menuData="basicRoutes"></MenuTree>
    <!-- <el-sub-menu index="1">
      <template #title>
        <el-icon><location /></el-icon>
        <span>Navigator One</span>
      </template>
      <el-menu-item-group title="Group One">
        <el-menu-item index="1-1">item one</el-menu-item>
        <el-menu-item index="1-2">item one</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="Group Two">
        <el-menu-item index="1-3">item three</el-menu-item>
      </el-menu-item-group>
      <el-sub-menu index="1-4">
        <template #title>item four</template>
        <el-menu-item index="1-4-1">item one</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
    <el-menu-item index="2">
      <el-icon><icon-menu /></el-icon>
      <span>Navigator Two</span>
    </el-menu-item>
    <el-menu-item index="3" disabled>
      <el-icon><document /></el-icon>
      <span>Navigator Three</span>
    </el-menu-item>
    <el-menu-item index="4">
      <el-icon><setting /></el-icon>
      <span>Navigator Four</span>
    </el-menu-item>
  </el-menu> -->
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
