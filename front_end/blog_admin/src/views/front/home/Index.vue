<template>
  <Slider :isAutoPlay="true" :playSpeed="5000"/>
  <Quote/>
  <PageContent/>
  <FootPart/>
</template>
<script lang="ts">
import {defineComponent} from "vue";
import Slider from "./Slider.vue"
import Quote from "/@/views/front/home/Quote.vue";
import PageContent from "/@/views/front/home/PageContent.vue";
import FootPart from "/@/views/front/home/FootPart.vue";

export default defineComponent({
  components: {
    Slider,
    Quote,
    PageContent,
    FootPart
  }
})
</script>