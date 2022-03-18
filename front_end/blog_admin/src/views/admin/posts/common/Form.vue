<template>
  <el-page-header content="文章一览" @back="goBack"/>
  <el-card>
    <template #header>
      <div>
        <h4>{{ title }}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top">
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
import UploadImage from "/@/components/form/upload/UploadImage.vue";

export default defineComponent({
  props: {
    title: {
      type: String,
      required: true,
    },
    postId: {
      type: String,
      required: false,
    }
  },
  components: {
    UploadImage
  },
  setup(props) {
    const {title, postId} = toRefs(props);
    const formData = ref({
      title: '',
      description: '',
      content: '',
      categories: [],
      tags: [],
      uploadFileId: '',
    });

    const imageUrl = ref('');

    const form = ref();

    const formLoading = ref(false);

    const rules = ref({
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

    const categoryLoading = ref(false);

    const categoryOptions = ref([]);

    const categoryRemoteMethod = async (query: string) => {
      if (query.trim() !== "") {
        categoryLoading.value = true;
        try {
          const data = postRequest("api/admin/fetch/categories", {name: query});
          if ((await data).data) categoryOptions.value = (await data).data;
        } finally {
          categoryLoading.value = false;
        }
      } else {
        categoryOptions.value = [];
      }
    }

    const tagLoading = ref(false);

    const tagOptions = ref([]);

    const tagRemoteMethod = async (query: string) => {
      if (query.trim() !== "") {
        tagLoading.value = true;
        try {
          const data = postRequest("api/admin/fetch/tags", {name: query});
          if ((await data).data) tagOptions.value = (await data).data;
        } finally {
          tagLoading.value = false;
        }
      } else {
        tagOptions.value = [];
      }
    }

    const clearForm = () => {
      form.value.resetFields();
    }

    const submitForm = async () => {
      let validResult = false;
      await form.value.validate((valid: boolean, fields: object) => {
        validResult = valid;
      })
      // const valid = await form.value.validate();
      if (validResult){
        formLoading.value = true;
        try{
          let data;
          if(postId.value){
            data = await putRequest(`api/admin/post/${postId.value}`, formData.value);
          } else {
            data = await postRequest("api/admin/post", formData.value);
          }
          if (await data) await router.push({name: 'AdminPostIndex'})
        } finally {
          formLoading.value = false;
        }
      }
    }

    const editFormData = async () => {
      formLoading.value = true;
      try{
        const data = await getRequest(`api/admin/post/${postId.value}`);
        if (data && data.data) {
          formData.value = data.data;
          formData.value.uploadFileId = data.data.fileUpload;
          if (data.data.fileUpload) {
            getRequest(`api/admin/upload_file/get_file_url/${data.data.fileUpload}`).then((data) => {
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
      try{
        const data = await postRequest(`api/admin/fetch/categories/selected`, {categories: formData.value.categories})
        if (data && data.data) categoryOptions.value = data.data;
      } finally {
        categoryLoading.value = false;
      }
    }

    const tagRemoteOpts = async () => {
      tagLoading.value = true;
      try{
        const data = await postRequest(`api/admin/fetch/tags/selected`, {tags: formData.value.tags})
        if (data && data.data) tagOptions.value = data.data;
      } finally {
        tagLoading.value = false;
      }
    }
    const goBack = () => {
      router.push({name: 'AdminPostIndex'})
    }
    if (postId.value) {
      onMounted(async () => {
        await editFormData();
        await categoryRemoteOpts();
        if (formData.value.tags.length != 0) await tagRemoteOpts();
      })
    }
    return {
      form,
      formData,
      title,
      goBack,
      rules,
      categoryLoading,
      categoryOptions,
      categoryRemoteMethod,
      tagLoading,
      tagOptions,
      tagRemoteMethod,
      submitForm,
      clearForm,
      imageUrl
    }
  }
})
</script>
