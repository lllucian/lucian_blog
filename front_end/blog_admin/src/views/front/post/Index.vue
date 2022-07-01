<template>
  <CoverPart :image-url="blog.imageUrl" :title="blog.title" :description="blog.description"/>
  <PageContent/>
  <FootPart/>
</template>
<script lang="ts" setup>
import CoverPart from "/@/views/front/post/CoverPart.vue";
import PageContent from "/@/views/front/post/PageContent.vue";
import FootPart from "/@/views/front/home/FootPart.vue";
import {onMounted, provide, ref} from "vue";
import {useRoute} from "vue-router";
import {getRequest} from "/@/requests";

interface blog {
  title: string,
  content: string,
  description: string,
  imageUrl: string,
  createdAt: string,
  updatedAt: string,
  username: string
}

const blog = ref<blog>({
  title: '',
  content: '',
  description: '',
  imageUrl: '',
  createdAt: '',
  updatedAt: '',
  username: ''
});

provide('blogData', blog);

const loadData = (async () => {
  var route = useRoute();
  const data = await getRequest(`/blog/${route.params.id}`);
  if (data && data.data){
    blog.value = data.data;
  }
});

onMounted(() => {
  loadData();
});
</script>