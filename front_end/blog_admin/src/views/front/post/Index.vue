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
  title: string | undefined,
  content: string | undefined,
  description: string | undefined,
  imageUrl: string | undefined,
  createdAt: string | undefined,
  updatedAt: string | undefined,
  username: string | undefined
}

const blog = ref<blog>({
  title: undefined,
  content: undefined,
  description: undefined,
  imageUrl: undefined,
  createdAt: undefined,
  updatedAt: undefined,
  username: undefined
});

provide('blogData', blog);

const loadData = (async () => {
  var route = useRoute();
  const data = await getRequest(`/api/blog/${route.params.id}`);
  if (data && data.data){
    blog.value = data.data;
  }
});

onMounted(() => {
  loadData();
});
</script>