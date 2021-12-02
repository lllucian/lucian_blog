<template>
  <el-page-header content="分类列表" @back="goBack"/>
  <el-card>
    <template #header>
      <div class="card-header">
        <h4>{{ title }}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top">
      <el-form-item label="名称" prop="name">
        <el-input v-model="formData.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="别名" prop="slut">
        <el-input v-model="formData.slut" clearable></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="formData.description" type="textarea" autosize clearable></el-input>
      </el-form-item>
      <el-form-item label="父分类" prop="parentId">
        <el-select-v2 v-model="formData.parentId"
                      :options="parentOptions"
                      @focus="parentRemoteMethod"
                      style="width: 100%;"
                      v-loading="parentLoading"
                      filterable
                      clearable>
        </el-select-v2>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input-number v-model="formData.sort" :min="1" :precision="0"></el-input-number>
      </el-form-item>
      <el-row style="justify-content: center">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button type="warning" @click="clearForm">清空表单内容
        </el-button>
      </el-row>
    </el-form>
  </el-card>
</template>

<script lang="ts">
import {defineComponent, ref, toRefs} from "vue";
import {router} from "/@/router";
import {postRequest} from "/@/requests";

export default defineComponent({
  props: {
    title: {
      type: String,
      required: true
    }
  },
  setup(props) {
    const {title} = toRefs(props);
    const formData = ref({
      name: '',
      slut: '',
      parentId: '',
      sort: 1,
      description: '',
    });

    const form = ref();

    const rules = ref({
      name: [
        {required: true, message: "名称必填", trigger: "blur"},
        {max: 255, message: '名称最长255个字符'}
      ],
      slut: [
        {required: true, message: "内容必填", trigger: "blur"}
      ],
      sort: [
        {required: true, message: "分类必选"}
      ]
    });

    const parentLoading = ref(false);

    const parentOptions = ref([]);

    const parentRemoteMethod = async () => {
      parentLoading.value = true;
      try {
        const data = postRequest("api/admin/fetch/getParentId");
        if ((await data).data) parentOptions.value = (await data).data;
      } finally {
        parentLoading.value = false;
      }
    }

    const clearForm = () => {
      form.value.resetFields();
    }

    const submitForm = async () => {
      // const valid = await form.value.validate();
    }

    const goBack = () => {
      router.push({name: 'AdminCategoryIndex'})
    }
    return {
      form,
      formData,
      title,
      goBack,
      rules,
      parentLoading,
      parentOptions,
      parentRemoteMethod,
      clearForm,
      submitForm,
    }
  }
})
</script>
<style>
.is-error .el-select-v2__wrapper {
  border-color: var(--el-color-danger);
}
</style>