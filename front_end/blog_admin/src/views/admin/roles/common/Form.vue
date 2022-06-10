<template>
  <el-page-header content="角色列表" @back="goBack"/>
  <el-card>
    <template #header>
      <div>
        <h4>{{ props.title }}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top">
      <el-form-item label="权限名" prop="name">
        <el-input v-model="formData.name">
          <template #prepend>ROLE_</template>
        </el-input>
      </el-form-item>
      <el-form-item label="权限中文名" prop="zhName">
        <el-input v-model="formData.zhName"></el-input>
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
import {FormInstance, FormRules} from "element-plus";
import {getRequest, postRequest, putRequest} from "/@/requests";
import {useRouter} from "vue-router";

const router = useRouter();

const props = defineProps<{title :string, roleId? :string}>();

const form = ref<FormInstance>();

const formData = reactive({name: '', zhName: ''});

const rules = reactive<FormRules>({
  name: [
    {required: true, message: '权限名不能为空', trigger: 'blur'},
    {max: 10, message: "权限名长度不能超过20", trigger: 'blur'}
  ],
  zhName: [
    {required: true, message: '权限中文名不能为空', trigger: 'blur'},
    {max: 20, message: "权限中文名长度不能超过20", trigger: 'blur'}
  ]
});

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (props.roleId) {
        putRequest(`/api/admin/role/${props.roleId}`, formData);
      } else {
        postRequest(`/api/admin/role`, formData);
      }
      router.push({name: 'AdminRolesIndex'});
    } else {
      console.log('error submit!', fields);
    }
  });
}

const clearForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
}

const goBack = () => {
  router.push({name: 'AdminRolesIndex'})
}

const editFormData = (async () => {
  const data = await getRequest(`/api/admin/role/${props.roleId}`)
  if (data && data.data) {
    formData.name = data.data.name;
    formData.zhName = data.data.zhName;
  }
});

onMounted(async () => {
  if (!props.roleId) return;
  await editFormData();
});
</script>

<!--<script lang="ts">-->
<!--import {defineComponent, onMounted, ref, toRefs} from "vue";-->
<!--import {router} from "/@/router";-->
<!--import {getRequest, postRequest, putRequest} from "/@/requests";-->
<!--import UploadImage from "/@/components/backend/form/upload/UploadImage.vue";-->
<!--import TEditor from "/@/components/backend/form/tinymce/TEditor.vue"-->


