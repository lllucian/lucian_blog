<template>
  <section class="ftco-section ftco-degree-bg">
    <div class="container">
      <div class="row">
        <LeftPart/>
        <RightPart/>
      </div>
    </div>
  </section> <!-- .section -->
</template>
<script lang="ts">
import {defineComponent} from "vue";
import RightPart from "/@/views/front/home/RightPart.vue";
import LeftPart from "/@/views/front/post/LeftPart.vue";

export default defineComponent({
  components: {
    LeftPart,
    RightPart
  }
})
</script>