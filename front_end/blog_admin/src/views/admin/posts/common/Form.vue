<template>
  <el-page-header content="文章一览" @back="goBack" />
  <el-card>
    <template #header>
      <div class="card-header">
        <h4>{{title}}</h4>
      </div>
    </template>
    <el-form ref="form" :model="formData" :rules="rules" label-width="120px" label-position="top">
      <el-form-item label="标题" prop="title">
        <el-input v-model="formData.title"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="formData.description" type="textarea"></el-input>
        {{formData.description}}
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <TEditor ref="editor" v-model="formData.content"/>
        {{formData.content}}
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script lang="ts">
import {defineComponent, ref, toRefs} from "vue";
import {router} from "/@/router";

export default defineComponent({
  props: {
    title: {
      type: String,
      required: true
    }
  },
  setup(props) {
    const { title } = toRefs(props);
    const formData = ref({
      title: '',
      description: '',
      content: '',
      categories: [],
      tags: []
    });

    const form = ref();

    const rules = ref({
      title: [
        {required: true, message: "标题必填", trigger: "blur" }
      ],
      content: [
        {required: true, message: "内容必填", trigger: "blur"}
      ]
    });

    const goBack = () => {
      router.push({name: 'AdminPostIndex'})
    }
    return {form, formData, title, goBack, rules}
  }
})
</script>
