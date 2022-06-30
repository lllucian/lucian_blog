<template>
  <footer class="ftco-footer ftco-bg-dark ftco-section">
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center">

          <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            COPYRIGHT © {{yearInfo}} LUCIANDENG.COM. ALL RIGHTS RESERVED.
            <i class="icon-heart" aria-hidden="true"></i> by <a href="https://beian.miit.gov.cn/" target="_blank">赣ICP2021007956号</a>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
        </div>
      </div>
    </div>
  </footer>
</template>
<script lang="ts">
import {defineComponent, ref} from "vue";

export default defineComponent({
  setup(){
    const yearInfo = ref(new Date().getFullYear());
    return {yearInfo}
  }
})
</script>