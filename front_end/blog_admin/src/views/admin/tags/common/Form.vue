<template>
  <el-page-header content="标签列表" @back="goBack"/>
  <el-card>
    <template #header>
      <div class="card-header">
        <h4>{{ title }}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top" v-loading="formLoading">
      <el-form-item label="名称" prop="name">
        <el-input v-model="formData.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="别名" prop="slug">
        <el-input v-model="formData.slug" clearable></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="formData.description" type="textarea" autosize clearable></el-input>
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
import {defineComponent, onMounted, ref, toRefs} from "vue";
import {router} from "/@/router";
import {getRequest, postRequest, putRequest} from "/@/requests";

export default defineComponent({
  props: {
    title: {
      type: String,
      required: true
    },
    tagId: {
      type: String,
      required: false
    }
  },
  setup(props) {
    const {title, tagId} = toRefs(props);
    const formData = ref({
      name: '',
      slug: '',
      parentId: '',
      sort: 1,
      description: '',
    });

    const form = ref();

    const formLoading = ref(false);

    const validSlutRegex = (rule: any, value: string, callback: Function) => {
      value ? new RegExp(/^\w+$/).test(value) ? callback() : callback(new Error("别名要为字母数字加连字符(-)的组合")) : callback()
    }

    const rules = ref({
      name: [
        {required: true, message: "名称必填", trigger: "blur"},
        {max: 255, message: '名称最长255个字符'}
      ],
      slug: [
        {required: true, message: "别名必填", trigger: "blur"},
        {validator: validSlutRegex, trigger: "blur"},
      ],
      sort: [
        {required: true, message: "分类必选"}
      ]
    });

    const parentLoading = ref(false);

    const parentOptions = ref([]);

    const clearForm = () => {
      form.value.resetFields();
    }

    const submitForm = async () => {
      const valid = await form.value.validate();
      if (valid){
        formLoading.value = true;
        try{
          let data = undefined;
          if(tagId.value){
            data = await putRequest(`api/admin/tag/${tagId.value}`, formData.value);
          } else {
            data = await postRequest("api/admin/tag", formData.value);
          }
          if (data) await router.push({name: 'AdminTagIndex'})
        } finally {
          formLoading.value = false;
        }

      }
    }

    const getInformation = async () => {
      formLoading.value = true;
      try{
        const data = await getRequest(`api/admin/tag/${tagId.value}`);
        if (data && data.data){
          formData.value = data.data;
        } else {
          await router.push({name: 'AdminTagIndex'});
        }
      } finally {
        formLoading.value = false;
      }
    };

    if (tagId && tagId.value){
      onMounted(() => {
        getInformation();
      });
    }

    const goBack = () => {
      router.push({name: 'AdminTagIndex'})
    }
    return {
      form,
      formData,
      title,
      goBack,
      rules,
      parentLoading,
      parentOptions,
      clearForm,
      submitForm,
      formLoading,
    }
  }
})
</script>
<style>
.is-error .el-select-v2__wrapper {
  border-color: var(--el-color-danger);
}
</style>
