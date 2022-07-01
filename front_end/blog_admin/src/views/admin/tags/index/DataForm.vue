<template>
  <el-collapse model-value="DataForm" accordion v-loading="loadingTable">
    <el-collapse-item name="DataForm">
      <template #title>
        <span
          class="iconify"
          data-icon="ant-design:ordered-list-outlined"
        ></span>
        检索数据
      </template>
      <el-table :data="dataTable" :border="true" stripe>
        <el-table-column
          type="selection"
          width="55"
          align="center"
          header-align="center"
        />
        <el-table-column prop="name" label="名称" width="140" header-align="center"></el-table-column>
        <el-table-column prop="slug" label="别名" header-align="center"> </el-table-column>
        <el-table-column prop="sort" label="排序" header-align="center"> </el-table-column>
        <el-table-column prop="postCount" label="文章数" header-align="center"> </el-table-column>
        <el-table-column prop="description" label="描述" header-align="center"> </el-table-column>
        <el-table-column prop="createdAt" label="创建时间" width="170" align="center"> </el-table-column>
        <el-table-column fixed="right" label="操作" width="100" align="center">
          <template #default="scope">
            <router-link :to="'/admin/tag/'+scope.row.id">
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
<script lang="ts">
import { defineComponent, onMounted, ref, toRefs, watch } from "vue";
import Pagination from "/@/components/backend/data/Pagination.vue";
import { setPageConditions, apiFormData } from "./query";
import {deleteRequest} from "/@/requests";
import Icon from "/@/components/common/basic/Icon.vue"

export default defineComponent({
  components: { Pagination, Icon },
  props: {
    dataTable: {
      type: Array,
      default: [],
      required: false,
    },
    total: {
      type: Number,
      default: 0,
      required: false,
    },
    size: {
      type: Number,
      default: 10,
      required: false,
    },
    current: {
      type: Number,
      default: 1,
      required: false,
    },
    loadingTable: {
      type: Boolean,
      default: false,
      required: false,
    },
  },
  emits: [
    "update:dataTable",
    "update:size",
    "update:total",
    "update:current",
    "update:loadingTable",
  ],
  setup(props, { emit }) {
    const pageInfo = ref({
      current: 1,
      size: 10,
      total: 0,
    });

    const { dataTable, total, current, size, loadingTable } = toRefs(props);

    onMounted(() => {
      getList();
    });

    watch(total, (newValue, oldValue) => {
      pageInfo.value.total = newValue;
    });

    watch(current, (newValue, oldValue) => {
      pageInfo.value.current = newValue;
    });

    watch(size, (newValue, oldValue) => {
      pageInfo.value.size = newValue;
    });

    const getList = async () => {
      setPageConditions(pageInfo.value);
      emit("update:loadingTable", true);
      try {
        const { records, current, size, total } = (pageInfo.value =
          await apiFormData());
        pageInfo.value.current = current;
        pageInfo.value.size = size;
        pageInfo.value.total = total;
        emit("update:dataTable", records);
        emit("update:current", current);
        emit("update:size", size);
        emit("update:total", total);
      } finally {
        emit("update:loadingTable", false);
      }
    };

    const deleteRow = async (index:number, rows:Array<any>, id:number) => {
      rows.splice(index, 1);
      emit("update:loadingTable", true);
      try{
        await deleteRequest(`/admin/tag/${id}`);
      } finally {
        pageInfo.value.current = 1;
        await getList();
        emit("update:loadingTable", false);
      }

    }

    return { dataTable, pageInfo, getList, loadingTable, deleteRow };
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
