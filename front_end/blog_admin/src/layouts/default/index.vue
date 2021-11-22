<template>
  <el-container :style="{height: pageHeight, border: '1px solid #eee'}">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <CustomMenu :pageHeight = "pageHeight"></CustomMenu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <el-icon style="margin-right: 15px"><setting /></el-icon>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>View</el-dropdown-item>
              <el-dropdown-item>Add</el-dropdown-item>
              <el-dropdown-item>Delete</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <span>Tom</span>
      </el-header>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="date" label="Date" width="140">
          </el-table-column>
          <el-table-column prop="name" label="Name" width="120">
          </el-table-column>
          <el-table-column prop="address" label="Address"> </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue'
import { Message, Setting } from '@element-plus/icons'
import { CustomMenu } from './menu'

export default defineComponent({
  components: {
    Message,
    Setting,
    CustomMenu
  },
  setup() {
    const item = {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles',
    }

    const tableData = ref(Array(20).fill(item))

    const pageHeight = ref(window.innerHeight + 'px');

    const getPageRizeHeight = async () => {
        pageHeight.value = window.innerHeight + 'px';
    }
    
   onMounted(() => {
       window.addEventListener("resize", getPageRizeHeight);
   })

    return {
      tableData,
      pageHeight
    }
  },
})
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  line-height: 60px;
}

.el-aside {
  color: var(--el-text-color-primary);
}
body {
    margin: 0;
}
</style>
