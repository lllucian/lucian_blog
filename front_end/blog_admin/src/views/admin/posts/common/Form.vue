<template>
  <el-page-header content="文章一览" @back="goBack"/>
  <el-card>
    <template #header>
      <div>
        <h4>{{ props.title }}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top"
             v-loading="formLoading">
      <el-form-item label="标题" prop="title">
        <el-input v-model="formData.title"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="formData.description" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <TEditor ref="editor" v-model="formData.content"/>
      </el-form-item>
      <el-form-item label="分类" prop="categories">
        <el-select-v2
            v-model="formData.categories"
            filterable
            :options="categoryOptions"
            remote
            :remote-method="categoryRemoteMethod"
            :loading="categoryLoading"
            style="width: 100%"
            placeholder="请选择分类"
            multiple
            clearable
        />
      </el-form-item>
      <el-form-item label="标签" prop="tags">
        <el-select-v2
            v-model="formData.tags"
            filterable
            :options="tagOptions"
            remote
            :remote-method="tagRemoteMethod"
            :loading="tagLoading"
            style="width: 100%"
            placeholder="请选择标签"
            multiple
            clearable
        />
      </el-form-item>
      <el-form-item label="封面" prop="uploadFileId">
        <UploadImage v-model="formData.uploadFileId" v-model:imageUrl="imageUrl"/>
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
import UploadImage from "/@/components/backend/form/upload/UploadImage.vue";
import TEditor from "/@/components/backend/form/tinymce/TEditor.vue"
import {RouteParamValue, useRouter} from "vue-router";
import {FormInstance} from "element-plus";

const router = useRouter();

interface Props {
  title: string,
  postId?: string | RouteParamValue[]
}

const props = withDefaults(defineProps<Props>(), {
  title: '',
  postId: ''
});

const form = ref<FormInstance>();

let formData = reactive<{
  title: string,
  description: string,
  content: string,
  categories: Array<string>,
  tags: Array<string>,
  uploadFileId: string
}>({
  title: '',
  description: '',
  content: '',
  categories: [],
  tags: [],
  uploadFileId: ''
});

const imageUrl = ref('');

const formLoading = ref<boolean>(false);

const rules = reactive({
  title: [
    {required: true, message: "标题必填", trigger: "blur"},
    {max: 255, message: '标题最长255个字符'}
  ],
  content: [
    {required: true, message: "内容必填", trigger: "change"}
  ],
  categories: [
    {required: true, message: "分类必选"}
  ],
  uploadFileId: [
    {required: true, message: "封面必须上传"}
  ]
});

const categoryLoading = ref<boolean>(false);

const categoryOptions = ref<Array<{ label: string, value: string }>>([]);

const categoryRemoteMethod = async (query: string) => {
  if (query.trim() !== "") {
    categoryLoading.value = true;
    try {
      const data = postRequest("/admin/fetch/categories", {name: query});
      if ((await data).data) categoryOptions.value = (await data).data;
    } finally {
      categoryLoading.value = false;
    }
  } else {
    categoryOptions.value = [];
  }
}

const tagLoading = ref<boolean>(false);

const tagOptions = ref<Array<{ label: string, value: string }>>([]);

const tagRemoteMethod = async (query: string) => {
  if (query.trim() !== "") {
    tagLoading.value = true;
    try {
      const data = postRequest("/admin/fetch/tags", {name: query});
      if ((await data).data) tagOptions.value = (await data).data;
    } finally {
      tagLoading.value = false;
    }
  } else {
    tagOptions.value = [];
  }
}

const clearForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
}

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      formLoading.value = true;
      try {
        let data;
        if (props.postId) {
          data = await putRequest(`/admin/post/${props.postId}`, formData);
        } else {
          data = await postRequest("/admin/post", formData);
        }
        if (await data) await router.push({name: 'AdminPostIndex'})
      } finally {
        formLoading.value = false;
      }
    } else {
      console.log('error submit!', fields);
    }
  });
}

const editFormData = async () => {
  formLoading.value = true;
  try {
    const data = await getRequest(`/admin/post/${props.postId}`);
    if (data && data.data) {
       Object.assign(formData, data.data);
      formData.uploadFileId = data.data.fileUpload;
      if (data.data.fileUpload) {
        getRequest(`/admin/upload_file/get_file_url/${data.data.fileUpload}`).then((data) => {
          if (data.data) imageUrl.value = data.data
        });
      }
    }
  } finally {
    formLoading.value = false;
  }
}

const categoryRemoteOpts = async () => {
  categoryLoading.value = true;
  try {
    const data = await postRequest(`/admin/fetch/categories/selected`, {categories: formData.categories})
    if (data && data.data) categoryOptions.value = data.data;
  } finally {
    categoryLoading.value = false;
  }
}

const tagRemoteOpts = async () => {
  tagLoading.value = true;
  try {
    const data = await postRequest(`/admin/fetch/tags/selected`, {tags: formData.tags})
    if (data && data.data) tagOptions.value = data.data;
  } finally {
    tagLoading.value = false;
  }
}
const goBack = () => {
  router.push({name: 'AdminPostIndex'})
}
if (props.postId) {
  onMounted(async () => {
    await editFormData();
    await categoryRemoteOpts();
    if (formData.tags.length != 0) await tagRemoteOpts();
  })
}
</script>
