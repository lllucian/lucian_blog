<template>
  <CoverPart/>
  <PageContent/>
  <FootPart/>
</template>
<script lang="ts">
import {defineComponent} from "vue";
import CoverPart from "/@/views/front/post/CoverPart.vue";
import PageContent from "/@/views/front/post/PageContent.vue";
import FootPart from "/@/views/front/home/FootPart.vue";

export default defineComponent({
  components: {
    CoverPart,
    PageContent,
    FootPart
  }
})
</script>