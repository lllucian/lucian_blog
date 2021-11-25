<template>
  <el-collapse model-value="DataForm" accordion>
    <el-collapse-item name="DataForm">
      <template #title>
        <span
          class="iconify"
          data-icon="ant-design:ordered-list-outlined"
        ></span>
        检索数据
      </template>
      <el-table :data="tableData" :border="true">
        <el-table-column type="selection" width="55" align="center" header-align="center"/>
        <el-table-column prop="date" label="Date" width="140">
        </el-table-column>
        <el-table-column prop="name" label="Name" width="120">
        </el-table-column>
        <el-table-column prop="address" label="Address"> </el-table-column>
      </el-table>
    </el-collapse-item>
  </el-collapse>
</template>
<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  setup() {
    const item = {
      date: "2016-05-02",
      name: "Tom",
      address: "No. 189, Grove St, Los Angeles",
    };

    const tableData = ref(Array(20).fill(item));
    return { tableData };
  },
});
</script>
<style scoped>
.el-collapse {
  padding: 8px;
  background: white;
}
</style>
