<template>
  <el-page-header content="标签列表" @back="goBack"/>
  <el-card>
    <template #header>
      <div class="card-header">
        <h4>{{ propData.title }}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top"
             v-loading="formLoading">
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
        <el-button type="primary" @click="submitForm(form)">提交</el-button>
        <el-button type="warning" @click="clearForm(form)">清空表单内容
        </el-button>
      </el-row>
    </el-form>
  </el-card>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {getRequest, postRequest, putRequest} from "/@/requests";
import {RouteParamValue, useRouter} from "vue-router";
import {FormInstance} from "element-plus";
const router = useRouter();

interface Props {
  title: string,
  tagId?: string | RouteParamValue[]
}

const propData = withDefaults(defineProps<Props>(), {
  title: '',
  tagId: ''
});

const form = ref<FormInstance>();

let formData = reactive<{
  name: string,
  slug: string,
  description: string,
  sort: number
}>({
  name: '',
  slug: '',
  description: '',
  sort: 1
});

const formLoading = ref<boolean>(false);

const validSlutRegex = (rule: any, value: string, callback: Function) => {
  value ? new RegExp(/^\w+$/).test(value) ? callback() : callback(new Error("别名要为字母数字加连字符(-)的组合")) : callback()
}

const rules = reactive({
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

const goBack = () => {
  router.push({name: 'AdminTagIndex'})
}

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid, fields) => {
    if (valid) {
      if (propData.tagId) {
        putRequest(`/api/admin/tag/${propData.tagId}`, formData);
      } else {
        postRequest(`/api/admin/tag`, formData);
      }
      router.push({name: 'AdminTagIndex'});
    } else {
      console.log('error submit!', fields);
    }
  });
}

const clearForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
}


const getInformation = async () => {
  formLoading.value = true;
  try{
    const data = await getRequest(`api/admin/tag/${propData.tagId}`);
    if (data && data.data){
      Object.assign(formData, data.data);
    } else {
      await router.push({name: 'AdminTagIndex'});
    }
  } finally {
    formLoading.value = false;
  }
};

if (propData.tagId){
  onMounted(() => {
    getInformation();
  });
}
</script>
<style>
.is-error .el-select-v2__wrapper {
  border-color: var(--el-color-danger);
}
</style>
