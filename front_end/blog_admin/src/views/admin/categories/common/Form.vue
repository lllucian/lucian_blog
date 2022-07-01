<template>
  <el-page-header content="分类列表" @back="goBack"/>
  <el-card>
    <template #header>
      <div class="card-header">
        <h4>{{ props.title }}</h4>
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
        <el-button type="primary" @click="submitForm(form)">提交</el-button>
        <el-button type="warning" @click="clearForm(form)">清空表单内容
        </el-button>
      </el-row>
    </el-form>
  </el-card>
</template>

<script lang="ts" setup>
import {defineComponent, onMounted, reactive, ref, toRefs} from "vue";
import {getRequest, postRequest, putRequest} from "/@/requests";
import {FormInstance} from "element-plus";
import {RouteParamValue, useRouter} from "vue-router";

const router = useRouter();

interface Props {
  title: string,
  categoryId?: string | RouteParamValue[]
}

const props = defineProps<Props>();

const form = ref<FormInstance>();

const formLoading = ref<boolean>(false);

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate(async (valid) => {
    if (valid) {
      formLoading.value = true;
      try {
        let data = undefined;
        if (props.categoryId) {
          data = putRequest(`/admin/category/${props.categoryId}`, formData);
        } else {
          data = postRequest("/admin/category", formData);
        }
        if (await data) await router.push({name: 'AdminCategoryIndex'})
      } finally {
        formLoading.value = false;
      }
    }
  });
}

const clearForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
}

const formData = reactive({
  name: '',
  slug: '',
  parentId: '',
  sort: 1,
  description: '',
});


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

const parentLoading = ref<boolean>(false);

const parentOptions = ref<Array<{ label: string, value: string }>>([]);

const parentRemoteMethod = async () => {
  parentLoading.value = true;
  try {
    let fetchUri = "/admin/fetch/getParentId";
    if (props.categoryId) fetchUri = `${fetchUri}/${props.categoryId}`;
    const data = postRequest(fetchUri);
    if ((await data).data) parentOptions.value = (await data).data;
  } finally {
    parentLoading.value = false;
  }
}

const getInformation = async () => {
  formLoading.value = true;
  try{
    const data = await getRequest(`/admin/category/${props.categoryId}`);
    if (data && data.data){
      Object.assign(formData, data.data);
    } else {
      await router.push({name: 'AdminCategoryIndex'});
    }
  } finally {
    formLoading.value = false;
  }
};

if (props.categoryId){
  onMounted(() => {
    getInformation();
    parentRemoteMethod();
  });
}

const goBack = () => {
  router.push({name: 'AdminCategoryIndex'})
}
</script>
<style>
.is-error .el-select-v2__wrapper {
  border-color: var(--el-color-danger);
}
</style>
