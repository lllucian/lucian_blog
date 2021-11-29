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
      <el-table :data="dataTable" :border="true" stripe>
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
      <Pagination v-model:currentPage="pageInfo.current" v-model:pageSize="pageInfo.size" v-model:total="pageInfo.total" @pagination="getList"></Pagination>
    </el-collapse-item>
  </el-collapse>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref, toRef, toRefs, watch } from "vue";
import Pagination from "/@/components/data/Pagination.vue";
import { setPageConditions, apiFormData } from "./query";

export default defineComponent({
  components: { Pagination },
  props: {
    dataTable: {
      type: Array,
      default: [],
      required: false,
    },
    total: {
      type: Number,
      default: 0,
      required: false
    },
    size: {
      type: Number,
      default: 10,
      required: false
    },
    current: {
      type: Number,
      default: 1,
      required: false
    }
  },
  emits: ['update:dataTable', 'update:size', 'update:total', 'update:current'],
  setup(props, {emit}) {
    const pageInfo = ref({
      current: 1,
      size: 10,
      total: 0,
    });

    const {dataTable, total, current, size} = toRefs(props);

    onMounted(async () => {
      getList();
    });

    watch(total, (newValue, oldValue) => {
      pageInfo.value.total = newValue;
    });

    watch(current, (newValue, oldValue) => {
      pageInfo.value.current = newValue;
    })
    
    watch(size, (newValue, oldValue) => {
      pageInfo.value.size = newValue;
    });

    const getList = async () => {
      setPageConditions(pageInfo.value)
      const {records, current, size, total} = pageInfo.value = await apiFormData();
      pageInfo.value.current = current;
      pageInfo.value.size = size;
      pageInfo.value.total = total;
      emit('update:dataTable', records);
      emit('update:current', current);
      emit('update:size', size);
      emit('update:total', total);
    };

    return { dataTable, pageInfo, getList};
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
