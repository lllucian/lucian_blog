<template>
  <div class="form">
    <h2>Lucian Blog后台管理系统</h2>
    <el-form
        label-position="top"
        label-width="100px"
        :model="loginFormData"
        :rules="rules"
        ref="loginForm"
        @keyup.enter="submitForm(loginForm)"
        v-loading="loading"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginFormData.username"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input v-model="loginFormData.password" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(loginForm)">登陆</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import {onBeforeMount, reactive, ref} from "vue";
import {stroage} from "/@/stroage";
import {FormInstance} from "element-plus";
import {authorizeRequests} from "/@/requests";
import {useRouter} from "vue-router";

const router = useRouter();

onBeforeMount(() => {
  stroage.commit({type: "clearToken"});
});

const loading = ref<boolean>(false);

const loginForm = ref<FormInstance>();

const loginFormData = reactive<{ username: string, password: string }>({
  username: '',
  password: ''
});

const rules = reactive({
  username: [
    {required: true, message: "请填写用户名", trigger: "blur"},
    {min: 3, max: 20, message: "要在3到20个长度之间", trigger: "blur"},
  ],
  password: {required: true, message: "请填写密码", trigger: "blur"},
});

const restForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return formEl;
  formEl.resetFields();
};

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return formEl;
  const valid: any = await formEl.validate();
  if (valid) {
    try {
      loading.value = true;
      const resp: any = await authorizeRequests(
          "/admin/login",
          loginFormData
      );
      if (resp) {
        const jwtToken = resp.data;
        if (jwtToken) {
          stroage.commit({type: "setToken", token: jwtToken});
          const redirectTo = router.currentRoute.value.query["redirect_to"] as string || "/";
          await router.push({path: redirectTo});
        }
      } else {
        restForm(formEl);
      }
    } finally {
      loading.value = false;
    }
  } else {
    return;
  }
};
// export default defineComponent({
//   setup() {
//     onBeforeMount(() => {
//       stroage.commit({ type: "clearToken" });
//     });
//
//     const loading = ref(false);
//
//     const loginForm = ref();
//
//     const loginFormData = ref({
//       username: "",
//       password: "",
//     });
//
//     const rules = ref({
//       username: [
//         { required: true, message: "请填写用户名", trigger: "blur" },
//         { min: 3, max: 20, message: "要在3到20个长度之间", trigger: "blur" },
//       ],
//       password: { required: true, message: "请填写密码", trigger: "blur" },
//     });
//
//     const restForm = () => {
//       loginForm.value.resetFields();
//     };
//
//     const submitForm = async () => {
//       const valid: any = await loginForm.value.validate();
//       if (valid) {
//         try {
//           loading.value = true;
//           const resp: any = await authorizeRequests(
//             "/api/admin/login",
//             loginFormData.value
//           );
//           if (resp) {
//             const jwtToken = resp.data;
//             if (jwtToken) {
//               stroage.commit({ type: "setToken", token: jwtToken });
//               const redirectTo = router.currentRoute.value.query["redirect_to"] as string || "/";
//               await router.push({path: redirectTo});
//             }
//           } else {
//             restForm();
//           }
//         } finally {
//           loading.value = false;
//         }
//       } else {
//         return;
//       }
//     };
//
//     return { loginForm, loginFormData, rules, submitForm, restForm, loading };
//   },
// });
</script>
<style>
body {
  background: #f0f0f1;
  min-width: 0;
  color: #3c434a;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
  Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif;
  font-size: 13px;
  line-height: 1.4;
  margin: 0;
}
</style>
<style scoped>
.form {
  width: auto;
  max-width: 470px;
  margin: auto;
  margin-top: 100px;
  padding: 50px 24px 34px;
  background: #fff;
  border: 1px solid #c3c4c7;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.04);
}

.form h2 {
  text-align: center;
}
</style>
