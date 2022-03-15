<template>
  <div class="col-lg-8">
    <div class="row">
      <div class="col-md-12" v-for="i of 10" :key="i">
        <postPart/>
      </div>
      <div class="row mt-5">
        <div class="col text-center">
          <div class="block-27">
            <ul>
              <li><a href="#">&lt;</a></li>
              <li class="active"><span>1</span></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">&gt;</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import {defineComponent} from "vue";
import postPart from "/@/views/front/home/PostPart.vue";

export default defineComponent({
  components: {
    postPart
  },
  setup() {

  }
})
</script>