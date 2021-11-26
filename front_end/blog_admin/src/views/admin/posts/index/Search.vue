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
            <el-form-item label="分类" prop="category">
              <el-select-v2
                v-model="SearchFormData.category"
                filterable
                :options="categoryOptions"
                remote
                :remote-method="categoryRemoteMethod"
                :loading="categoryLoading"
                style="width: 100%"
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
            <DateTimePickerCommponet labelName='创建时间' propName="created" v-model="SearchFormData.created"></DateTimePickerCommponet>
          </el-col>
          <el-col :span="12">
            <DateTimePickerCommponet labelName='修改时间' propName="updated" v-model="SearchFormData.updated"></DateTimePickerCommponet>
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
import { defineComponent, ref } from "vue";
import { postRequest } from "/@/requests";
import DateTimePickerCommponet from "/@/components/form/DateTimePickerCommponet.vue"

export default defineComponent({
  components: {
    DateTimePickerCommponet
  },
  setup() {
    const SearchForm = ref();
    let SearchFormData = ref({
      category: "",
      title: "",
      tags: new Array(),
      created: "",
      updated: "",
      deleted: false,
    });

    const categoryOptions = ref([]);

    const categoryLoading = ref(false);

    const tagOptions = ref([]);

    const tagLoading = ref(false);

    const commitSearchForm = async () => {};

    const clearSearchForm = async () => {
      SearchForm.value.resetFields();
    };

    const categoryRemoteMethod = async (query: string) => {
      if (query.trim() !== "") {
        categoryLoading.value = true;
        const data = postRequest("api/admin/fetch/categories", { name: query });
        if ((await data).data) categoryOptions.value = (await data).data;
        categoryLoading.value = false;
      } else {
        categoryOptions.value = [];
      }
    };

    const tagRemoteMethod = async (query: string) => {
      if (query.trim() !== "") {
        tagLoading.value = true;
        const data = postRequest("api/admin/fetch/tags", { name: query });
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
