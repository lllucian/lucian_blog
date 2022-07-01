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
            <el-form-item label="分类" prop="categories">
              <el-select-v2
                v-model="SearchFormData.categories"
                filterable
                :options="categoryOptions"
                remote
                :remote-method="categoryRemoteMethod"
                :loading="categoryLoading"
                style="width: 100%"
                multiple
                placeholder="请选择分类"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标签" prop="tags">
              <el-select-v2
                v-model="SearchFormData.tags"
                filterable
                :options="tagOptions"
                remote
                :remote-method="tagRemoteMethod"
                :loading="tagLoading"
                style="width: 100%"
                multiple
                placeholder="请选择标签"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题" prop="title">
              <el-input
                v-model="SearchFormData.title"
                placeholder="请输入"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <DateTimePickerComponent
              labelName="创建时间"
              propName="created"
              v-model="SearchFormData.created"
            ></DateTimePickerComponent>
          </el-col>
          <el-col :span="12">
            <DateTimePickerComponent
              labelName="修改时间"
              propName="updated"
              v-model="SearchFormData.updated"
            ></DateTimePickerComponent>
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
import DateTimePickerComponent from "/@/components/backend/form/datepicker/DateTimePickerComponent.vue";
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
      categories:  new Array<number>(),
      title: "",
      tags: new Array<number>(),
      created: new Array<string>(),
      updated: new Array<string>(),
      size: 10,
      current: 1,
      total: 0,
      records: [],
    });

    const categoryOptions = ref([]);

    const categoryLoading = ref(false);

    const tagOptions = ref([]);

    const tagLoading = ref(false);

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

    const categoryRemoteMethod = async (query: string) => {
      if (query.trim() !== "") {
        categoryLoading.value = true;
        const data = postRequest("/admin/fetch/categories", { name: query });
        if ((await data).data) categoryOptions.value = (await data).data;
        categoryLoading.value = false;
      } else {
        categoryOptions.value = [];
      }
    };

    const tagRemoteMethod = async (query: string) => {
      if (query.trim() !== "") {
        tagLoading.value = true;
        const data = postRequest("/admin/fetch/tags", { name: query });
        if ((await data).data) tagOptions.value = (await data).data;
        tagLoading.value = false;
      } else {
        tagOptions.value = [];
      }
    };
    return {
      SearchForm,
      SearchFormData,
      commitSearchForm,
      clearSearchForm,
      categoryRemoteMethod,
      categoryOptions,
      categoryLoading,
      tagLoading,
      tagOptions,
      tagRemoteMethod,
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
