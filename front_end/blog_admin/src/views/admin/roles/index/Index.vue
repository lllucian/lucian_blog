<template>
  <el-row>
    <el-col :span="24">
      <SearchForm v-model:dataTable="tableInfo.dataTable" v-model:size="tableInfo.size" v-model:current="tableInfo.current" v-model:total="tableInfo.total" v-model:loadingTable="loadingTable"/>
    </el-col>
  </el-row>
  <el-row>
    <el-col :span="24">
      <DataForm v-model:dataTable="tableInfo.dataTable" v-model:size="tableInfo.size" v-model:current="tableInfo.current" v-model:total="tableInfo.total" v-model:loadingTable="loadingTable"></DataForm>
    </el-col>
  </el-row>
</template>
<script lang="ts" setup>
import SearchForm from './Search.vue'
import DataForm from './Data.vue'
import {ref} from "vue";

const tableInfo = ref({
  dataTable: [],
  size: 10,
  current: 1,
  total: 0
});
const loadingTable = ref(false);
</script>
<style lang="scss" scoped>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
</style>