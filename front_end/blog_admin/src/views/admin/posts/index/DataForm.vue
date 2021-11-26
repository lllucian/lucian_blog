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
      <el-table :data="tableData" :border="true" stripe>
        <el-table-column
          type="selection"
          width="55"
          align="center"
          header-align="center"
        />
        <el-table-column prop="categories" label="分类" width="140">
        </el-table-column>
        <el-table-column label="标签" width="120">
          <template #default="scope">
            <el-tag
              size="medium"
              v-for="(tag, index) in scope.row.tags"
              :key="index"
              >{{ tag }}</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="title" label="标题"> </el-table-column>
        <el-table-column prop="description" label="描述"> </el-table-column>
        <el-table-column prop="createdAt" label="创建时间"> </el-table-column>
        <el-table-column prop="updatedAt" label="修改时间"> </el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template #default>
            <el-button type="text" size="small">编辑</el-button>
            <el-button type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-collapse-item>
  </el-collapse>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import { getRequest } from "/@/requests";

export default defineComponent({
  setup() {

    const tableData = ref([]);

    onMounted(async() => {
      const data = await getRequest("api/admin/posts");
      if (data.data && data.data.recourds){ 
        console.log(data.data.recourds)
      }else {
        tableData.value = [];
      }
    });

    return { tableData };
  },
});
</script>
<style scoped>
.el-collapse {
  padding: 8px;
  background: white;
}

.el-tag + .el-tag {
  margin-left: 10px;
}
</style>
