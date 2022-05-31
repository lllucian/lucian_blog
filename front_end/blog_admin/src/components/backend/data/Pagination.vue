<template>
  <el-pagination
    v-model:currentPage="currentPage"
    :page-sizes="[10, 20, 50, 100]"
    v-model:pageSize="pageSize"
    layout="total, sizes, prev, pager, next"
    v-model:total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    background
    v-bind="$attrs"
  >
  </el-pagination>
</template>
<script lang="ts">
import { defineComponent, toRefs } from "vue";

export default defineComponent({
  props: {
    currentPage: {
      type: Number,
      default: 1,
      required: true,
    },
    pageSize: {
      type: Number,
      default: 10,
      required: true,
    },
    total: {
      type: Number,
      required: true,
    },
  },
  emits: ["update:currentPage", "update:pageSize", "pagination", "update:total"],
  setup(props, { emit }) {
    const { currentPage, pageSize, total } = toRefs(props);

    const handleSizeChange = (value: number) => {
      emit("update:pageSize", value);
      emit("pagination", {currentPage, pageSize});
    };

    const handleCurrentChange = (value: number) => {
      emit("update:currentPage", value);
      emit("pagination", {currentPage, pageSize});
    };
    return {
      currentPage,
      pageSize,
      total,
      handleSizeChange,
      handleCurrentChange,
    };
  },
});
</script>
