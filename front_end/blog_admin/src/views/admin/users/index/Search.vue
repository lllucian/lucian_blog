<template>
  <el-collapse model-value="search">
    <el-collapse-item name="search">
      <template #title>
        <span class="iconify" data-icon="bi:search"></span>
        检索
      </template>
      <el-form :model="SearchFormData" label-position="top" label-width="120px" ref="SearchForm">
        <el-row>
          <el-col :span="8">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="SearchFormData.username" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="SearchFormData.email" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="权限" prop="roleId">
              <el-select-v2
                  v-model="SearchFormData.roleId"
                  filterable
                  :options="roleOptions"
                  remote
                  :remote-method="roleRemoteMethod"
                  :loading="roleLoading"
                  style="width: 100%"
                  multiple
                  placeholder="请选择角色"
                  clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="justify-content: center">
          <el-button type="primary" @click="commitSearchForm(SearchForm)">检索</el-button>
          <el-button type="warning" @click="clearSearchForm(SearchForm)"
          >条件重置</el-button
          >
        </el-row>
      </el-form>
    </el-collapse-item>
  </el-collapse>
</template>
<script lang="ts" setup>
import {ref} from "vue";
import {
  apiFormData,
  getPageSize,
  queryConditionsByPageInterface,
  setQueryConditions
} from "./query";
import {FormInstance} from "element-plus";
import {postRequest} from "/@/requests";

const SearchForm = ref<FormInstance>();

const SearchFormData = ref<queryConditionsByPageInterface>({
  username: '',
  email: '',
  roleId: '',
  size: 10,
  current: 1,
  total: 0,
  records: []
});
const emits = defineEmits<{(e: 'update:loadingTable', isLoading: Boolean) :void,
  (e: 'update:dataTable', records: Array<any>) :void,
  (e: 'update:total', total: Number) :void,
  (e: 'update:size', size: Number) :void,
  (e: 'update:current', current: Number) :void}>();

const commitSearchForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  SearchFormData.value.size = getPageSize();
  setQueryConditions(SearchFormData.value);
  emits("update:loadingTable", true);
  try {
    const { records, current, size, total } = await apiFormData();
    emits("update:dataTable", records);
    emits("update:size", size);
    emits("update:current", current);
    emits("update:total", total);
  } finally {
    emits("update:loadingTable", false);
  }
};

const roleLoading = ref<boolean>(false)

const roleOptions = ref([]);

const roleRemoteMethod = async (query: string) => {
  if (query.trim() !== "") {
    roleLoading.value = true;
    const data = postRequest("/admin/fetch/roles", { query: query });
    if ((await data).data) roleOptions.value = (await data).data;
    roleLoading.value = false;
  } else {
    roleOptions.value = [];
  }
};

const clearSearchForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};
</script>
<style scoped>
.el-collapse {
  padding: 8px;
  background: white;
}
</style>
<style>
.el-collapse-item__header {
  padding-left: 8px;
}

.el-collapse-item__wrap {
  border-bottom: unset;
}
</style>
<style lang="scss">
.el-col {
  padding-right: 8px;
  &:nth-child(3) {
    padding-right: 0;
  }
  &:nth-child(5) {
    padding-right: 0;
  }
}
</style>