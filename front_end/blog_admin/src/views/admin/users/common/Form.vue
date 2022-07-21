<template>
  <el-page-header content="用户一览" @back="goBack"/>
  <el-card>
    <template #header>
      <div>
        <h4>{{ props.title }}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top"
             v-loading="formLoading">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formData.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="formData.password" type="password" :show-password="true"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="formData.email"></el-input>
      </el-form-item>
      <el-form-item label="启用状态" props="enabled">
        <el-switch
            v-model="formData.enabled"
            active-text="启用"
            inactive-text="未启用"
            active-value="ENABLED"
            inactive-value="DISABLED"
            size="large"
        />
      </el-form-item>
      <el-form-item label="禁用状态" props="locked">
        <el-switch
            v-model="formData.locked"
            active-text="锁定"
            inactive-text="未锁定"
            active-value="LOCKED"
            inactive-value="UNLOCKED"
            size="large"
        />
      </el-form-item>
      <el-form-item label="角色" prop="roles">
        <el-select-v2
            v-model="formData.roleIds"
            filterable
            :options="roleOptions"
            remote
            :remote-method="roleRemoteMethod"
            :loading="roleLoading"
            style="width: 100%"
            placeholder="请选择角色"
            multiple
            clearable
        />
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
  userId?: string | RouteParamValue[]
}

const props = withDefaults(defineProps<Props>(), {
  title: '',
  userId: ''
});

const form = ref<FormInstance>();

let formData = reactive<{
  id: string | null,
  username: string,
  password: string,
  email: string,
  enabled: string,
  locked: string,
  roleIds: Array<string>
}>({
  id: null,
  username: '',
  password: '',
  email: '',
  enabled: 'ENABLED',
  locked: 'UNLOCKED',
  roleIds: [],
});

const imageUrl = ref('');

const formLoading = ref<boolean>(false);

const ValidatorPass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback();
  }else if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{5,16}/.test(value)) {
    callback();
  } else {
    callback(new Error("密码最少5个最多16个字符，至少1个大写字母，1个小写字母，1个数字和1个特殊字符"));
  }
}

const ValidatorEmail = (rule: any, value: any, callback: any) => {
  if (/^[a-zA-Z\d_-]+@[a-zA-Z\d_-]+(\.[a-zA-Z\d_-]+)+$/.test(value)) {
    callback();
  } else {
    callback("邮箱格式不正确")
  }
}
const rules = reactive({
  username: [
    {required: true, message: "用户名必填", trigger: "blur"},
    {max: 255, message: '用户名最长255个字符', trigger: 'blur'},
    {min: 5, message: '用户名最少5个字符', trigger: 'blur'}
  ],
  password: [
    {required: !props.userId, message: "密码必填", trigger: "blur"},
    {min: 5, message: '密码最少5个字符', trigger: 'blur'},
    {max: 16, message: '密码最长16个字符', trigger: 'blur'},
    {validator: ValidatorPass, trigger: 'blur'}
  ],
  email: [
    {required: true, message: "邮箱必填", trigger: 'blur'},
    {validator: ValidatorEmail, trigger: 'blur'}
  ]
});

const roleLoading = ref<boolean>(false);

const roleOptions = ref<Array<{ label: string, value: string }>>([]);

const roleRemoteMethod = async (query: string) => {
  if (query.trim() !== "") {
    roleLoading.value = true;
    try {
      const data = postRequest("/admin/fetch/roles", {query: query});
      if ((await data).data) roleOptions.value = (await data).data;
    } finally {
      roleLoading.value = false;
    }
  } else {
    roleOptions.value = [];
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
        if (props.userId) {
          data = await putRequest(`/admin/user`, formData);
        } else {
          data = await postRequest("/admin/user", formData);
        }
        if (await data) await router.push({name: 'AdminUsersIndex'})
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
    const data = await getRequest(`/admin/user/${props.userId}`);
    if (data && data.data) {
      Object.assign(formData, data.data);
    }
  } finally {
    formLoading.value = false;
  }
}

const roleRemoteOpts = async () => {
  roleLoading.value = true;
  try {
    const data = await postRequest(`/admin/fetch/roles/selected`, formData.roleIds)
    if (data && data.data) roleOptions.value = data.data;
  } finally {
    roleLoading.value = false;
  }
}


const goBack = () => {
  router.push({name: 'AdminUsersIndex'})
}

if (props.userId) {
  onMounted(async () => {
    await editFormData();
    await roleRemoteOpts();
  });
}

onMounted(() => {
 console.log(formData.id)
})
</script>
