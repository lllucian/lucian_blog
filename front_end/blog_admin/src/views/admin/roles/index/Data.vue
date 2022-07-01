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
        <el-table-column prop="name" label="权限名" width="140" header-align="center"></el-table-column>
        <el-table-column prop="zhName" label="权限中文名" header-align="center"> </el-table-column>
        <el-table-column prop="accountNumber" label="关联账号" align="center"> </el-table-column>
        <el-table-column fixed="right" label="操作" width="100" align="center">
          <template #default="scope">
            <router-link :to="'/admin/role/'+scope.row.id">
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
import { defineComponent, onMounted, ref, toRefs, watch } from "vue";
import Pagination from "/@/components/backend/data/Pagination.vue";
import { setPageConditions, apiFormData } from "./query";
import {deleteRequest} from "/@/requests";
import Icon from "/@/components/common/basic/Icon.vue"
const props  = defineProps<{loadingTable :boolean, dataTable :Array<any>, total: number, size: number, current :number}>();
const emits = defineEmits<{(e: 'update:loadingTable', isLoading: Boolean) :void,
    (e: 'update:dataTable', records: Array<any>) :void,
    (e: 'update:total', total: Number) :void,
    (e: 'update:size', size: Number) :void,
    (e: 'update:current', current: Number) :void}>();

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

const deleteRow = async (index: number, rows: Array<Object>, id: number) => {
  rows.splice(index, 1);
  emits("update:loadingTable", true);
  try {
    await deleteRequest(`/admin/role/${id}`);
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
