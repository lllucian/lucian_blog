<template>
  <el-page-header content="文章一览" @back="goBack"/>
  <el-card>
    <template #header>
      <div class="card-header">
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
      title: '',
      description: '',
      content: '',
      categories: [],
      tags: []
    });

    const form = ref();

    const rules = ref({
      title: [
        {required: true, message: "标题必填", trigger: "blur"},
        {max: 255, message: '标题最长255个字符'}
      ],
      content: [
        {required: true, message: "内容必填", trigger: "change"}
      ],
      categories: [
        {required: true, message: "分类必须"}
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

    const goBack = () => {
      router.push({name: 'AdminPostIndex'})
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
    }
  }
})
</script>
