<template>
  <section class="ftco-section">
    <div class="container">
      <div class="row">
        <LeftPart/>
        <RightPart/>
      </div>
    </div>
  </section>
</template>
<script lang="ts">
import {defineComponent} from "vue";
import LeftPart from "/@/views/front/home/LeftPart.vue";
import RightPart from "/@/views/front/home/RightPart.vue";

export default defineComponent({
  components: {
    LeftPart,
    RightPart
  }
})
</script>