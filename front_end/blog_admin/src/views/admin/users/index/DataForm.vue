<template>
  <el-collapse model-value="DataForm" accordion v-loading="props.loadingTable">
    <el-collapse-item name="DataForm">
      <template #title>
        <span
            class="iconify"
            data-icon="ant-design:ordered-list-outlined"
        ></span>
        检索数据
      </template>
      <el-table :data="props.dataTable" :border="true" stripe>
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
                size="large"
                v-for="(tag, index) in scope.row.tags"
                :key="index"
            >{{ tag }}
            </el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="signLastIp" label="上次登录IP"></el-table-column>
        <el-table-column prop="signCurrentIp" label="当前登录IP"></el-table-column>
        <el-table-column prop="signCurrentIp" label="角色">
          <template #default="scope">
            <el-tag
                size="large"
                v-for="(role, index) in scope.row.roles"
                :key="index"
            >{{ role }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="120" align="center">
          <template #default="scope">
            <router-link :to="'/admin/post/'+scope.row.id">
              <el-button type="text">
                <Icon icon="ant-design:edit-outlined" :size="18"></Icon>
              </el-button>
            </router-link>
            <el-popconfirm title="确认删除吗？" @confirm="deleteRow(scope.$index, dataTable, scope.row.id)">
              <template #reference>
                <el-button type="text">
                  <Icon icon="akar-icons:trash-can" color="red" :size="18"></Icon>
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <Pagination
          v-model:currentPage="pageInfo.current"
          v-model:pageSize="pageInfo.size"
          v-model:total="pageInfo.total"
          @pagination="getList"
      ></Pagination>
    </el-collapse-item>
  </el-collapse>
</template>
<script lang="ts" setup>
import {defineProps, onMounted, ref} from "vue";
import Pagination from "/@/components/backend/data/Pagination.vue";
import {setPageConditions, apiFormData} from "./query";
import {deleteRequest} from "/@/requests";
import Icon from "/@/components/common/basic/Icon.vue";

const props = defineProps<{ loadingTable: boolean, dataTable: Array<any>, total: number, size: number, current: number }>();

const emits = defineEmits<{
  (e: 'update:loadingTable', isLoading: Boolean): void,
  (e: 'update:dataTable', records: Array<any>): void,
  (e: 'update:total', total: Number): void,
  (e: 'update:size', size: Number): void,
  (e: 'update:current', current: Number): void
}>();

const pageInfo = ref({
  current: 1,
  size: 10,
  total: 0,
});
const getList = (async () => {
  setPageConditions(pageInfo.value);
  emits("update:loadingTable", true);
  try {
    const {records, current, size, total} = (pageInfo.value =
        await apiFormData());
    pageInfo.value.current = current;
    pageInfo.value.size = size;
    pageInfo.value.total = total;
    emits("update:dataTable", records);
    emits("update:current", current);
    emits("update:size", size);
    emits("update:total", total);
  } finally {
    emits("update:loadingTable", false);
  }
})

onMounted(() => getList());

const deleteRow = async (index: number, rows: any[], id: number) => {
  rows.splice(index, 1);
  emits("update:loadingTable", true);
  try {
    await deleteRequest(`/admin/user/${id}`);
  } finally {
    pageInfo.value.current = 1;
    await getList();
    emits("update:loadingTable", false);
  }
}
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
