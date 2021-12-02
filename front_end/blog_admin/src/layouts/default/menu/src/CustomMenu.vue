<template>
  <el-menu
    :style="{minHeight: pageHeight}"
    :default-active="$route.meta.alias||$route.path"
    :router="true"
    :collapse="isCollapse"
    :collapse-transition="false"
  >
    <MenuTree :menuData="basicRoutes"></MenuTree>
  </el-menu>
</template>

<script lang="ts">
import { defineComponent, toRef } from "vue";
import {MenuTree} from '/@/layouts/default/menu'
import {basicRoutes} from '/@/router/routes'
import {router} from '/@/router'


export default defineComponent({
  components: {
    MenuTree
  },
  props: {
    pageHeight: {type: String, required: false},
    modelValue: {type: Boolean, required: false, default: false}
  },
  emits: ['update:modelValue'],
  setup(props) {
    const pageHeight = toRef(props, 'pageHeight');

    const isCollapse = toRef(props, "modelValue");

    return {
      pageHeight,
      basicRoutes,
      isCollapse,
    };
  },
});
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
}
</style>