<!--export default defineComponent({-->
<!--  props: {-->
<!--    title: {-->
<!--      type: String,-->
<!--      required: true,-->
<!--    },-->
<!--    postId: {-->
<!--      type: String,-->
<!--      required: false,-->
<!--    }-->
<!--  },-->
<!--  components: {-->
<!--    UploadImage,-->
<!--    TEditor-->
<!--  },-->
<!--  setup(props) {-->
<!--    const {title, postId} = toRefs(props);-->
<!--    const formData = ref({-->
<!--      title: '',-->
<!--      description: '',-->
<!--      content: '',-->
<!--      categories: [],-->
<!--      tags: [],-->
<!--      uploadFileId: '',-->
<!--    });-->

<!--    const imageUrl = ref('');-->

<!--    const form = ref();-->

<!--    const formLoading = ref(false);-->

<!--    const rules = ref({-->
<!--      title: [-->
<!--        {required: true, message: "标题必填", trigger: "blur"},-->
<!--        {max: 255, message: '标题最长255个字符'}-->
<!--      ],-->
<!--      content: [-->
<!--        {required: true, message: "内容必填", trigger: "change"}-->
<!--      ],-->
<!--      categories: [-->
<!--        {required: true, message: "分类必选"}-->
<!--      ],-->
<!--      uploadFileId: [-->
<!--        {required: true, message: "封面必须上传"}-->
<!--      ]-->
<!--    });-->

<!--    const categoryLoading = ref(false);-->

<!--    const categoryOptions = ref([]);-->

<!--    const categoryRemoteMethod = async (query: string) => {-->
<!--      if (query.trim() !== "") {-->
<!--        categoryLoading.value = true;-->
<!--        try {-->
<!--          const data = postRequest("api/admin/fetch/categories", {name: query});-->
<!--          if ((await data).data) categoryOptions.value = (await data).data;-->
<!--        } finally {-->
<!--          categoryLoading.value = false;-->
<!--        }-->
<!--      } else {-->
<!--        categoryOptions.value = [];-->
<!--      }-->
<!--    }-->

<!--    const tagLoading = ref(false);-->

<!--    const tagOptions = ref([]);-->

<!--    const tagRemoteMethod = async (query: string) => {-->
<!--      if (query.trim() !== "") {-->
<!--        tagLoading.value = true;-->
<!--        try {-->
<!--          const data = postRequest("api/admin/fetch/tags", {name: query});-->
<!--          if ((await data).data) tagOptions.value = (await data).data;-->
<!--        } finally {-->
<!--          tagLoading.value = false;-->
<!--        }-->
<!--      } else {-->
<!--        tagOptions.value = [];-->
<!--      }-->
<!--    }-->

<!--    const clearForm = () => {-->
<!--      form.value.resetFields();-->
<!--    }-->

<!--    const submitForm = async () => {-->
<!--      let validResult = false;-->
<!--      await form.value.validate((valid: boolean, fields: object) => {-->
<!--        validResult = valid;-->
<!--      })-->
<!--      // const valid = await form.value.validate();-->
<!--      if (validResult){-->
<!--        formLoading.value = true;-->
<!--        try{-->
<!--          let data;-->
<!--          if(postId.value){-->
<!--            data = await putRequest(`api/admin/post/${postId.value}`, formData.value);-->
<!--          } else {-->
<!--            data = await postRequest("api/admin/post", formData.value);-->
<!--          }-->
<!--          if (await data) await router.push({name: 'AdminPostIndex'})-->
<!--        } finally {-->
<!--          formLoading.value = false;-->
<!--        }-->
<!--      }-->
<!--    }-->

<!--    const editFormData = async () => {-->
<!--      formLoading.value = true;-->
<!--      try{-->
<!--        const data = await getRequest(`api/admin/post/${postId.value}`);-->
<!--        if (data && data.data) {-->
<!--          formData.value = data.data;-->
<!--          formData.value.uploadFileId = data.data.fileUpload;-->
<!--          if (data.data.fileUpload) {-->
<!--            getRequest(`api/admin/upload_file/get_file_url/${data.data.fileUpload}`).then((data) => {-->
<!--              if (data.data) imageUrl.value = data.data-->
<!--            });-->
<!--          }-->
<!--        }-->
<!--      } finally {-->
<!--        formLoading.value = false;-->
<!--      }-->
<!--    }-->

<!--    const categoryRemoteOpts = async () => {-->
<!--      categoryLoading.value = true;-->
<!--      try{-->
<!--        const data = await postRequest(`api/admin/fetch/categories/selected`, {categories: formData.value.categories})-->
<!--        if (data && data.data) categoryOptions.value = data.data;-->
<!--      } finally {-->
<!--        categoryLoading.value = false;-->
<!--      }-->
<!--    }-->

<!--    const tagRemoteOpts = async () => {-->
<!--      tagLoading.value = true;-->
<!--      try{-->
<!--        const data = await postRequest(`api/admin/fetch/tags/selected`, {tags: formData.value.tags})-->
<!--        if (data && data.data) tagOptions.value = data.data;-->
<!--      } finally {-->
<!--        tagLoading.value = false;-->
<!--      }-->
<!--    }-->
<!--    const goBack = () => {-->
<!--      router.push({name: 'AdminPostIndex'})-->
<!--    }-->
<!--    if (postId.value) {-->
<!--      onMounted(async () => {-->
<!--        await editFormData();-->
<!--        await categoryRemoteOpts();-->
<!--        if (formData.value.tags.length != 0) await tagRemoteOpts();-->
<!--      })-->
<!--    }-->
<!--    return {-->
<!--      form,-->
<!--      formData,-->
<!--      title,-->
<!--      goBack,-->
<!--      rules,-->
<!--      categoryLoading,-->
<!--      categoryOptions,-->
<!--      categoryRemoteMethod,-->
<!--      tagLoading,-->
<!--      tagOptions,-->
<!--      tagRemoteMethod,-->
<!--      submitForm,-->
<!--      clearForm,-->
<!--      imageUrl-->
<!--    }-->
<!--  }-->
<!--})-->
<!--</script>-->
