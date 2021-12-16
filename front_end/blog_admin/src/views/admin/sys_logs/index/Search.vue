<template>
  <el-collapse model-value="search">
    <el-collapse-item name="search">
      <template #title>
        <span class="iconify" data-icon="bi:search"></span>
        检索
      </template>
      <el-form
        ref="SearchForm"
        :model="SearchFormData"
        label-width="120px"
        label-position="top"
      >
        <el-row>
          <el-col :span="8">
            <el-form-item label="操作者" prop="userId">
              <el-select-v2
                  v-model="SearchFormData.userId"
                  filterable
                  :options="userOptions"
                  remote
                  :remote-method="userRemoteMethod"
                  :loading="userLoading"
                  style="width: 100%"
                  placeholder="请选择操作者"
                  clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="方法" prop="method">
              <el-input v-model="SearchFormData.method" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作" prop="operation">
              <el-input v-model="SearchFormData.operation" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="justify-content: center">
          <el-button type="primary" @click="commitSearchForm">检索</el-button>
          <el-button type="warning" @click="clearSearchForm"
            >条件重置</el-button
          >
          <!-- </el-form-item> -->
        </el-row>
      </el-form>
    </el-collapse-item>
  </el-collapse>
</template>

<script lang="ts">
import { defineComponent, Ref, ref } from "vue";
import { postRequest } from "/@/requests";
import DateTimePickerComponent from "/@/components/form/datepicker/DateTimePickerComponent.vue";
import {
  apiFormData,
  getPagesize,
  queryConditionsByPageInterface,
  setQueryConditions,
} from "./query";

export default defineComponent({
  components: {
    DateTimePickerComponent,
  },
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
    "update:total",
    "update:size",
    "update:current",
    "update:loadingTable",
  ],
  setup(props, { emit }) {
    const SearchForm = ref();
    let SearchFormData: Ref<queryConditionsByPageInterface> = ref({
      userId: null,
      method: '',
      operation: '',
      size: 10,
      current: 1,
      total: 0,
      records: [],
    });

    const userOptions = ref([]);

    const userLoading = ref(false);

    const commitSearchForm = async () => {
      SearchFormData.value.size = getPagesize();
      setQueryConditions(SearchFormData.value);
      emit("update:loadingTable", true);
      try {
        const { records, current, size, total } = await apiFormData();
        emit("update:dataTable", records);
        emit("update:size", size);
        emit("update:current", current);
        emit("update:total", total);
      } finally {
        emit("update:loadingTable", false);
      }
    };

    const clearSearchForm = () => {
      SearchForm.value.resetFields();
    };

    const userRemoteMethod = async (query: string) => {
      if (query.trim() !== "") {
        userLoading.value = true;
        const data = postRequest("api/admin/fetch/users", { name: query });
        if ((await data).data) userOptions.value = (await data).data;
        userLoading.value = false;
      } else {
        userOptions.value = [];
      }
    };

    return {
      SearchForm,
      SearchFormData,
      commitSearchForm,
      clearSearchForm,
      userOptions,
      userRemoteMethod,
      userLoading,
    };
  },
});
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
